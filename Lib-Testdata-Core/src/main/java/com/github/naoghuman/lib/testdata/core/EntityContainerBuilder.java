/*
 * Copyright (C) 2017 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.lib.testdata.core;

import com.github.naoghuman.lib.testdata.internal.configurationcomponent.ConfigurationComponentType;
import java.util.Objects;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class EntityContainerBuilder {
    
    public static final ClassStep create() {
        return new EntityBuilderImpl();
    }
    
    public interface ClassStep {
        public MappingIdStep clazz(final Class clazz);
    }
    
    public interface MappingIdStep {
        public ConfigurationComponentTypeStep mappingId(final long mappingId);
    }
    
    public interface ConfigurationComponentTypeStep {
        public TaskStep configurationComponentTypeStep(final ConfigurationComponentType configurationComponentType);
    }
    
    public interface TaskStep {
        public Builder task(final Task<Void> task);
    }
    
    public interface Builder {
        public Builder required(final Class clazz);
        public EntityContainer build();
    }
    
    private static final class EntityBuilderImpl implements 
            ClassStep, MappingIdStep, ConfigurationComponentTypeStep, TaskStep, Builder
    {
        private static final String PARA_CLAZZ      = "clazz"; // NOI18N
        private static final String PARA_CONFIGURATION_COMPONENT_TYPE = "configurationComponentType"; // NOI18N
        private static final String PARA_MAPPING_ID = "mappingId"; // NOI18N
        private static final String PARA_TASK       = "task"; // NOI18N
        
        private final ObservableList<Class> requiredEntities     = FXCollections.observableArrayList();
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
    
        EntityBuilderImpl() {
            this.init();
        }

        private void init() {
            // Mandory attributes
            properties.put(PARA_CLAZZ,      new SimpleObjectProperty());
            properties.put(PARA_CONFIGURATION_COMPONENT_TYPE, new SimpleObjectProperty());
            properties.put(PARA_MAPPING_ID, new SimpleLongProperty());
            properties.put(PARA_TASK,       new SimpleObjectProperty());
        }

        @Override
        public MappingIdStep clazz(final Class clazz) {
            Objects.requireNonNull(clazz);
            properties.put(PARA_CLAZZ, new SimpleObjectProperty(clazz));
            
            return this;
        }

        @Override
        public ConfigurationComponentTypeStep mappingId(final long mappingId) {
            properties.put(PARA_MAPPING_ID, new SimpleLongProperty(mappingId));
            
            return this;
        }

        @Override
        public TaskStep configurationComponentTypeStep(final ConfigurationComponentType configurationComponentType) {
            Objects.requireNonNull(configurationComponentType);
            properties.put(PARA_CONFIGURATION_COMPONENT_TYPE, new SimpleObjectProperty(configurationComponentType));
            
            return this;
        }

        @Override
        public Builder task(Task<Void> task) {
//            Objects.requireNonNull(task); // TODO
            properties.put(PARA_TASK, new SimpleObjectProperty(task));
            
            return this;
        }

        @Override
        public Builder required(final Class clazz) {
            Objects.requireNonNull(clazz);
            
            if (!requiredEntities.contains(clazz)) {
                requiredEntities.add(clazz);
            }
            
            return this;
        }

        @Override
        public EntityContainer build() {
            final ObjectProperty opClazz = (ObjectProperty) properties.get(PARA_CLAZZ);
            final ObjectProperty opConfigurationComponentType = (ObjectProperty) properties.get(PARA_CONFIGURATION_COMPONENT_TYPE);
            final LongProperty lpMappingId = (LongProperty) properties.get(PARA_MAPPING_ID);
            final ObjectProperty opTask = (ObjectProperty) properties.get(PARA_TASK);
            
            return EntityContainer.create(
                    (Class) opClazz.getValue(),
                    lpMappingId.getValue(),
                    (ConfigurationComponentType) opConfigurationComponentType.getValue(),
                    (Task<Void>) opTask.getValue(),
                    requiredEntities);
        }
        
    }
    
}

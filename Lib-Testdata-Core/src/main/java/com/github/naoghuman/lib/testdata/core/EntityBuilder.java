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

import java.util.Objects;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class EntityBuilder {
    
    public static final ClassStep create() {
        return new EntityBuilderImpl();
    }
    
    public interface ClassStep {
        public MappingIdStep clazz(final Class clazz);
    }
    
    public interface MappingIdStep {
        public Builder mappingId(final long mappingId);
    }
    
    public interface Builder {
        public Builder required(final Class clazz);
        public Entity build();
    }
    
    private static final class EntityBuilderImpl implements 
            ClassStep, MappingIdStep, Builder
    {
        private static final String PARA_CLAZZ      = "clazz"; // NOI18N
        private static final String PARA_MAPPING_ID = "mappingId"; // NOI18N
        
        private final ObservableList<Class> requiredEntities     = FXCollections.observableArrayList();
        private final ObservableMap<String, Property> properties = FXCollections.observableHashMap();
    
        EntityBuilderImpl() {
            this.init();
        }

        private void init() {
            // Mandory attributes
            properties.put(PARA_CLAZZ,      new SimpleObjectProperty());
            properties.put(PARA_MAPPING_ID, new SimpleLongProperty());
        }

        @Override
        public MappingIdStep clazz(final Class clazz) {
            Objects.requireNonNull(clazz);
            properties.put(PARA_CLAZZ, new SimpleObjectProperty(clazz));
            
            return this;
        }

        @Override
        public Builder mappingId(final long mappingId) {
            properties.put(PARA_MAPPING_ID, new SimpleLongProperty(mappingId));
            
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
        public Entity build() {
            final ObjectProperty opClazz    = (ObjectProperty) properties.get(PARA_CLAZZ);
            final LongProperty lpMappingId  = (LongProperty)   properties.get(PARA_MAPPING_ID);
            
            return Entity.create(
                    (Class) opClazz.getValue(),
                    lpMappingId.getValue(),
                    requiredEntities);
        }
        
    }
    
}

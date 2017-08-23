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

import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationPresenter;
import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationType;
import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationView;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        public ConfigurationTypeStep mappingId(final long mappingId);
    }
    
    public interface ConfigurationTypeStep {
        public TaskStep configurationType(final ConfigurationType configurationType);
    }
    
    public interface TaskStep {
        public Builder task(final TestdataGenerationTask testdataGenerationTask);
    }
    
    public interface Builder {
        public Builder required(final Class clazz);
        public EntityContainer build();
    }
    
    private static final class EntityBuilderImpl implements 
            ClassStep, MappingIdStep, ConfigurationTypeStep, TaskStep, Builder
    {
        private final ObservableList<Class> requiredEntities     = FXCollections.observableArrayList();
        
        private long mappingId;
        
        private Class clazz;
        private ConfigurationType configurationType;
        private TestdataGenerationTask testdataGenerationTask;
    
        EntityBuilderImpl() {
            
        }

        @Override
        public MappingIdStep clazz(final Class clazz) {
            Objects.requireNonNull(clazz);
            this.clazz = clazz;
            
            return this;
        }

        @Override
        public ConfigurationTypeStep mappingId(final long mappingId) {
            this.mappingId = mappingId;
            
            return this;
        }

        @Override
        public TaskStep configurationType(final ConfigurationType configurationType) {
            Objects.requireNonNull(configurationType);
            this.configurationType = configurationType;
            
            return this;
        }

        @Override
        public Builder task(final TestdataGenerationTask testdataGenerationTask) {
            Objects.requireNonNull(testdataGenerationTask);
            this.testdataGenerationTask = testdataGenerationTask;
            
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
            final ConfigurationView      view      = new ConfigurationView();
            final ConfigurationPresenter presenter = view.getRealPresenter();
            testdataGenerationTask.configure(presenter.maxEntitiesProperty(), presenter.timePeriodProperty());
            
            return EntityContainer.create(
                    clazz, mappingId, configurationType, testdataGenerationTask, 
                    view, presenter, requiredEntities);
        }
        
    }
    
}

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

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationPresenter;
import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationType;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class EntityContainer {
    
    public static final EntityContainer create(
            final Class entity, final long mappingId, 
            final ConfigurationType configurationType,
            final TestdataGenerationTask testdataGenerationTask,
            final ConfigurationPresenter configurationPresenter
    ) {
        return create(
                entity, mappingId, configurationType,
                testdataGenerationTask, configurationPresenter,
                FXCollections.observableArrayList());
    }
    
    public static final EntityContainer create(
            final Class entity, final long mappingId, final ConfigurationType configurationType,
            final TestdataGenerationTask testdataGenerationTask, final ConfigurationPresenter configurationPresenter,
            final ObservableList<Class> requiredEntities
    ) {
        return new EntityContainer(
                entity, mappingId, configurationType, 
                testdataGenerationTask, configurationPresenter,
                requiredEntities);
    }
    
    private final BooleanProperty entityIsDisableProperty        = new SimpleBooleanProperty(Boolean.FALSE);
    private final BooleanProperty entityIsSelectedProperty       = new SimpleBooleanProperty(Boolean.FALSE);
    private final ObservableList<Class> previousRequiredEntities = FXCollections.observableArrayList();
    
    private final long mappingId;
    
    private final Class entity;
    private final ConfigurationPresenter configurationPresenter;
    private final ConfigurationType configurationType;
    private final TestdataGenerationTask testdataGenerationTask;
    
    private EntityContainer(
            final Class entity, final long mappingId, final ConfigurationType configurationType,
            final TestdataGenerationTask testdataGenerationTask, final ConfigurationPresenter configurationPresenter,
            final ObservableList<Class> previousRequiredEntities
    ) {
        this.entity    = entity;
        this.mappingId = mappingId;
        this.configurationType      = configurationType;
        this.testdataGenerationTask = testdataGenerationTask;
        
        this.previousRequiredEntities.addAll(previousRequiredEntities);
        if (this.previousRequiredEntities.isEmpty()) {
            this.previousRequiredEntities.add(None.class);
        }
        
        this.configurationPresenter = configurationPresenter;
        this.configurationPresenter.configuration(this.getSimpleName(),
                this.getConfigurationType().isQuantityAndTimeperiod());
        
        LoggerFacade.getDefault().debug(this.getClass(), String.format("Create %s", this.toString())); // NOI18N
    }
    
    public void disableEntityInNavigation(boolean disable) {
        entityIsDisableProperty.setValue(disable);
    }
    
    public BooleanProperty entityIsDisableProperty() {
        return entityIsDisableProperty;
    }
    
    public BooleanProperty entityIsSelectedProperty() {
        return entityIsSelectedProperty;
    }
    
    public ConfigurationPresenter getConfigurationPresenter() {
        return configurationPresenter;
    }
    
    public ConfigurationType getConfigurationType() {
        return configurationType;
    }
    
    public Class getEntity() {
        return entity;
    }
    
    public long getMappingId() {
        return mappingId;
    }
    
    public ObservableList<Class> getPreviousRequiredEntities() {
        return previousRequiredEntities;
    }
    
    public boolean hasPreviousRequiredEntities() {
        return !(previousRequiredEntities.size() == 1 
                && previousRequiredEntities.get(0).getSimpleName().equals(None.class.getSimpleName()));
    }
    
    public String getSimpleName() {
        return entity.getSimpleName();
    }
    
    public TestdataGenerationTask getTestdataGenerationTask() {
        return testdataGenerationTask;
    }
    
    public boolean isEntitySelected() {
        return entityIsSelectedProperty.getValue();
    }
    
    public void selectEntityInNavigation(boolean selected) {
        entityIsSelectedProperty.setValue(selected);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (mappingId ^ (mappingId >>> 32));
        hash = 59 * hash + Objects.hashCode(entity);
        hash = 59 * hash + Objects.hashCode(configurationType);
        hash = 59 * hash + Objects.hashCode(testdataGenerationTask);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntityContainer other = (EntityContainer) obj;
        if (this.mappingId != other.mappingId) {
            return false;
        }
        if (!Objects.equals(this.entity, other.entity)) {
            return false;
        }
        if (this.configurationType != other.configurationType) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("EntityContainer["); // NOI18N
        
        sb.append("simplename=") .append(this.getSimpleName()); // NOI18N
        sb.append(", mappingId=").append(this.getMappingId()); // NOI18N
        sb.append(", configurationType=").append(this.getConfigurationType()); // NOI18N
        sb.append(", testdataGenerationTask=").append(this.getTestdataGenerationTask().getTitle()); // NOI18N
        
        sb.append(", requiredEntities=("); // NOI18N
        previousRequiredEntities.stream()
                .forEach(requiredEntityClass -> {
                    sb.append(requiredEntityClass.getSimpleName()); // NOI18N
                    sb.append(", "); // NOI18N
                });
        if (!previousRequiredEntities.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(")"); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
    private class None {
        
    }
    
}

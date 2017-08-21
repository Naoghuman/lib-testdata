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
import com.github.naoghuman.lib.testdata.internal.configurationcomponent.ConfigurationComponentType;
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
            final ConfigurationComponentType configurationComponentType
    ) {
        return create(
                entity, mappingId, configurationComponentType,
                FXCollections.observableArrayList());
    }
    
    public static final EntityContainer create(
            final Class entity, final long mappingId,
            final ConfigurationComponentType configurationComponentType,
            final ObservableList<Class> requiredEntities
    ) {
        return new EntityContainer(
                entity, mappingId, configurationComponentType, requiredEntities);
    }
    
    private final BooleanProperty entityIsSelectedProperty       = new SimpleBooleanProperty(Boolean.FALSE);
    private final ObservableList<Class> previousRequiredEntities = FXCollections.observableArrayList();
    
    private final long mappingId;
    
    private final Class entity;
    private final ConfigurationComponentType configurationComponentType;
    
    private EntityContainer(
            final Class entity, final long mappingId,
            final ConfigurationComponentType configurationComponentType, 
            final ObservableList<Class> previousRequiredEntities
    ) {
        this.entity    = entity;
        this.mappingId = mappingId;
        this.configurationComponentType = configurationComponentType;
        
        this.previousRequiredEntities.addAll(previousRequiredEntities);
        if (this.previousRequiredEntities.isEmpty()) {
            this.previousRequiredEntities.add(None.class);
        }
        
        LoggerFacade.getDefault().debug(this.getClass(), String.format("Create %s", this.toString())); // NOI18N
    }
    
    public BooleanProperty entityIsSelectedProperty() {
        return entityIsSelectedProperty;
    }
    
    public ConfigurationComponentType getConfigurationComponentType() {
        return configurationComponentType;
    }
    
    public Class getEntity() {
        return entity;
    }
    
    public long getMappingId() {
        return mappingId;
    }
    
    public String getSimpleName() {
        return entity.getSimpleName();
    }
    
    public ObservableList<Class> getPreviousRequiredEntities() {
        return previousRequiredEntities;
    }
    
    public boolean hasPreviousRequiredEntities() {
        return !(previousRequiredEntities.size() == 1 
                && previousRequiredEntities.get(0).getSimpleName().equals(None.class.getSimpleName()));
    }
    
    public boolean isEntitySelected() {
        return entityIsSelectedProperty.getValue();
    }
    
    public void selectEntityInNavigation(boolean selected) {
        entityIsSelectedProperty.setValue(selected);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("EntityContainer["); // NOI18N
        
        sb.append("simplename=") .append(this.getSimpleName()); // NOI18N
        sb.append(", mappingId=").append(this.getMappingId()); // NOI18N
        
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
        
        sb.append(", configuration-component-type=").append(this.getConfigurationComponentType()); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
    private class None {
        
    }
    
}

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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class Entity {
    
    public static final Entity create(
            final Class clazz, final long mappingId
    ) {
        return create(clazz, mappingId, FXCollections.observableArrayList());
    }
    
    public static final Entity create(
            final Class clazz, final long mappingId,
            final ObservableList<Class> requiredEntities
    ) {
        return new Entity(clazz, mappingId, requiredEntities);
    }
    
    private final ObservableList<Class> requiredEntities = FXCollections.observableArrayList();
    
    private final long mappingId;
    
    private final Class clazz;
    
    private Entity(
            final Class clazz, final long mappingId, 
            final ObservableList<Class> requiredEntities
    ) {
        this.clazz     = clazz;
        this.mappingId = mappingId;
        
        this.requiredEntities.addAll(requiredEntities);
        if (this.requiredEntities.isEmpty()) {
            this.requiredEntities.add(None.class);
        }
        
        LoggerFacade.getDefault().debug(this.getClass(), String.format("Create %s", this.toString())); // NOI18N
    }
    
    public Class getClazz() {
        return clazz;
    }
    
    public long getMappingId() {
        return mappingId;
    }
    
    public String getSimpleName() {
        return clazz.getSimpleName();
    }
    
    public ObservableList<Class> getRequiredEntities() {
        return requiredEntities;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Entity["); // NOI18N
        
        sb.append("simplename=") .append(this.getSimpleName()); // NOI18N
        sb.append(", mappingId=").append(this.getMappingId()); // NOI18N
        
        sb.append(", requiredEntities=("); // NOI18N
        requiredEntities.stream()
                .forEach(requiredEntityClass -> {
                    sb.append(requiredEntityClass.getSimpleName()); // NOI18N
                    sb.append(", "); // NOI18N
                });
        if (!requiredEntities.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(")"); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
    private class None {
        
    }
    
}
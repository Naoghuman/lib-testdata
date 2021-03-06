/**
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
package com.github.naoghuman.lib.testdata.demo;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.preferences.core.PreferencesFacade;
import com.github.naoghuman.lib.testdata.core.EntityContainer;
import com.github.naoghuman.lib.testdata.core.EntityContainerBuilder;
import com.github.naoghuman.lib.testdata.core.StartTestdataGeneration;
import com.github.naoghuman.lib.testdata.demo.entity.EntityA;
import com.github.naoghuman.lib.testdata.demo.entity.EntityA2;
import com.github.naoghuman.lib.testdata.demo.entity.EntityB;
import com.github.naoghuman.lib.testdata.demo.entity.EntityB2;
import com.github.naoghuman.lib.testdata.demo.entity.EntityB3;
import com.github.naoghuman.lib.testdata.demo.entity.EntityC;
import com.github.naoghuman.lib.testdata.demo.entity.EntityD;
import com.github.naoghuman.lib.testdata.demo.task.EntityA2Task;
import com.github.naoghuman.lib.testdata.demo.task.EntityATask;
import com.github.naoghuman.lib.testdata.demo.task.EntityB2Task;
import com.github.naoghuman.lib.testdata.demo.task.EntityB3Task;
import com.github.naoghuman.lib.testdata.demo.task.EntityBTask;
import com.github.naoghuman.lib.testdata.demo.task.EntityCTask;
import com.github.naoghuman.lib.testdata.demo.task.EntityDTask;
import com.github.naoghuman.lib.testdata.internal.configurationcomponent.ConfigurationComponentType;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * 
 * @author PRo
 * @since  0.1.0
 */
public class StartDemoTestdataGeneration extends Application {

    public static void main(final String[] args) {
        launch(args);
    }
    
    private StartTestdataGeneration startTestdataGeneration;
    
    @Override
    public void init() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "init()"); // NOI18N
        
        super.init();
        
        final Boolean dropPreferencesFileAtStart = Boolean.FALSE;
        PreferencesFacade.getDefault().init(dropPreferencesFileAtStart);
        
        startTestdataGeneration = new StartTestdataGeneration();
        startTestdataGeneration.init();
        
        final ObservableList<EntityContainer> entities = FXCollections.observableArrayList();
//        entities.add(EntityContainerBuilder.create().clazz(EntityD.class) .mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY)           .task(new EntityDTask()).required(EntityB.class).required(EntityB2.class).build());
//        entities.add(EntityContainerBuilder.create().clazz(EntityA2.class).mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY_TIMEPERIOD).task(new EntityA2Task()).required(EntityA.class).build());
//        entities.add(EntityContainerBuilder.create().clazz(EntityB3.class).mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY)           .task(new EntityB3Task()).required(EntityD.class).required(EntityB2.class).build());
//        entities.add(EntityContainerBuilder.create().clazz(EntityB2.class).mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY_TIMEPERIOD).task(new EntityB2Task()).required(EntityB.class).build());
//        entities.add(EntityContainerBuilder.create().clazz(EntityC.class) .mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY_TIMEPERIOD).task(new EntityCTask()).required(EntityD.class).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityA.class) .mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY)           .task(new EntityATask()).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityB.class) .mappingId(System.nanoTime()).configurationComponentType(ConfigurationComponentType.QUANTITY)           .task(new EntityBTask()).build());
        startTestdataGeneration.register("testdatabase", entities); // NOI18N
    }
    
    @Override
    public void start(final Stage stage) {
        LoggerFacade.getDefault().debug(this.getClass(), "start(Stage)"); // NOI18N
        
        startTestdataGeneration.start(stage);
    }
    
    @Override
    public void stop() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "stop()"); // NOI18N
        
        super.stop();
        
        startTestdataGeneration.stop();
    }
    
}

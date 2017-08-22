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
import com.github.naoghuman.lib.testdata.core.EntityContainer;
import com.github.naoghuman.lib.testdata.core.EntityContainerBuilder;
import com.github.naoghuman.lib.testdata.core.StartTestdataGenerationFramework;
import com.github.naoghuman.lib.testdata.demo.entity.EntityA;
import com.github.naoghuman.lib.testdata.demo.entity.EntityA2;
import com.github.naoghuman.lib.testdata.demo.entity.EntityB;
import com.github.naoghuman.lib.testdata.demo.entity.EntityB2;
import com.github.naoghuman.lib.testdata.demo.entity.EntityB3;
import com.github.naoghuman.lib.testdata.demo.entity.EntityC;
import com.github.naoghuman.lib.testdata.demo.entity.EntityD;
import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationType;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * 
 * @author PRo
 * @since  0.1.0
 */
public class TemplateStartTestdataGeneration extends Application {

    public static void main(final String[] args) {
        launch(args);
    }
    
    private StartTestdataGenerationFramework startTestdataGenerationFramework;
    
    @Override
    public void init() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "init()"); // NOI18N
        
        super.init();
        
        startTestdataGenerationFramework = new StartTestdataGenerationFramework();
        
        final ObservableList<EntityContainer> entities = FXCollections.observableArrayList();
        entities.add(EntityContainerBuilder.create().clazz(EntityD.class) .mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY)           .task(null).required(EntityB.class).required(EntityB2.class).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityA2.class).mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY_TIMEPERIOD).task(null).required(EntityA.class).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityB3.class).mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY)           .task(null).required(EntityD.class).required(EntityB2.class).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityB2.class).mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY_TIMEPERIOD).task(null).required(EntityB.class).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityC.class) .mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY_TIMEPERIOD).task(null).required(EntityD.class).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityA.class) .mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY)           .task(null).build());
        entities.add(EntityContainerBuilder.create().clazz(EntityB.class) .mappingId(System.nanoTime()).configurationType(ConfigurationType.QUANTITY)           .task(null).build());
        startTestdataGenerationFramework.register(entities);
        
        startTestdataGenerationFramework.init();
    }
    
    @Override
    public void start(final Stage stage) {
        LoggerFacade.getDefault().debug(this.getClass(), "start(Stage)"); // NOI18N
        
        startTestdataGenerationFramework.start(stage);
    }
    
    @Override
    public void stop() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "stop()"); // NOI18N
        
        super.stop();
        
        startTestdataGenerationFramework.stop();
    }
    
}

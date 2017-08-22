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
package com.github.naoghuman.lib.testdata.core;

import com.airhacks.afterburner.injection.Injector;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.internal.framework.FrameworkProvider;
import java.util.Comparator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Naoghuman
 * @since  0.1.0
 */
public class StartTestdataGenerationFramework extends Application {

    public static void main(final String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "init()"); // NOI18N
        
        super.init();
    }
    
    @Override
    public void start(final Stage stage) {
        LoggerFacade.getDefault().debug(this.getClass(), "start(Stage)"); // NOI18N
        
        final Scene scene = new Scene(FrameworkProvider.getDefault().getView(), 1280.0d, 720.0d);
        stage.setScene(scene);
        stage.setTitle("Testdata Generation v0.1.2");
        
        stage.show();
    }
    
    @Override
    public void stop() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "stop()"); // NOI18N
        
        try {
            FrameworkProvider.getDefault().shutdown();
        } catch (InterruptedException e) {
        }
        
        Injector.forgetAll();
//        DatabaseFacade.getDefault().shutdown();
        
        super.stop();
    }

    public void register(final ObservableList<EntityContainer> entities) {
        LoggerFacade.getDefault().debug(this.getClass(), "register(ObservableList<Entity>)"); // NOI18N
        
        FXCollections.sort(entities, (EntityContainer o1, EntityContainer o2) -> {
            return o1.getSimpleName().compareTo(o2.getSimpleName());
        });
        
        FrameworkProvider.getDefault().register(entities);
    }
    
}

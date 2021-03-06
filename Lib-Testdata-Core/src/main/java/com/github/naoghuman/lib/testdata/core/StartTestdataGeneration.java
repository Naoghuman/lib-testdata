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
import com.github.naoghuman.lib.database.core.DatabaseFacade;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.preferences.core.PreferencesFacade;
import com.github.naoghuman.lib.properties.core.PropertiesFacade;
import com.github.naoghuman.lib.testdata.internal.configuration.PreferencesConfiguration;
import com.github.naoghuman.lib.testdata.internal.configuration.PropertiesConfiguration;
import com.github.naoghuman.lib.testdata.internal.framework.FrameworkProvider;
import com.github.naoghuman.lib.testdata.internal.i18n.Properties;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Naoghuman
 * @since  0.1.0
 */
public class StartTestdataGeneration extends Application implements PropertiesConfiguration {

    public static void main(final String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "init()"); // NOI18N
        
        PropertiesFacade.getDefault().register(KEY__APPLICATION__RESOURCE_BUNDLE);
        
        super.init();
    }
    
    @Override
    public void start(final Stage stage) {
        LoggerFacade.getDefault().debug(this.getClass(), "start(Stage)"); // NOI18N
        
        final Scene scene = new Scene(FrameworkProvider.getDefault().getView(), 1280.0d, 720.0d);
        stage.setScene(scene);
        
        final String title = Properties.getPropertyForApplication(KEY__APPLICATION__TITLE)
                + Properties.getPropertyForApplication(KEY__APPLICATION__VERSION);
        stage.setTitle(title);
        
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
        DatabaseFacade.getDefault().shutdown();
        
        super.stop();
    }

    public void register(final String database, final ObservableList<EntityContainer> entityContainers) {
        LoggerFacade.getDefault().debug(this.getClass(), "register(ObservableList<Entity>)"); // NOI18N
        
        PreferencesFacade.getDefault().put(PreferencesConfiguration.PREF__TESTDATA__DATABASE, database);
        
        FrameworkProvider.getDefault().register(entityContainers);
    }
    
}

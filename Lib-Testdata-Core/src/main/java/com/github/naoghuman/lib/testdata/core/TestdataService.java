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

import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.internal.configuration.ActionConfiguration;
import com.github.naoghuman.lib.testdata.internal.configurationcomponent.ConfigurationComponentPresenter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class TestdataService extends Service<Void> {
    
    private final DoubleProperty progressProperty = new SimpleDoubleProperty(0.0d);
    
    private EntityContainer entityContainer;
    
    public TestdataService() {
        
    }
    
    public void configure(final EntityContainer entityContainer) {
        LoggerFacade.getDefault().debug(this.getClass(), "register(Task<Void>)"); // NOI18N
        
        this.entityContainer = entityContainer;
        
        progressProperty.unbind();
        progressProperty.setValue(0);
        progressProperty.bind(super.progressProperty());
        
        final ConfigurationComponentPresenter presenter = this.entityContainer.getConfigurationComponentPresenter();
        presenter.getProgressBarPercentInformation().textProperty().bind(
                Bindings.createStringBinding(() -> {
                    int process = (int) (progressProperty.getValue() * 100.0d);
                    if (process <= 0) {
                        process = 0;
                    } else {
                        ++process;
                    }

                    return process + "%"; // NOI18N
                },
                progressProperty));
        presenter.progressProperty().unbind();
        presenter.progressProperty().bind(super.progressProperty());
    }

    @Override
    protected Task<Void> createTask() {
        return entityContainer.getTestdataGenerationTask();
    }

    public void setOnSucceeded(final boolean resetGui) {
        super.setOnSucceeded((WorkerStateEvent t) -> {
            LoggerFacade.getDefault().debug(this.getClass(), "setOnSucceeded(boolean)"); // NOI18N
            
            final ConfigurationComponentPresenter presenter = entityContainer.getConfigurationComponentPresenter();
            if (!presenter.getProgressBarPercentInformation().getText().equals("100%")) { // NOI18N
                presenter.getProgressBarPercentInformation().textProperty().unbind();
                presenter.getProgressBarPercentInformation().setText("100%"); // NOI18N
            }
            
            if (resetGui) {
                ActionHandlerFacade.getDefault().handle(ActionConfiguration.ON_ACTION__ENABLE_CONFIGURATION);
            }
        });
    }
    
}

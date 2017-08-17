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
package com.github.naoghuman.lib.testdata.internal.navigation;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.core.Entity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class NavigationPresenter implements Initializable {
    
    @FXML private CheckBox cbEntity;
    @FXML private FlowPane fpEntity;
    @FXML private Label lEntity;
    
    private Entity entity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize NavigationPresenter"); // NOI18N
        
    }

    public void configure(final Entity entity) {
        LoggerFacade.getDefault().debug(this.getClass(), "Configure"); // NOI18N
        
        this.entity = entity;
        
        this.onActionPrepareGui();
    }
    
    private void onActionPrepareGui() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action prepare [Gui]"); // NOI18N
        
        // Prepare the gui
        lEntity.setText(entity.getSimpleName());
        
        entity.getRequiredEntities()
                .forEach(clazz -> {
                    final Label l = new Label();
                    l.setText(clazz.getSimpleName());
                    l.setFont(Font.font("System", FontPosture.ITALIC, 12.0d)); // NOI18N
                    l.setTextFill(Color.web("#ea0000")); // NOI18N
                    
                    fpEntity.getChildren().add(l);
                });
    }
    
}

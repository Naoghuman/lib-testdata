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
package com.github.naoghuman.lib.testdata.internal.configuration;

import com.github.naoghuman.lib.testdata.internal.configuration.items.TimeperiodItems;
import com.github.naoghuman.lib.testdata.internal.configuration.items.QuantityItems;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.core.EntityContainer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class ConfigurationPresenter implements Initializable {
    
    @FXML private ComboBox cbQuantityItems;
    @FXML private ComboBox cbTimeperiodItems;
    @FXML private Label lEntityName;
    @FXML private Label lPreviousRequiredEntities;
    @FXML private Label lProgressBarPercentInformation;
    @FXML private ProgressBar pbEntity;
    @FXML private VBox  vbTimeperiod;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "initialize(URL, ResourceBundle)"); // NOI18N
        
        this.initializeComboBoxQuantity();
        this.initializeComboBoxTimeperiod();
    }

    private void initializeComboBoxQuantity() {
        LoggerFacade.getDefault().info(this.getClass(), "initializeComboBoxQuantity()"); // NOI18N
        
        cbQuantityItems.getItems().addAll(QuantityItems.getQuantityItems());
        cbQuantityItems.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {

            @Override
            public ListCell<Integer> call(ListView<Integer> param) {
                return new ListCell<Integer>() {

                        @Override
                        protected void updateItem(Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            
                            if (item == null) {
                                super.setText(null);
                                return;
                            }
                            
                            super.setText("" + item); // NOI18N
                        }
                    };
            }
        });
    }

    private void initializeComboBoxTimeperiod() {
        LoggerFacade.getDefault().info(this.getClass(), "initializeComboBoxTimeperiod()"); // NOI18N
        
        cbTimeperiodItems.getItems().addAll(TimeperiodItems.getTimeperiodItems());
        cbTimeperiodItems.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {

            @Override
            public ListCell<Integer> call(ListView<Integer> param) {
                return new ListCell<Integer>() {

                        @Override
                        protected void updateItem(Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            
                            if (item == null) {
                                super.setText(null);
                                return;
                            }
                            
                            super.setText("" + item); // NOI18N
                        }
                    };
            }
        });
    }
    
    public void configuration(final String simpleName, final boolean timeperiodIsManagedAndVisible) {
        LoggerFacade.getDefault().debug(this.getClass(), "configuration(EntityContainer)"); // NOI18N
        
        lEntityName.setText(String.format(lEntityName.getText(), simpleName));
        
        vbTimeperiod.setManaged(timeperiodIsManagedAndVisible);
        vbTimeperiod.setVisible(timeperiodIsManagedAndVisible);
    }
    
    public void onActionQuantityEntities() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionQuantityEntities()"); // NOI18N
        
//        final Integer quantityEntities = (Integer) cbQuantityEntities.getSelectionModel().getSelectedItem();
//        PreferencesFacade.getDefault().putInt(PREF__TESTDATA__QUANTITY_ENTITIES__TOPIC, quantityEntities);
    }
    
    public void onActionQuantityTimePeriod() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionQuantityTimePeriod()"); // NOI18N
        
//        final Integer quantityTimePeriod = (Integer) cbQuantityTimePeriod.getSelectionModel().getSelectedItem();
//        PreferencesFacade.getDefault().putInt(PREF__TESTDATA__QUANTITY_TIMEPERIOD__TOPIC, quantityTimePeriod);
    }
    
}

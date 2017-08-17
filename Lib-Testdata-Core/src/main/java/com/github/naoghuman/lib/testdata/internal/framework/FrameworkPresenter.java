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
package com.github.naoghuman.lib.testdata.internal.framework;

import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.action.core.RegisterActions;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.core.Entity;
import com.github.naoghuman.lib.testdata.internal.configuration.ActionConfiguration;
import com.github.naoghuman.lib.testdata.internal.navigation.NavigationPresenter;
import com.github.naoghuman.lib.testdata.internal.navigation.NavigationView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class FrameworkPresenter implements Initializable, ActionConfiguration, RegisterActions {
    
    @FXML private Button bCreateTestdata;
//    @FXML private AnchorPane apDialogLayer;
    @FXML private CheckBox cbDeleteDatabase;
    @FXML private CheckBox cbSelectAll;
    @FXML private ListView<Entity> lvEntities;
    @FXML private ScrollPane spEntities;
    @FXML private TabPane tpEntities;
    @FXML private TabPane tpTestdata;
    @FXML private VBox vbEntities;
    
    private final ObservableList<Entity> entities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize FrameworkPresenter"); // NOI18N
        
        this.initializeListView();
        
        this.register();
    }
    
    private void initializeListView() {
        LoggerFacade.getDefault().info(this.getClass(), "Initialize ListView"); // NOI18N
        
        final Callback callbackHabits = (Callback<ListView<Entity>, ListCell<Entity>>) (ListView<Entity> listView) -> new ListCell<Entity>() {
            @Override
            protected void updateItem(final Entity entity, final boolean empty) {
                super.updateItem(entity, empty);
                
                this.setText(null);
                this.setGraphic(null);
                
                if (entity != null && !empty) {
                    final NavigationView view = new NavigationView();
                    final NavigationPresenter presenter = view.getRealPresenter();
                    presenter.configure(entity);
                    
                    this.setGraphic(view.getView());
                }
            }
        };
        lvEntities.setCellFactory(callbackHabits);
//        lvEntities.setOnMouseClicked(event -> {
//            // Open the Term
//            if (
//                    event.getClickCount() == 2
//                    && !lvEntities.getSelectionModel().isEmpty()
//            ) {
//                // Open the Habit
//                final Entity entity = lvEntities.getSelectionModel().getSelectedItem();
////                this.onActionShowHabitInOverview(habit);
//            }
//        });
//        lvEntities.setOnKeyPressed(event -> {
//            final KeyCode keyCode = event.getCode();
//            if (
//                    keyCode.equals(KeyCode.ENTER)
//                    || keyCode.equals(KeyCode.SPACE)
//                    || keyCode.equals(KeyCode.TAB)
//            ) {
//                // Open the Habit
//                final Entity entity = lvEntities.getSelectionModel().getSelectedItem();
////                this.onActionShowHabitInOverview(habit);
//            }
//        });
    }
    
    public void onActionCreateTestdata() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create Testdata"); // NOI18N
    }
    
    public void onActionDeleteDatabase() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action delete Database"); // NOI18N
        
//        PreferencesFacade.getDefault().putBoolean(PREF__TESTDATA__IS_SELECTED_DELETE_DATABASE, cbDeleteDatabase.isSelected());
    }
    
    public void onActionRefreshNavigation() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action refresh [Navigation]"); // NOI18N
        
        lvEntities.getItems().clear();
        lvEntities.getItems().addAll(entities);
    }
    
    public void onActionSelectAll(ActionEvent event) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action select all"); // NOI18N
        
    }

    void onActionShutdown() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action shutdown"); // NOI18N
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().debug(this.getClass(), "register()"); // NOI18N
        
        this.registerOnActionRefreshNavigation();
    }

    void register(ObservableList<Entity> entities) {
        LoggerFacade.getDefault().debug(this.getClass(), "register(ObservableList<Entity>)"); // NOI18N
        
        this.entities.clear();
        this.entities.addAll(entities);
        
        ActionHandlerFacade.getDefault().handle(ON_ACTION__REFRESH_NAVIGATION);
    }

    private void registerOnActionRefreshNavigation() {
        LoggerFacade.getDefault().debug(this.getClass(), "Register on action refresh [Navigation]"); // NOI18N
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__REFRESH_NAVIGATION,
                (ActionEvent event) -> {
                    this.onActionRefreshNavigation();
                });
    }
    
}

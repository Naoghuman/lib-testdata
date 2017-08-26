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
import com.github.naoghuman.lib.testdata.core.EntityContainer;
import com.github.naoghuman.lib.testdata.internal.configuration.ActionConfiguration;
import com.github.naoghuman.lib.testdata.internal.configuration.ConfigurationPresenter;
import com.github.naoghuman.lib.testdata.internal.navigation.NavigationPresenter;
import com.github.naoghuman.lib.testdata.internal.navigation.NavigationView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.util.Duration;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class FrameworkPresenter implements Initializable, ActionConfiguration, RegisterActions {
    
    @FXML private Button bCreateTestdata;
    @FXML private Button bResolveDependencies;
    @FXML private Button bShowConfigurationComponents;
//    @FXML private AnchorPane apDialogLayer;
    @FXML private CheckBox cbDeleteDatabase;
    @FXML private CheckBox cbSelectAll;
    @FXML private ListView<EntityContainer> lvEntities;
    @FXML private ScrollPane spEntities;
    @FXML private TabPane tpEntities;
    @FXML private TabPane tpTestdata;
    @FXML private VBox vbEntities;
    
    private final BooleanProperty disableProperty = new SimpleBooleanProperty(Boolean.FALSE);
    private final ObservableList<EntityContainer> entities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "initialize(URL, ResourceBundle)"); // NOI18N
        
//        this.initializeButtons();
        this.initializeListView();
        
        this.register();
    }
    
//    private void initializeButtons() {
//        LoggerFacade.getDefault().info(this.getClass(), "initializeButtons()"); // NOI18N
//        
//        bCreateTestdata.disableProperty().bind(Bindings.isEmpty(vbEntities.getChildren()));
//    }
    
    private void initializeListView() {
        LoggerFacade.getDefault().info(this.getClass(), "initializeListView()"); // NOI18N
        
        final Callback callback = (Callback<ListView<EntityContainer>, ListCell<EntityContainer>>) (ListView<EntityContainer> listView) -> new ListCell<EntityContainer>() {
            @Override
            protected void updateItem(final EntityContainer entityContainer, final boolean empty) {
                super.updateItem(entityContainer, empty);
                
                this.setText(null);
                this.setGraphic(null);
                
                if (entityContainer != null && !empty) {
                    final NavigationView view = new NavigationView();
                    final NavigationPresenter presenter = view.getRealPresenter();
                    presenter.configure(entityContainer);
                    
                    this.setGraphic(view.getView());
                }
            }
        };
        lvEntities.setCellFactory(callback);
//        lvEntities.setOnMouseClicked(event -> {
//            // Open the Term
//            if (
//                    event.getClickCount() == 2
//                    && !lvEntities.getSelectionModel().isEmpty()
//            ) {
//                // Open the Habit
//                final EntityContainer entity = lvEntities.getSelectionModel().getSelectedItem();
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
//                final EntityContainer entity = lvEntities.getSelectionModel().getSelectedItem();
////                this.onActionShowHabitInOverview(habit);
//            }
//        });
    }
    
    public void onActionCreateTestdata() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionCreateTestdata()"); // NOI18N
        
        /*
        TODO
         - Form every ConfigurationPresenter get in order which they are added:
            - Create an service with
               - Configurationdatas, task
            - Add the service to a SequientialTask
         - Play the SequientialTask to generate the Testdata.
        */
        final SequentialTransition sequentialTransition = new SequentialTransition();
        
        final PauseTransition ptDeactivateComponents = new PauseTransition();
        ptDeactivateComponents.setDuration(Duration.millis(50.0d));
//        ptDeactivateComponents.setDuration(Duration.ZERO);
        ptDeactivateComponents.setOnFinished((ActionEvent event) -> {
            this.onActionDisableConfigurationComponents();
        });
        sequentialTransition.getChildren().add(ptDeactivateComponents);
        
        if (cbDeleteDatabase.isSelected()) {
//            final PauseTransition ptDropDatabase = new PauseTransition();
//            ptDropDatabase.setDuration(Duration.millis(50.0d));
//            ptDropDatabase.setOnFinished((ActionEvent event) -> {
//                LoggerFacade.getDefault().debug(this.getClass(), "Drop database"); // NOI18N
//                DatabaseFacade.getDefault().drop(Properties.getPropertyForTestdataApplication(KEY__TESTDATA_APPLICATION__DATABASE));
//            });
//            sequentialTransition.getChildren().add(ptDropDatabase);
        }
        
//        final PauseTransition ptRegisterDatabase = new PauseTransition();
//        ptRegisterDatabase.setDuration(Duration.millis(150.0d));
//        ptRegisterDatabase.setOnFinished((ActionEvent event) -> {
//            LoggerFacade.getDefault().debug(this.getClass(), "Register database"); // NOI18N
//            DatabaseFacade.getDefault().register(Properties.getPropertyForTestdataApplication(KEY__TESTDATA_APPLICATION__DATABASE));
//        });
//        sequentialTransition.getChildren().add(ptRegisterDatabase);
        
//        final PauseTransition ptCreateTestdata = new PauseTransition();
//        ptCreateTestdata.setDuration(Duration.millis(150.0d));
//        ptCreateTestdata.setOnFinished((ActionEvent event) -> {
//            this.onActionExecuteServicesForTestdataGeneration();
//        });
//        sequentialTransition.getChildren().add(ptCreateTestdata);
        
        sequentialTransition.playFromStart();
    }
    
    private void onActionDisableConfigurationComponents() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionDisableConfigurationComponents()"); // NOI18N
        
        bCreateTestdata.disableProperty().bind(disableProperty);
        bResolveDependencies.disableProperty().bind(disableProperty);
        bShowConfigurationComponents.disableProperty().bind(disableProperty);
    
        cbDeleteDatabase.disableProperty().bind(disableProperty);
        cbSelectAll.disableProperty().bind(disableProperty);
        
        lvEntities.getItems().stream()
                .forEach((entityContainer) -> {
                    entityContainer.disableEntityInNavigation(Boolean.TRUE);
                });
        vbEntities.getChildren().stream()
                .forEach(node -> {
                    if (node instanceof VBox) {
                        final VBox vb = (VBox) node;
                        final Object obj = vb.getUserData();
                        if (obj instanceof ConfigurationPresenter) {
                            final ConfigurationPresenter presenter = (ConfigurationPresenter) obj;
                            presenter.disableComboBoxes(Boolean.TRUE);
                        }
                    }
                });
        
        disableProperty.setValue(Boolean.TRUE);
    }
    
    public void onActionDeleteDatabase() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionDeleteDatabase()"); // NOI18N
        
//        PreferencesFacade.getDefault().putBoolean(PREF__TESTDATA__IS_SELECTED_DELETE_DATABASE, cbDeleteDatabase.isSelected());
    }
    
    public void onActionRefreshNavigation() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionRefreshNavigation()"); // NOI18N
        
        lvEntities.getItems().clear();
        lvEntities.getItems().addAll(entities);
    }
    
    public void onActionResolvePreviousNeededEntities() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionResolvePreviousNeededEntities()"); // NOI18N
        
        bShowConfigurationComponents.setDisable(Boolean.TRUE);
            
        final AtomicBoolean atomicBoolean = new AtomicBoolean(Boolean.FALSE);
        lvEntities.getItems().stream()
                .filter(entityContainer ->
                        entityContainer.isEntitySelected()
                )
                .forEach(entityContainer -> {
                    atomicBoolean.set(Boolean.TRUE);
                    this.onActionResolvePreviousNeededEntities(entityContainer);
                });
        
        if (atomicBoolean.get()) {
            bShowConfigurationComponents.setDisable(Boolean.FALSE);
        }
    }
    
    private void onActionResolvePreviousNeededEntities(EntityContainer entityContainer) {
        if (!entityContainer.hasPreviousRequiredEntities()) {
            return;
        }
        
        LoggerFacade.getDefault().debug(this.getClass(), String.format(
                "onActionResolvePreviousNeededEntities(EntityContainer): %s", // NOI18N
                entityContainer.getSimpleName()));
        
        entityContainer.getPreviousRequiredEntities().stream()
                .forEach(clazz -> {
                    lvEntities.getItems().stream()
                            .filter(entityContainer2 ->
                                    clazz.getSimpleName().equals(entityContainer2.getSimpleName())
                                    && !entityContainer2.isEntitySelected()
                            )
                            .forEach(entityContainer2 -> {
                                entityContainer2.selectEntityInNavigation(Boolean.TRUE);
                                this.onActionResolvePreviousNeededEntities(entityContainer2);
                            });
                });
    }
    
    public void onActionSelectAllEntities(ActionEvent event) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action select all"); // NOI18N
        
        if (!(event.getSource() instanceof CheckBox)) {
            return;
        }
        
        final CheckBox checkBox = (CheckBox) event.getSource();
        final boolean selected = checkBox.isSelected();
        lvEntities.getItems().stream()
                .forEach((entityContainer) -> {
                    entityContainer.selectEntityInNavigation(selected);
                });
    }
    
    public void onActionShowConfigurationComponents() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionShowConfigurationComponents()"); // NOI18N
        
        // For the case that the developer have changed the selection
        this.onActionResolvePreviousNeededEntities();
        
        lvEntities.getItems().stream()
                .filter(entityContainer ->
                        entityContainer.isEntitySelected()
                )
                .forEach(entityContainer -> {
                    vbEntities.getChildren().add(entityContainer.getConfigurationPresenter().getView());
                });
        bCreateTestdata.setDisable(vbEntities.getChildren().isEmpty());
    }

    void onActionShutdown() {
        LoggerFacade.getDefault().debug(this.getClass(), "onActionShutdown()"); // NOI18N
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().debug(this.getClass(), "register()"); // NOI18N
        
        this.registerOnActionRefreshNavigation();
    }

    void register(final ObservableList<EntityContainer> entities) {
        LoggerFacade.getDefault().debug(this.getClass(), "register(ObservableList<Entity>)"); // NOI18N
        
        FXCollections.sort(entities, (EntityContainer entityContainer1, EntityContainer entityContainer2) -> {
            return entityContainer1.getSimpleName().compareTo(entityContainer2.getSimpleName());
        });
        
        final ObservableList<EntityContainer> alphabeticallySortedCopy = FXCollections.observableArrayList();
        alphabeticallySortedCopy.addAll(entities);
        
        final ObservableList<EntityContainer> previousRequiredEntitiesSorted = FXCollections.observableArrayList();
        while(!alphabeticallySortedCopy.isEmpty()) {
            final EntityContainer entityContainer = alphabeticallySortedCopy.remove(0);
            entityContainer.getPreviousRequiredEntities().stream()
                    .forEach(clazz -> {
                        final Optional<EntityContainer> optional = this.getEntityContainer(previousRequiredEntitiesSorted, clazz);
                        if (!optional.isPresent()) {
                            final Optional<EntityContainer> optional2 = this.getEntityContainer(alphabeticallySortedCopy, clazz);
                            if (optional2.isPresent()) {
                                alphabeticallySortedCopy.remove(optional2.get());
                                previousRequiredEntitiesSorted.add(optional2.get());
                            }
                        }
                    });
            
            previousRequiredEntitiesSorted.add(entityContainer);
        }
        
        this.entities.clear();
        this.entities.addAll(previousRequiredEntitiesSorted);
        
        ActionHandlerFacade.getDefault().handle(ON_ACTION__REFRESH_NAVIGATION);
    }
    
    private Optional<EntityContainer> getEntityContainer(final ObservableList<EntityContainer> entities, final Class clazz) {
        return entities.stream()
                .filter(ec3 -> ec3.getSimpleName().equals(clazz.getSimpleName()))
                .findFirst();
    }

    private void registerOnActionRefreshNavigation() {
        LoggerFacade.getDefault().debug(this.getClass(), "registerOnActionRefreshNavigation()"); // NOI18N
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__REFRESH_NAVIGATION,
                (ActionEvent event) -> {
                    this.onActionRefreshNavigation();
                });
    }
    
}

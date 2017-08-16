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
package com.github.naoghuman.lib.testdata.internal.testdataframework;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class TestdataFrameworkPresenter implements Initializable {
    
    @FXML private Button bCreateTestdata;
//    @FXML private AnchorPane apDialogLayer;
    @FXML private CheckBox cbDeleteDatabase;
    @FXML private CheckBox cbSelectAll;
    @FXML private ListView lvEntities;
    @FXML private ScrollPane spEntities;
    @FXML private TabPane tpEntities;
    @FXML private TabPane tpTestdata;
    @FXML private VBox vbEntities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void onActionCreateTestdata() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action create Testdata"); // NOI18N
    }
    
    public void onActionDeleteDatabase() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action delete Database"); // NOI18N
        
//        PreferencesFacade.getDefault().putBoolean(PREF__TESTDATA__IS_SELECTED_DELETE_DATABASE, cbDeleteDatabase.isSelected());
    }
    
    public void onActionSelectAll(ActionEvent event) {
        LoggerFacade.getDefault().debug(this.getClass(), "On action select all"); // NOI18N
        
    }

    void onActionShutdown() {
        LoggerFacade.getDefault().debug(this.getClass(), "On action shutdown"); // NOI18N
    }
    
}

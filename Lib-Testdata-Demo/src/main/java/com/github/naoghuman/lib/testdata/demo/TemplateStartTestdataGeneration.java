package com.github.naoghuman.lib.testdata.demo;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.testdata.core.StartTestdataGeneration;

import javafx.application.Application;
import javafx.stage.Stage;

public class TemplateStartTestdataGeneration extends Application {

    public static void main(final String[] args) {
        launch(args);
    }
    
    private StartTestdataGeneration startTestdataGeneration;
    
    @Override
    public void init() throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "init()"); // NOI18N
        
        super.init();
        
        startTestdataGeneration = new StartTestdataGeneration();
        startTestdataGeneration.init();
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

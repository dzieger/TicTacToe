package com.dzieger.tictactoe;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenuController {
	
	@FXML
	public Button quitGame;

    @FXML
    private void playRegularGame() throws IOException {
        App.setRoot("regular");
    }
    
    @FXML
    private void playUltimateGame() throws IOException {
    	App.setRoot("ultimate");
    }
    
    @FXML
    private void quitGame(ActionEvent event) {
        Stage stage = (Stage) quitGame.getScene().getWindow();
        stage.close();
    }
}

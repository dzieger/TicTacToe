package com.dzieger.tictactoe;

import java.io.IOException;

import com.dzieger.tictactoe.models.Board;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegularGameController {
	
	private boolean isPlayerX = true;
	private Button[][] tiles;
	
	private String winner;
	
	private Board board;
	
	@FXML
	private VBox regularVBox;
	
	@FXML
	private GridPane gameGrid;
	
	@FXML
	public void initialize() {
		this.board = new Board();
		this.tiles = board.getTiles();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int r = row, c = col;
				this.tiles[row][col].setOnAction(e -> handleTileAssignment(tiles[r][c]));
				this.gameGrid.add(this.tiles[r][c], col, row);
			}
		}
		
	}
	
	@FXML
	private void handleTileAssignment(Button tile){
		String player = isPlayerX ? "X" : "O";
		if (tile.getText().isEmpty()) {
			tile.setText(player);
			if(checkWin(player)) {
				System.out.println("Winner found");
				declareWinner(player);
			}
			isPlayerX = !isPlayerX;
		}
	}
	
	private boolean checkWin(String player) {
		System.out.println("Checking for winner");
		for (int i = 0; i < 3; i++) {
			if (tiles[i][0].getText().equals(player) &&
					tiles[i][1].getText().equals(player) &&
					tiles[i][2].getText().equals(player)) {
				return true;
			}
			
			if (tiles[0][i].getText().equals(player) &&
					tiles[1][i].getText().equals(player) &&
					tiles[2][i].getText().equals(player)) {
				return true;
			}
		}
		
		if (tiles[0][0].getText().equals(player) &&
				tiles[1][1].getText().equals(player) &&
				tiles[2][2].getText().equals(player)) {
			return true;
		}
		
		if (tiles[2][0].getText().equals(player) &&
				tiles[1][1].getText().equals(player) &&
				tiles[0][2].getText().equals(player)) {
			return true;
		}
		
		return false;
	}
	
	private void declareWinner(String player){
		this.winner = player;	
		gameGrid.setDisable(true);
		
		// Container for Label declaring victor and button to take you to main menu
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		
		Label victor = new Label("Player " + this.winner + " wins!");
		
		Button menu = new Button("Main Menu");
		menu.setPrefSize(100, 50);
		menu.setOnAction(e -> {
			try {
				App.setRoot("main-menu");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		hbox.getChildren().addAll(victor, menu);
		regularVBox.getChildren().add(hbox);
	}
	
}
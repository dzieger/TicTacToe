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

public class UltimateGameController {
	
	private Board[][] boards= new Board[3][3];
	
	private String[][] winners = new String[3][3];
	
	private GridPane[][] grids = new GridPane[3][3];
	
	private boolean isPlayerX = true;
	
	private String winner;
	
	@FXML
	private GridPane gameGrid;
	
	@FXML
	private VBox ultimateVBox;
	
	@FXML
	public void initialize() {
		for (int brow = 0; brow < 3; brow++) {
			for (int bcol = 0; bcol < 3; bcol++) {
				GridPane gameBoard = new GridPane();
				gameBoard.setHgap(10);
				gameBoard.setVgap(10);
				Board board = new Board();
				Button[][] tiles = board.getTiles();
				for (int tRow = 0; tRow < 3; tRow++) {
					for (int tCol = 0; tCol < 3; tCol++) {
						// Added these variable for explicit use within the Lambda function
						// TODO: Find a cleaner alternative if one exists. Issue was Local variable brow defined in
						// an enclosing scope must be final or at least effectively final
						int x = brow, y = bcol;
						int r = tRow, c = tCol;
						tiles[tRow][tCol].setOnAction(e -> handleTileAssignment(tiles[r][c], x, y, r, c));
						gameBoard.add(tiles[r][c], tCol, tRow);
					}
				}
				boards[brow][bcol] = board;
				this.gameGrid.add(gameBoard, bcol, brow);
				grids[brow][bcol] = gameBoard;
			}
		}
	}
	
	private void handleTileAssignment(Button tile, int boardRow, int boardCol, int subRow, int subCol) {
		if (tile.getText().isEmpty()) {
			tile.setText(isPlayerX ? "X" : "O");
			String player = isPlayerX ? "X" : "O";
			if (checkWin(player, boardRow, boardCol)) {
				try {
					if (checkGameWin(player)) {
						declareWinner(player);
					}
				} catch (NullPointerException e) {
					System.out.println("Not enough board wins for a winner yet.");
				}
			}
			isPlayerX = !isPlayerX;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					grids[row][col].setDisable(true);
				}
			}
			grids[subRow][subCol].setDisable(false);
		}
	}
	
	// Checks individual board win
	private boolean checkWin(String player, int row, int col) {
		Button[][] tiles = boards[row][col].getTiles();
		for (int i = 0; i < 3; i++) {
			if (tiles[i][0].getText().equals(player) &&
					tiles[i][1].getText().equals(player) &&
					tiles[i][2].getText().equals(player)) {
				if(winners[row][col] == null) {
					winners[row][col] = isPlayerX ? "X" : "O";
				}
				return true;
			}
			
			if (tiles[0][i].getText().equals(player) &&
					tiles[1][i].getText().equals(player) &&
					tiles[2][i].getText().equals(player)) {
				if(winners[row][col] == null) {
					winners[row][col] = isPlayerX ? "X" : "O";
				}
				return true;
			}
		}
		
		if (tiles[0][0].getText().equals(player) &&
				tiles[1][1].getText().equals(player) &&
				tiles[2][2].getText().equals(player)) {
			if(winners[row][col] == null) {
				winners[row][col] = isPlayerX ? "X" : "O";
			}
			return true;
		}
		
		if (tiles[2][0].getText().equals(player) &&
				tiles[1][1].getText().equals(player) &&
				tiles[0][2].getText().equals(player)) {
			if(winners[row][col] == null) {
				winners[row][col] = isPlayerX ? "X" : "O";
			}
			return true;
		}
		
		return false;
	}
	
	private boolean checkGameWin(String player) throws NullPointerException{
		for (int i = 0; i < 3; i++) {
			if ((winners[i][0] != null && winners[i][0].equals(player)) &&
					(winners[i][1] != null && winners[i][1].equals(player)) &&
					(winners[i][2] != null && winners[i][2].equals(player))) {
				return true;
			}
			
			if ((winners[0][i] != null && winners[0][i].equals(player)) &&
					(winners[1][i] != null && winners[1][i].equals(player)) &&
					(winners[2][i] != null && winners[2][i].equals(player))) {
				return true;
			}
		}
		
		if (winners[0][0].equals(player) &&
				winners[1][1].equals(player) &&
				winners[2][2].equals(player)) {
			return true;
		}
		
		if (winners[2][0].equals(player) &&
				winners[1][1].equals(player) &&
				winners[0][2].equals(player)) {
			return true;
		}
		
		return false;
	}
	
	private void declareWinner(String player) {
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
		ultimateVBox.getChildren().add(hbox);
	}

}

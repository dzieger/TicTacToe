package com.dzieger.tictactoe.models;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Board {
	
	private Button[][] tiles = new Button[3][3];
	
	public Board() {
		buildBoard();
	}
	
	private void buildBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				Button tile = new Button();
				tile.setPrefSize(100,  100);
				tiles[row][col] = tile;
			}
		}
	}

	
	public Button[][] getTiles() {
		return this.tiles;
	}

}

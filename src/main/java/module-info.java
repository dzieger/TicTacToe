module com.dzieger.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;

    opens com.dzieger.tictactoe to javafx.fxml;
    exports com.dzieger.tictactoe;
}

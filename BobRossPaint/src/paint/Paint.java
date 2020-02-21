package paint;


import java.io.Serializable;

import javafx.application.Application;
import javafx.stage.Stage;

public class Paint extends Application implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6355569752888123496L;
	PaintModel model; // Model
	View view; // View + Controller

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		this.model = new PaintModel();
		
		this.view = new View(model, stage);
	}
}

package app;

import app.view.principal.PrincipalView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launch extends Application {
	
	@Override
	public void start(Stage arg0) throws Exception {
		PrincipalView.buildAndShowScreen(arg0);
	}
	
	public static void main(String args[]) {
		launch();
	}
}
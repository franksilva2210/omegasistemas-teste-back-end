package app.view.principal;

import java.io.IOException;

import app.control.principal.PrincipalControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrincipalView {
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		PrincipalView.stage = stage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		PrincipalView.scene = scene;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static void setRoot(Parent root) {
		PrincipalView.root = root;
	}
	
	//----------|CONSTROI E EXIBE TELA|----------
	public static void buildAndShowScreen(Stage stageOwner) {
		buildScreen(stageOwner);
		showScreen();
	}
	
	//----------|EXIBE TELA|----------
	public static void showScreen() {
		stage.show();
	}
	
	//----------|CONSTROI TELA|----------
	public static void buildScreen(Stage stageOwner) {
		buildRoot();
		buildScene();
		buildStage(stageOwner);
	}
	
	private static void buildRoot() {
		FXMLLoader rootFxml = new FXMLLoader();
		rootFxml.setLocation(PrincipalView.class.getResource("PrincipalView.fxml"));
		rootFxml.setController(new PrincipalControl());
		
		try {
			root = rootFxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void buildScene() {
		scene = new Scene(root);
	}
	
	private static void buildStage(Stage stagePrimary) {
		stage = stagePrimary;
		stage.setTitle("Home");
		stage.setScene(scene);
	}
}
package app.view.caixa.buscar;

import java.io.IOException;

import app.control.caixa.buscar.BuscarCaixaControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuscarCaixaView {
	
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		BuscarCaixaView.stage = stage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		BuscarCaixaView.scene = scene;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static void setRoot(Parent root) {
		BuscarCaixaView.root = root;
	}
	
	//----------|CONSTROI E EXIBE TELA|----------
	public static void buildAndShowScreen(Stage stageOwner) {
		buildScreen(stageOwner);
		showScreen();
	}
	
	//----------|EXIBE TELA|----------
	public static void showScreen() {
		stage.showAndWait();
	}
	
	//----------|CONSTROI TELA|----------
	public static void buildScreen(Stage stageOwner) {
		buildRoot();
		buildScene();
		buildStage(stageOwner);
	}
	
	private static void buildRoot() {
		FXMLLoader rootFxml = new FXMLLoader();
		rootFxml.setLocation(BuscarCaixaView.class.getResource("BuscarCaixaView.fxml"));
		rootFxml.setController(new BuscarCaixaControl());
		
		try {
			root = rootFxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void buildScene() {
		scene = new Scene(root);
	}
	
	private static void buildStage(Stage stageOwner) {
		stage = new Stage();
		stage.setTitle("Buscar Caixa");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageOwner);
		stage.setScene(scene);
	}
}
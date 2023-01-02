package app.view.movcaixa.cadastro;

import java.io.IOException;

import app.control.movcaixa.cadastro.CadastroMovCaixaControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastroMovCaixaView {
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		CadastroMovCaixaView.stage = stage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		CadastroMovCaixaView.scene = scene;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static void setRoot(Parent root) {
		CadastroMovCaixaView.root = root;
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
		rootFxml.setLocation(CadastroMovCaixaView.class.getResource("CadastroMovCaixaView.fxml"));
		rootFxml.setController(new CadastroMovCaixaControl());
		
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
		stage.setTitle("Movimentar Caixa");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageOwner);
		stage.setScene(scene);
	}
}
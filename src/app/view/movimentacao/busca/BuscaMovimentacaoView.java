package app.view.movimentacao.busca;

import java.io.IOException;

import app.control.movimentacao.busca.BuscaMovimentacaoControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuscaMovimentacaoView {
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		BuscaMovimentacaoView.stage = stage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		BuscaMovimentacaoView.scene = scene;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static void setRoot(Parent root) {
		BuscaMovimentacaoView.root = root;
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
		rootFxml.setLocation(BuscaMovimentacaoView.class.getResource("BuscaMovimentacaoView.fxml"));
		rootFxml.setController(new BuscaMovimentacaoControl());
		
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
		stage.setTitle("Buscar Movimentos de Caixa");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageOwner);
		stage.setScene(scene);
	}
}

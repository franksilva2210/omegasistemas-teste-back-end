package app.view.msg.info;

import java.io.IOException;

import app.control.msg.info.MensagemInfoControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MensagemInfoView {

	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		MensagemInfoView.stage = stage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		MensagemInfoView.scene = scene;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static void setRoot(Parent root) {
		MensagemInfoView.root = root;
	}
	
	public static void loadAndShowStage(Stage stageOwner) {
		FXMLLoader rootFxml = new FXMLLoader();
		rootFxml.setLocation(MensagemInfoView.class.getResource("MensagemInfoView.fxml"));
		rootFxml.setController(new MensagemInfoControl());
		
		root = null;
		try {
			root = rootFxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scene = new Scene(root);
		
		stage = new Stage();
		stage.setTitle("Mensagem Informativa");
		stage.setResizable(false);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageOwner);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
}

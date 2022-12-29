package app.view.msg.confirm;

import java.io.IOException;

import app.control.msg.confirm.MensagemConfirmacaoControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MensagemConfirmacaoView {
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		MensagemConfirmacaoView.stage = stage;
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setScene(Scene scene) {
		MensagemConfirmacaoView.scene = scene;
	}
	
	public static Parent getRoot() {
		return root;
	}
	
	public static void setRoot(Parent root) {
		MensagemConfirmacaoView.root = root;
	}
	
	public static void loadAndShowStage(Stage stageOwner) {
		FXMLLoader rootFxml = new FXMLLoader();
		rootFxml.setLocation(MensagemConfirmacaoView.class.getResource("MensagemConfirmacaoView.fxml"));
		rootFxml.setController(new MensagemConfirmacaoControl());
		
		root = null;
		try {
			root = rootFxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scene = new Scene(root);
		
		stage = new Stage();
		stage.setTitle("Mensagem Confirmação");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(stageOwner);
		stage.setScene(scene);
		stage.showAndWait();
	}
}

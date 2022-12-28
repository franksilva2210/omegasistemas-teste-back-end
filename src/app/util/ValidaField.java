package app.util;

import javafx.scene.Node;

public class ValidaField {
	
	private Node control;
	private Boolean error;
	
	public Node getControl() {
		return control;
	}

	public void setControl(Node control) {
		this.control = control;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public static boolean validateControl(Node control) {
		return false;
	}
}

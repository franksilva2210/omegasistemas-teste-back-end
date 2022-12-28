package app.util;

import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

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

	public void validateControl() {
        if(control instanceof TextInputControl)
            validateTextInputField();
    }

    private void validateTextInputField() {
        TextInputControl textField = (TextInputControl) control;
        if (textField.getText() == null || textField.getText().equals(""))
        	error = true;
        else
        	error = false;
    }
    
    public void clear() {
    	control = null;
    	error = null;
    }
}

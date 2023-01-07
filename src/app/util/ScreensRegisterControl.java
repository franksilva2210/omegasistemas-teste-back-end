package app.util;

public abstract class ScreensRegisterControl {
	
	abstract protected void showDataScreen();
	
	abstract protected void clearDataScreen();
	
	abstract protected boolean processDataInterface();
	
	abstract protected boolean processDataObject();
	
	abstract protected boolean processDataPersistence();
	
	abstract protected boolean extractFields();
	
	abstract protected boolean validateFields();
}

package app.util;

public abstract class ScreensRegisterControl {
	
	abstract protected void showDataScreen();
	
	abstract protected void clearDataScreen();
	
	abstract protected boolean processDataInterface();
	
	abstract protected boolean processDataObject();
	
	abstract protected void processDataPersistence();
	
	abstract protected void extractFields();
	
	abstract protected boolean validateFields();
}

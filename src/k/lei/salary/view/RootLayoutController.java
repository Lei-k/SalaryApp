package k.lei.salary.view;

import javafx.fxml.FXML;
import k.lei.salary.MainApp;

public class RootLayoutController {
	
	//Reference to the main application.
	MainApp mainApp;
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @para mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	@FXML
	public void handleWorkTimeDialog(){
		mainApp.showWorkTimeDialog();
	}
	
	@FXML
	public void handleInidividualCalculateDialog(){
		mainApp.showIndividualCalculateDialog();
	}
}

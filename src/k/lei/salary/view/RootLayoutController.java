package k.lei.salary.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Lei-k;
 */
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
	
	@FXML
	public void handleAllCalculateDialog(){
		mainApp.showAllCalculateDialog();
	}
	
	@FXML
	void handleAboutAuthor(){
		mainApp.showAboutAuthor();
	}
	
	/**
	 * Creates an empty salary app.
	 */
	@FXML
	private void handleNew(){
		mainApp.getWorkData().clear();
		mainApp.setWorkFilePath(null);
	}
	
	/**
	 * Opens a FileChooser to let the user select an salary app to load.
	 */
	@FXML
	private void handleOpen(){
		FileChooser fileChooser = new FileChooser();
		
		//Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		//Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		
		if(file != null){
			mainApp.loadWorkDataFromFile(file);
		}
	}
	
	@FXML
	private void handleSave(){
		File workFile = mainApp.getWorkFilePath();
		if(workFile != null){
			mainApp.saveWorkDataToFile(workFile);
		}else{
			handleSaveAs();
		}
	}
	
	@FXML
	private void handleSaveAs(){
		FileChooser fileChooser = new FileChooser();
		
		//Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		//Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
		
		if(file != null){
			//Make sure it has the correct extension
			if(!file.getPath().endsWith(".xml")){
				file = new File(file.getPath() + ".xml");
			}
			mainApp.saveWorkDataToFile(file);
		}
	}
	
	@FXML
	private void handleAbout(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SalaryApp");
		alert.setHeaderText("SalaryApp");
		alert.setContentText("Author: Lei-k\n"
				+ "Website: https://github.com/Lei-k");
		
		alert.showAndWait();
	}
	
	/**
	 * Closes the application.
	 */
	@FXML private void handleExit(){
		System.exit(0);
	}
}

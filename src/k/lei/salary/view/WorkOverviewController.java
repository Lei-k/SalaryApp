package k.lei.salary.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import k.lei.salary.MainApp;
import k.lei.salary.model.Work;
import k.lei.salary.util.DateUtil;

public class WorkOverviewController {
	
	@FXML private TableView<Work> workTable;
	@FXML private TableColumn<Work, String> companyNameColumn;
	
	@FXML private Label companyNameLabel;
	@FXML private Label workNameLabel;
	@FXML private Label wagePerHourLabel;
	@FXML private Label workPositionLabel;
	@FXML private Label workTypeLabel;
	@FXML private Label onWorkDayLabel;
	
	//Reference to the main application.
	private MainApp mainApp;
	
	/**
	 * The constructor.
	 * The constructor is called before the initialized() method.
	 */
	public WorkOverviewController(){
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 */
	
	@FXML
	private void initialize(){
		//Initialize the work table with the one column.
		companyNameColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
		
		//Clear work details.
		showWorkDetails(null);
		
		//Listen for selection changes and show the work details when changed.
		workTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showWorkDetails(newValue));
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
		//Add observable list data to the table.
		workTable.setItems(mainApp.getWorkData());
	}
	
	/**
	 * Fills all text fields to show details about the work.
	 * If the specified work is null, all text field are cleared.
	 * 
	 * @param work the work or null
	 */
	
	private void showWorkDetails(Work work){
		if(work != null){
			//Fill the labels with info from the work object.
			companyNameLabel.setText(work.getCompanyName());
			workNameLabel.setText(work.getWorkName());
			wagePerHourLabel.setText(Integer.toString(work.getWagePerHour()));
			workPositionLabel.setText(work.getWorkPosition());
			workTypeLabel.setText(work.getWorkType());
			onWorkDayLabel.setText(DateUtil.format(work.getOnWorkDay()));
		}else{
			companyNameLabel.setText("");
			workNameLabel.setText("");
			wagePerHourLabel.setText("");
			workPositionLabel.setText("");
			workTypeLabel.setText("");
			onWorkDayLabel.setText("");
		}
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteWork(){
		int selectedIndex = workTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0){
			workTable.getItems().remove(selectedIndex);
		}else{
			//Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Work Selected");
			alert.setContentText("Please select a work in the table.");
			
			alert.showAndWait();
		}
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new work.
	 */
	@FXML
	private void handleNewWork(){
		Work tempWork = new Work();
		boolean okClicked = mainApp.showWorkEditDialog(tempWork);
		if(okClicked){
			mainApp.getWorkData().add(tempWork);
		}
	}
	
	/**
	 * Called when the user clicks the edit button. Open a dialog to edit
	 * details for the selected work.
	 */
	@FXML
	private void handleEditWork(){
		Work selectedWork = workTable.getSelectionModel().getSelectedItem();
		if(selectedWork != null){
			boolean okClicked = mainApp.showWorkEditDialog(selectedWork);
			if(okClicked){
				showWorkDetails(selectedWork);
			}
		}else{
			//Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("沒有選擇任何工作！");
			alert.setContentText("請在表格中選擇工作");
			
			alert.showAndWait();
		}
	}
}

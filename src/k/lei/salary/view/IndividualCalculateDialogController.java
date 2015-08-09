package k.lei.salary.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import k.lei.salary.model.SalaryCalculator;
import k.lei.salary.model.Work;

public class IndividualCalculateDialogController {
	
	@FXML private TableView<Work> workTable;
	@FXML private TableColumn<Work, String> companyNameColumn;
	
	@FXML private Label salaryLabel;
	
	private Stage dialogStage;
	
	
	/**
	 * Initializes the controller class, the method is automatical called
	 * after fxml file is loaded.
	 */
	@FXML
	private void initialize(){
		salaryLabel.setText("0");
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	/**
	 * Sets the work table
	 * 
	 * @param workData
	 */
	public void setWorkData(ObservableList<Work> workData){
		//Sets work table with one column.
		workTable.setItems(workData);
		companyNameColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
		
		//Adds listener to change salary.
		workTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showWorkSalary(newValue));
	}
	
	/**
	 * Dispaly work salary in the dialog.
	 * 
	 * @param work
	 */
	public void showWorkSalary(Work work){
		SalaryCalculator calculator = new SalaryCalculator();
		work.setWorkSalary((int)(calculator.calculate(work)));
		salaryLabel.setText(Integer.toString(work.getWorkSalary()));
	}
}

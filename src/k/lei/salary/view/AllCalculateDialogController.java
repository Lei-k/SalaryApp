package k.lei.salary.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import k.lei.salary.model.SalaryCalculator;
import k.lei.salary.model.Work;

public class AllCalculateDialogController {
	
	@FXML private TableView<Work> SalaryTable;
	@FXML private TableColumn<Work, String> companyNameColumn;
	@FXML private TableColumn<Work, String> workNameColumn;
	@FXML private TableColumn<Work, Integer> salaryColumn;
	
	private Stage dialogStage;
	private ObservableList<Work> workData;
	
	/*
	 * this method is automatically called after fxml file loaded.
	 */
	public void initialize(){
	}
	
	public void setWorkData(ObservableList<Work> workData){
		this.workData = workData;
	}
	
	public void showSalaryTable(){
		SalaryTable.setItems(workData);
		
		int tempAllSalary = 0;
		SalaryCalculator calculator = new SalaryCalculator();
		for(int i = 0 ; i < workData.size() ; i++){
			workData.get(i).setWorkSalary((int)(calculator.calculate(workData.get(i))));
			tempAllSalary += calculator.calculate(workData.get(i));
		}
		Work tempWork = new Work();
		tempWork.setCompanyName("總薪：");
		tempWork.setWorkName("");
		tempWork.setWorkSalary(tempAllSalary);
		workData.add(tempWork);
		
		companyNameColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
		workNameColumn.setCellValueFactory(cellData -> cellData.getValue().workNameProperty());
		salaryColumn.setCellValueFactory(cellData -> cellData.getValue().workSalaryProperty().asObject());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}

package k.lei.salary.view;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import k.lei.salary.model.SalaryCalculator;
import k.lei.salary.model.Work;
import k.lei.salary.model.WorkTime;

public class WorkTimeDialogController {
	
	@FXML private TableView<Work> workTable;
	@FXML private TableColumn<Work, String> companyNameColumn;
	
	@FXML private TableView<WorkTime> workTimeTable;
	@FXML private TableColumn<WorkTime, String> workDayColumn;
	@FXML private TableColumn<WorkTime, Integer> startHourColumn;
	@FXML private TableColumn<WorkTime, String> startSemicolonColumn;
	@FXML private TableColumn<WorkTime, Integer> startMinuteColumn;
	@FXML private TableColumn<WorkTime, String> linkSymbolColumn;
	@FXML private TableColumn<WorkTime, Integer> endHourColumn;
	@FXML private TableColumn<WorkTime, String> endSemicolonColumn;
	@FXML private TableColumn<WorkTime, Integer> endMinuteColumn;
	
	@FXML private TextField workDayField;
	@FXML private TextField startHourField;
	@FXML private TextField startMinuteField;
	@FXML private TextField endHourField;
	@FXML private TextField endMinuteField;
	
	private Stage dialogStage;
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after fxml file has been loaded.
	 */
	@FXML
	private void initialize(){
	}
	
	/**
	 * Sets the stage of this dialog.
	 */
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
		setKeyBoardEvent();
	}
	
	/**
	 * Sets the work data and company table.
	 * 
	 * @param workData
	 */
	public void setWorkData(ObservableList<Work> workData){
		workTable.setItems(workData);
		//Sets work table with one column.
		companyNameColumn.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
		
		//Adds listener to change work time detail.
		workTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showWorkTimeData(newValue));
	}
	
	public void setKeyBoardEvent(){
		dialogStage.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER){
				handleEnter();
			}
			if(e.getCode() == KeyCode.DELETE){
				handleDelete();
			}
		});
	}
	
	/**
	 * Sets the work time table to be edited in dialog.
	 * 
	 * @param work
	 */
	public void showWorkTimeData(Work work){
		workTimeTable.setItems(work.getWorkTimeData());
		//Sets work time table with seven columns.
		workDayColumn.setCellValueFactory(cellData -> cellData.getValue().getWorkDayProperty());
		startHourColumn.setCellValueFactory(cellData -> cellData.getValue().getStartHourProperty().asObject());
		startSemicolonColumn.setCellValueFactory(cellData -> cellData.getValue().getStartSemicolonProperty());
		startMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().getStartMinuteProperty().asObject());
		linkSymbolColumn.setCellValueFactory(cellData -> cellData.getValue().getLinkSymbolProperty());
		endHourColumn.setCellValueFactory(cellData -> cellData.getValue().getEndHourProperty().asObject());
		endSemicolonColumn.setCellValueFactory(cellData -> cellData.getValue().getEndSemicolonProperty());
		endMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().getEndMinuteProperty().asObject());
	}
	
	/**
	 * Called when user click Enter.
	 * 
	 * @return
	 */
	@FXML
	public void handleEnter(){
		Work selectedWork = workTable.getSelectionModel().getSelectedItem();
		if(selectedWork != null){
			if(isInputValid()){
				WorkTime tempWorkTime = new WorkTime();
				tempWorkTime.setWorkDay(workDayField.getText());
				tempWorkTime.setStartHour(Integer.parseInt(startHourField.getText()));
				tempWorkTime.setStartMinute(Integer.parseInt(startMinuteField.getText()));
				tempWorkTime.setEndHour(Integer.parseInt(endHourField.getText()));
				tempWorkTime.setEndMinute(Integer.parseInt(endMinuteField.getText()));
				if(SalaryCalculator.calculateOneTime(tempWorkTime) >= 0){
					SalaryCalculator calculator = new SalaryCalculator();
					selectedWork.setWorkSalary((int)(calculator.calculate(selectedWork)));
			        selectedWork.getWorkTimeData().add(tempWorkTime);
			        showWorkTimeData(selectedWork);
				}else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(dialogStage);
					alert.setTitle("警告");
					alert.setHeaderText("輸入錯誤的時間");
					alert.setContentText("結束時間必須大於起始時間");
					
					alert.showAndWait();
				}
			}
		}else{
			//Nothing selected.
			warningDialog("沒有選擇工作", "請在左方工作表選擇一組工作");
		}
	}
	
	@FXML
	public void handleDelete(){
		int selectedIndex = workTimeTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex  >= 0){
			workTimeTable.getItems().remove(selectedIndex);
		}else{
			warningDialog("未選擇時間", "請在下方時間表選擇一組時間");
		}
	}
	
	/**
	 * Validate the user input in the text field.
	 * 
	 * @return true if input is valid
	 */
	public boolean isInputValid(){
		String errorMessage = "";
		
		if(startHourField.getText() == null || startHourField.getText().length() == 0){
			errorMessage += "請輸入開始時間（時）\n";
		}else{
			try{
				Integer.parseInt(startHourField.getText());
				if(!WorkTime.isValidHour(Integer.parseInt(startHourField.getText()))){
					errorMessage += "請輸入正確的開始時間（時，必須介於0跟24之間）\n";
				}
			}catch(NumberFormatException e){
				errorMessage += "請輸入正確的開始時間（時，必須是整數）\n";
			}
		}
		if(startMinuteField.getText() == null || startMinuteField.getText().length() == 0){
			errorMessage += "請輸入開始時間（分）\n";
		}else{
			try{
				Integer.parseInt(startMinuteField.getText());
				if(!WorkTime.isValidMinute(Integer.parseInt(startMinuteField.getText()))){
					errorMessage += "請輸入正確的開始時間（分，必須介於0至60之間）";
				}
			}catch(NumberFormatException e){
				errorMessage += "請輸入正確的開始時間（分，必須是整數）\n";
			}
		}
		if(endHourField.getText() == null || endHourField.getText().length() == 0){
			errorMessage += "請輸入結束時間（時）\n";
		}else{
			try{
				Integer.parseInt(endHourField.getText());
				if(!WorkTime.isValidHour(Integer.parseInt(endHourField.getText()))){
					errorMessage += "請輸入正確的結束時間（時，必須介於0至24之間）\n";
				}
			}catch(NumberFormatException e){
				errorMessage += "請輸入正確的結束時間（時，必須是整數）\n";
			}
		}
		if(endMinuteField.getText() == null || endMinuteField.getText().length() == 0){
			errorMessage += "請輸入結束時間（分）\n";
		}else{
			try{
				Integer.parseInt(endMinuteField.getText());
				if(!WorkTime.isValidMinute(Integer.parseInt(endMinuteField.getText()))){
					errorMessage += "請輸入正確的結束時間（分，必須介於0至60之間）\n";
				}
			}catch(NumberFormatException e){
				errorMessage += "請輸入正確的結束時間（分，必須是整數）\n";
			}
		}
		
		if(errorMessage.length() == 0){
			return true;
		}else{
			warningDialog("輸入錯誤", errorMessage);
			
			return false;
		}
	}
	
	private void warningDialog(String header, String content){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("警告");
		alert.initOwner(dialogStage);
		alert.setHeaderText(header);
		alert.setContentText(content);
		
		alert.showAndWait();
	}
}
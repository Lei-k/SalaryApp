package k.lei.salary.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import k.lei.salary.model.Work;
import k.lei.salary.util.DateUtil;

public class WorkEditDialogController {
	
	@FXML private TextField companyNameField;
	@FXML private TextField workNameField;
	@FXML private TextField wagePerHourField;
	@FXML private TextField workPositionField;
	@FXML private TextField workTypeField;
	@FXML private TextField onWorkDayField;
	
	private Stage dialogStage;
	private Work work;
	private boolean okClicked = false;
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize(){
	}
	
	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	/**
	 * Sets the work to be edited in the dialog.
	 * 
	 * @param work
	 */
	public void setWork(Work work){
		this.work = work;
		
		companyNameField.setText(work.getCompanyName());
		workNameField.setText(work.getWorkName());
		wagePerHourField.setText(Integer.toString(work.getWagePerHour()));
		workPositionField.setText(work.getWorkPosition());
		workTypeField.setText(work.getWorkType());
		onWorkDayField.setText(DateUtil.format(work.getOnWorkDay()));
		onWorkDayField.setPromptText("yyyy-MM--dd");
	}
	
	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked(){
		return okClicked;
	}
	
	/**
	 * Called when the user clicks ok.
	 * @return 
	 */
	@FXML
	private void handleOk(){
		if(isInputValid()){
			work.setCompanyName(companyNameField.getText());
			work.setWorkName(workNameField.getText());
			work.setWagePerHour(Integer.parseInt(wagePerHourField.getText()));
			work.setWorkPosition(workPositionField.getText());
			work.setWorkType(workTypeField.getText());
			work.setOnWorkDay(DateUtil.parse(onWorkDayField.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}
		
	/**
     * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid(){
		String errorMessage = "";
		
		if(companyNameField.getText() == null ||
				companyNameField.getText().length() == 0){
			errorMessage += "請輸入正確的公司名稱!\n";
		}
		if(workNameField.getText() == null ||
			    workNameField.getText().length() == 0){
			errorMessage += "請輸入正確的工作名稱!\n";
		}
		if(wagePerHourField.getText() == null ||
				wagePerHourField.getText().length() == 0){
			errorMessage += "請輸入正確的時薪\n";
		}else{
			//try to parse the wage per hour into an int.
			try{
				Integer.parseInt(wagePerHourField.getText());
			}catch(NumberFormatException e){
				errorMessage += "請輸入正確的時薪（必須要是整數）\n";
			}
		}
		if(workPositionField.getText() == null ||
				workPositionField.getText().length() == 0){
			errorMessage += "請輸入正確的工作地點\n";
		}
		if(workTypeField.getText() == null ||
				workTypeField.getText().length() == 0){
			errorMessage += "請輸入正確的工作類型\n";
		}
		if(onWorkDayField.getText() == null ||
				onWorkDayField.getText().length() == 0){
			errorMessage += "請輸入正確的日期\n";
		}else{
			if(!DateUtil.validDate(onWorkDayField.getText())){
				errorMessage += "請輸入正確的日期（格式：yyyy-MM-dd）\n";
			}
		}
		
		if(errorMessage.length() == 0){
			return true;
		}else{
			//Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fiedls");
			alert.setHeaderText("請輸入正確的數值");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			
			return false;
		}		
	}
}

package k.lei.salary;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import k.lei.salary.model.Work;
import k.lei.salary.view.IndividualCalculateDialogController;
import k.lei.salary.view.RootLayoutController;
import k.lei.salary.view.WorkEditDialogController;
import k.lei.salary.view.WorkOverviewController;
import k.lei.salary.view.WorkTimeDialogController;

public class MainApp extends Application{
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Work> workData = FXCollections.observableArrayList();
	
	public MainApp(){
		//Add some sample data.
		workData.add(new Work("飛揚科技"));
		workData.add(new Work("雷氏團膳"));
	}
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SalaryApp");
		
		initRootLayout();
		
		showWorkOverview();
	}
	
	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout(){
		try{
			//Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();
			
			//Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			
			//Give the scene containing the root layout.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Shows the work overview inside root layout.
	 */
	public void showWorkOverview(){
		try{
			//Load work overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkOverview.fxml"));
			AnchorPane workOverview = (AnchorPane)loader.load();
			
			//Set work overview into the center of root layout.
			rootLayout.setCenter(workOverview);
			
			//Give the controller access to the main app.
			WorkOverviewController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified work. If the user
	 * click OK, the changes are saved into the provided work object and true
	 * is returned.
	 * 
	 * @param work the work object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showWorkEditDialog(Work work){
		try{
			//Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkEditDialog.fxml"));
			AnchorPane page = (AnchorPane)loader.load();
			
			//Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("編輯");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Set the work into the controller.
			WorkEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setWork(work);
			
			//Show the dialog and wait until the user close it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void showWorkTimeDialog(){
		try{
			//Load the fxml file and create a new stage for pupop dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkTimeDialog.fxml"));
			AnchorPane page = (AnchorPane)loader.load();
			
			//Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("時間表");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Set the work time into the controller.
			WorkTimeDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setWorkData(workData);
			
			//Show the dialog and wait until user close it
			dialogStage.showAndWait();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void showIndividualCalculateDialog(){
		try{
			//Load the fxml file and create a new stage for pupop dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/IndividualCalculateDialog.fxml"));
			AnchorPane page = (AnchorPane)loader.load();
			
			//Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("單項薪資表");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Set the work time into the controller.
			IndividualCalculateDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setWorkData(workData);
			
			//Show the dialog and wait until user close it
			dialogStage.showAndWait();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	public ObservableList<Work> getWorkData(){
		return workData;
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

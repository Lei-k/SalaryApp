package k.lei.salary.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import k.lei.salary.util.LocalDateAdapter;

/**Model class for a Work.
 * 
 * @author k
 *
 */
public class Work {
	
	private final StringProperty companyName;
	private final StringProperty workName;
	private final IntegerProperty wagePerHour;
	private final StringProperty workPosition;
	private final StringProperty workType;
	private final ObjectProperty<LocalDate> onWorkDay;
	private final SimpleIntegerProperty workSalary;
	private ObservableList<WorkTime> workTimeData =
			FXCollections.observableArrayList();
	
	/**
	 * Default constructor.
	 */
	
	public Work(){
		this(null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param companyName
	 */
	
	public Work(String companyName){
		this.companyName = new SimpleStringProperty(companyName);
		
		//Some initial dummy data, just for convenient testing.
		this.workName = new SimpleStringProperty("未填");
		this.wagePerHour = new SimpleIntegerProperty(120);
		this.workPosition = new SimpleStringProperty("未填");
		this.workType = new SimpleStringProperty("未填");
		this.onWorkDay = new SimpleObjectProperty<LocalDate>(LocalDate.of(1996, 02, 25));
		this.workSalary = new SimpleIntegerProperty(0);
		this.workTimeData.removeAll();
	}
	
	public String getCompanyName(){
		return companyName.get();
	}
	
	public void setCompanyName(String companyName){
		this.companyName.set(companyName);
	}
	
	public StringProperty companyNameProperty(){
		return companyName;
	}
	
	public String getWorkName(){
		return workName.get();
	}
	
	public void setWorkName(String workName){
		this.workName.set(workName);
	}
	
	public StringProperty workNameProperty(){
		return this.workName;
	}
	
	public int getWagePerHour(){
		return wagePerHour.get();
	}
	
	public void setWagePerHour(int wage){
		this.wagePerHour.set(wage);
	}
	
	public IntegerProperty wagePerHourProperty(){
		return wagePerHour;
	}
	
	public String getWorkPosition(){
		return workPosition.get();
	}
	
	public void setWorkPosition(String workPosition){
		this.workPosition.set(workPosition);
	}
	
	public StringProperty workProperty(){
		return workPosition;
	}
	
	public String getWorkType(){
		return workType.get();
	}
	
	public void setWorkType(String workType){
		this.workType.set(workType);
	}
	
	public StringProperty workTypeProperty(){
		return workType;
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getOnWorkDay(){
		return onWorkDay.get();
	}
	
	public void setOnWorkDay(LocalDate onWorkDay){
		this.onWorkDay.set(onWorkDay);
	}
	
	public ObjectProperty<LocalDate> onWorkDayProperty(){
		return onWorkDay;
	}
	
	public int getWorkSalary(){
		return workSalary.get();
	}
	
	public void setWorkSalary(int workSalary){
		this.workSalary.set(workSalary);
	}
	
	public IntegerProperty workSalaryProperty(){
		return workSalary;
	}
	
	public void setWorkTimeData(ObservableList<WorkTime> workTimeData){
		this.workTimeData = workTimeData;
	}
	
	public ObservableList<WorkTime> getWorkTimeData(){
		return workTimeData;
	}
}

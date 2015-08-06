package k.lei.salary.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkTime {
	
	private IntegerProperty startHour;
	private StringProperty startSemicolon;
	private IntegerProperty startMinute;
	private StringProperty linkSymbol;
	private IntegerProperty endHour;
	private StringProperty endSemicolon;
	private IntegerProperty endMinute;
	
	public WorkTime(){
		//Initializes work time property
		this.startHour = new SimpleIntegerProperty(0);
		startSemicolon = new SimpleStringProperty(":");
		this.startMinute = new SimpleIntegerProperty(0);
		linkSymbol = new SimpleStringProperty("~");
		this.endHour = new SimpleIntegerProperty(0);
		endSemicolon = new SimpleStringProperty(":");
		this.endMinute = new SimpleIntegerProperty(0);
	}
	
	public int getStartHour(){
		return startHour.get();
	}
	
	public void setStartHour(int startHour){
		if(isValidHour(startHour)){
			this.startHour.set(startHour);
		}
	}
	
	public IntegerProperty getStartHourProperty(){
		return startHour;
	}
	
	public String getStartSemicolon(){
		return startSemicolon.get();
	}
	
	public void setStartSemicolon(String startSemicolon){
		this.startSemicolon.set(startSemicolon);
	}
	
	public StringProperty getStartSemicolonProperty(){
		return startSemicolon;
	}
	
	public int getStartMinute(){
		return startMinute.get();
	}
	
	public void setStartMinute(int startMinute){
		if(isValidMinute(startMinute)){
		    this.startMinute.set(startMinute);
		}
	}
	
	public IntegerProperty getStartMinuteProperty(){
		return startMinute;
	}
	
	public String getLinkSymbol(){
		return linkSymbol.get();
	}
	
	public void setLinkSymbol(String linkSymbol){
		this.linkSymbol.set(linkSymbol);
	}
	
	public StringProperty getLinkSymbolProperty(){
		return linkSymbol;
	}
	
	public int getEndHour(){
		return endHour.get();
	}
	
	public void setEndHour(int endHour){
		if(isValidHour(endHour)){
		    this.endHour.set(endHour);
		}
	}
	
	public IntegerProperty getEndHourProperty(){
		return endHour;
	}
	
	public String getEndSemicolon(){
		return endSemicolon.get();
	}
	
	public void setEndSemicolon(String endSemicolon){
		this.endSemicolon.set(endSemicolon);
	}
	
	public StringProperty getEndSemicolonProperty(){
		return endSemicolon;
	}
	
	public int getEndMinute(){
		return endMinute.get();
	}
	
	public void setEndMinute(int endMinute){
		if(isValidMinute(endMinute)){
		    this.endMinute.set(endMinute);
		}
	}
	
	public IntegerProperty getEndMinuteProperty(){
		return endMinute;
	}
	
	public static boolean isValidHour(int hour){
		return hour >= 0 && hour <= 24;
	}
	
	public static boolean isValidMinute(int minute){
		return minute >= 0 && minute <=60;
	}
}

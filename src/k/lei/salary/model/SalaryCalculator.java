package k.lei.salary.model;

public class SalaryCalculator {
	
	private double salary;
	
	public SalaryCalculator(){
		this.salary = 0;
	}
	
	public double getSalary(){
		return salary;
	}
	
	public void setSalary(double salary){
		this.salary = salary;
	}
	
	public double calculate(Work work){
		salary = 0;
		for(int i = 0 ; i < work.getWorkTimeData().size() ; i++){
			salary += work.getWagePerHour()*calculateOneTime(work.getWorkTimeData().get(i));
		}
		return salary;
	}
	
	public static double calculateOneTime(WorkTime workTime){
		double startHour = workTime.getStartHour();
		double startMinute = workTime.getStartMinute();
		double endHour = workTime.getEndHour();
		double endMinute = workTime.getEndMinute();
		
		double tempHour = endHour - startHour;
		double tempMinute = endMinute - startMinute;
		if(Double.compare(endMinute, startMinute) < 0){
			tempHour--;
			tempMinute += 60;
		}
		
		return tempHour + (tempMinute/60);
	}
}

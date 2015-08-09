package k.lei.salary.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of works. This is used for saving the
 * list of work to XML
 * 
 * @author k
 *
 */

@XmlRootElement(name = "works")
public class WorkListWrapper {
	
	private List<Work> works;
	
	@XmlElement(name = "work")
	public List<Work> getWorks(){
		return works;
	}
	
	public void setWorks(List<Work> works){
		this.works = works;
	}
}

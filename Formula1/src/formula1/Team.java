package formula1;

import java.util.List;

public class Team {
	private String name;
	private Box box;
	private List<Pilot> pilots;
	
	public Team(String name, List<Pilot> pilots) {
		super();
		this.name = name;
		this.pilots = pilots;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public List<Pilot> getPilots() {
		return pilots;
	}
	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}
	
	
}

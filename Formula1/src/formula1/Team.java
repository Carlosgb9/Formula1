package formula1;

import java.util.List;

public class Team {
	private String name;
	private Box box;
	private Thread boxThread;
	private List<Pilot> pilots;
	private String color;
	
	public Team(String name, List<Pilot> pilots, RaceStatus rs, String color) throws InterruptedException {
		super();
		this.name = name;
		this.pilots = pilots;
		box = new Box(rs, this);
		boxThread = new Thread(box);
		boxThread.setName("Box" + name);
		boxThread.start();
		System.out.println("Box (" + name + ") started!");
		this.color = color;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}

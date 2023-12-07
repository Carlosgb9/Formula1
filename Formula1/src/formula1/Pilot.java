package formula1;

public class Pilot implements Runnable {
	private String name;
	private int laps;
	private int time;
	private int sleepTime;
	private RaceStatus rs;
	
	public Pilot(String name, int laps) {
		super();
		this.name = name;
		this.laps = laps;
		this.time = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLaps() {
		return laps;
	}
	public void setLaps(int laps) {
		this.laps = laps;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public void run() {
		try {
			sleepTime = (int) (Math.random() * 23) + 6;
			Thread.sleep(sleepTime);
			rs.lap(this);
		}catch(InterruptedException e) {	
		}
	}
	
	
	
	
	
}

package formula1WithoutBoxes;


public class SimplePilot implements Runnable {
	private String name;
	private int laps;
	private int time;
	private int sleepTime;
	private SimpleRacestatus rs;
	
	public SimplePilot(String name, int laps, SimpleRacestatus rs) {
		super();
		this.name = name;
		this.laps = laps;
		this.time = 0;
		this.rs = rs;
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
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	public SimpleRacestatus getRs() {
		return rs;
	}
	public void setRs(SimpleRacestatus rs) {
		this.rs = rs;
	}

	@Override
	public void run() {
		while (!rs.isFinished()) {
			try {
				sleepTime = (int) (Math.random() * 5) + 20; 
				time += sleepTime;
				Thread.sleep(sleepTime);
				rs.lap(this);
			}catch(InterruptedException e) {	
			}
		}
	}
}


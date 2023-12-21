package formula1;

public class Pilot implements Runnable {
	private String name;
	private int laps;
	private int time;
	private int sleepTime;
	private RaceStatus rs;
	
	public Pilot(String name, int laps, RaceStatus rs) {
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
	public RaceStatus getRs() {
		return rs;
	}
	public void setRs(RaceStatus rs) {
		this.rs = rs;
	}
	
	@Override
	public void run() {
		while (!rs.isFinished()) {
			try {
				sleepTime = (int) (Math.random() * 20) + 80; // Tenim en compte que una volta dura de mitja 1 minut y 30 segons (90s) i afegim una variable aleatoria de 3 mls. Multipliquem per 1000 per obtenir el temps en segons i afegir-lo cada volta
				time += sleepTime;
				Thread.sleep(sleepTime);
				rs.lap(this);
			}catch(InterruptedException e) {	
			}
		}
	}
}

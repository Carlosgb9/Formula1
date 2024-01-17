package formula1;

public class Pilot implements Runnable {
	private static final int MAX_TANK = 25;
	private String name;
	private int laps;
	private int time;
	private int sleepTime;
	private RaceStatus rs;
	private Team team;
	private int fuelTank = MAX_TANK;
	private String output;
	private boolean onBox;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getFuelTank() {
		return fuelTank;
	}

	public void setFuelTank(int fuelTank) {
		this.fuelTank = fuelTank;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Override
	public void run() {
		while (!rs.isFinished() && laps > 0) {
			try {
				output = team.getColor() + "Pilot: [" + name + "(" + team.getName() + ") laps=" + laps + "fuel=" + fuelTank + "] ";
				sleepTime = (int) (Math.random() * 5) + 20; 
				laps--;
				time += sleepTime;
				Thread.sleep(sleepTime);
				fuelTank--;
				output = team.getColor() + "Pilot: [" + name + "(" + team.getName() + ") laps=" + laps + "fuel=" + fuelTank + "] ";
				if (fuelTank <= 5) {
					setPilotIn();
				}
				rs.lap(this);
			} catch (InterruptedException e) {
			}
		}
	}

	private void setPilotIn() throws InterruptedException {
		synchronized (team.getBox()) {
			if (team.getBox().isFree()) {
				System.out.println(output + " getting into the box");
				team.getBox().setPilotInBox(this);
				team.getBox().notify();
				onBox = true;
			} else {
				System.out.println(output + " BOX BUSY!!");
				return;
			}
		}
		if (onBox) {
			synchronized (getName()) {
				System.out.println("El pilot ha entrat al box" + output);
				while (fuelTank != MAX_TANK) {
					getName().wait();
				}
			}
			onBox = false;
		}
		synchronized (team.getBox()) {
			team.getBox().setPilotInBox(null);
			team.getBox().notify();
		}
	}

	public void refuel() {
		fuelTank = MAX_TANK;
		output = team.getColor() + "Pilot: [" + name + "(" + team.getName() + ") laps=" + laps + "fuel=" + fuelTank + "] ";
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
	}
}

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
				output = "Pilot: [" + name + "(" + team.getName() + ") laps=" + laps + "fuel=" + fuelTank + "] ";
				sleepTime = (int) (Math.random() * 20) + 80; // Tenim en compte que una volta dura de mitja 1 minut y 30
																// segons (90s) i afegim una variable aleatoria de 3
																// mls. Multipliquem per 1000 per obtenir el temps en
				laps--; // segons i afegir-lo cada volta
				time += sleepTime;
				Thread.sleep(sleepTime);
				fuelTank--;
				output = "Pilot: [" + name + "(" + team.getName() + ") laps=" + laps + "fuel=" + fuelTank + "] ";
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
				onBox = true;
				System.out.println(output + " getting into the box");
				team.getBox().setPilotInBox(this);
				team.getBox().notify();
				System.out.println("El pilot ha entrat al box");
				while (fuelTank != MAX_TANK) {
					team.getBox().wait();
				}
				team.getBox().setPilotInBox(null);
				team.getBox().notify();
			} else {
				System.out.println(output + " BOX BUSY!!");
				return;
			}
		}
	}

	public void refuel() {
		fuelTank = MAX_TANK;
		output += "Pilot: [" + name + "(" + team + ") laps=" + laps + "fuel=" + fuelTank + "] ";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}

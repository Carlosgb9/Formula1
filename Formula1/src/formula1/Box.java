package formula1;

public class Box implements Runnable {
	private RaceStatus rs;
	private Pilot pilotInBox;
	private Team team;

	public Box(RaceStatus rs, Team team) {
		super();
		this.rs = rs;
		this.team = team;
	}

	public RaceStatus getRc() {
		return rs;
	}

	public void setRc(RaceStatus rc) {
		this.rs = rc;
	}

	public Pilot getPilotInBox() {
		return pilotInBox;
	}

	public synchronized void setPilotInBox(Pilot pilotInBox) {
		if (pilotInBox != null) {
			System.out.println(team.getColor() +
					"\tBOX(" + pilotInBox.getTeam().getName() + "): " + pilotInBox.getOutput() + " is in his box");
		}
		this.pilotInBox = pilotInBox;
	}

	public synchronized boolean isFree() {
		if (pilotInBox == null) {
			return true;
		} else {
			return false;
		}
	}

	public void setPilotOut() throws InterruptedException {
		synchronized (this) {
			while (isFree()) {
				System.out.println("Box is free");
				wait();
			}
		}

		synchronized (pilotInBox.getName()) {
			pilotInBox.refuel();
			System.out
					.println(team.getColor() + " \tBOX(" + pilotInBox.getTeam().getName() + "): " + pilotInBox.getOutput() + " refuelled");
			pilotInBox.getName().notify();
		}
		synchronized (this) {
			while (!isFree()) {
				wait();
			}
			System.out.println( team.getColor() + "El pilot ha surtit del box" + team.getName());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				setPilotOut();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rs.isFinished()) {
				break;
			}
		}
		System.out.println("Box tancat");
	}
}

package formula1;

public class Box implements Runnable {
	private RaceStatus rs;
	private Pilot pilotInBox;

	public Box(RaceStatus rs) {
		super();
		this.rs = rs;
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
		System.out
				.println("\tBOX(" + pilotInBox.getTeam().getName() + "): " + pilotInBox.getOutput() + " is in his box");
		this.pilotInBox = pilotInBox;
	}

	public synchronized boolean isFree() {
		if (pilotInBox == null) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void setPilotOut() throws InterruptedException {
		while (isFree()) {
			System.out.println("Box is free");
			wait();
		}
		pilotInBox.refuel();
		System.out.println("\tBOX(" + pilotInBox.getTeam().getName() + "): " + pilotInBox.getOutput() + " refuelled");
		notify();
		while (!isFree()) {
			wait();
		}
		System.out.println("El pilot ha surtit del box");
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

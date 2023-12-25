package formula1;

import java.util.Arrays;

public class Box {
	private RaceStatus rs;
	private Pilot pilotInBox;
	
	public Box(RaceStatus rs, Pilot pilotInBox) {
		super();
		this.rs = rs;
		this.pilotInBox = pilotInBox;
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
	public void setPilotInBox(Pilot pilotInBox) {
		this.pilotInBox = pilotInBox;
	}
	
	public boolean isFree() {
		if (pilotInBox == null) {
			return true;
		} else return false;
	}
	
	public void setPilot(Pilot pilot) {
		
	}
	public synchronized void setPilotOut() {
		try {
			while (isFree()) {
				System.out.println("Box is free");
				wait();
			}
		} catch (InterruptedException e) {
		}
		System.out.println("El pilot ha surtit del box");
		notify();
	}
	
	public void run() {
		while (true) {
			setPilotOut();
			if (rs.isFinished()) {
				break;
			}
			pilotInBox.refuel();
		}
		System.out.println("Box tancat");
	}
}

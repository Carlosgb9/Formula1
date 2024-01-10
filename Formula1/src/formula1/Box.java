package formula1;

import java.util.Arrays;

public class Box implements Runnable{
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
	public void setPilotInBox(Pilot pilotInBox) {
		System.out.println("\tBOX(" + pilotInBox.getTeam().getName()+"): "+ pilotInBox.getOutput()+" is in his box");
		this.pilotInBox = pilotInBox;
	}
	
	public boolean isFree() {
		if (pilotInBox == null) {
			return true;
		} else return false;
	}
	
	public synchronized void setPilotOut() throws InterruptedException {
		while (isFree()) {
			System.out.println("Box is free");
			wait();
		}
		pilotInBox.refuel();
		System.out.println("\tBOX(" + pilotInBox.getTeam().getName()+"): "+ pilotInBox.getOutput()+" refuelled");
		notify();
		pilotInBox = null;
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




//El box tiene que notificar al piloto de que ha recargado la pasofa y haces un notify, osea que el piloto al entrar al box entra en un wait


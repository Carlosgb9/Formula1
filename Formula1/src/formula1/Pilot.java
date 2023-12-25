package formula1;

import java.util.Arrays;


public class Pilot implements Runnable {
	private static final int MAX_TANK = 25;
	private String name;
	private int laps;
	private int time;
	private int sleepTime;
	private RaceStatus rs;
	private Team team;
	private int fuelTank = MAX_TANK;
	
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

	@Override
	public void run() {
		while (!rs.isFinished()) {
			try {
				sleepTime = (int) (Math.random() * 20) + 80; // Tenim en compte que una volta dura de mitja 1 minut y 30 segons (90s) i afegim una variable aleatoria de 3 mls. Multipliquem per 1000 per obtenir el temps en segons i afegir-lo cada volta
				time += sleepTime;
				Thread.sleep(sleepTime);
				fuelTank--;
				if (fuelTank<=5) {
					
				}
				rs.lap(this);
			}catch(InterruptedException e) {	
			}
		}
	}
	
//	public void run() {
//		while (clientes > 0) {
//			// Esperar un cliente
//			waitClient();
//
//			// Tomar nota (generar comanda)
//			String order = getOrder();
//
//			// Poner la comanda en el panel
//			board.enqueueOrder(order);
//			clientes--;
//		}
//		board.enqueueOrder(Pizzeria.END_OF_DUTY);
//		System.out.println("Waiter finished");
//	}
//	
//	
//	
//	
//	public synchronized void enqueueOrder(String order) {
//		waitNotFull();
//		orders.offer(order);
//		System.out.println("Orders \"" + order + "\" enqueued. Queue contains " + Arrays.toString(orders.toArray()));
//
//		// Notificar a la cocina
//		notify();
//	}
//	
//	private void waitNotFull() {
//		try {
//			while (isFull()) {
//				System.out.println("Waiter waiting... ");
//				wait();
//			}
//		} catch (InterruptedException e) {
//		}
//	}
	
	
	
	public void refuel() {
		fuelTank = MAX_TANK;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}

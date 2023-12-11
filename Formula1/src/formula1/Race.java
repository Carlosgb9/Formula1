package formula1;

public class Race {
	
	private static final String[] RACERS = { "Alonso", "Verstappen", "Vettel", "Hamilton", "Sainz", "Leclerc" };
	
	private static Thread[] racers;
	private static final int TOTAL_LAPS = 70;
	
	public static void main(String[] args) throws Exception{
		RaceStatus rs = new RaceStatus();
		
		racers = new Thread[RACERS.length];
		
		
		for (int i = 0; i < RACERS.length; i++) {
			racers[i] = new Thread(new Pilot(RACERS[i], TOTAL_LAPS, rs));
			racers[i].setName(RACERS[i]);
		}
		
		for (Thread racer : racers) {
			racer.start();
		}
		for (Thread racer : racers) {
			racer.join();
		}
		
		System.out.println(rs);
	}

}

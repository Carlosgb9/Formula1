package formula1;

public class Race {
	private static final String[] RACERS = { "Alonso", "Verstappen", "Vettel", "Hamilton", "Sainz", "Leclerc" };
	
	private static Thread[] racers;
	private static final int TOTAL_LAPS = 70;
	
	public static void main(String[] args) throws Exception{
		Initializer initializer = new Initializer();
		RaceStatus rs = new RaceStatus();
		
		Pilot[] pilots = initializer.pilotInit(rs, TOTAL_LAPS);
		Team[] equips = initializer.teamInit(pilots, rs);
		
		
		racers = new Thread[RACERS.length];
		
		
		for (int i = 0; i < pilots.length; i++) {
			racers[i] = new Thread(pilots[i]);
			racers[i].setName(pilots[i].getName());
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

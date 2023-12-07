package formula1;

import java.util.ArrayList;
import java.util.List;

public class RaceStatus {
	private static final int AMPLE_NOM = 18;
	private static final int AMPLE_TEMPS =15;
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false;
	
	public synchronized void lap(Pilot pilot) {
		System.out.println("Pilot: [" + pilot.getName() + ", laps=" + pilot.getLaps() + "]");
		if (pilot.getLaps() == 0) {
			finish = true;
		}
		if (isFinished()) {
			score.add(pilot);
		}
	}
	
	public boolean isFinished() {
		return finish;
	}

	@Override
	public String toString() {
		String result = "";
		int pos = 0;
		result += "[RACE RESULTS]";
		result += System.lineSeparator();
		for(Pilot p : score) {
			result += pos + ". " +
		}
		return result;
	}
	
	
	
}

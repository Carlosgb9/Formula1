package formula1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RaceStatus {
	private static final int AMPLE_NOM = 18;
	private static final int AMPLE_TEMPS =15;
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false;
	
	public synchronized void lap(Pilot pilot) {
		if (pilot.getLaps()==0) {
			finish = true;
		}
		System.out.println("Pilot: [" + pilot.getName() + ", laps=" + pilot.getLaps() + "]");
		pilot.setLaps(pilot.getLaps()-1);
		if (isFinished()) {
			score.add(pilot);
		}
		
//		if (pilot.getLaps() == 0) {
//			finish = true;
//		} else {
//			pilot.setLaps(pilot.getLaps()-1);
//			System.out.println("Pilot: [" + pilot.getName() + ", laps=" + pilot.getLaps() + "]");
//		}
//		if (isFinished()) {
//			score.add(pilot);
//		}
	}
	
	public synchronized boolean isFinished() {
		return finish;
	}

	@Override
	public String toString() {
		String result = "";
		int pos = 1;
		result += "[RACE RESULTS]";
		result += System.lineSeparator();
		for(Pilot p : score) {
			result += pos + ". " + p.getName() + " | " + p.getTime() + "s" ;
			result += System.lineSeparator();
			pos++;
		}
		return result;
	}
	
	private String timeFormat (int time) {
		int hores;
		int minuts;
		int segons;
		return null;
	}
	
	
	
}

package formula1WithoutBoxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimpleRacestatus {
	private List<SimplePilot> score = new ArrayList<>();
	private boolean finish = false;
	
	public synchronized void lap(SimplePilot pilot) {
		pilot.setLaps(pilot.getLaps()-1);
		if (pilot.getLaps() == 0) {
			finish = true;
		}
		System.out.println("Pilot: [" + pilot.getName() + ", laps=" + pilot.getLaps() + "]");
		if (isFinished()) {
			score.add(pilot);
		}
	}
	
	public synchronized boolean isFinished() {
		return finish;
	}

	@Override
	public String toString() {
		int pos = 1;
		String result = "";
		ordenar();
		result += "[RACE RESULTS]";
		result += System.lineSeparator();
			for(SimplePilot p : score) {
				if (p.getLaps()==0) {
					result += pos + ". " + p.getName() + " | " + timeFormat(p.getTime()*4) + "s" ;
				} else {
					result += pos + ". " +p.getName() + " | +" + p.getLaps() + " lap";
				}
				result += System.lineSeparator();
				pos++;
			}
		return result;
	}
	
	private String timeFormat (int time) {
		int horas = time / 3600;
        int minutos = (time % 3600) / 60;
        int segundosRestantes = time % 60;

        return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
	}
	
	private void ordenar() {
			Comparator<SimplePilot> comparador = Comparator.comparingInt(SimplePilot::getLaps).thenComparingInt(SimplePilot::getTime);
			Collections.sort(score, comparador);
	}	
}


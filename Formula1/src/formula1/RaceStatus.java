package formula1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RaceStatus {
	private static final int AMPLE_NOM = 18;
	private static final int AMPLE_TEMPS = 15;
	private List<Pilot> score = new ArrayList<>();
	private boolean finish = false;

	public synchronized void lap(Pilot pilot) {
		if (pilot.getLaps() == 0) {
			finish = true;
		}
		if (pilot.getFuelTank() <= 5) {
			pilot.setOutput(pilot.getOutput() + "need refueling. Fuel=" + pilot.getFuelTank());
		}
		System.out.println(pilot.getOutput());
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
		String result = "\u001B[0m";
		ordenar();
		result += "[RACE RESULTS]";
		result += System.lineSeparator();
		for (Pilot p : score) {
			if (p.getLaps() == 0) {
				result += pos + ". " + p.getName() + " | " + timeFormat(p.getTime()*4) + "s";
			} else {
				result += pos + ". " + p.getName() + " | +" + p.getLaps() + " lap";
			}
			result += System.lineSeparator();
			pos++;
		}
		return result;
	}

	private String timeFormat(int time) {
		int horas = time / 3600;
		int minutos = (time % 3600) / 60;
		int segundosRestantes = time % 60;

		return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
	}

	private void ordenar() {
		Comparator<Pilot> comparador = Comparator.comparingInt(Pilot::getLaps).thenComparingInt(Pilot::getTime);
		Collections.sort(score, comparador);
	}
}

package formula1;

import java.util.ArrayList;
import java.util.List;

public class Initializer {

	private static Pilot[] pilots;
	private static Team[] teams;

	public Pilot[] pilotInit(RaceStatus rs, int totalLaps) {

		String[] nomPilots = { "Alonso", "Vettel", "Hamilton", "Verstappen", "Sainz", "Leclerc" };

		pilots = new Pilot[nomPilots.length];
		for (int i = 0; i < nomPilots.length; i++) {
			pilots[i] = new Pilot(nomPilots[i], totalLaps, rs);
		}
		return pilots;
	}

	public Team[] teamInit(Pilot[] pilots, RaceStatus rs) throws InterruptedException {

		String[] nomEquips = { "Aston Martin", "Red Bull", "Ferrari" };
		String[] colorEquips = { "\u001B[32m", "\u001B[34m", "\u001B[31m"};

		List<Pilot> pilotsEquip = new ArrayList<Pilot>();

		teams = new Team[nomEquips.length];

		for (int i = 0; i < pilots.length; i++) {
			pilotsEquip.add(pilots[i]);
			if (i % 2 != 0) {
				int j = (i - 1) / 2;
				teams[j] = new Team(nomEquips[j], pilotsEquip, rs, colorEquips[j]);
				pilots[i].setTeam(teams[j]);
				pilots[i - 1].setTeam(teams[j]);
				pilotsEquip.clear();
			}
		}

		return teams;
	}
}

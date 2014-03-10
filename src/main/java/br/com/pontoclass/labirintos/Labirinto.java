package br.com.pontoclass.labirintos;

import java.util.Arrays;
import java.util.List;

public class Labirinto {
	private Entrance[]	entrances;

	public Labirinto(Entrance...entrances) {
		this.entrances = entrances;
	}
	
	public List<Entrance> getEntrances() {
		return Arrays.asList(entrances);
	}
}
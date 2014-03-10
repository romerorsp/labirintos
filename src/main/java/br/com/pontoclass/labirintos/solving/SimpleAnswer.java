package br.com.pontoclass.labirintos.solving;

import java.util.Collections;
import java.util.List;

public class SimpleAnswer implements Answer {

	private List<String[]>	partial;
	private final String[]	solution;

	public SimpleAnswer(List<String[]> partial) {
		this.partial = partial;
		solution = new String[]{};
	}
	
	public SimpleAnswer(List<String[]> partial, String[] solution) {
		this.partial = partial;
		this.solution = solution;
	}
	
	@Override
	public boolean hasSolution() {
		return this.solution.length > 0;
	}

	@Override public List<String[]> getSolutionList() {
		return Collections.singletonList(this. solution);
	}

	@Override public List<String[]> getEntireMapping() {
		return Collections.emptyList();
	}

	@Override public List<String[]> getPartialMapping() {
		return this.partial;
	}

}

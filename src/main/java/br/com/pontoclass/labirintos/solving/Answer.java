package br.com.pontoclass.labirintos.solving;

import java.util.List;

public interface Answer {
	public boolean hasSolution();
	
	public List<String[]> getSolutionList();
	
	public List<String[]> getEntireMapping();
	
	public List<String[]> getPartialMapping();
}
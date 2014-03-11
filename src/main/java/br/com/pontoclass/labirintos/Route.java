package br.com.pontoclass.labirintos;

import java.util.List;

public interface Route {

	public String ENTRANCE_OPEN	= "(";
	public String ENTRANCE_CLOSE = ")";
	public String EXIT_OPEN	= "[";
	public String EXIT_CLOSE = "]";

	public List<? extends Route> getParents();

	public List<? extends Route> getChildren();
	
	public boolean isEntrance();
	
	public boolean isExit();
	
	public boolean isDescendentOfMine(Route route);
	
	public boolean isAscendentOfMine(Route route);

	public boolean hasChildren();

	public void addParent(Route parent);
	
	public void addChild(Route child);
}

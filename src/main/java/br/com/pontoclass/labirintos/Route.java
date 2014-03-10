package br.com.pontoclass.labirintos;

import java.util.List;

public interface Route {

	public List<? extends Route> getParents();

	public List<? extends Route> getChildren();
	
	public boolean isEntrance();
	
	public boolean isExit();
	
	public boolean isChildOfMine(Route route);
	
	public boolean isParentOfMine(Route route);

	public boolean hasChildren();

}

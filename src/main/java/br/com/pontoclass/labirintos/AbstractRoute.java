package br.com.pontoclass.labirintos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rômero Ricardo
 * <p>Classe para representar as possíveis rotas de um ponto no Labirinto</p>
 *
 */
abstract class AbstractRoute implements Route {
    private final List<Route>	parents = new ArrayList<>();
	private final List<Route>	children = new ArrayList<>();
	private final Type	type;
	private final String	name;

	enum Type {
		ENTRANCE, EXIT, HIDDEN;
	}
	
	public AbstractRoute(String name, final List<? extends Route> parents, final List<? extends Route> children, Type type) {
		this.name = name;
		this.parents.addAll(parents);
		this.children.addAll(children);
		this.type = type;
    }
	
	public AbstractRoute(String name, final List<? extends Route> relatives, final Relationship relationship, Type type) {
		this.name = name;
		this.type = type;
		switch(relationship) {
			case PARENT: {
				this.parents.addAll(relatives);
				break;
			}
			default : {
				this.children.addAll(relatives);
				break;
			}
		}
    }

	public AbstractRoute(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public void addParent(Route parent) {
		parents.add(parent);
		if(this.getClass().isInstance(parent) && !parent.getChildren().contains(this)) {
			this.getClass().cast(parent).addChild(this);
		}
	}
	
	public void addChild(Route child) {
		children.add(child);
		if(this.getClass().isInstance(child) && !child.getParents().contains(this)) {
			this.getClass().cast(child).addParent(this);
		}
	}
	
	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	@Override
	public boolean isDescendentOfMine(Route route) {
		return children.contains(route);		
	};
	
	@SuppressWarnings("unused") private boolean deepSearchChild(Route candidate) {
		for(Route route: children) {
			if(route.isDescendentOfMine(candidate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAscendentOfMine(Route route) {
		return parents.contains(route);
	};

	@SuppressWarnings("unused") private boolean deepSearchParent(Route candidate) {
		for(Route route: parents) {
			if(route.isAscendentOfMine(candidate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Route> getParents() {
		return parents;
	}

	@Override
	public List<Route> getChildren() {
		return children;
	}
	
	@Override
	public boolean isEntrance() {
		return type == Type.ENTRANCE;
	}
	
	@Override
	public boolean isExit() {
		return type == Type.EXIT;
	}
	
	@Override public String toString() {
		return this.name;
	}
}
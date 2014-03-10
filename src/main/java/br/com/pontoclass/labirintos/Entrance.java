package br.com.pontoclass.labirintos;

import java.util.List;

public class Entrance extends AbstractRoute {
	
	public Entrance(String name, final List<? extends Route> children) {
		super(name, children, Relationship.CHILD, Type.ENTRANCE);
    }

	public Entrance(String name) {
		super(name, Type.ENTRANCE);
	}
}

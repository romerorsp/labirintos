package br.com.pontoclass.labirintos;

import java.util.List;

public class Exit extends AbstractRoute {
	
	public Exit(String name, final List<? extends Route> parents) {
		super(name, parents, Relationship.PARENT, Type.EXIT);
    }
}

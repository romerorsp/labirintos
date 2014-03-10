package br.com.pontoclass.labirintos;

import java.util.List;

public class Hidden extends AbstractRoute {
	
	public Hidden(String name, final List<? extends Route> relatives, Relationship relationship) {
		super(name, relatives, relationship, Type.HIDDEN);
    }
	
	public Hidden(String name) {
		super(name, Type.HIDDEN);
	}
}

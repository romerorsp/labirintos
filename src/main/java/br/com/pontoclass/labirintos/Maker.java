package br.com.pontoclass.labirintos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Maker {
	private String[] input;
	
	private Maker(){};

	public Maker withInput(String... input) {
		this.input = input;
		return this;
	}
	
	public Labirinto build(ModelType type) {
		switch(type) {
			case TERMINAL_INPUT: {
				return buildLabirinto(input);
			}
			default: return buildDefaultLabirinto();
		}
	}

	private static Labirinto buildLabirinto(String[] input) {
		Pattern pattern = Pattern.compile(String.format("(\\%1$s[a-zA-Z0-9]+\\%2$s|\\%3$s[a-zA-Z0-9]+\\%4$s|[a-zA-Z0-9]+)(\\ |\\:|\\=|\\-\\>)*\\|((\\%1$s[a-zA-Z0-9]+\\%2$s|\\%3$s[a-zA-Z0-9]+\\%4$s|[a-zA-Z0-9]+)(\\,(\\%1$s[a-zA-Z0-9]+\\%2$s|\\%3$s[a-zA-Z0-9]+\\%4$s|[a-zA-Z0-9]+))*)+\\|", Route.ENTRANCE_OPEN, Route.ENTRANCE_CLOSE, Route.EXIT_OPEN, Route.EXIT_CLOSE));
		Map<String, Route> map = new HashMap<String, Route>();
		List<Route> entrances = new ArrayList<Route>();
		for(String value: input) {
			Matcher matcher = pattern.matcher(value);
			while(matcher.find()) {
				String key = matcher.group(1);
				Route keyRoute;
				if(!map.containsKey(key)) {
					if(key.startsWith(Route.ENTRANCE_OPEN)) {
						keyRoute = new Entrance(key);
						map.put(key, keyRoute);
						entrances.add(keyRoute);
					} else if(key.startsWith(Route.EXIT_OPEN)) {
						keyRoute = new Exit(key);
						map.put(key, keyRoute);
					} else {
						keyRoute = new Hidden(key);
						map.put(key, keyRoute);
					}
				} else {
					keyRoute = map.get(key);
				}
				String[] children = matcher.group(3).split(",");
				for(String child: children) {
					Route childRoute;
					if(!map.containsKey(child)) {
						if(child.startsWith(Route.ENTRANCE_OPEN)) {
							childRoute = new Entrance(child);
							map.put(child, childRoute);
						} else if(child.startsWith(Route.EXIT_OPEN)) {
							childRoute = new Exit(child);
							map.put(child, childRoute);
						} else {
							childRoute = new Hidden(child);
							map.put(child, childRoute);
						}
					} else {
						childRoute = map.get(child);
					}
					keyRoute.addChild(childRoute);
				}
			}
		}
		return new Labirinto(entrances.toArray(new Entrance[]{}));
	}

	private static Labirinto buildDefaultLabirinto() {
		Entrance entranceA = new Entrance("A");
		AbstractRoute b = new Hidden("B"),
				      c = new Hidden("C"),
				      d = new Hidden("D"),
				      e = new Hidden("E"),
				      f = new Hidden("F"),
				      g = new Hidden("G"),
				      h = new Hidden("H"),
				      i = new Hidden("I"),
				      j = new Hidden("J"),
				      k = new Hidden("K"),
				      l = new Hidden("L"),
				      m = new Hidden("M");
		entranceA.addChild(b);
		b.addChild(c);
		b.addChild(d);
		d.addChild(e);
		d.addChild(k);
		e.addChild(f);
		e.addChild(g);
		g.addChild(h);
		g.addChild(i);
		i.addChild(j);
		i.addChild(k);
		i.addChild(m);
		k.addChild(d);
		k.addChild(l);
		return new Labirinto (entranceA);
	}

	public static Maker createMaker() {
		return new Maker();
	}
}
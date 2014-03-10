package br.com.pontoclass.labirintos;

public class Maker {
	public static Labirinto build(ModelType type) {
		switch(type) {
			default: return buildDefaultLabirinto();
		}
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
}
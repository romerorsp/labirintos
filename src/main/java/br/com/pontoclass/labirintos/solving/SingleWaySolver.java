package br.com.pontoclass.labirintos.solving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.pontoclass.labirintos.Entrance;
import br.com.pontoclass.labirintos.Labirinto;
import br.com.pontoclass.labirintos.Route;

public class SingleWaySolver implements Solver {

	private List<String[]> foundRoutes = new ArrayList<>();
	private List<String> foundRoute = new ArrayList<>();
	private List<String> foundSequence = new ArrayList<>();
	
	@Override
	public Answer solve(Labirinto labirinto) {
		List<Entrance> entrances = labirinto.getEntrances();
		boolean found = false;
		for(int i = 0;i < entrances.size() && !found; i++) {
			Route route = entrances.get(i);
			foundRoute.add(route.toString());
			foundSequence.add(route.toString());
			List<? extends Route> children = route.getChildren();
			found = findWay(route, children);
			if(found) {
				buildAnswer(true);
			}
		}
		return buildAnswer(false);
	}

	private boolean findWay(Route parent, List<? extends Route> children) {
		for(Route child: children) {
			foundRoute.add(child.toString());
			foundSequence.add(child.toString());
			if(child.isChildOfMine(parent)) {
				System.out.println(foundSequence);
				System.out.println(foundRoute);
				foundRoutes.add(foundRoute.toArray(new String[]{}));
				foundSequence.add(parent.toString());
				foundRoute.remove(foundRoute.size() - 1);
			}else if(child.isExit()) {
				return foundRoutes.add(foundRoute.toArray(new String[]{})) || true;
			} else if(child.hasChildren()) {
				int indexSeq = foundSequence.size();
				int indexRoute = foundRoute.size();
				boolean result = findWay(child, child.getChildren());
				if(result) {
					return true;
				}
				foundSequence = new ArrayList<>(foundSequence.subList(0, indexSeq - 1));
				foundRoute = new ArrayList<>(foundRoute.subList(0, indexRoute - 1));
			} else {
				System.out.println(foundSequence);
				System.out.println(foundRoute);
				foundRoutes.add(foundRoute.toArray(new String[]{}));
				foundRoute.remove(foundRoute.size() - 1);
				foundSequence.add(parent.toString());
			}
		}
		return false;
	}

	private Answer buildAnswer(boolean found) {
		Collections.sort(foundRoutes, new RoutesComparator());
		return found? new SimpleAnswer(foundRoutes, foundSequence.toArray(new String[]{})): 
					  new SimpleAnswer(foundRoutes);
	}
	
	private class RoutesComparator implements Comparator<String[]> {

		@Override
		public int compare(String[] a, String[] b) {
			if(a == null && b == null) {
				return 0;
			} else if(a == null) {
				return -1;
			} else if(b == null) {
				return 1;
			} else if(a.length > b.length) {
				for(int i = 0; i < b.length; i++) {
					if(a[i].equals(b[i])) {
						continue;
					}
					return a[i].compareTo(b[i]);
				}
			} else {
				for(int i = 0; i < a.length; i++) {
					if(a[i].equals(b[i])) {
						continue;
					}
					return a[i].compareTo(b[i]);
				}
			}
			return 0;
		}
	}
}
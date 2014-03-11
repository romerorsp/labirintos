package br.com.pontoclass.labirintos.main;

import java.util.Arrays;
import java.util.List;

import br.com.pontoclass.labirintos.Labirinto;
import br.com.pontoclass.labirintos.Maker;
import br.com.pontoclass.labirintos.ModelType;
import br.com.pontoclass.labirintos.solving.Answer;
import br.com.pontoclass.labirintos.solving.SingleWaySolver;
import br.com.pontoclass.labirintos.Route;

public class Solve {
	public static void main(String[] args) {
		Labirinto labirinto = Maker.createMaker().withInput(args).build(ModelType.TERMINAL_INPUT);
		System.out.println("------------------------------");
		System.out.println(String.format("Iniciando a tentativa de solução do labirinto: '%s'", Arrays.toString(args)));
		System.out.println("------------------------------");
		Answer answer = new SingleWaySolver().solve(labirinto);
		if(!answer.hasSolution()) {
			System.out.println("##### Nenhuma saída foi encontrada #####");
			System.out.println();
			System.out.println("Passos incorretos durante a solução: ");
			List<String[]> partial = answer.getPartialMapping();
			for(int j = 0; j < partial.size(); j++ ) {
				System.out.print(String.format("%d: ", j+1));
				String[] solution = answer.getPartialMapping().get(j);
				for(int i = 0; i < solution.length; i++) {
					String step = solution[i];
					if(i+1 == solution.length) {
						System.out.print(String.format("%s.", step));
					} else {
						System.out.print(String.format("%s->", step));
					}
					
				}
				System.out.println();
			}
		} else {
			System.out.println("##### A saída foi encontrada! #####");
			System.out.print("Passos Realizados: ");
			String[] solution = answer.getSolutionList().get(0);
			for(int i = 0; i < solution.length; i++) {
				String step = solution[i];
				if(i+1 == solution.length) {
					System.out.print(String.format("%s.", step));
				} else {
					System.out.print(String.format("%s->", step));
				}
				
			}
			System.out.println();
			System.out.print("Possível Solução: ");
			solution = new String[]{};
			for(String[] possible: answer.getPartialMapping()) {
				if(possible[possible.length-1].startsWith(Route.EXIT_OPEN)) {
					solution = possible;
					break;
				}
			}
			for(int i = 0; i < solution.length; i++) {
				String step = solution[i];
				if(i+1 == solution.length) {
					System.out.print(String.format("%s.", step));
				} else {
					System.out.print(String.format("%s->", step));
				}
				
			}
			
			System.out.println();
			System.out.println("Passos incorretos durante a solução: ");
			List<String[]> partial = answer.getPartialMapping();
			for(int j = 0; j < partial.size()-1; j++ ) {
				System.out.print(String.format("%d: ", j+1));
				solution = answer.getPartialMapping().get(j);
				for(int i = 0; i < solution.length; i++) {
					String step = solution[i];
					if(i+1 == solution.length) {
						System.out.print(String.format("%s.", step));
					} else {
						System.out.print(String.format("%s->", step));
					}
					
				}
				System.out.println();
			}
		}
		System.out.println("------------------------------");
		System.out.println("bye!");
	}
}

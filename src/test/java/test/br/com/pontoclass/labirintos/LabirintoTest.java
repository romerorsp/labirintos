package test.br.com.pontoclass.labirintos;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.pontoclass.labirintos.Labirinto;
import br.com.pontoclass.labirintos.Maker;
import br.com.pontoclass.labirintos.ModelType;
import br.com.pontoclass.labirintos.solving.Answer;
import br.com.pontoclass.labirintos.solving.SingleWaySolver;

/**
 * Unit test for labirinto testing.
 */
public class LabirintoTest {

	private Labirinto	labirinto;
	private SingleWaySolver	solver;

	@Before
	public void setUp() {
		this.labirinto = Maker.build(ModelType.DEFAULT);
		this.solver = new SingleWaySolver();
	}
	
	@Test
	public void solvingTest() {
		Answer answer = solver.solve(labirinto);
		Assert.assertNotNull(answer);
		Assert.assertFalse(answer.hasSolution());
		Assert.assertNotNull(answer.getEntireMapping());
		Assert.assertTrue(answer.getEntireMapping().isEmpty());
		Assert.assertNotNull(answer.getPartialMapping());
		Assert.assertEquals(answer.getPartialMapping().size(), 8);
		Assert.assertArrayEquals(answer.getPartialMapping().get(0), new String[]{"A", "B", "C"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(1), new String[]{"A", "B", "D", "E", "F"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(2), new String[]{"A", "B", "D", "E", "G", "H"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(3), new String[]{"A", "B", "D", "E", "G", "I", "J"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(4), new String[]{"A", "B", "D", "E", "G", "I", "K", "D"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(5), new String[]{"A", "B", "D", "E", "G", "I", "K", "L"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(6), new String[]{"A", "B", "D", "E", "G", "I", "M"});
		Assert.assertArrayEquals(answer.getPartialMapping().get(7), new String[]{"A", "B", "D", "K"});
//		Assert.assertArrayEquals(answer.getPartialMapping().get(8), new String[]{"A", "B", "D", "K", "I", "M"});
//		Assert.assertArrayEquals(answer.getPartialMapping().get(9), new String[]{"A", "B", "D", "K", "L"});
	}
}
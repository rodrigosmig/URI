import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class TrocaDeCartas {	
	public static void main(String[] args) {
		Set<Integer> listaAlice = new HashSet<Integer>();
		Set<Integer> listaBeatriz = new HashSet<Integer>();
		Scanner entrada = new Scanner(System.in);
		
		String valor = entrada.nextLine();
		while(!valor.equals("0 0")) {			
			
			String [] quantidadeAB = valor.split(" ");
			int quantArrayA = Integer.parseInt(quantidadeAB[0]);
			int quantArrayB = Integer.parseInt(quantidadeAB[1]);
			
			String valoresA = entrada.nextLine();
			String [] arrayValoresA = valoresA.split(" ");
			for(int x = 0; x < arrayValoresA.length; x++) {

				listaAlice.add(Integer.parseInt(arrayValoresA[x]));
			}
			
			String valoresB = entrada.nextLine();
			String [] arrayValoresB = valoresB.split(" ");
			for(int x = 0; x < arrayValoresB.length; x++) {
				listaBeatriz.add(Integer.parseInt(arrayValoresB[x]));
			}
			System.out.println(quantidadeTrocas(listaAlice, listaBeatriz));

			listaAlice.clear();
			listaBeatriz.clear();
			
			valor = entrada.nextLine();
		}		
	}
	
	public static int quantidadeTrocas(Set<Integer> alice, Set<Integer> beatriz) {
		int contAlice = 0;
		int contBeatriz = 0;

		for(Integer num : alice) {
			if(!beatriz.contains(num)) {
				contAlice++;
			}
		}

		for(Integer num : beatriz) {
			if(!alice.contains(num)) {
				contBeatriz++;
			}
		}

		if(contAlice < contBeatriz) {
			return contAlice;
		}
		else {
			return contBeatriz;
		}
	}
}
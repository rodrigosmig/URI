import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ParesImpares {
	private static Scanner entrada;
	private static ArrayList<Integer> pares;
	private static ArrayList<Integer> impares;

	public static void main(String[] args) {
		entrada = new Scanner (System.in);
		
		pares = new ArrayList<Integer>();
		impares = new ArrayList<Integer>();

		int n = Integer.parseInt(entrada.nextLine());
		for(int x = 0; x < n; x++) {
			int numero = Integer.parseInt(entrada.nextLine());
			if(numero % 2 == 0) {
				pares.add(numero);
			}
			else {
				impares.add(numero);
			}
		}
		Collections.sort(pares);
		Collections.sort(impares);
		for(Integer p : pares) {
			System.out.println(p);
		}
		for(int x = (impares.size() - 1); x >= 0; x--) {
			System.out.println(impares.get(x));
		}
	}
}
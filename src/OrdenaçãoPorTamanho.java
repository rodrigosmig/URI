import java.util.Scanner;
import java.util.ArrayList;

public class OrdenaçãoPorTamanho {
	private static Scanner entrada;
	private static ArrayList<String> lista;
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int n = Integer.parseInt(entrada.nextLine());
		lista = new ArrayList<String>();

		for(int x = 0; x < n; x++) {
			String inputLine = entrada.nextLine();
			String [] palavras = inputLine.split(" ");
			String[] palavrasOrdenadas = ordenaPalavrasPorTamanho(palavras);

			String texto = "";
			for(int s = 0; s < palavrasOrdenadas.length - 1; s++) {
				texto += palavrasOrdenadas[s] + " ";
			}
			texto += palavrasOrdenadas[palavrasOrdenadas.length - 1];
			lista.add(texto);
		}
		for (String i : lista) {
			System.out.println(i);
		}
	}

	public static String[] ordenaPalavrasPorTamanho(String[] arrayPalavras) {
		boolean ordenado = false;
		String aux;
		while(ordenado == false) {
			int cont = 0;
			for(int x = 0; x < arrayPalavras.length - 1; x++) {
				if(arrayPalavras[x].length() < arrayPalavras[x + 1].length()) {
					aux = arrayPalavras[x + 1];
					arrayPalavras[x + 1] = arrayPalavras[x];
					arrayPalavras[x] = aux;
					cont++;
				}
			}
			if(cont == 0) {
				ordenado = true;
			}
		}
		return arrayPalavras;
	}
}
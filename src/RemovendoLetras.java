import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class RemovendoLetras {
		
	public static void main(String[] args) throws IOException {
		long inicio = System.nanoTime();
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String palavra;
		String letraRetirada;
		Set<String> combinacoes;
		int contadorCaractere = 0;
		
		combinacoes = new TreeSet<String>();
		
		palavra = input.readLine();
		
		int tamanhoPalavra = palavra.length();
		
		while(contadorCaractere < tamanhoPalavra) {
			//armazena a primeira letra da palavra
			letraRetirada = palavra.substring(0, 1);
			//palavra sem a primeira letra
			palavra = palavra.substring(1);			
			combinaLetra(letraRetirada, palavra, combinacoes);
			contadorCaractere++;
		}

		for(String s : combinacoes) {
			output.write(s);
			output.newLine();
		}

		output.flush();
		output.close();

	}
	
	public static void combinaLetra(String letraRetirada, String palavra, Set<String> combinacoes) {
		//insere a letra retirada na lista
		combinacoes.add(letraRetirada);
		
		if(palavra.isEmpty()) {
			return;
		}
		
		//faz a combinação letra retirada junto com cada um dos caracteres da palavra
		for(int y = 0; y < palavra.length(); y++) {
			String letraRetirada2 = letraRetirada + palavra.substring(y, y + 1);
			for(int k = y + 1; k < palavra.length(); k++) {
				combinacoes.add(letraRetirada2 + palavra.substring(k, k + 1));
			}
			if(palavra.length() >= y + 1) {
				combinaLetra(letraRetirada2, palavra.substring(y + 1), combinacoes);
			}			
		}
		
	}
}
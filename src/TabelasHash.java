import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class TabelasHash {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(input.readLine());
		
		for(int n = 0; n < N; n++) {
			String s = input.readLine();
			String[] s2 = s.split(" ");
			int M = Integer.parseInt(s2[0]);
			int C = Integer.parseInt(s2[1]);
			int[][] enderecosBase = new int[M][C];

			String chaves = input.readLine();	
			StringTokenizer tokenizer = new StringTokenizer(chaves, " ");

			int parada = 0;
			while(parada < C) {
				int valorChave = Integer.parseInt(tokenizer.nextToken());
				for(int x = 0; x < C; x++) {
					if(enderecosBase[valorChave % M][x] == 0) {
						enderecosBase[valorChave % M][x] = valorChave;
						break;
					}
				}
				parada++;
			}

			String resposta = "";			
			
			for(int x = 0; x < M; x++) {
				int contador = 0;
				resposta += x + " -> ";
				for(int y = 0; y < C; y++) {
					contador++;
					if(enderecosBase[x][y] == 0) {
						resposta += "\\" + "\n";
						break;
					}
					resposta += enderecosBase[x][y] + " -> ";
					if(contador == C) {
						resposta += "\\" + "\n";
					}
				}
			}			
			
			if((n + 1) != N) {
				resposta += "\n";
			}		

			output.write(resposta);

		}
		output.flush();
		output.close();
	}
}
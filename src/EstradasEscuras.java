import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EstradasEscuras {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String entrada = input.readLine();
		
		while(!entrada.equals("0 0")) {
			
			String[] entrada2 = entrada.split(" ");
			
			int n = Integer.parseInt(entrada2[0]);
			int m = Integer.parseInt(entrada2[1]);
			
			int[][] matrizAdjacencia = new int[n][n];
			
			for(int x = 0; x < matrizAdjacencia.length; x++) {
				for(int y = 0; y < matrizAdjacencia.length; y++){
					matrizAdjacencia[x][y] = Integer.MAX_VALUE;
				}				
			}
			
			int custoTotal = 0;
			int valorEconomizado;
			
			for(int k = 0; k < m; k++) {
				entrada = input.readLine();
				String[] aresta = entrada.split(" ");
				matrizAdjacencia[Integer.parseInt(aresta[0])][Integer.parseInt(aresta[1])] = Integer.parseInt(aresta[2]);
				matrizAdjacencia[Integer.parseInt(aresta[1])][Integer.parseInt(aresta[0])] = Integer.parseInt(aresta[2]);
				custoTotal += Integer.parseInt(aresta[2]);
			}
			
			valorEconomizado = custoTotal - prim(matrizAdjacencia);
			
			output.write("" + valorEconomizado);
			output.newLine();			
			entrada = input.readLine();
		}

		output.flush();
		output.close();

	}
	
	public static int prim(int[][] matriz) {
		boolean[] visitados = new boolean[matriz.length];
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		
		int tempY;
		int tempX;
		int menorCaminho;
		int cont = 0;
		int soma = 0;
		
		visitados[0] = true;
		vertices.add(0);
		
		while(cont < matriz.length - 1) {
			
			tempY = 0;
			tempX = 0;
			menorCaminho = Integer.MAX_VALUE;

			for(int x = 0; x < vertices.size(); x++) {
				for(int y = 0; y < matriz.length; y++) {
					if(visitados[y] == false) {
						if(matriz[vertices.get(x)][y] < menorCaminho) {
							menorCaminho = matriz[vertices.get(x)][y];
							tempY = y;
							tempX = vertices.get(x);
						}
					}
				}
			}
			
			vertices.add(tempY);
			visitados[tempY] = true;
			soma += menorCaminho;
			matriz[tempX][tempY] = Integer.MAX_VALUE;
			cont++;
		}
		return soma;
	}	
}

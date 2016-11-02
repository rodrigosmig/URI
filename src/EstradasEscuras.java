import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class EstradasEscuras {
	
	protected static class Node {
		protected int valor;
		protected boolean visita;
		
		public Node(int v, boolean f) {
			valor = v;
			visita = f;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		String entrada = input.readLine();
		
		while(!entrada.equals("0 0")) {
			
			String[] entrada2 = entrada.split(" ");
			
			int n = Integer.parseInt(entrada2[0]);
			int m = Integer.parseInt(entrada2[1]);
			
			Node[][] matrizAdjacencia = new Node[n][n];
			
			for(int x = 0; x < matrizAdjacencia.length; x++) {
				for(int y = 0; y < matrizAdjacencia.length; y++){
					Node novoNode = new Node(Integer.MAX_VALUE, false);
					matrizAdjacencia[x][y] = novoNode;
				}				
			}
			
			int custoTotal = 0;
			int valorEconomizado;
			
			for(int k = 0; k < m; k++) {
				entrada = input.readLine();
				String[] aresta = entrada.split(" ");
				matrizAdjacencia[Integer.parseInt(aresta[0])][Integer.parseInt(aresta[1])].valor = Integer.parseInt(aresta[2]);
				matrizAdjacencia[Integer.parseInt(aresta[1])][Integer.parseInt(aresta[0])].valor = Integer.parseInt(aresta[2]);
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
	
	public static int prim(Node[][] matriz) {
		ArrayList<Integer> vertices = new ArrayList<Integer>(matriz.length);
		LinkedList<Integer> resultado = new LinkedList<Integer>();
		
		int tempY;
		int tempX;
		int menorCaminho;
		int cont = 0;
		int soma = 0;
		
		vertices.add(0);
		
		while(cont < matriz.length - 1) {
			
			tempY = 0;
			tempX = 0;
			menorCaminho = Integer.MAX_VALUE;
			
			for(int x = 0; x < vertices.size(); x++) {
				for(int y = 0; y < matriz.length; y++) {
					if(matriz[vertices.get(x)][y].valor < menorCaminho && matriz[vertices.get(x)][y].visita == false) {
						if(!vertices.contains(y)) {
							menorCaminho = matriz[vertices.get(x)][y].valor;
							tempY = y;
							tempX = vertices.get(x);
						}					
					}
				}			
			}
			matriz[tempX][tempY].visita = true;
			matriz[tempY][tempX].visita = true;
			resultado.add(matriz[tempX][tempY].valor);
			vertices.add(tempY);
			cont++;
		}
		for(int valor : resultado) {
			soma += valor;
		}
		return soma;
	}	
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class RerissonEOChurrasco  {
	
	private static LinkedList<String> amigosConvidados;
	
	public static class Grafo {
		private Node[] vertices;
		private static final int BRANCO = 0;
		private static final int CINZA = 1;
		private static final int PRETO = 2;
		private int tamanho;
		
		private class Node {
			private String nome;
			private LinkedList<Node> amigos;
			private int cor;
			private int relacao;
			
			public String toString() {
				return nome;
			}
		}
		
		public Grafo(int num) {
			vertices = new Node[num];
			tamanho = 0;
		}
		
		public int add(String n) {
			Node newNode = new Node();
			newNode.nome = n;
			newNode.cor = BRANCO;
			newNode.relacao = 0;
			newNode.amigos = new LinkedList<Node>();
			vertices[tamanho] = newNode;
			return tamanho++;
		}
		
		public int retornaIndice(String n) {
			for(int x = 0; x < vertices.length; x++) {
				if(vertices[x] != null && vertices[x].nome.equals(n)) {
					return x;
				}
			}
			return -1;
		}
		
		public void addAmigo(int a1, int a2) {
			this.vertices[a1].amigos.add(vertices[a2]);
		}
		
		public void BFS(int grau) {
			Queue<Node> fila = new LinkedList<Node>();
			amigosConvidados = new LinkedList<String>();
			
			vertices[0].cor = CINZA;
			fila.add(vertices[0]);
			
			while(fila.size() != 0) {
				Node amigo = fila.remove();
				
				if(amigo.relacao < grau) {
					for(Node n : amigo.amigos) {
						if(n.cor == BRANCO) {
							n.cor = CINZA;
							n.relacao = amigo.relacao + 1;
							amigosConvidados.add(n.nome);
							fila.add(n);
						}
					}
				}
				amigo.relacao = PRETO;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		String entrada = input.readLine();
		String[] entrada2 = entrada.split(" ");
		int n = Integer.parseInt(entrada2[0]);
		int G = Integer.parseInt(entrada2[1]);
		
		int amigo1 = 0;
		int amigo2 = 0;
		
		//inicializa o grafo
		Grafo grafo = new Grafo(n);
		
		grafo.add("Rerisson");
		
		for(int x = 0; x < n; x++) {
			String entradaAmigos = input.readLine();
			String[] entradaAmigos2 = entradaAmigos.split(" ");	
			
			//guarda o indíce em que os aigos estão cadastrados na lista de adjacência
			amigo1 = grafo.retornaIndice(entradaAmigos2[0]);
			amigo2 = grafo.retornaIndice(entradaAmigos2[1]);
			
			//insere o amigo na lista de adjacência caso ainda não esteja
			if(amigo1 < 0) {
				//adiciona vértice
				amigo1 = grafo.add(entradaAmigos2[0]);
			}			
			if(amigo2 < 0) {
				//adiciona vértice
				amigo2 = grafo.add(entradaAmigos2[1]);
			}
			
			//adiciona na lista de amigos de cada um
			grafo.addAmigo(amigo1, amigo2);
			grafo.addAmigo(amigo2, amigo1);
			
		}
		
		//faz a busca em largura
		grafo.BFS(G);
		
		Collections.sort(amigosConvidados);
		
		output.write("" + amigosConvidados.size());
		output.newLine();
		
		for(String s : amigosConvidados) {
			output.write(s);
			output.newLine();
		}
		
		output.flush();
		output.close();
    }    
}
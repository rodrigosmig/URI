import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ItinerarioDoPapaiNoel {
	
	private static class Grafo {
		
		private static class Heap {
			
			private int size = 0;

			public int pai(int i) {
				return i / 2;
			}

			public int esquerda(int i) {
				return 2 * i + 1;
			}

			public int direita(int i) {
				return 2 * i + 2;
			}

			public void heapSort(Aresta[] A) {
				buildHeap(A);
				for(int i = A.length - 1; i > 0; i--) {
					Aresta temp = A[0];
					A[0] = A[i];
					A[i] = temp;
					this.size--;
					Heapify(A, 0);			
				}
			}

			public void buildHeap(Aresta[] A) {
				this.size = A.length;
				for(int x = A.length / 2 - 1; x >= 0; x--) {
					Heapify(A, x);
				}
			}

			public void Heapify(Aresta[] A, int i) {
				int l = esquerda(i);
				int r = direita(i);
				int menor = 0;
				
				if(l < this.size && A[l].peso > A[i].peso) {
					menor = l;
				}
				else {
					menor = i;
				}
				
				if(r < this.size && A[r].peso > A[menor].peso) {
					menor = r;
				}
				
				if(menor != i) {
					Aresta temp = A[i];
					A[i] = A[menor];
					A[menor] = temp;
					Heapify(A, menor);
				}
			}
		}
		
		private static class Aresta {
			private int origem;
			private int destino;
			private int peso;
		}
		
		private static class DisjointSet {
			private int cnt;
			private int[] pai;
			private int[] sz;
			
			public DisjointSet(int vertices) {
				this.cnt = vertices;
				this.pai = new int[vertices];
				this.sz = new int[vertices];
				
				for(int x = 0; x < pai.length; x++) {
					pai[x] = x;
				}
				
				for(int x = 0; x < sz.length; x++) {
					sz[x] = 1;
				}
			}
			
			public int find(int x) {
				if(x != this.pai[x]) {
					this.pai[x] = find(this.pai[x]);
				}
				return this.pai[x];
			}		
			
			public void union(int x, int y) {
				int i = find(x);
				int j = find(y);
				
				if(this.pai[i] == this.pai[j]) {
					return;
				}
				
				if(this.sz[i] < this.sz[j]) {
					this.pai[i] = j;
					this.sz[j] = this.sz[j] + this.sz[i];
				}
				else {
					this.pai[j] = i;
					this.sz[i] = this.sz[i] + this.sz[j];
				}
				cnt -= 1;
			}
			
			public int quantConjunto() {
				return this.cnt;
			}	
		}

		private Aresta[] arestas;
		private int quantVertices;
		private int quantAresta;
		private int menorCaminho;

		public Grafo(int n, int m) {
			this.arestas = new Aresta[m];
			this.quantVertices = n;
			this.menorCaminho = 0;
			this.quantAresta = 0;
		}
		
		public void addAresta(int origem, int destino, int peso) {
			Aresta aresta = new Aresta();
			aresta.origem = origem;
			aresta.destino = destino;
			aresta.peso = peso;
			this.arestas[quantAresta] = aresta;
			quantAresta++;
		}
		
		public void ordenaArestas() {
			Heap heap = new Heap();
			heap.heapSort(this.arestas);
		}
		
		public String toString() {
			String s = "";
			for(Aresta a : this.arestas) {
				s += a.origem + "-" + a.destino + " : " + a.peso + "\n";
			}
			return s;
		}
		
		public void kruskal() {
			//ordenas as arestas em ordem crescente;
			ordenaArestas();
			//inicia o conjunto disjunto
			DisjointSet conjuntoDisjunto = new DisjointSet(quantVertices);
//			//lista com as arestas selecionadas

			
			int contadorFila = 0;
			
			while((contadorFila < this.arestas.length) && (conjuntoDisjunto.quantConjunto() != 1)) {
				Aresta a = this.arestas[contadorFila];
				
				if(conjuntoDisjunto.find(a.origem) != conjuntoDisjunto.find(a.destino)) {

					conjuntoDisjunto.union(a.origem, a.destino);
					this.menorCaminho += a.peso;
				}
				contadorFila++;
			}			
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
			
			
			Grafo grafo = new Grafo(n, m);
			
			for(int k = 0; k < m; k++) {
				entrada = input.readLine();
				String[] aresta = entrada.split(" ");

				grafo.addAresta(Integer.parseInt(aresta[0]), Integer.parseInt(aresta[1]), Integer.parseInt(aresta[2]));
			}
			
			grafo.kruskal();			
			
			output.write("" + grafo.menorCaminho);
			output.newLine();			
			
			entrada = input.readLine();
		}

		output.flush();
		output.close();
	}
}

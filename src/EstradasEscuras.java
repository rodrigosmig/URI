import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class EstradasEscuras {
	
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

			public void heapSort(ArrayList<Aresta> A) {
				buildHeap(A);
				for(int i = A.size() - 1; i > 0; i--) {
					Aresta temp = A.get(0);					
					A.set(0, A.get(i));
					A.set(i, temp);					
					this.size--;
					Heapify(A, 0);			
				}
			}

			public void buildHeap(ArrayList<Aresta> A) {
				this.size = A.size();
				for(int x = A.size() / 2 - 1; x >= 0; x--) {
					Heapify(A, x);
				}
			}

			public void Heapify(ArrayList<Aresta> A, int i) {
				int l = esquerda(i);
				int r = direita(i);
				int menor = 0;
				
				if(l < this.size && A.get(l).peso > A.get(i).peso) {
					menor = l;
				}
				else {
					menor = i;
				}
				
				if(r < this.size && A.get(r).peso > A.get(menor).peso) {
					menor = r;
				}

				if(menor != i) {
					Aresta temp = A.get(i);
					A.set(i, A.get(menor));
					A.set(menor, temp);
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

		private ArrayList<Integer> vertices;
		private ArrayList<Aresta> arestas;
		private int menorCaminho;

		public Grafo(int n, int m) {
			this.vertices = new ArrayList<Integer>(n);
			this.arestas = new ArrayList<Aresta>(m);
			this.menorCaminho = 0;

			for(int x = 0; x < n; x++) {
				vertices.add(x);
			}
		}
		
		public void addAresta(int origem, int destino, int peso) {
			Aresta aresta = new Aresta();
			aresta.origem = origem;
			aresta.destino = destino;
			aresta.peso = peso;
			this.arestas.add(aresta);
		}
		
		public void ordenaArestas() {
			Heap heap = new Heap();
			heap.heapSort(this.arestas);
		}
		
		public void kruskal() {
			//ordenas as arestas em ordem crescente;
			ordenaArestas();
			//inicia o conjunto disjunto
			DisjointSet conjuntoDisjunto = new DisjointSet(vertices.size());
			//lista com as arestas selecionadas
			LinkedList<Aresta> A = new LinkedList<Aresta>();
			
			while((this.arestas.size() != 0) && (conjuntoDisjunto.quantConjunto() != 1)) {
				Aresta a = this.arestas.remove(0);
				
				if(conjuntoDisjunto.find(a.origem) != conjuntoDisjunto.find(a.destino)) {
					A.add(a);
					conjuntoDisjunto.union(a.origem, a.destino);
					this.menorCaminho += a.peso;
				}
			}			
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String entrada = input.readLine();
		
		
		while(!entrada.equals("0 0")) {
			
			int custoTotal = 0;
			int valorEconomizado;
			
			String[] entrada2 = entrada.split(" ");
			
			int n = Integer.parseInt(entrada2[0]);
			int m = Integer.parseInt(entrada2[1]);
			
			
			Grafo grafo = new Grafo(n, m);
			
			for(int k = 0; k < m; k++) {
				entrada = input.readLine();
				String[] aresta = entrada.split(" ");

				grafo.addAresta(Integer.parseInt(aresta[0]), Integer.parseInt(aresta[1]), Integer.parseInt(aresta[2]));
				custoTotal += Integer.parseInt(aresta[2]);
			}
			grafo.kruskal();			
			
			valorEconomizado = custoTotal - grafo.menorCaminho;
			
			output.write("" + valorEconomizado);
			output.newLine();			
			
			entrada = input.readLine();
		}
		
		output.flush();
		output.close();
	}
}

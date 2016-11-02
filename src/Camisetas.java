import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Camisetas {
    private static Scanner input;
	private static LinkedList<Camiseta> listaCasos;
	private static LinkedList<Camiseta> listaFinal;	
	
	private static class Camiseta {
		private String nome;
		private String cor;
		private String tamanho;
		
		public Camiseta(String nome, String cor, String tamanho) {
			this.nome = nome;
			this.cor = cor;
			this.tamanho = tamanho;
		}

		public String getNome() {
			return nome;
		}	

		public String getCor() {
			return cor;
		}

		public String getTamanho() {
			return tamanho;
		}

		public String toString() {
			return this.cor + " " + this.tamanho + " " + this.nome;
		}
	}	
	
	private static class PedidoComparator implements Comparator<Camiseta>{
		public int compare(Camiseta ped1, Camiseta ped2) {
			int c = ped1.getCor().compareTo(ped2.getCor());
			if(c != 0) return c;

			c = ped1.getTamanho().compareTo(ped2.getTamanho());		
			if(c != 0) {
				if(c < 0) {
					return 1; 	
				}
				else {
					return -1;
				}				
			}
			return ped1.getNome().compareTo(ped2.getNome());
		}
	}

	public static void main(String[] args) {
		input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        int cont = 0;
        while(n != 0) {            
        	listaCasos = new LinkedList<Camiseta>();
            for(int x = 0; x < n; x++) {
                String nome = input.nextLine();
                String corTamanho = input.nextLine();
                String[] corTamanhoSplited = corTamanho.split(" ");
                Camiseta camiseta = new Camiseta(nome, corTamanhoSplited[0], corTamanhoSplited[1]);
                listaCasos.add(camiseta);
            }
            n = Integer.parseInt(input.nextLine());
            PedidoComparator comparador = new PedidoComparator();
	        Collections.sort(listaCasos, comparador);
	        for(Camiseta c : listaCasos) {
	        	System.out.println(c);
	        }
	        if(n != 0) {
	        	System.out.println("");
	        }
        }                
	}
}
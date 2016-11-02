import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class OndeEstaOMarmore {

	public static int[] marmoresOrdenados;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int contador = 1;
		String respostaFinal = "";
		int chave;

		String s = input.readLine();

		while(!s.equals("0 0")) {
			output.write("CASE# " + contador + ":\n");
			
			String[] s2 = s.split(" ");
			int N = Integer.parseInt(s2[0]);
			int Q = Integer.parseInt(s2[1]);

			int[] marmores = new int[N];
			int[] respostas = new int[Q];

			for(int x = 0; x < N; x++) {
				marmores[x] = Integer.parseInt(input.readLine());
			}

			marmoresOrdenados = countingSort(marmores, 10000);

			for(int x = 0; x < Q; x++) {
				respostas[x] = Integer.parseInt(input.readLine());
				chave = binarySearch(marmoresOrdenados, respostas[x]);
				if(chave != -1) {
					output.write(respostas[x] + " found at " + (chave + 1) + "\n");
				}
				else {
					output.write(respostas[x] + " not found" + "\n");
				}
			}
			s = input.readLine();
			contador++;
		}
		output.flush();
		output.close();
	}

	public static int binarySearch(int[] vetor, int chave) {
		int lo = 0;
		int hi = vetor.length - 1;
		int mid;

		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;
			if(chave < vetor[mid]) {
				hi = mid - 1;
			}
			else {
				if(chave > vetor[mid]) {
					lo = mid + 1;
				}
				else {
					int cont = mid;
					while(chave == vetor[cont]) {
						if(cont - 1 >= 0 && chave == vetor[cont - 1]) {
							cont--;
						}
						else {
							return cont;
						}						
					}
					return cont;				
				}
			}
		}
		return -1;
	}

	public static int[] countingSort(int[] A, int k) {
		int[] B = new int[A.length];
		int[] C = new int[k];

		for(int i = 0; i < k; i++) {
			C[i] = 0;
		}

		for(int j = 0; j < A.length; j++) {
			C[A[j]] += 1;
		}

		for(int i = 1; i < k; i++) {
			C[i] = C[i] + C[i -1];
		}

		for(int j = A.length - 1; j >= 0; j--) {
			B[--C[A[j]]] = A[j];
		}
		return B;
	}
}
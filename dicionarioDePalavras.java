import java.io.File;
import java.util.Scanner;

public class dicionarioDePalavras {

	public static void main(String[] args) throws Exception {
		String arquivo = ("dicionarioDoSamuel.txt");
		imprimirPalavraDoDicionario(lerArquivo(arquivo));
	}

	public static String[] lerArquivo(String nomeArquivo) throws Exception {
		File arquivo = new File(nomeArquivo);
		Scanner lerLinha = new Scanner(arquivo);

		String palavraDoAquivoTxt = "";
		int indiceDicionarioDePalavras = 0;
		String[] dicionarioDePalavras = new String[1000];

		while (lerLinha.hasNextLine()) {
			String linha = lerLinha.nextLine();
			String[] leitorLinha = linha.split("\\n|\\r|\\s");

			for (int indice = 0; indice < leitorLinha.length; indice++) {

				palavraDoAquivoTxt = leitorLinha[indice].toLowerCase();
				boolean encontrouPalavraIgual = buscaBinaria(dicionarioDePalavras, palavraDoAquivoTxt,
						indiceDicionarioDePalavras);

				if (!palavraDoAquivoTxt.equals("") && !encontrouPalavraIgual) {

					inserirPalavraDeFormaOrdenadaNoDicionario(dicionarioDePalavras, palavraDoAquivoTxt,
							indiceDicionarioDePalavras);
					indiceDicionarioDePalavras++;
				}
			}
		}
		lerLinha.close();
		return dicionarioDePalavras;
	}

	public static void inserirPalavraDeFormaOrdenadaNoDicionario(String[] dicionarioDePalavras,
			String palavraDoAquivoTxt, int indiceDicionarioDePalavras) {

		int indice = indiceDicionarioDePalavras;
		while (indice > 0 && palavraDoAquivoTxt.compareTo(dicionarioDePalavras[indice - 1]) < 0) {
			dicionarioDePalavras[indice] = dicionarioDePalavras[indice - 1];
			indice--;
		}
		dicionarioDePalavras[indice] = palavraDoAquivoTxt;

	}

	public static boolean buscaBinaria(String[] dicionarioDePalavras, String palavraDoAquivoTxt,
			int indiceDicionarioDePalavras) {

		int inicio = 0;
		int fim = indiceDicionarioDePalavras - 1;
		int meio = 0;

		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			if (dicionarioDePalavras[meio] != null && dicionarioDePalavras[meio].compareTo(palavraDoAquivoTxt) == 0) {
				return true;
			}

			if (dicionarioDePalavras[meio] != null && dicionarioDePalavras[meio].compareTo(palavraDoAquivoTxt) > 0) {
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}
		return false;
	}

	public static void imprimirPalavraDoDicionario(String[] dicionarioDePalavras) {
		int totalDePalavrasDiferentes = 0;
		for (int indice = 0; indice < dicionarioDePalavras.length; indice++) {
			if (dicionarioDePalavras[indice] != null) {
				totalDePalavrasDiferentes++;
				System.out.println(dicionarioDePalavras[indice]);
			}
		}
		System.out.println("total de palavras diferentes no dicionario = " + totalDePalavrasDiferentes);
	}

}

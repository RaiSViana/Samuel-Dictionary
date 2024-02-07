package com.mycompany.diciosamuel;
/*
Entrega do Trabalho 1- Algoritmos e Programação II
Nós,
Raí Fernando Santos Viana
Guilherme Santos Pereira
Victor Levenetz Mariano
declaramos que
todas as respostas são fruto de nosso próprio trabalho,
não copiamos respostas de colegas externos à equipe,
não disponibilizamos nossas respostas para colegas externos ao grupo e
não realizamos quaisquer outras atividades desonestas para nos beneficiar ou
prejudicar outros.
*/
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Rai
 */
public class DicioSamuel {

	//Criação do vetor com 1000 posições
	public static String dicioSamuel[] = new String[1000];
	
	//Contabilização das palavras
	public static int totalPalavras = 0;

	public static void main(String[] args) throws Exception {
		
		lerArquivo("Arquivo.txt");
		
		for (int i = 0; i < totalPalavras; i++) {
			System.out.println(dicioSamuel[i]);
		}
		System.out.println("total de palavras diferentes no dicionario = " + totalPalavras);

	}

	// Função responsavel pela leitura do arquivo.
	public static void lerArquivo(String Arquivo) throws Exception {

                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Rai\\Documents\\NetBeansProjects\\Samuel-Dictionary\\DicioSamuel\\src\\main\\java\\com\\mycompany\\diciosamuel\\Arquivo.txt"));

		boolean cond = true;	//Variavél para controle do total de palavras
		String linha; 			//Variavél para receber cada palavra de uma frase
		String frase[];			//Variavél para receber as frases de cada linha do documento

		//leitura das linhas ate que seja retornado null;
		while (cond) {
			linha = br.readLine();
			if (linha == null) {
				break;
			}

			frase = linha.toLowerCase().split(" ");

			//Ao armazenar cada palavra da frase de cada linha, se inicia a verificação de cada palavra armazenada no vetor
			for (int i = 0; i < frase.length; i++) {
				//O método split() cria palavras vazias então foi incluída essa verificação
				if (!frase[i].isEmpty()) {					
					//Inclui a palavra caso não exista no dicionario
					if (totalPalavras == 0) {
						dicioSamuel[totalPalavras] = frase[i];
						totalPalavras++;
					} else {						
						//Verifica se o total de palavras já foi preenchido
						if (totalPalavras == dicioSamuel.length) {
							cond = false;
							break;
						}						
						//Verificação se a palavra já tem no dicionário, através da busca binária
						if (!verDicio(frase[i])) {							
							//incluisao caso não tenha. 
							addDicio(dicioSamuel, frase[i]);
						}
					}

				}
			}
		}
		br.close();
	}

	//Função de busca binária para verificar se a palavra já consta no dicionário
	public static boolean verDicio(String palavra) {

		int inicio = 0;		
		int meio;
                int fim = totalPalavras - 1;
		
		//Manipulação dos setores do vetor para encontrar se o elemento está ou não dentro do vetor
		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			
                        //Uso do metodo compareTo para verificar se a String e maior ou menor que a posição do "meio"			
			if (palavra.compareTo(dicioSamuel[meio]) < 0) {
				fim = meio - 1;
			}
			else if (palavra.compareTo(dicioSamuel[meio]) > 0) {
				inicio = meio + 1;
			} else {
				return true;
			}
		}
		return false;
	}

	//Função para fazer a inserção da nova palavra de forma ordenada
	public static void addDicio(String[] v, String frase) {

		//Inserção da palavra na última posição
		dicioSamuel[totalPalavras] = frase;
			//Reacolocação da palavra na posição ordenada
			int j = totalPalavras;
			String x = v[j];
			while (j > 0 && x.compareTo(v[j - 1]) < 0) {
				v[j] = v[j - 1];
				j--;
			}
			v[j] = x;
		
		totalPalavras++;
	}
}

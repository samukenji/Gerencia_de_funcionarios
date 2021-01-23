package funcao_principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Samuel Kenji Nagao

/* DESCRI��O:
 * 1�: Lendo dados de funcion�rios (Nome, email e sal�rio) a partir de um arquivo e imprimindo uma lista de funcion�rios .txt 
 * 2�: Mostrando os e-mails em ordem alfab�tica dos funcion�rios cujo sal�rio � superior a um valor fornecido pelo usu�rio
 * 3�: Mostrando a soma dos sal�rios dos funcion�rios cujo nome come�a com a letra S.*/

/* EXEMPLO DE ARQUIVO:
Samuel,samuelnagao@hotmail.com,3000.00
Sabrina,sabrinavieira512@gmail.com,1500.00
Leonardo,leonardo.lima@hotmail.com,1100.00
Bruna,brunalmf19@gmail.com,1300.00
 */

public class Principal {

	public static void main(String[] args) throws ParseException {
		
		List<Funcionario> lista= new ArrayList<>();
		
		Scanner entrar=new Scanner(System.in);
		
		String s= "C:\\Users\\ceian\\Documents\\programas java\\arq para stream.txt";
		
		//Lendo o arquivo usando try-with-resources...
		try(BufferedReader br = new BufferedReader(new FileReader(s)))
		{
			String linha= br.readLine();
			
			while(linha != null)
				{
					String[] vetor= linha.split(",");
					//Cada funcion�rio ser� adicionado � lista
					lista.add(new Funcionario(vetor[0], vetor[1], Double.valueOf(vetor[2])));
					linha= br.readLine();
				}
			System.out.println("LISTA DE FUNCION�RIOS:");
			
			//Chamada de fun��o...
			mostralista(lista);		
		}
		
		//Tratamento de excess�o
		catch(IOException excessao)
		{
			System.out.println(excessao.getMessage());
		}
		
		//Entrada de dado...
		System.out.print("\nDigite um valor de sal�rio: ");
		Double valor= entrar.nextDouble();
		
		/*
		 -Cria��o de Stream a partir da lista
		 - Uso da opera��o intermedi�ria filter() com express�o lambda, para filtrar somente os funcion�rios com o sal�rio superior ao fornecido.
		 - Uso do map() para percorrer a Stream e chamar a express�o lambda para cada elemento da mesma.
		 - Uso da opera��o terminal collect() para criar uma nova lista a partir da Stream.
		 */
		List<String> emails= lista.stream().filter(x -> x.getSalario() > valor).map(	 f1 -> f1.getEmail()).sorted().collect(Collectors.toList());
		
		System.out.println("\nLISTA DE EMAILS ESPEC�FICOS: ");		
		mostralista(emails);
		
		/*
		- Criando Stream a partir da lista.
		- Filtrando os funcion�rios cujo nome come�a com a letra S
		- Percorrendo a Stream com uma express�o lambda, que recebe um funcion�rio e retorna apenas o seu nome.
		- Usando o m�todo reduce() para somar os elementos da Stream.
		*/
		Double somadesalarios= lista.stream().filter(x -> x.getNome().charAt(0) == 'S').map( f2 -> f2.getSalario()).reduce(0.0, (x, y) ->  x+y	);
		System.out.println("\nSOMA DE SAL�RIOS DOS FUNCION�RIOS QUE COME�AM COM A LETRA S= " + somadesalarios);
		
		entrar.close();
	}
	
	//M�todo respons�vel por imprimir uma lista de objetos
	public static void mostralista(List<? extends Object> lista)
	{
		for(Object f : lista)
		{
			System.out.println(f);
		}
	}
}

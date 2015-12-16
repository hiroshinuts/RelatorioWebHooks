package br.com.hiroshinuts.contador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContaUrl {
	/*
	 * Classe ContaUrl - recebe como entrada um arquivo texto, identifica as
	 * diferentes palavras e contabiliza quantas vezes elas aparecem.
	 */
	public static void main(String[] args) throws Exception {

		// (0) Declaração das Variaveis

		String curLine;
		Map<String, Integer> mapPalavras;
		mapPalavras = new HashMap<String, Integer>();

		// (1) Abre o arquivo texto

		FileReader txtFile = new FileReader("/home/hiro/Desktop/Teste/log");// Colocar
																			// o
																			// local
																			// do
																			// arquivo
		BufferedReader txtBuffer = new BufferedReader(txtFile);

		// (2) Lê todas as linhas do arquivo ,

		curLine = txtBuffer.readLine();

		while (curLine != null) {

			String minusculo = curLine.toLowerCase(); //deixa todas as palavras em minusculo
			Pattern p = Pattern
					.compile("((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)");
			Matcher m = p.matcher(minusculo);

			while (m.find()) {
				String token = m.group();
				Integer freq = mapPalavras.get(token);

				if (freq != null) {
					mapPalavras.put(token, freq + 1);
				} else {
					mapPalavras.put(token, 1);
				}
			}
			curLine = txtBuffer.readLine();
		}

		txtBuffer.close();
		for (Map.Entry<String, Integer> entry : mapPalavras.entrySet()) {
			System.out.println(entry.getKey() + "\tfreq=" + entry.getValue());
		}
	}
}

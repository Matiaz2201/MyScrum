package com.myscrum.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 
public class Read {
 
	public String reading(){
		Sessao sessao = new Sessao(); 
	    String linha = null; 
	    sessao.getUsuario();
 
    try {
      FileReader arq = new FileReader("user.home");
      BufferedReader lerArq = new BufferedReader(arq);
      
      linha = lerArq.readLine(); // l� a primeira linha
// a vari�vel "linha" recebe o valor "null" quando o processo

      //System.getProperty("java.io.tmpdir");
      
      
      arq.close();
    } catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo",
          e.getMessage());
    }
 
    System.out.println();
	return linha;
  }
}
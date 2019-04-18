package com.myscrum.model;

import java.io.File;
import java.io.IOException;

public class Salvar
{
   
    
    	public void write(){
    	try{
    		
    	   //create a temp file
    	   File temp = File.createTempFile("user", ".tmp"); 
    		
    	   System.out.println("Temp file : " + temp.getAbsolutePath());
    		
    	}catch(IOException e){
    		
    	   e.printStackTrace();
    		
    	}
    }
}
     
    

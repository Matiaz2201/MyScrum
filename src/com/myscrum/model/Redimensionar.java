package com.myscrum.model;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;



public class Redimensionar {

	public static ImageIcon maximizar(String imagePath){

		Toolkit toolkit = Toolkit.getDefaultToolkit();//criando variavel para pegar medidas da tela
        Dimension scrnsize = toolkit.getScreenSize();//recebendo medidas da tela em uma variavel Dimension
		int x = (int)scrnsize.getWidth();
		int y = (int)scrnsize.getHeight();
        
        
		//Carrega a imagem 
		ImageIcon img = new ImageIcon(Redimensionar.class.getResource(imagePath));

		//Redimenciona a imagem usando as dimensões maxima 
		Image newImg=img.getImage().getScaledInstance(x, y-50, Image.SCALE_DEFAULT);

		//Gera uma nova Imagem
		ImageIcon img_ = new ImageIcon(newImg);

		return img_;
		
	}

	public static ImageIcon redimensionar(String imagePath, Dimension d,int scale){

		//Carrega a imagem 
		ImageIcon img = new ImageIcon(imagePath);

		//Redimenciona a imagem usando as dimensões passadas como parâmetro e o respectivo algoritmo
		Image newImg=img.getImage().getScaledInstance(d.width, d.height, scale);

		//Gera uma nova Imagem
		ImageIcon img_ = new ImageIcon(newImg);

		return img_;


	}

}

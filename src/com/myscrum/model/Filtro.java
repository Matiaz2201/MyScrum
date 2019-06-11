package com.myscrum.model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

class Filtro extends FileFilter {
	 public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        }
	    String filename = f.getName();
       if (filename.endsWith(".xls")) {
	               return true;
	            } else {
	               return false;
	            }
	        }
	 public String getDescription() {
		      return ".xls";
		    }
	    }

package com.wip.sample;

import java.io.FileWriter;
import java.io.IOException;

public class MyRunnable implements Runnable {
	private final String line;
	private final String path;
	private final String fileName;
	FileWriter fileWriter ; 

	MyRunnable(String path,String line,String fileName) throws IOException {
		this.line = line;
		this.fileName=fileName;
		this.path=path;
			}

	@Override
	public void run() {
			try {
		    	synchronized (line) {
	    		String newLine = System.getProperty("line.separator");
	    		fileWriter =new FileWriter(path+fileName,true);
		    	fileWriter.write(line+newLine);
		    	fileWriter.close();
				}
	   	    	
			} catch (IOException e) {
				 e.printStackTrace();
				 
			}
	}
	
}

package com.wip.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcommandApplication implements CommandLineRunner {

	 private static final Logger logger = LoggerFactory.getLogger(SpringcommandApplication.class);

	 private static final String PATH="c:/Rajani/";
	 
	 
	 public static void main(String[] args) {
		 SpringApplication.run(SpringcommandApplication.class, args).close();
		 }
	 
	@Override
	public void run(String... args) throws Exception {
		  if (args.length >1) {
		String fileName=args[0];
		int MYTHREADS=Integer.parseInt(args[1]);
		String fileOutPath= "test.txt";
		File file = new File(PATH+fileOutPath);
		File readFile = new File(PATH+fileName);
		//Existing file delete
		  if (file.exists())
			  file.delete();
 	   	//Read file check
		  if (!readFile.exists()) {
			  logger.info("File Missing:" );
		  }
		  else {
		    		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
		    		BufferedReader bufferreader = new BufferedReader(new FileReader(PATH+fileName));
		    		while (bufferreader.ready())
		    		{ 
		    		Runnable worker = new MyRunnable(PATH,encrypt(bufferreader.readLine(),4),fileOutPath);
		    		executor.execute(worker);
		    		}
		    		bufferreader.close();
		    		executor.shutdown();
		  }	
		  }
	}



	public static String encrypt(String str, int shift) {
		StringBuffer result = new StringBuffer();
		if (str != null) {
		str = str.toLowerCase();
		char ch1[] = str.toCharArray();
		char ch3;

		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(ch1[i])) {
				ch3 = (char) (((int) ch1[i] + shift - 97) % 26 + 97);
				result.append(ch3);
			} else if (ch1[i] == ' ') {
				result.append(ch1[i]);
			}
		}
		}
		return result.toString();

	}

	public static String decrypt(String str, int shift) {
		StringBuffer result = new StringBuffer();
		if (str != null) {
		char ch2[] = str.toCharArray();
		char ch4;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(ch2[i])) {
				if (((int) ch2[i] - shift) < 97) {
					ch4 = (char) (((int) ch2[i] - shift - 97 + 26) % 26 + 97);

				} else {
					ch4 = (char) (((int) ch2[i] - shift - 97) % 26 + 97);
				}
				result.append(ch4);
			}

			else if (ch2[i] == ' ') {
				result.append(ch2[i]);
			}
		}
		}
		return result.toString();

	}
	
	public void readWriteToEncryptFile(String readFileName,String writeFileName) throws IOException {
		BufferedReader bufferreader =null;
		FileWriter fileWriter = null;
		try {
	    	bufferreader = new BufferedReader(new FileReader(PATH+readFileName));  
	    	fileWriter = new FileWriter((PATH+writeFileName));
	    	String newLine = System.getProperty("line.separator");
	    	    while(bufferreader.ready()) {
	    	    	fileWriter.write(encrypt(bufferreader.readLine(),4)+newLine);
	    	    }
	    	       fileWriter.close();
	    	       bufferreader.close();
	    	} catch (FileNotFoundException e) {
	    		 if (fileWriter!=null ||bufferreader!=null) { 
	    	       fileWriter.close();
	    	       bufferreader.close();
	    		 }
	    	} catch (IOException e) {
	    		if (fileWriter!=null ||bufferreader!=null) {
	    	       fileWriter.close();
	    	       bufferreader.close();
	    		}
	    	}
	}
		public void readWriteToDecryptFile(String readFileName,String writeFileName) throws IOException {
			BufferedReader bufferreader =null;
			FileWriter fileWriter = null;
			try {
		    	bufferreader = new BufferedReader(new FileReader(PATH+readFileName));  
		    	fileWriter = new FileWriter((PATH+writeFileName));
		    	String newLine = System.getProperty("line.separator");
		    	    while(bufferreader.ready()) {
		    	    	fileWriter.write(decrypt(bufferreader.readLine(),4)+newLine);
		    	    }
		    	       fileWriter.close();
		    	       bufferreader.close();
		    	} catch (FileNotFoundException e) {
		    		 if (fileWriter!=null ||bufferreader!=null) { 
		    	       fileWriter.close();
		    	       bufferreader.close();
		    		 }
		    	} catch (IOException e) {
		    		if (fileWriter!=null ||bufferreader!=null) {
		    	       fileWriter.close();
		    	       bufferreader.close();
		    		}
		    	}


	}


}
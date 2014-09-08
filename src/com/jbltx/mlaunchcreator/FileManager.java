package com.jbltx.mlaunchcreator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileManager {
	
	
	//UNZIP FILE METHOD
	public static void unZipIt(String inputZipFile, String outputFolder) {
		try
		{
			File folder = new File(MainConstants.OUTPUT_FOLDER);
			if(!folder.exists()){
				folder.mkdir();
			}
			else
			{
				deleteDir(folder);
				folder.mkdir();
			}
			int BUFFER = 2048;
			File file = new File(inputZipFile);
			ZipFile zip = new ZipFile(file);
			String newPath = inputZipFile.substring(0, inputZipFile.length() - 4);
			
			new File(newPath).mkdir();
			Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();
			
			
			while (zipFileEntries.hasMoreElements()){
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
		        String currentEntry = entry.getName();
		        File destFile = new File(newPath, currentEntry);
		        
		        File destinationParent = destFile.getParentFile();
		        
		        destinationParent.mkdirs();
		        
		        if (!entry.isDirectory()){
		        	BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
		        	int currentByte;
		        	byte data[] = new byte[BUFFER];
		        	FileOutputStream fos = new FileOutputStream(destFile);
		        	BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
		        	
		        	while ((currentByte = is.read(data, 0, BUFFER)) != -1){
		        		dest.write(data, 0, currentByte);
		        	}
		        	dest.flush();
		            dest.close();
		            is.close();
		        }
			}
			zip.close();
			System.out.println("Décompression finished."); //END DECOMPRESSION
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	//=================================================================================================//
	
	
	//DELETE FILES AND DIRS RECURSIVELY
	public static void deleteDir(File file){
		if(file.isDirectory()){
			if(file.list().length==0){
				file.delete();
			}
			else{
				String files[] = file.list();
				for (String temp : files){
					File fileDelete = new File(file, temp);
					deleteDir(fileDelete);
				}
				if(file.list().length==0){
					file.delete();
				}
			}
		}
		else{
			file.delete();
		}
	}
	
	//=================================================================================================//
	
	
	//COPY FILES AND DIRS RECURSIVELY
	public static void copyFile(File source, File destination) throws IOException{
		
		
		if (source.isDirectory()){
			if (!destination.exists()){
				destination.mkdir();
			}

			String[] children = source.list();
			for (int i=0; i<children.length; i++){
				copyFile(new File(source, children[i]), new File(destination, children[i]));
			}
		
		}else{
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination);
			
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0){
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
		
	}

	//=================================================================================================//
	
	
	//COMPILE JAR FILES USING MAVEN
	static void compileJarFile(String projectFolder){
		if(OS.OS.indexOf("win") >= 0){
			System.out.println("Begining Windows process...");
			if(!System.getenv("JAVA_HOME").contains("jdk")){
				System.out.println("You don't have JAVA_HOME environment variable set on your machine, please install jdk 1.7 and set JAVA_HOME environment variable to your jdk installation dir.");
			}
			try{
				Runtime rt = Runtime.getRuntime();
				Process pr = rt.exec(MainConstants.MAVEN_INSTALL_DIR + "mvn.bat -f " + MainConstants.OUTPUT_FOLDER + "/" + projectFolder + "/pom.xml clean package");
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line=null;
				while((line=input.readLine()) != null) {
	                    System.out.println(line);
	            }
				int exitVal = pr.waitFor();
				System.out.println("Exited with error code "+exitVal);
			} catch(Exception e){
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		else if(OS.OS.indexOf("mac") >= 0 || OS.OS.indexOf("sunos") >= 0 || OS.OS.indexOf("nix") >= 0 || OS.OS.indexOf("nux") >= 0 || OS.OS.indexOf("aix") > 0){
			if(!System.getenv("JAVA_HOME").contains("java")){
				System.out.println("You don't have JAVA_HOME environment variable set on your machine, please install jdk 1.7 and set JAVA_HOME environment variable to your jdk installation dir.");
			}
			System.out.println("Begining Unix process...");
			try{
				Runtime rt = Runtime.getRuntime();
				Process pr = rt.exec(MainConstants.MAVEN_INSTALL_DIR + "mvn -f " + MainConstants.OUTPUT_FOLDER + "/" + projectFolder + "/pom.xml clean package");
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line=null;
				while((line=input.readLine()) != null) {
	                    System.out.println(line);
	            }
				int exitVal = pr.waitFor();
				System.out.println("Exited with error code "+exitVal);
			} catch(Exception e){
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
	}
	
	//=================================================================================================//
	
	
}

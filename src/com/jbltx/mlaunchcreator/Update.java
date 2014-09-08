package com.jbltx.mlaunchcreator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;

public class Update {

	public static boolean updateTags() throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(MainConstants.INPUT_ZIP_FILE);
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1){
			md.update(dataBytes, 0, nread);
		};
		byte[] mdbytes = md.digest();
		fis.close();
		
		BigInteger bigInt = new BigInteger(1, mdbytes);
		String output = bigInt.toString(16);
		String md5_local = output;
		System.out.println("Local Hash : " + md5_local);
		
		
		StringBuilder contentBuilder = new StringBuilder();
		URL md5ExtFile = new URL(MainConstants.MD5_UPDATE);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(md5ExtFile.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
        	contentBuilder.append(inputLine);
        }
        in.close();
        
        String md5_distant = contentBuilder.toString().toLowerCase();
        
        System.out.println("Remote Hash : " + md5_distant);
        
        if(md5_local.equals(md5_distant)){
        	return false;
        }
        else{
        	return true;
        }

	}
	
}

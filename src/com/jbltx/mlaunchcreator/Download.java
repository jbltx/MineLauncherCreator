package com.jbltx.mlaunchcreator;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {

	public static void downloadIt() {
		URL website;
		try {
			website = new URL(MainConstants.SOURCES_URL);
			
			try{
				InputStream in = new BufferedInputStream(website.openStream());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int n = 0;
				while (-1!=(n=in.read(buf))){
					out.write(buf, 0, n);
				}
				out.close();
				in.close();
				byte[] response = out.toByteArray();
				FileOutputStream fos = new FileOutputStream(MainConstants.INPUT_ZIP_FILE);
				fos.write(response);
				fos.close();
				System.out.println("Download finished.");
			}catch(IOException ce){
				System.out.print("Connection problem : " + ce);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}

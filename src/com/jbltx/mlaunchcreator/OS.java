package com.jbltx.mlaunchcreator;

public class OS {
	public static String OS = System.getProperty("os.name").toLowerCase();
	
	
	public static String checkOS(){
		if (isWindows()){
			return System.getenv("APPDATA");
		}else if (isMac()){
			return System.getProperty("user.home") + "/Library/Application ";
		}else if(isUnix()){
			return System.getProperty("user.home");
		}else if (isSolaris()){
			return System.getProperty("user.dir");
		}
		else{
			return System.getProperty("user.dir");
		}
	}
	
	
	public static boolean isWindows() {
		 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OS.indexOf("sunos") >= 0);
 
	}
	
	
}

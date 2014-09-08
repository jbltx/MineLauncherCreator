package com.jbltx.mlaunchcreator;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Forge {

	static String extractedJarFile;
	static String code_version;
	static String game_version_name;
	static String game_version;

	public static void acquireForgeVersion(String game_vers, String game_vers_name, String code_vers) {

		code_version = code_vers;
		game_version_name = game_vers_name;
		game_version = game_vers;

		File versDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/versions");
		if (!versDir.exists()) {
			versDir.mkdir();
			System.out.println("Versions dir created.");
		}
		File versDefDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/versions/" + game_version + "-" + game_version_name);
		if (!versDefDir.exists()) {
			versDefDir.mkdir();
			System.out.println("Version " + game_version + " dir created.");
		}
		File mineForgeDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge");
		if (!mineForgeDir.exists()) {
			mineForgeDir.mkdir();
			System.out.println("Minecraftforge dir created.");
		}
		if (game_version.contains("1.6.")) {
			System.out.println("Managing 1.6.X game version...");
			File mineForge2Dir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge/minecraftforge");
			if (!mineForge2Dir.exists()) {
				mineForge2Dir.mkdir();
			}
			File mavenDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge/minecraftforge/" + code_version);
			if (!mavenDir.exists()) {
				mavenDir.mkdir();
			}

			downloadVersion(game_version, game_version_name, code_version);
			UnzipJar(MainConstants.OUTPUT_FOLDER + "/forge.jar");
		} else {
			System.out.println("Managing 1.7.X game version...");
			File mineForge2Dir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge/forge");
			if (!mineForge2Dir.exists()) {
				mineForge2Dir.mkdir();
			}
			File mavenDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge/forge/" + game_version + "-" + code_version);
			if (!mavenDir.exists()) {
				mavenDir.mkdir();
			}

			downloadVersion(game_version, game_version_name, code_version);
			UnzipJar(MainConstants.OUTPUT_FOLDER + "/forge.jar");
		}

	}

	private static void UnzipJar(String jarFile) {
		System.out.println("Unziping " + jarFile);
		try {
			@SuppressWarnings("resource")
			JarFile jar = new JarFile(jarFile);

			Enumeration<JarEntry> enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
				JarEntry file = (JarEntry) enumEntries.nextElement();

				if (file.getName().contains(".jar")) {
					if (game_version.contains("1.6.")) {
						extractedJarFile = MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge/minecraftforge/" + code_version + "/minecraftforge-" + code_version + ".jar";
					} else {
						extractedJarFile = MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/maven/net/minecraftforge/forge/" + game_version + "-" + code_version + "/forge-" + game_version + "-" + code_version + ".jar";
					}
					File f = new File(extractedJarFile);
					InputStream is = jar.getInputStream(file);
					FileOutputStream fos = new FileOutputStream(f);
					while (is.available() > 0) {
						fos.write(is.read());
					}
					fos.close();
					is.close();
					UnzipJson(extractedJarFile);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void UnzipJson(String extractedJarFile2) {
		System.out.println("Unziping JSON file " + extractedJarFile2);
		try {
			@SuppressWarnings("resource")
			JarFile jar = new JarFile(extractedJarFile2);
			Enumeration<JarEntry> enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
				JarEntry file = (JarEntry) enumEntries.nextElement();
				if (file.getName().contains(".json")) {
					String outputJsonVersion = MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/versions/" + game_version + "-" + game_version_name + "/" + game_version + "-" + game_version_name + ".json";
					File f = new File(outputJsonVersion);
					InputStream is = jar.getInputStream(file);
					FileOutputStream fos = new FileOutputStream(f);
					while (is.available() > 0) {
						fos.write(is.read());
					}
					fos.close();
					is.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void downloadVersion(String game_version, String game_version_name, String code_version) {
		System.out.println("Downloading Forge version at Forge website...");
		URL forgeWebsite;
		try {
			forgeWebsite = new URL(MainConstants.FORGE_RESSOURCES_URL + game_version + "-" + code_version + "/forge-" + game_version + "-" + code_version + "-installer.jar");

			try {
				InputStream in = new BufferedInputStream(forgeWebsite.openStream());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int n = 0;
				while (-1 != (n = in.read(buf))) {
					out.write(buf, 0, n);
				}
				out.close();
				in.close();
				byte[] response = out.toByteArray();
				FileOutputStream fos = new FileOutputStream(MainConstants.OUTPUT_FOLDER + "/forge.jar");
				fos.write(response);
				fos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}

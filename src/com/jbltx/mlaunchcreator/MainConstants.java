package com.jbltx.mlaunchcreator;

import java.io.File;

public class MainConstants {

	public static final String APP_NAME = "MineLauncherCreator";
	public static final String APP_VERSION = "1.0.0";
	public static final String WORKING_DIR = OS.checkOS() + "/" + APP_NAME;
	public static final String INPUT_ZIP_FILE = WORKING_DIR + "/sources.zip";
	public static final String OUTPUT_FOLDER = WORKING_DIR + "/sources";
	public static final String SERVER_DIR = OUTPUT_FOLDER + "/SERVER";
	public static final String MAVEN_INSTALL_DIR = OUTPUT_FOLDER + "/apache-maven-3.2.2/bin/";
	public static final String MD5_UPDATE = "https://www.dropbox.com/s/w5wr16cc72kudad/sources.txt?dl=1";
	public static final String SOURCES_URL = "https://www.dropbox.com/s/9r0y9qpad4p0ptv/sources.zip?dl=1";
	public static final String FORGE_RESSOURCES_URL = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/";

	public static final File BOOTSTRAP_SOURCE_JAR = new File(OUTPUT_FOLDER + "/minecraft-bootstrap/target/minecraft-bootstrap-1.0.1-jar-with-dependencies.jar");
	public static final File LAUNCHER_SOURCE_JAR = new File(OUTPUT_FOLDER + "/minecraft-launcher/target/minecraft-launcher-1.0.1-jar-with-dependencies.jar");
	public static final File BOOTSTRAP_DEST_JAR = new File(SERVER_DIR + "/launcher/bootstrap.jar");
	public static final File LAUNCHER_DEST_JAR = new File(SERVER_DIR + "/launcher/launcher.jar");

	public static final File VERSIONS_SOURCE_JSON = new File(OUTPUT_FOLDER + "/versions/versions.json");
	public static final File VERSIONS_DEST_JSON = new File(SERVER_DIR + "/launcher/versions/versions.json");
}

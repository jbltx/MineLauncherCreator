package com.jbltx.mlaunchcreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Proc {

	private static String GAME_VERSION;
	private static String VERSION_NAME;

	public static void editJavaFiles(String applicationNameInput, String defaultProfileInput, String uRLBaseNameInput, boolean useModInput, String uRL_REGISTER_Input, String uRL_BLOG_Input, boolean uSE_PERSONNAL_AUTH_Input,
			String PERSONNAL_AUTH_URL_Input, String gameVersion_Input, String gameVersionName_Input) {

		try {
			Path path = Paths.get(MainConstants.OUTPUT_FOLDER + "/minecraft-bootstrap/src/main/java/net/minecraft/bootstrap/BootstrapConstants.java");
			Charset charset = StandardCharsets.UTF_8;

			if (uRLBaseNameInput != "") {
				if (!uRLBaseNameInput.endsWith("/")) {
					uRLBaseNameInput += "/";
				}
				String contentUrlBase = new String(Files.readAllBytes(path), charset);
				contentUrlBase = contentUrlBase.replaceAll("http://localhost/customcraft/", uRLBaseNameInput);
				Files.write(path, contentUrlBase.getBytes(charset));

				System.out.println("Serveur URL updated to " + uRLBaseNameInput);
			}

			if (applicationNameInput != "") {
				String contentAppName = new String(Files.readAllBytes(path), charset);
				contentAppName = contentAppName.replaceAll("\"customcraft\"", "\"" + applicationNameInput + "\"");
				Files.write(path, contentAppName.getBytes(charset));

				System.out.println("Application Name updated in bootstrap.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Path path2 = Paths.get(MainConstants.OUTPUT_FOLDER + "/minecraft-launcher/src/main/java/net/minecraft/launcher/LauncherConstants.java");
			Charset charset2 = StandardCharsets.UTF_8;

			if (!applicationNameInput.isEmpty()) {
				String contentAppName2 = new String(Files.readAllBytes(path2), charset2);
				contentAppName2 = contentAppName2.replaceAll("\"customcraft\"", "\"" + applicationNameInput + "\"");
				Files.write(path2, contentAppName2.getBytes(charset2));

				System.out.println("Application Name updated in launcher.");
			}

			if (uRLBaseNameInput.contains("http://")) {
				if (!uRLBaseNameInput.endsWith("/")) {
					uRLBaseNameInput += "/";
				}
				String contentUrlBase2 = new String(Files.readAllBytes(path2), charset2);
				contentUrlBase2 = contentUrlBase2.replaceAll("http://localhost/customcraft/", uRLBaseNameInput);
				Files.write(path2, contentUrlBase2.getBytes(charset2));
			}

			if (defaultProfileInput != null) {
				String contentProfile = new String(Files.readAllBytes(path2), charset2);
				contentProfile = contentProfile.replaceAll("DEFAULT_PROFILE_NAME = \"customcraft", "DEFAULT_PROFILE_NAME = \"" + defaultProfileInput);
				Files.write(path2, contentProfile.getBytes(charset2));
			}

			if (useModInput == true) {
				String contentUseMod = new String(Files.readAllBytes(path2), charset2);
				contentUseMod = contentUseMod.replaceAll("false;", "true;");
				Files.write(path2, contentUseMod.getBytes(charset2));

				System.out.println("Mods enabled.");
			}

			if (uRL_REGISTER_Input.contains("http://")) {
				String contentRegister = new String(Files.readAllBytes(path2), charset2);
				contentRegister = contentRegister.replaceAll("https://account.mojang.com/register", uRL_REGISTER_Input);
				Files.write(path2, contentRegister.getBytes(charset2));

				System.out.println("Register link updated.");
			}

			if (uRL_BLOG_Input.contains("http://")) {
				String contentBlog = new String(Files.readAllBytes(path2), charset2);
				contentBlog = contentBlog.replaceAll("http://mcupdate.tumblr.com", uRL_BLOG_Input);
				Files.write(path2, contentBlog.getBytes(charset2));

				System.out.println("Blog link updated.");
			}

			if (PERSONNAL_AUTH_URL_Input.contains("https://")) {
				if (!PERSONNAL_AUTH_URL_Input.endsWith("/")) {
					PERSONNAL_AUTH_URL_Input += "/";
				}
				String contentAuth = new String(Files.readAllBytes(path2), charset2);
				contentAuth = contentAuth.replaceAll("http://localhost/remote/", PERSONNAL_AUTH_URL_Input);
				Files.write(path2, contentAuth.getBytes(charset2));

				System.out.println("Personnal Authentication Server URL updated.");
			}

			if (uSE_PERSONNAL_AUTH_Input != true) {
				String contentAuth = new String(Files.readAllBytes(path2), charset2);
				contentAuth = contentAuth.replaceAll("AUTH = true", "AUTH = false");
				Files.write(path2, contentAuth.getBytes(charset2));

				System.out.println("Personnal Authentication Server disabled.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		GAME_VERSION = gameVersion_Input;
		VERSION_NAME = gameVersionName_Input;

		if (!VERSION_NAME.toLowerCase().contains("forge")) {
			System.out.println("Legacy Version Chosen.");
			try {
				Path path3 = Paths.get(MainConstants.OUTPUT_FOLDER + "/versions/" + GAME_VERSION + "/" + GAME_VERSION + ".json");
				if (!applicationNameInput.isEmpty()) {
					String contentVersionID = new String(Files.readAllBytes(path3), StandardCharsets.UTF_8);
					contentVersionID = contentVersionID.replaceAll("id\": \"" + GAME_VERSION, "id\": \"" + GAME_VERSION + "-" + VERSION_NAME);
					Files.write(path3, contentVersionID.getBytes(StandardCharsets.UTF_8));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Forge Version Chosen.");
			String codeVersion = VERSION_NAME.substring(5);
			Forge.acquireForgeVersion(GAME_VERSION, VERSION_NAME, codeVersion);

			if (!uRLBaseNameInput.isEmpty()) {
				try {
					Path path4 = Paths.get(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/versions/" + GAME_VERSION + "-" + VERSION_NAME + "/" + GAME_VERSION + "-" + VERSION_NAME + ".json");
					String contentForgeVersion = new String(Files.readAllBytes(path4), StandardCharsets.UTF_8);
					String contentForgeVersionURL = contentForgeVersion.replaceAll("http://files.minecraftforge.net/maven/", uRLBaseNameInput + "launcher/maven/");
					Files.write(path4, contentForgeVersionURL.getBytes(StandardCharsets.UTF_8));

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		createVersionJson(gameVersion_Input, gameVersionName_Input);

		moveRessourcesByVersion(gameVersion_Input, gameVersionName_Input);

	}

	private static void moveRessourcesByVersion(String gameVersion_Input, String gameVersionName_Input) {
		File ressourcesSrcDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/ressources/version");
		File ressourcesDestDir = new File(MainConstants.OUTPUT_FOLDER + "/SERVER/ressources/" + gameVersion_Input + "-" + gameVersionName_Input);
		ressourcesSrcDir.renameTo(ressourcesDestDir);
	}

	@SuppressWarnings("unchecked")
	private static void createVersionJson(String version, String versionName) {
		JSONObject jsonData = new JSONObject();
		JSONObject versionArray_obj = new JSONObject();
		JSONObject latestVersion_obj = new JSONObject();
		latestVersion_obj.put("snapshot", version + "-" + versionName);
		latestVersion_obj.put("release", version + "-" + versionName);
		versionArray_obj.put("time", getReleaseTime(version));
		versionArray_obj.put("id", version + "-" + versionName);
		versionArray_obj.put("type", "release");
		versionArray_obj.put("processArguments", "username_session_version");
		versionArray_obj.put("releaseTime", getReleaseTime(version));
		JSONArray versionsArray = new JSONArray();
		versionsArray.add(versionArray_obj);
		jsonData.put("versions", versionsArray);
		jsonData.put("latest", latestVersion_obj);

		try {
			FileWriter file = new FileWriter(MainConstants.OUTPUT_FOLDER + "/versions/versions.json");
			file.write(jsonData.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Object getReleaseTime(String version) {

		JSONParser parser = new JSONParser();
		String date = "";

		try {
			Object obj = parser.parse(new FileReader(MainConstants.OUTPUT_FOLDER + "/versions/" + version + "/" + version + ".json"));
			JSONObject jsonObject = (JSONObject) obj;
			date = (String) jsonObject.get("releaseTime");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}

	public static void manageServerFolder() {

		File serverFolder = new File(MainConstants.SERVER_DIR);
		if (!serverFolder.exists()) {
			serverFolder.mkdir();
		}
		File ressourcesFolder = new File(MainConstants.SERVER_DIR + "/ressources");
		if (!ressourcesFolder.exists()) {
			ressourcesFolder.mkdir();
		}
		File launcherFolder = new File(MainConstants.SERVER_DIR + "/launcher");
		if (!launcherFolder.exists()) {
			launcherFolder.mkdir();
		}
		File versionsFolder = new File(MainConstants.SERVER_DIR + "/launcher/versions");
		if (!versionsFolder.exists()) {
			versionsFolder.mkdir();
		}
		File versionFolder = new File(MainConstants.SERVER_DIR + "/launcher/versions/" + GAME_VERSION + "-" + VERSION_NAME);
		if (!versionFolder.exists()) {
			versionFolder.mkdir();
		}
		try {
			if (!VERSION_NAME.toLowerCase().contains("forge")) {
				FileManager.copyFile(new File(MainConstants.OUTPUT_FOLDER + "/versions/" + GAME_VERSION + "/" + GAME_VERSION + ".json"), new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/versions/" + GAME_VERSION + "-" + VERSION_NAME + "/"
						+ GAME_VERSION + "-" + VERSION_NAME + ".json"));
			}
			FileManager.copyFile(new File(MainConstants.OUTPUT_FOLDER + "/versions/" + GAME_VERSION + "/" + GAME_VERSION + ".jar"), new File(MainConstants.OUTPUT_FOLDER + "/SERVER/launcher/versions/" + GAME_VERSION + "-" + VERSION_NAME + "/"
					+ GAME_VERSION + "-" + VERSION_NAME + ".jar"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			FileManager.copyFile(MainConstants.BOOTSTRAP_SOURCE_JAR, MainConstants.BOOTSTRAP_DEST_JAR);
			FileManager.copyFile(MainConstants.LAUNCHER_SOURCE_JAR, MainConstants.LAUNCHER_DEST_JAR);
			FileManager.copyFile(MainConstants.VERSIONS_SOURCE_JSON, MainConstants.VERSIONS_DEST_JSON);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package com.jbltx.mlaunchcreator;

import java.io.File;
import java.util.List;

import javax.swing.JFrame;

/**
 * MineLauncherCreator
 * Main.java
 * Purpose: Create custom Minecraft launcher for servers administrators.
 *
 * @author Jambonlatex
 * @version 1.0 07/18/2014
 */

public class Main {

	List<String> fileList;

	static PopUp loadingWindow;
	static UI mainWindow;

	public static void main(String[] args) throws InterruptedException {

		createUi();

		createInstance();

	}

	// UI CREATION
	private static void createUi() {

		mainWindow = new UI();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(640, 480);
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(false);

		loadingWindow = new PopUp();
		loadingWindow.setVisible(true);
		PopUp.labelMsg.setText("Loading...");

	}

	// INSTANCE CREATION
	private static void createInstance() {
		File workingDir = new File(MainConstants.WORKING_DIR);

		if (!workingDir.exists()) {
			workingDir.mkdir();
			PopUp.labelMsg.setText("Temp Directory created.");
			System.out.println("Temp Directory created.");
		}

		File sourcesZip = new File(MainConstants.INPUT_ZIP_FILE);
		if (sourcesZip.exists()) {

			PopUp.labelMsg.setText("Checking updates...");

			System.out.println("A source code archive was found on your machine. Checking updates...");
			try {
				if (Update.updateTags()) {

					PopUp.labelMsg.setText("Update found ! Downloading new content...");

					System.out.println("Update found ! Downloading new content...");
					Download.downloadIt();

					PopUp.labelMsg.setText("Uncompressing content...");

				} else {

					PopUp.labelMsg.setText("You already have the most recent content, uncompressing it...");

					System.out.println("You already have the most recent content. No download required.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {

				PopUp.labelMsg.setText("No content found on your machine, downloading it...");

				System.out.println("No content found on your machine, downloading it...");
				Download.downloadIt();

				PopUp.labelMsg.setText("Uncompressing content...");

				PopUp.labelMsg.setText("Uncompressing content...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Uncompressing content...");
		FileManager.unZipIt(MainConstants.INPUT_ZIP_FILE, MainConstants.OUTPUT_FOLDER);

		loadingWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

}

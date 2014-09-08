package com.jbltx.mlaunchcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel applicationName_label;
	private JLabel defaultProfile_label;
	private JLabel urlBaseName_label;
	private JLabel useMod_label;
	private JLabel URL_REGISTER_Input_label;
	private JLabel URL_BLOG_Input_label;
	private JLabel USE_PERSONNAL_AUTH_label;
	private JLabel PERSONNAL_AUTH_URL_label;

	private JTextField applicationName_field;
	private JTextField defaultProfile_field;
	private JTextField urlBaseName_field;
	private JTextField URL_REGISTER_Input_field;
	private JTextField URL_BLOG_Input_field;
	private JTextField PERSONNAL_AUTH_URL_field;
	private JComboBox<Object> VERSION_ID_field;
	private JTextField VERSION_NAME_field;

	private JCheckBox useMod_checkbox;
	private JCheckBox USE_PERSONNAL_AUTH_checkbox;

	private JButton launchProc;

	private JFileChooser outputFile;
	private File FILE_OUTPUT;

	private JTabbedPane tabs;
	private JPanel mainPanel;
	private JPanel logPanel;
	private JPanel aboutPanel;
	private JPanel productPanel;
	static JTextArea textArea;

	public UI() {
		super(MainConstants.APP_NAME + " v" + MainConstants.APP_VERSION);
		
		ImageIcon saveIcon  = new ImageIcon(getClass().getResource("/icon.png"));
		
		setIconImage(saveIcon.getImage());
		
		tabs = new JTabbedPane();

		mainPanel = new JPanel();

		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		label1 = new JLabel("Personnal Minecraft Launcher Creation Application");
		label1.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;

		applicationName_label = new JLabel("Enter your Application Name : ");
		applicationName_label.setToolTipText("The Application Name is the name of the installation directory.");
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(2, 20, 2, 20);
		mainPanel.add(applicationName_label, c);

		applicationName_field = new JTextField();
		applicationName_field.setText("customcraft");
		applicationName_field.setPreferredSize(new Dimension(200, 24));
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(applicationName_field, c);

		defaultProfile_label = new JLabel("The name of the default profile in the launcher : ");
		defaultProfile_label.setToolTipText("The name of the default profile in the launcher.");
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(defaultProfile_label, c);

		defaultProfile_field = new JTextField();
		defaultProfile_field.setText("customcraft");
		defaultProfile_field.setPreferredSize(new Dimension(200, 24));
		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(defaultProfile_field, c);

		urlBaseName_label = new JLabel("<html>Enter the URL of your server : <font color='red'>DON'T FORGET THE \"/\" at the end of the URL please !</font></html>");
		urlBaseName_label.setToolTipText("The URL of your server which stores launcher files, minecraft versions, and mods.");
		c.gridx = 0;
		c.gridy = 5;
		mainPanel.add(urlBaseName_label, c);

		urlBaseName_field = new JTextField();
		urlBaseName_field.setText("http://localhost/customcraft/");
		urlBaseName_field.setPreferredSize(new Dimension(200, 24));
		c.gridx = 0;
		c.gridy = 6;
		mainPanel.add(urlBaseName_field, c);

		useMod_checkbox = new JCheckBox();
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.weightx = 0.01;
		mainPanel.add(useMod_checkbox, c);

		useMod_label = new JLabel("Use Mods Update");
		useMod_label.setToolTipText("Check this box if");
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 0.99;
		mainPanel.add(useMod_label, c);

		URL_REGISTER_Input_label = new JLabel("Enter the URL of the \"Register\" button : ");
		URL_REGISTER_Input_label.setToolTipText("URL to register Minecraft account if it doesn't exist.");
		c.gridx = 0;
		c.gridy = 8;
		c.weightx = 1;
		c.gridwidth = 2;
		mainPanel.add(URL_REGISTER_Input_label, c);

		URL_REGISTER_Input_field = new JTextField();
		URL_REGISTER_Input_field.setText("https://account.mojang.com/register");
		URL_REGISTER_Input_field.setPreferredSize(new Dimension(200, 24));
		c.gridx = 0;
		c.gridy = 9;
		mainPanel.add(URL_REGISTER_Input_field, c);

		URL_BLOG_Input_label = new JLabel("Enter the URL of the homepage blog : ");
		URL_BLOG_Input_label.setToolTipText("URL of the blog shown as homepage in the launcher.");
		c.gridx = 0;
		c.gridy = 10;
		c.weightx = 1;
		c.gridwidth = 2;
		mainPanel.add(URL_BLOG_Input_label, c);

		URL_BLOG_Input_field = new JTextField();
		URL_BLOG_Input_field.setText("http://mcupdate.tumblr.com");
		URL_BLOG_Input_field.setPreferredSize(new Dimension(200, 24));
		c.gridx = 0;
		c.gridy = 11;
		mainPanel.add(URL_BLOG_Input_field, c);

		USE_PERSONNAL_AUTH_checkbox = new JCheckBox();
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		c.weightx = 0.01;
		mainPanel.add(USE_PERSONNAL_AUTH_checkbox, c);

		USE_PERSONNAL_AUTH_label = new JLabel("Use Personnal Secondary Authentication Server");
		USE_PERSONNAL_AUTH_label.setToolTipText("Check this box if");
		c.gridx = 1;
		c.gridy = 12;
		c.weightx = 0.99;
		mainPanel.add(USE_PERSONNAL_AUTH_label, c);

		PERSONNAL_AUTH_URL_label = new JLabel("Enter the URL of your Personnal Secondary Authentication Server ");
		PERSONNAL_AUTH_URL_label.setToolTipText("URL for the secondary authentication, if used.");
		c.gridx = 0;
		c.gridy = 13;
		c.weightx = 1;
		c.gridwidth = 2;
		mainPanel.add(PERSONNAL_AUTH_URL_label, c);

		PERSONNAL_AUTH_URL_field = new JTextField();
		PERSONNAL_AUTH_URL_field.setText("http://localhost/remote/");
		PERSONNAL_AUTH_URL_field.setPreferredSize(new Dimension(200, 24));
		c.gridx = 0;
		c.gridy = 14;
		mainPanel.add(PERSONNAL_AUTH_URL_field, c);

		String[] availableVersions = { "1.6.2", "1.6.4", "1.7.2", "1.7.4", "1.7.5", "1.7.9", "1.7.10" };
		VERSION_ID_field = new JComboBox<Object>(availableVersions);
		VERSION_ID_field.setSelectedIndex(2);
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		c.weightx = 0.01;
		mainPanel.add(VERSION_ID_field, c);

		VERSION_NAME_field = new JTextField();
		VERSION_NAME_field.setText("Forge9.11.1.965");
		c.gridx = 1;
		c.gridy = 15;
		mainPanel.add(VERSION_NAME_field, c);

		launchProc = new JButton("Launch");
		launchProc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Thread queryThread = new Thread() {
					public void run() {
						runQueries();
					}
				};
				queryThread.start();
			}
		});

		c.gridx = 0;
		c.gridy = 16;
		c.weightx = 2;
		c.gridwidth = 2;
		mainPanel.add(launchProc, c);

		tabs.addTab("Main", null, mainPanel, "Main");
		tabs.setSelectedIndex(0);

		logPanel = new JPanel();
		if(OS.OS.indexOf("win")>=0){
			textArea = new JTextArea(28, 90);
		}
		else{
			textArea = new JTextArea(28, 70);
		}
		textArea.setEditable(false);
		textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);

		logPanel.add(scrollPane, BorderLayout.CENTER);
		MessageConsole mc = new MessageConsole(textArea);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);
		mc.setMessageLines(999);

		tabs.addTab("Log", null, logPanel, "Log");
		
		
		
		
		aboutPanel = new JPanel();
		aboutPanel.setLayout(new GridLayout(2,1));
		
		productPanel = new JPanel();
		productPanel.setLayout(new GridLayout(7,2));	
		
		
		JLabel iconLabel = new JLabel(saveIcon);

		JLabel productLabel = new JLabel("Product : ");
		productLabel.setHorizontalAlignment(JLabel.RIGHT);
		JLabel appNameLabel = new JLabel(MainConstants.APP_NAME);
		JLabel versionLabel = new JLabel("Version : ");
		versionLabel.setHorizontalAlignment(JLabel.RIGHT);
		JLabel appVersionLabel = new JLabel(MainConstants.APP_VERSION);
		JLabel dateLabel = new JLabel("Date : ");
		dateLabel.setHorizontalAlignment(JLabel.RIGHT);
		JLabel appDateLabel = new JLabel("07/18/2014");		
		

		JLabel licenseStatLabel = new JLabel("License : ");
		licenseStatLabel.setHorizontalAlignment(JLabel.RIGHT);
		JLabel licenseLabel = new JLabel("Free, non commercial use.");
		JLabel authorLabel = new JLabel("Author : ");
		authorLabel.setHorizontalAlignment(JLabel.RIGHT);
		JLabel authorMeLabel = new JLabel("Jambonlatex");
		
		productPanel.add(productLabel);
		productPanel.add(appNameLabel);
		productPanel.add(versionLabel);
		productPanel.add(appVersionLabel);
		productPanel.add(dateLabel);
		productPanel.add(appDateLabel);
		productPanel.add(licenseStatLabel);
		productPanel.add(licenseLabel);
		productPanel.add(authorLabel);
		productPanel.add(authorMeLabel);
		
		aboutPanel.add(iconLabel);
		aboutPanel.add(productPanel);
		
		tabs.addTab("About", null, aboutPanel, "About");

		add(tabs);
	}

	private void runQueries() {
		tabs.setSelectedIndex(1);

		outputFile = new JFileChooser();
		outputFile.setCurrentDirectory(new java.io.File("."));
		outputFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		outputFile.setAcceptAllFileFilterUsed(false);
		int rVal = outputFile.showSaveDialog(UI.this);

		if (rVal == JFileChooser.APPROVE_OPTION) {

			/*
			 * PopUp compilingPopUp = new PopUp();
			 * PopUp.labelMsg.setText("Compiling");
			 * compilingPopUp.setVisible(true);
			 */

			FILE_OUTPUT = outputFile.getSelectedFile();
			System.out.println("Chosen File : " + FILE_OUTPUT);
			Proc.editJavaFiles(applicationName_field.getText(), defaultProfile_field.getText(), urlBaseName_field.getText(), useMod_checkbox.isSelected(), URL_REGISTER_Input_field.getText(), URL_BLOG_Input_field.getText(),
					USE_PERSONNAL_AUTH_checkbox.isSelected(), PERSONNAL_AUTH_URL_field.getText(), (String) VERSION_ID_field.getSelectedItem(), VERSION_NAME_field.getText());
			FileManager.compileJarFile("minecraft-bootstrap");
			FileManager.compileJarFile("minecraft-launcher");

			Proc.manageServerFolder();

			File serverInDir = new File(MainConstants.SERVER_DIR);
			File serverOutDir = new File(FILE_OUTPUT + "/SERVER");
			if (!serverOutDir.exists()) {
				serverOutDir.mkdir();
			}
			try {
				FileManager.copyFile(serverInDir, serverOutDir);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			/* compilingPopUp.setVisible(false); */
			createLogFile(FILE_OUTPUT);
			JOptionPane.showMessageDialog(new JFrame(), "Finished, have fun !", "Finish", JOptionPane.PLAIN_MESSAGE);

		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			// cancelled
		}

	}

	private void createLogFile(File fILE_OUTPUT) {
		try {
			long time = System.currentTimeMillis();
			Timestamp timestamp = new java.sql.Timestamp(time);
			String req = timestamp.toString().replace(":", "-").replace(" ", "-");
			String logFilename = fILE_OUTPUT + "/MLC_log" + req + ".txt";
			FileWriter fw = new FileWriter(new File(logFilename).getAbsoluteFile(), true);
			textArea.write(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
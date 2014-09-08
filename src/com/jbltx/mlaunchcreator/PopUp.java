package com.jbltx.mlaunchcreator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUp extends JFrame {

	private static final long serialVersionUID = 1L;

	public static JLabel labelMsg;

	public PopUp() {
		super("Loading Files...");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		setLayout(new BorderLayout());

		labelMsg = new JLabel();
		labelMsg.setHorizontalAlignment(JLabel.CENTER);

		add(labelMsg);
		setUndecorated(true);
		setAlwaysOnTop(true);

		setSize(450, 40);
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		setLocation(x, y);
	}
}

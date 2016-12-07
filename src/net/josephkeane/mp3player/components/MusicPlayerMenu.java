package net.josephkeane.mp3player.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenu;

public class MusicPlayerMenu extends JMenu {
	private static final long serialVersionUID = -935576003820629446L;

	public MusicPlayerMenu(String text, char key) {
		super(text);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setMnemonic(key);
	}
}

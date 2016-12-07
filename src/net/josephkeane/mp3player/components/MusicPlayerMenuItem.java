package net.josephkeane.mp3player.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MusicPlayerMenuItem extends JMenuItem{
	private static final long serialVersionUID = 5634743021123995640L;

	public MusicPlayerMenuItem(String text, char key) {
		super(text);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFont(new Font("Arial", Font.BOLD, 12));
		setAccelerator(KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}
}

package net.josephkeane.mp3player.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MusicPlayerButton extends JButton{
	private static final long serialVersionUID = -6216429601482971295L;
	
	public MusicPlayerButton(ImageIcon icon) {
		this.setIcon(icon);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setBackground(Color.WHITE);
	}
}

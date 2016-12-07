package net.josephkeane.mp3player.display;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.josephkeane.mp3player.MusicPlayer;

public class MusicPlayerFrame extends JFrame{
	private static final long serialVersionUID = 5670738741338479329L;
	
	private String title = "MP3 Player";
	private ImageIcon icon = new ImageIcon("img/icon.png");
	private MusicPlayerMenuBar menuBar;
	
	public MusicPlayerFrame(MusicPlayer mp) {
		menuBar = new MusicPlayerMenuBar(mp);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 350);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(icon.getImage());
		setJMenuBar(menuBar);
		setVisible(true);
	}
	
}

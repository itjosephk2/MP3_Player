package net.josephkeane.mp3player.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.josephkeane.mp3player.data_types.PlayList;
import net.josephkeane.mp3player.data_types.Song;

public class Display {
	private static int i = -1;

	public static void errorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void plainMessage(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.PLAIN_MESSAGE);
	}

	public static int warningMessage(String msg) {
		return JOptionPane.showConfirmDialog(null, msg, "Warning",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	public static Song addFile() {
		String titleMsg = "Song Title";
		String artistMsg = "Artist Title";
		JTextField titleTextField = new JTextField();
		JTextField artistTextField = new JTextField();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("C:/Users/Public/Music"));
		chooser.setFileFilter(new FileNameExtensionFilter("Sound Files", "mp3"));
		Song song;

		Object[] components = new Object[5];

		components[0] = titleMsg;
		components[1] = titleTextField;
		components[2] = artistMsg;
		components[3] = artistTextField;
		components[4] = chooser;

		int response = JOptionPane.showConfirmDialog(null, components, "Add Song", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION)
			return null;
		else {
			try {
				String title = titleTextField.getText();
				String path = chooser.getSelectedFile().getAbsolutePath();
				if (title.equals("") || title.equals("") || path.equals(""))
					throw new NullPointerException();
				song = new Song(title, artistTextField.getText());
				song.setPath(path);
				return song;
			} catch (NullPointerException npe) {
				Display.errorMessage("Please do not leave field empty");
			} catch (Exception e) {
				Display.errorMessage("Error: " + e);
			}

		}
		return null;
	}

	public static int removeFile(Song[] songs, int currentIndex) {
		JComboBox<Song> listBox = new JComboBox<Song>(songs);
		listBox.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "unchecked"})
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((JComboBox<Song>) e.getSource()).getSelectedIndex() == currentIndex) {
					errorMessage("You cannot Delete The Song you are Currently Playing");
				}
				else 
					i = listBox.getSelectedIndex();
			}
		});
		int response = JOptionPane.showConfirmDialog(null, listBox, "Remove Song", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION)
			return -1;
		else {
			if (i == -1) {
				warningMessage("Don't Forget to choose a song");
				return i;
			}
			int warnResponse = warningMessage("Are You sure you want to delete " + songs[i]);
			if (warnResponse == JOptionPane.CANCEL_OPTION || warnResponse == JOptionPane.CLOSED_OPTION)
				return -1;
			return i;
		}
	}

	public static int playFile(Song[] songs) {
		JComboBox<Song> listBox = new JComboBox<Song>(songs);
		listBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				i = listBox.getSelectedIndex();
			}
		});
		int response = JOptionPane.showConfirmDialog(null, listBox, "Add Song", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION)
			return -1;
		else
			return i;
	}

	public static PlayList playListCreate(Song[] allSongs) {
		PlayList tmpPlayList = new PlayList("");
		String titleMsg = "Play List Name";
		JTextField titleTextField = new JTextField();
		JCheckBox[] songsCheckBox = new JCheckBox[allSongs.length];
		for (int i = 0; i < songsCheckBox.length; i++) {
			songsCheckBox[i] = new JCheckBox(allSongs[i].toString());
			songsCheckBox[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < songsCheckBox.length; i++) {
						if (songsCheckBox[i] == e.getSource()) {
							tmpPlayList.addSong(allSongs[i]);
						}
					}
				}
			});
		}

		Object[] components = new Object[3];

		components[0] = titleMsg;
		components[1] = titleTextField;
		components[2] = songsCheckBox;

		int response = JOptionPane.showConfirmDialog(null, components, "Creat PlayList", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.CANCEL_OPTION)
			return null;
		else {
			tmpPlayList.setName(titleTextField.getText());
			return tmpPlayList;
		}
	}

	public static int PlayListSelect(PlayList[] playListArray) {
		JComboBox<PlayList> playListBox = new JComboBox<PlayList>(playListArray);
		playListBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				i = playListBox.getSelectedIndex();
			}
		});
		
		int response = JOptionPane.showConfirmDialog(null, playListBox, "Select PlayList",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if (response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION)
			return -1;
		else {
			return i;
		}
	}
}

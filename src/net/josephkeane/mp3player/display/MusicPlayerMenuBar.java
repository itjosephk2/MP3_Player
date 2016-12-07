package net.josephkeane.mp3player.display;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import net.josephkeane.mp3player.MusicPlayer;
import net.josephkeane.mp3player.components.MusicPlayerMenu;
import net.josephkeane.mp3player.components.MusicPlayerMenuItem;
import net.josephkeane.mp3player.data_types.PlayList;
import net.josephkeane.mp3player.data_types.Song;

public class MusicPlayerMenuBar extends JMenuBar{
	private static final long serialVersionUID = 7738971398293953079L;
	
	private JMenu fileMenu;
	private JMenu playListMenu;
	private MusicPlayerMenuItem fileAdd = new MusicPlayerMenuItem("Add Song", 'A');
	private MusicPlayerMenuItem fileRemove = new MusicPlayerMenuItem("Remove Song", 'D');
	private MusicPlayerMenuItem filePlay = new MusicPlayerMenuItem("Play Song", 'P');
	private MusicPlayerMenuItem fileSave = new MusicPlayerMenuItem("Save Song", 'S');
	private MusicPlayerMenuItem playListAdd = new MusicPlayerMenuItem("Create PlayList", 'C');
	private MusicPlayerMenuItem playListSelect = new MusicPlayerMenuItem("Select PlayList", 'L');
	private MusicPlayer mp;
	private Song[] songsArray;
	private Song[] allSongsArray;
	private PlayList[] playListArray;
	private PlayList tmpPlayList;
	
	public MusicPlayerMenuBar(MusicPlayer mp) {
		this.mp = mp;
		this.setBackground(Color.BLACK);
		fileMenu = new MusicPlayerMenu("File", 'F');
		playListMenu = new MusicPlayerMenu("PlayLists", 'P');
		if (mp.playLists.get(0).playList.size() != 0) 
			resetArrays();
		initFileAdd();
		initFileRemove();
		initFilePlay();
		initFileSave();
		initPlayListAdd();
		initPlayListSelect();
		add(fileMenu);
		add(playListMenu);
	}
	
	private void resetArrays() {
		songsArray = mp.currentPlayList.playList.toArray(new Song[mp.currentPlayList.playList.size()]);
		allSongsArray = mp.currentPlayList.playList.toArray(new Song[mp.playLists.get(0).playList.size()]);
		playListArray = mp.playLists.toArray(new PlayList[mp.playLists.size()]);
	}
	
	private void initFileAdd() {
		fileAdd.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent args) {
				Song song = Display.addFile();
				if (song == null)
					return;
				mp.playLists.get(0).playList.add(song);
				resetArrays();
				if (mp.playLists.get(0).playList.size() == 1)	
					mp.changeSong(mp.playLists.size() -1);
				mp.save();
//				testerCode();
			}
		});
		fileMenu.add(fileAdd);
	}

	private void initFileRemove() {
		fileRemove.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mp.playLists.get(0).playList.size() == 0) {
					Display.errorMessage("There is no songs");
					return;
				}
				int i = Display.removeFile(songsArray, mp.currentSongIndex); 
				if (i == -1)
					return;
				mp.removeSong(i);
				resetArrays();
				mp.save();
//				testerCode();
			}
		});
		fileMenu.add(fileRemove);
	}

	private void initFilePlay() {
		filePlay.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mp.playLists.get(0).playList.size() == 0) {
					Display.errorMessage("There is no songs");
					return;
				}
				int i = Display.playFile(songsArray);
				if (i == -1)
					return;
				mp.changeSong(i);
				mp.save();
//				testerCode();
			}
		});
		fileMenu.add(filePlay);
	}
	
	private void initFileSave() {	
		fileSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileSave.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				mp.save();
//				testerCode();
			}
		});
		fileMenu.add(fileSave);
	}
	
	private void initPlayListAdd() {
		playListAdd.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				tmpPlayList = Display.playListCreate(allSongsArray);
				if (tmpPlayList == null)
					return;
				mp.playLists.add(tmpPlayList);
				resetArrays();
				mp.save();
//				testerCode();
			}
		});
		playListMenu.add(playListAdd);
	}
	
	private void initPlayListSelect() {
		playListSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = Display.PlayListSelect(playListArray);
				if (i == -1)
					return;
				mp.changePlayList(i);
				resetArrays();
				mp.stop();
//				testerCode();
			}
		});
		playListMenu.add(playListSelect);
		
	}
	
	public void testerCode() {
		System.out.println("Current Song = " + mp.currentSong);
		System.out.println("Current PlayList = " + mp.currentPlayList);
		System.out.println("Current Index = " + mp.currentSongIndex);
		System.out.println("Current Playlist song list = " + mp.currentPlayList.playList);
	}
}

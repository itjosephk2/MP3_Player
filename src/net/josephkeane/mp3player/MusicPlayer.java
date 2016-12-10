package net.josephkeane.mp3player;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import net.josephkeane.mp3player.data_types.PlayList;
import net.josephkeane.mp3player.data_types.Song;
import net.josephkeane.mp3player.display.Display;
import net.josephkeane.mp3player.display.MusicPlayerFrame;
import net.josephkeane.mp3player.display.MusicPlayerPanel;
import net.josephkeane.mp3player.extras.FileDemo;

public class MusicPlayer extends Application {
	public ArrayList<PlayList> playLists = new ArrayList<PlayList>();
	// means of referencing the current PlayList
	public PlayList currentPlayList;
	public Song currentSong;
	public int currentSongIndex;
	public String title = "MP3 Player";
	public boolean shuffle;

	private FileDemo d;

	private Media hit;
	private MediaPlayer mediaPlayer;

	public MusicPlayerFrame frame;
	public MusicPlayerPanel panel;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
	}

	// public static void main(String[] args)
	public MusicPlayer() {
		// Setting the playlist ArryayList to whatever is Stored in the file
		d = new FileDemo();
		playLists = d.readArrayListFromFile(playLists);
		// Ensures all songs Always exists
		if (playLists.size() == 0)
			playLists.add(new PlayList("All Songs"));
		// Set current PlayList to allSongs
		currentPlayList = playLists.get(0);
		// Set current song to first Song
		setSong();
		// creates panel and frame and puts the panel in the frame
		panel = new MusicPlayerPanel(this);
		frame = new MusicPlayerFrame(this);
		frame.add(panel);
	}

	// functionality
	private void setSong() {
		if (currentPlayList.playList.size() > 0)
			setSong(currentPlayList.playList.get(0));
	}

	private void setSong(Song song) {
		// sets currentSong to the song passed in
		currentSong = song;
		// Does the Javafx side of things
		hit = new Media(new File(song.getPath()).toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
	}

	private void setPlayList(PlayList playList) {
		currentPlayList = playList;
		mediaPlayer.stop();
		setSong(currentPlayList.playList.get(0));
		refresh();
	}

	private void refresh() {
		// resets all the information on the screen
		panel.setSectionLabel(currentPlayList.getName());
		panel.setTitleLabel(currentSong.getTitle());
		panel.setArtistLabel(currentSong.getArtist());
		panel.setLengthLabel("00:00");
		SwingUtilities.updateComponentTreeUI(frame);
	}

	public void changeSong(int i) {
		currentSongIndex = i;
		Song song = currentPlayList.playList.get(currentSongIndex);
		setSong(song);
		refresh();
	}

	public void changePlayList(int i) {
		PlayList playList = playLists.get(i);
		setPlayList(playList);
		refresh();
	}

	public void play() {
		// currentSong.Played();
		mediaPlayer.play();
	}

	public void stop() {
		mediaPlayer.stop();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void save() {
		// Saves ArrayList to a file
		d.writeArrayListToFile(playLists);
		Display.plainMessage("File Saved", "Saving...");
	}

	public void removeSong(int index) {
		playLists.get(0).playList.remove(index);
	}

	public void shuffle() {
		Collections.shuffle(currentPlayList.playList);
	}

	public void sortSong() {
		Collections.sort(currentPlayList.playList, (s1, s2) -> {
			return s1.getTitle().compareTo(s2.getTitle());
		});
	}
	
	public void sortArtist() {
		Collections.sort(currentPlayList.playList, (s1, s2) -> {
			return s1.getArtist().compareTo(s2.getArtist());
		});
		System.out.println(currentPlayList.playList);
	}

	
	// wasnt needed
//	public void search(String title, String artist) {
//		int pos = Collections.binarySearch(currentPlayList.playList, new Song(title, artist), (s1, s2) -> {
//			return s1.getTitle().compareTo(s2.getTitle());
//		}); 
//
//		if (pos >= 0)
//			Display.plainMessage("Found The song " + title, title);
//		else
//			Display.errorMessage("Song does not exist in current playlist");
//	}

}
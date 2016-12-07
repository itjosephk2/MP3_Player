package net.josephkeane.mp3player.data_types;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayList implements Serializable{
	private static final long serialVersionUID = 186004684598960473L;
	
	//Attributes
	public ArrayList<Song> playList = new ArrayList<Song>();
	private String name = "PlayList 2";
	
	public PlayList(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSong(Song song) {
		playList.add(song);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

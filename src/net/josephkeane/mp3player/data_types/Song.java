package net.josephkeane.mp3player.data_types;

import java.io.Serializable;

public class Song implements Serializable{	
	private static final long serialVersionUID = -6228860444076412187L;
	
	private String title;
	private String path;
	private String artist;
	private int timesPlayed;
	
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		timesPlayed = 0;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}
	
	public String getArtist() {
		// TODO Auto-generated method stub
		return artist;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return title + " by " + artist;
	}
	
	public void Played() {
		timesPlayed++;
	}
	
}

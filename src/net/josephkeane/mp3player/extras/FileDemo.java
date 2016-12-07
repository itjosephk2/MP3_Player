package net.josephkeane.mp3player.extras;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import net.josephkeane.mp3player.data_types.PlayList;
import net.josephkeane.mp3player.display.Display;

public class FileDemo {

	public void writeArrayListToFile(ArrayList<PlayList> playLists) {
		try {
			File f = new File("mydata.dat");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(playLists);

			oos.close();
			fos.close();

		} catch (Exception e) {
			Display.errorMessage("Error: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<PlayList> readArrayListFromFile(ArrayList<PlayList> playLists) {
		try {
			File f = new File("mydata.dat");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			playLists = (ArrayList<PlayList>) ois.readObject();

			ois.close();
			fis.close();

		} catch (Exception e) {
			Display.errorMessage("Error: " + e.getMessage());
		}
		Display.plainMessage("File Succesfully Read", "Loading..");
		return playLists;
	}
}

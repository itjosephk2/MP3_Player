package net.josephkeane.mp3player.display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.josephkeane.mp3player.MusicPlayer;
import net.josephkeane.mp3player.components.MusicPlayerButton;

public class MusicPlayerPanel extends JPanel {
	private static final long serialVersionUID = -5173062421030713551L;

	private MusicPlayer mp;

	private JLabel sectionMsg;
	private JLabel sectionLabel;
	private JLabel titleLabel;
	private JLabel titleMsg;
	private JLabel artistMsg;
	private JLabel artistLabel;
	private JLabel lengthMsg;
	private JLabel lengthLabel;

	private MusicPlayerButton playButton;
	private MusicPlayerButton pauseButton;
	private MusicPlayerButton stopButton;
	private MusicPlayerButton nextButton;
	private MusicPlayerButton previousButton;

	private JCheckBox shuffleBox;

	private GridBagConstraints gc;

	public MusicPlayerPanel(MusicPlayer mp) {
		this.mp = mp;
		setBackground(Color.WHITE);
		initLayout();
		initSectionMsg();
		initSectionLabel();
		initTitleMsg();
		initTitleLabel();
		initArtistMsg();
		initArtistLabel();
		initLengthMsg();
		initLengthLabel();
		initPlayButton();
		initPauseButton();
		initStopButton();
		initNextButton();
		initPreviousButton();
		initShuffleBox();
	}

	private void initLayout() {
		this.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 0.5;
	}

	private void initSectionMsg() {
		sectionMsg = new JLabel("PlayList:");
		gc.gridx = 1;
		gc.gridy = 0;
		add(sectionMsg, gc);
	}

	private void initSectionLabel() {
		sectionLabel = new JLabel(mp.currentPlayList.toString());
		gc.gridx = 2;
		gc.gridy = 0;
		add(sectionLabel, gc);
	}

	private void initTitleMsg() {
		titleMsg = new JLabel("Song title:");
		gc.gridx = 0;
		gc.gridy = 1;
		add(titleMsg, gc);
	}

	private void initTitleLabel() {
		titleLabel = new JLabel();
		if (mp.currentPlayList.playList.size() > 0)
			titleLabel = new JLabel(mp.currentSong.getTitle());
		gc.gridx = 1;
		gc.gridy = 1;
		add(titleLabel, gc);
	}

	private void initArtistMsg() {
		artistMsg = new JLabel("Song Artist:");
		gc.gridx = 2;
		gc.gridy = 1;
		add(artistMsg, gc);
	}

	private void initArtistLabel() {
		artistLabel = new JLabel();
		if (mp.currentPlayList.playList.size() > 0)
			artistLabel = new JLabel(mp.currentSong.getArtist());
		gc.gridx = 3;
		gc.gridy = 1;
		add(artistLabel, gc);
	}

	private void initLengthMsg() {
		lengthMsg = new JLabel("Song Length:");
		gc.gridx = 1;
		gc.gridy = 3;
		add(lengthMsg, gc);
	}

	private void initLengthLabel() {
		lengthLabel = new JLabel("00:00");
		gc.gridx = 2;
		gc.gridy = 3;
		add(lengthLabel, gc);
	}

	private void initPlayButton() {
		playButton = new MusicPlayerButton(new ImageIcon("img\\playimg.png"));
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mp.play();
			}
		});
		gc.gridx = 1;
		gc.gridy = 4;
		add(playButton, gc);
	}

	public void initPauseButton() {
		pauseButton = new MusicPlayerButton(new ImageIcon("img\\pauseimg.png"));
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mp.pause();
			}
		});
		gc.gridx = 2;
		gc.gridy = 4;
		add(pauseButton, gc);
	}

	public void initStopButton() {
		stopButton = new MusicPlayerButton(new ImageIcon("img\\stopimg.png"));
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mp.stop();
			}
		});
		gc.gridx = 2;
		gc.gridy = 5;
		add(stopButton, gc);
	}

	public void initNextButton() {
		nextButton = new MusicPlayerButton(new ImageIcon("img\\nextimg.png"));
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mp.stop();
				int newSong = 0;
				if (mp.currentPlayList.playList.size() > 1) {
					do {
						if (mp.currentSongIndex != mp.currentPlayList.playList.size() - 1)
							newSong = mp.currentSongIndex + 1;
						else
							newSong = 0;
					} while (mp.currentPlayList.playList.get(newSong) == mp.currentSong);
				} else {
					return;
				}
				mp.changeSong(newSong);
			}
		});
		gc.gridx = 3;
		gc.gridy = 4;
		add(nextButton, gc);
	}

	public void initPreviousButton() {
		previousButton = new MusicPlayerButton(new ImageIcon("img\\previousimg.png"));
		previousButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mp.stop();
				int newSong = 0;
				if (mp.currentPlayList.playList.size() > 1) {
					do {
						if (mp.currentSongIndex != 0)
							newSong = mp.currentSongIndex - 1;
						else
							newSong = mp.currentPlayList.playList.size() - 1;
					} while (newSong == mp.currentSongIndex);
				} else {
					return;
				}
				mp.changeSong(newSong);
			}
		});
		gc.gridx = 0;
		gc.gridy = 4;
		add(previousButton, gc);
	}

	private void initShuffleBox() {
		shuffleBox = new JCheckBox(new ImageIcon("img\\shuffleimginactive.png"));
		shuffleBox.setBackground(Color.WHITE);
		shuffleBox.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!mp.shuffle)
					shuffleBox.setIcon(new ImageIcon("img\\shuffleimginactive.png"));
				else if (mp.shuffle)
					shuffleBox.setIcon(new ImageIcon("img\\shuffleimgactive.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (mp.shuffle)
					shuffleBox.setIcon(new ImageIcon("img\\shuffleimginactive.png"));
				else if (!mp.shuffle)
					shuffleBox.setIcon(new ImageIcon("img\\shuffleimgactive.png"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!mp.shuffle) {
					mp.shuffle = true;
					shuffleBox.setIcon(new ImageIcon("img\\shuffleimgactive.png"));
					mp.shuffle();
				} else {
					mp.shuffle = false;
					shuffleBox.setIcon(new ImageIcon("img\\shuffleimginactive.png"));
					mp.sortSong();
				}
			}
		});
		gc.gridx = 1;
		gc.gridy = 5;
		add(shuffleBox, gc);
	}
	
	public void setSectionLabel(String text) {
		sectionLabel.setText(text);
	}

	public void setTitleLabel(String text) {
		titleLabel.setText(text);
	}

	public void setArtistLabel(String text) {
		artistLabel.setText(text);
	}

	public void setLengthLabel(String text) {
		lengthLabel.setText(text);
	}
	
}

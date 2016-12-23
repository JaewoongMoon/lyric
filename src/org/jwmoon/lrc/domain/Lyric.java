/**
 * @ Lyric.java 
 */
package org.jwmoon.lrc.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * org.jwmoon.lrc.domain
 * Lyric.java 
 * </pre>
 *
 * @brief	: 
 * @author	: ¹®Àç¿õ(mjw8585@gmail.com)
 * @Date	: 2016. 11. 29.
 */
public class Lyric {

	private String artist;
	private String songName;
	private String albumName;
	private List<LyricRow> rows = new ArrayList<LyricRow>();
	private String lrcFileName;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public List<LyricRow> getRows() {
		return rows;
	}

	public void setRows(List<LyricRow> rows) {
		this.rows = rows;
	}

	public String getLrcFileName() {
		return lrcFileName;
	}

	public void setLrcFileName(String lrcFileName) {
		this.lrcFileName = lrcFileName;
	}
	
	
	
}

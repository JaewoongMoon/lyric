/**
 * @ LrcWriter.java 
 */
package org.jwmoon.lrc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jwmoon.lrc.domain.Lyric;
import org.jwmoon.lrc.domain.LyricRow;

/**
 * <pre>
 * org.jwmoon.lrc
 * LrcWriter.java 
 * </pre>
 *
 * @brief	: 
 * @author	: �����(mjw8585@gmail.com)
 * @Date	: 2016. 11. 30.
 */
public class LrcWriter {

	private static final String lrcSavePath = "C://lyrics/";
	
	/**
	 * @Method 	: makeLrcString
	 * @brief	: Lyric Ŭ������ ������ LRC ���Ŀ� �´� ���ڿ� ������ ��ȯ���ش�.  
	 * @author	: �����(mjw8585@gmail.com)
	 * @Date	: 2016. 11. 30.
	 * @param lyric
	 * @return
	 */
	public String makeLrcString(Lyric lyric){
		String result = "";
		result += "[ar:" + lyric.getArtist() + "]\r\n";
		result += "[ti:" + lyric.getSongName() + "]\r\n";
		result += "[al:" + lyric.getAlbumName() + "]\r\n";
		if (lyric.getRows() != null && lyric.getRows().size() > 0 ){
			
			for (LyricRow row : lyric.getRows()){
				result += "[" + row.getTime() + "]"
						+ row.getOriginal() + " / "
						+ row.getTranslate() + "\r\n";
			}
		}
		return result;
	}
	
	public void writeLrcFile(Lyric lyric){
		
		try {
	      ////////////////////////////////////////////////////////////////
	      BufferedWriter out = new BufferedWriter(
	    		  new FileWriter(lrcSavePath + lyric.getLrcFileName()));
	      String s = makeLrcString(lyric);

	      out.write(s); 
	      out.newLine();

	      out.close();
	      ////////////////////////////////////////////////////////////////
	    } catch (IOException e) {
	        System.err.println(e); // ������ �ִٸ� �޽��� ���
	        //System.exit(1);
	    }

	}
	
	public static void main(String[] args) {
		// lrc file read
		LrcReader reader = new LrcReader();
		Lyric lyric = reader.parseLrc("C://lyrics/lucky.lrc");
		
		// lrc file write
		lyric.setLrcFileName("lucky2.lrc");
		LrcWriter writer = new LrcWriter();
		writer.writeLrcFile(lyric);
		
	}
}

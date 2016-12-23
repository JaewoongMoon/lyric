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
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 11. 30.
 */
public class LrcWriter {

	private static final String lrcSavePath = "C://lyrics/";
	
	/**
	 * @Method 	: makeLrcString
	 * @brief	: Lyric 클래스의 정보를 LRC 형식에 맞는 문자열 값으로 변환해준다.  
	 * @author	: 문재웅(mjw8585@gmail.com)
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
	        System.err.println(e); // 에러가 있다면 메시지 출력
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

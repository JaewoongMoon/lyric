/**
 * @ LrcReader.java 
 */
package org.jwmoon.lrc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jwmoon.lrc.domain.Lyric;
import org.jwmoon.lrc.domain.LyricRow;

/**
 * <pre>
 * org.jwmoon.lrc
 * LrcReader.java 
 * </pre>
 *
 * @brief	: 
 * @author	: �����(mjw8585@gmail.com)
 * @Date	: 2016. 11. 29.
 */
public class LrcReader {

	/**
	 * @Method 	: getLrcContent
	 * @brief	: ���Ͽ��� ������ �о List<String> ���� �����Ѵ�.  
	 * @author	: �����(mjw8585@gmail.com)
	 * @Date	: 2016. 11. 29.
	 * @param path
	 * @return
	 */
	@SuppressWarnings("finally")
	public List getLrcContent(String path){
		List<String> result = new ArrayList<String>();
		
		try {
	      ////////////////////////////////////////////////////////////////
	      BufferedReader in = new BufferedReader(new FileReader(path));
	      String s;
	      
	      while ((s = in.readLine()) != null) {
	        //System.out.println(s);
	    	 //result += s + "\r\n";
	    	  result.add(s);
	      }
	      in.close();
	      ////////////////////////////////////////////////////////////////
	    } catch (IOException e) {
	        System.err.println(e); // ������ �ִٸ� �޽��� ���
	        
	    } finally{
	    	return result;
	    }
	}
	
	/**
	 * @Method 	: parseLrc
	 * @brief	: Lrc ������ ������ �Ľ��ؼ� ������ ������Ʈ�� ��ȯ�Ѵ�. 
	 * @author	: �����(mjw8585@gmail.com)
	 * @Date	: 2016. 11. 29.
	 * @param file
	 */
	public Lyric parseLrc(List<String> list){
		Lyric lyric = new Lyric();
		
		
		for (String row : list){
			if(row.startsWith("[ar:")){
				//row.s
				String artist = row.replace("[ar:", "").replace("]", "");
				lyric.setArtist(artist);
			}else if (row.startsWith("[ti:")){
				String songTitle = row.replace("[ti:", "").replace("]", "");
				lyric.setSongName(songTitle);
			}else if (row.startsWith("[al:")){
				String albumName = row.replace("[al:", "").replace("]", "");
				lyric.setAlbumName(albumName);
			}else{
				// [00:00:00] Ÿ���� ����ǥ������ üũ�ϴ� �ڵ带 �־�� ������
				// �� �ȵǰ� �ð��� �� �ʿ��� ����� �׳� else �� ó���صα�� �Ѵ�. 
				LyricRow l = new LyricRow();
				String time = row.substring(row.indexOf("[") + 1, (row.indexOf("]")) );
				String original = row.substring((row.indexOf("]") + 1), row.indexOf("/"));
				String translate = row.substring((row.indexOf("/") + 1), row.length());
				l.setTime(time);
				l.setOriginal(original);
				l.setTranslate(translate);
				lyric.getRows().add(l);
			}
		}
		
		return lyric;
	}
	
	public Lyric parseLrc(String path){
		return parseLrc(getLrcContent(path));
	}
	
	public void printLrc(Lyric lyric){
		if (lyric != null){
			System.out.println("artist : " + lyric.getArtist());
			System.out.println("song title : " + lyric.getSongName());
			System.out.println("album name : " + lyric.getAlbumName());
			if (lyric.getRows() != null && lyric.getRows().size() > 0 ){
				
				for(LyricRow row : lyric.getRows()){
					System.out.println("time : " + row.getTime() + ", original : " + 
				row.getOriginal() + ", translate : " + row.getTranslate());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String path = "C://lyrics/lucky.lrc";
		/*���Ͽ��� �о���� �׽�Ʈ*/ 
		
		LrcReader r = new LrcReader();
		List<String> list = r.getLrcContent(path);
		//System.out.println(content);
		r.printLrc(r.parseLrc(list));
		
		
		/*�Ľ̿� ����� ����ǥ���ĵ� �׽�Ʈ */
		// type 1. start with [ar: or [ti: or [al:
		//String target = "[ar:Britney Spears]";
		//System.out.println(target.startsWith("[ar:"));
		
		// type 2. [00:00:00] Ÿ���� ���ڷ� �����ϴ���?
		//String target2 = "[00:11:22]";
		//String regex = "\\^([\\d\\d:\\d\\d:\\d\\d])";
		//System.out.println(target2.matches(regex));
		
		//System.out.println("abc".matches("a*c"));
	}	
}

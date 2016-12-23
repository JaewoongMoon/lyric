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
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 11. 29.
 */
public class LrcReader {

	/**
	 * @Method 	: getLrcContent
	 * @brief	: 파일에서 내용을 읽어서 List<String> 으로 리턴한다.  
	 * @author	: 문재웅(mjw8585@gmail.com)
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
	        System.err.println(e); // 에러가 있다면 메시지 출력
	        
	    } finally{
	    	return result;
	    }
	}
	
	/**
	 * @Method 	: parseLrc
	 * @brief	: Lrc 파일의 내용을 파싱해서 도메인 오브젝트로 변환한다. 
	 * @author	: 문재웅(mjw8585@gmail.com)
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
				// [00:00:00] 타입의 정규표현식을 체크하는 코드를 넣어야 하지만
				// 잘 안되고 시간이 더 필요한 관계로 그냥 else 로 처리해두기로 한다. 
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
		/*파일에서 읽어오기 테스트*/ 
		
		LrcReader r = new LrcReader();
		List<String> list = r.getLrcContent(path);
		//System.out.println(content);
		r.printLrc(r.parseLrc(list));
		
		
		/*파싱에 사용할 정규표현식들 테스트 */
		// type 1. start with [ar: or [ti: or [al:
		//String target = "[ar:Britney Spears]";
		//System.out.println(target.startsWith("[ar:"));
		
		// type 2. [00:00:00] 타입의 문자로 시작하는지?
		//String target2 = "[00:11:22]";
		//String regex = "\\^([\\d\\d:\\d\\d:\\d\\d])";
		//System.out.println(target2.matches(regex));
		
		//System.out.println("abc".matches("a*c"));
	}	
}

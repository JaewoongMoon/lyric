/**
 * @ LyricRow.java 
 */
package org.jwmoon.lrc.domain;

/**
 * <pre>
 * org.jwmoon.lrc.domain
 * LyricRow.java 
 * </pre>
 *
 * @brief	: 
 * @author	: ¹®Àç¿õ(mjw8585@gmail.com)
 * @Date	: 2016. 11. 29.
 */
public class LyricRow {

	private String time;
	private String original;
	private String translate;
	private String pronun; // ¹ßÀ½
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getTranslate() {
		return translate;
	}
	public void setTranslate(String translate) {
		this.translate = translate;
	}
	
	public String getPronun() {
		return pronun;
	}
	public void setPronun(String pronun) {
		this.pronun = pronun;
	}
	
	
}

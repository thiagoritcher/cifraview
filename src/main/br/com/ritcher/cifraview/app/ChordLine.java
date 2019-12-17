package br.com.ritcher.cifraview.app;

import java.util.*;
import java.util.regex.*;

public class ChordLine {
	private int tone = 0;

	public void setTone(int tone){
		this.tone = tone;
	}

	public int getTone(){
		return this.tone;
	}

	public void increaseTone(){
		this.tone++;
	}
	
	public void decreaseTone(){
		this.tone--;
	}

	public int w,x,z;

	public String getLine(String orig){	
		Pattern p = Pattern.compile("\\s*([^\\s]+)\\s*");
		Matcher m = p.matcher(orig);
		ArrayList<String> chords = new ArrayList();
		ArrayList<Integer[]> finds = new ArrayList();

		while(m.find()){
			w++;
			finds.add(new Integer[]{m.start(), m.end()});
			String c = orig.substring(m.start(), m.end());
			chords.add(getChord(c, this.tone));
		}

		String result = orig;
		for(int i = chords.size() -1; i > -1; i--){
			Integer[] s = finds.get(i);
			result = result.substring(0, s[0]) + chords.get(i) + result.substring(s[1]);
		}

		return result;
	}

	public ChordLine(){
		for(int i = 0; i < chordEqArray.length; i+= 2){
			chordEq.put(chordEqArray[i+1], chordEqArray[i]);
		}

		for(int i = 0; i < chordArray.length; i++){
			chordList.add(chordArray[i]);
		}
	}

	private String[] chordArray = new String[]{"A", "A#",  "B", "C", "C#", 
		"D", "D#", "E", "F", "F#", "G", "G#"};
	
	private String[] chordEqArray = new String[]{"A#", "Bb", "C#", "Db", 
		"D#", "Eb", "F#", "Gb", "G#", "Ab"};

	HashMap<String,String> chordEq = new HashMap<String,String>();
	ArrayList<String> chordList = new ArrayList<String>();

	public String a,b,c,d,e,f;

	public String getChord(String chord, int tone){
		String leter = chord.substring(0,1).toUpperCase();
		String diff = null;

		a = leter;

		String base;	
		if(chord.length() > 1){
			diff = chord.substring(1,2).toLowerCase();
		}
		
		b = diff;
		
		if(diff != null && !"#".equals(diff) && !"b".equals(diff)){
			base = leter;
		}
		else if("b".equals(diff)){
			String eq = chordEq.get("" + leter + diff);
			base = eq; 
		}
		else {
			base = leter + diff;
		}

		c = base;


		int index = chordList.indexOf(base);
		if(index < 0){
			return chord;
		}
		index = (index + tone);
		while(index < 0){
			index += chordList.size() -1;
		}
		index = index % chordList.size();
		
		String newBase = chordList.get(index);
		return newBase + chord.substring(base.length());
	}

	public boolean isChordLine(String line){
		return line.matches("\\s*([ABCDEFGb#1-9mM\\-\\s/\\(\\)])+\\s*");
	}
}




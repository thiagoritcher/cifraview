package br.com.ritcher.cifraview.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CifraToneFile implements ToneSetting {
	/**
	 * 
	 */
	private final CifraViewMain cifraViewMain;

	/**
	 * @param cifraViewMain
	 */
	public CifraToneFile(CifraViewMain cifraViewMain) {
		this.cifraViewMain = cifraViewMain;
		
		String dir = ".";
		if(System.getenv("APPDATA") != null){
			dir = System.getenv("APPDATA");
		}
		toneFile =  new File(dir + File.separator + ".GuitarTabViewer" + File.separator +  "cifras.tone");
		System.out.println(toneFile.getAbsolutePath());
		tones = new Properties();
	}

	File toneFile;
	private Properties tones;
	
	
	void load(){
		if(!toneFile.exists()){
			return;
		}
		
		tones = new Properties();
		try {
			InputStream inputStream = new FileInputStream(toneFile);
			tones.load(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		
	}

	void save() {
		if(this.cifraViewMain.file == null){
			return;
		}
		toneFile.getParentFile().mkdirs();
		
		FileOutputStream outputStream;
		try {
			if(!toneFile.exists()){
				toneFile.createNewFile();
			}
			
			outputStream = new FileOutputStream(toneFile);
			tones.store(outputStream, "GuitarView Tones");
		
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	File curentFile;
	
	public void setCurentFile(File curentFile) {
		this.curentFile = curentFile;
		this.cifraViewMain.panel.chordLine.setTone(getTone());
	}

	public int getTone() {
		String key = curentFile.getParentFile().getName() + "/" + curentFile.getName();
		if(tones.containsKey(key)) {
			String property = tones.getProperty(key);
			return Integer.parseInt(property);
		}
		else {
			return 0;
		}
	}
	
	public void setTone(int tone) {
		tones.setProperty(curentFile.getParentFile().getName() + "/" + curentFile.getName(), Integer.toString(tone));
	}
}

package br.com.ritcher.cifraview.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CifraCapFile  {
	/**
	 * 
	 */
	private final CifraViewMain cifraViewMain;

	/**
	 * @param cifraViewMain
	 */
	public CifraCapFile(CifraViewMain cifraViewMain) {
		this.cifraViewMain = cifraViewMain;
		
		String dir = ".";
		if(System.getenv("APPDATA") != null){
			dir = System.getenv("APPDATA");
		}
		capFile =  new File(dir + File.separator + ".GuitarTabViewer" + File.separator +  "cifras.cap");
		System.out.println(capFile.getAbsolutePath());
		caps = new Properties();
	}

	File capFile;
	private Properties caps;
	
	
	void load(){
		if(!capFile.exists()){
			return;
		}
		
		caps = new Properties();
		try {
			InputStream inputStream = new FileInputStream(capFile);
			caps.load(inputStream);
			
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
		capFile.getParentFile().mkdirs();
		
		FileOutputStream outputStream;
		try {
			if(!capFile.exists()){
				capFile.createNewFile();
			}
			
			outputStream = new FileOutputStream(capFile);
			caps.store(outputStream, "GuitarView Caps");
		
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
		this.cifraViewMain.panel.setCap(getCap());
	}

	public int getCap() {
		String key = curentFile.getParentFile().getName() + "/" + curentFile.getName();
		if(caps.containsKey(key)) {
			String property = caps.getProperty(key);
			return Integer.parseInt(property);
		}
		else {
			return 0;
		}
	}
	
	public void setCap(int cap) {
		caps.setProperty(curentFile.getParentFile().getName() + "/" + curentFile.getName(), Integer.toString(cap));
		this.cifraViewMain.panel.setCap(getCap());
	}
}

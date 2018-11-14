package br.com.ritcher.cifraview.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

final class CifraProperties {
	/**
	 * 
	 */
	private final CifraViewMain cifraViewMain;

	/**
	 * @param cifraViewMain
	 */
	CifraProperties(CifraViewMain cifraViewMain) {
		this.cifraViewMain = cifraViewMain;
		
		String dir = ".";
		if(System.getenv("APPDATA") != null){
			dir = System.getenv("APPDATA");
		}
		propertiesFile =  new File(dir + File.separator + "GuitarTabViewer" + File.separator +  "app.properties");
	}

	File propertiesFile;
	
	
	void loadProperties(){
		if(!propertiesFile.exists()){
			return;
		}
		
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(propertiesFile);
			properties.load(inputStream);
			
			this.cifraViewMain.file = new File(properties.getProperty("file"));
			this.cifraViewMain.fontsize = Integer.parseInt(properties.getProperty("fontSize"));
			this.cifraViewMain.lineSpacing = Integer.parseInt(properties.getProperty("lineSpacing"));
			this.cifraViewMain.antialiasing = Integer.parseInt(properties.getProperty("antialiasing"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		
	}

	void saveProperties() {
		if(this.cifraViewMain.file == null){
			return;
		}
		propertiesFile.getParentFile().mkdirs();
		
		Properties properties = new Properties();
		properties.put("file", this.cifraViewMain.file.getAbsolutePath());
		properties.put("fontSize", Integer.toString(this.cifraViewMain.fontsize));
		properties.put("lineSpacing", Integer.toString(this.cifraViewMain.lineSpacing));
		properties.put("antialiasing",Integer.toString(this.cifraViewMain.antialiasing));
		
		FileOutputStream outputStream;
		try {
			if(!propertiesFile.exists()){
				propertiesFile.createNewFile();
			}
			outputStream = new FileOutputStream(propertiesFile);
			properties.store(outputStream, "Guitar Tab Viewr Defaults");
			
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
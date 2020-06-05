package br.com.ritcher.cifraview.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class CifraFile {
	/**
	 * 
	 */
	private final CifraViewMain cifraViewMain;

	/**
	 * @param cifraViewMain
	 */
	CifraFile(CifraViewMain cifraViewMain) {
		this.cifraViewMain = cifraViewMain;
	}

	private List<String>  readFile(File file) {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line;
			while((line = reader.readLine())!= null){
				list.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	void openFile() {
		this.cifraViewMain.cifraTone.setCurentFile(this.cifraViewMain.file);
		this.cifraViewMain.lines = readFile(this.cifraViewMain.file);
		this.cifraViewMain.updateText();
	}
}
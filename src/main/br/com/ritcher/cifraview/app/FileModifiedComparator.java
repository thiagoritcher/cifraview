package br.com.ritcher.cifraview.app;

import java.io.File;
import java.util.Comparator;

final class FileModifiedComparator implements Comparator<File> {
	
	public int compare(File f1, File f2) {
		if(f1.lastModified() < f2.lastModified()){
			return 1;
		}
		else if(f1.lastModified() > f2.lastModified()){
			return -1;
		}
		else {
			return 0;
		}
	}
}
package br.com.ritcher.cifraview.app;

import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


final class CifraViewKeyListener extends KeyAdapter {
	
	/**
	 * 
	 */
	private final CifraActions cifraActions;

	/**
         * @param cifraViewMain
	 */
	CifraViewKeyListener(CifraActions cifraActions) {
		this.cifraActions = cifraActions;
	}
	


	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		if ((e.getKeyCode() == KeyEvent.VK_F1)){
           cifraActions.showHelp();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && ((e.getModifiers() & KeyEvent.ALT_MASK) != 0)){
            cifraActions.openLastFile();
		}
        else if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            cifraActions.openFile();
		}
		
		else if ((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            cifraActions.changeFont();
		}

		else if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            cifraActions.changeFontSpacing();
		}

		else if ((e.getKeyCode() == KeyEvent.VK_A) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            cifraActions.changeAntialiasing();
		}
		
		
		else if ((e.getKeyCode() == KeyEvent.VK_G) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            cifraActions.searchGoogle();
		}
		
		else if ((e.getKeyCode() == KeyEvent.VK_P) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            cifraActions.searchYoutube();
		}
	}
}

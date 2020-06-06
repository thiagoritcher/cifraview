package br.com.ritcher.cifraview.app;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
		else if ((e.getKeyCode() == KeyEvent.VK_O) && e.isControlDown() && e.isAltDown()){
            cifraActions.openLastFile();
		}
        else if ((e.getKeyCode() == KeyEvent.VK_O) && e.isControlDown()){
            cifraActions.openFile();
		}
		
		else if ((e.getKeyCode() == KeyEvent.VK_F) && e.isControlDown()){
            cifraActions.changeFont();
		}

		else if ((e.getKeyCode() == KeyEvent.VK_S) && e.isControlDown()){
            cifraActions.changeFontSpacing();
		}

		else if ((e.getKeyCode() == KeyEvent.VK_A) && e.isControlDown()){
            cifraActions.changeAntialiasing();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_J) && e.isControlDown()){
            cifraActions.decreaseTone();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_K) && e.isControlDown()){
            cifraActions.increaseTone();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_J) && e.isAltDown()){
            cifraActions.decreaseCap();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_K) && e.isAltDown()){
            cifraActions.increaseCap();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_G) && e.isControlDown()){
            cifraActions.searchGoogle();
		}
		
		else if ((e.getKeyCode() == KeyEvent.VK_P) && e.isControlDown()){
            cifraActions.searchYoutube();
		}
		if ((e.getKeyCode() == KeyEvent.VK_SPACE)){
            cifraActions.cicleScreen();
		}
	}
}

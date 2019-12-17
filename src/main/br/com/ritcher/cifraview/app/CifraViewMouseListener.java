package br.com.ritcher.cifraview.app;

import java.awt.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.*;
import javax.swing.JOptionPane;


final class CifraViewMouseListener extends MouseAdapter implements ActionListener {
	
    JPopupMenu popup;
    JMenuItem itemOpen, itemOpenLast, itemFontSize, itemFontSpacing,  itemFontAntialias, itemYoutube, itemGoogle, itemIncreaseTone, itemDecreaseTone; 

    
	/**
	 * 
	 */
	private final CifraActions cifraActions;

	/**
         * @param cifraViewMain
	 */
	CifraViewMouseListener(CifraActions cifraActions) {
		this.cifraActions = cifraActions;
        createMenu();
	}
	


	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
        if(e.isPopupTrigger())
            popup.show(e.getComponent(), e.getX(), e.getY());
	}


    public void createMenu() {
        popup = new JPopupMenu();

        itemOpen = new JMenuItem("Open");
        itemOpen.addActionListener(this);
        popup.add(itemOpen);
        
        itemOpenLast = new JMenuItem("Open last");
        itemOpenLast.addActionListener(this);
        popup.add(itemOpenLast);

        itemIncreaseTone = new JMenuItem("Increase tone");
        itemIncreaseTone.addActionListener(this);
        popup.add(itemIncreaseTone);
        
		itemDecreaseTone = new JMenuItem("Decrease tone");
        itemDecreaseTone.addActionListener(this);
        popup.add(itemDecreaseTone);

        itemFontSize = new JMenuItem("Font size");
        itemFontSize.addActionListener(this);
        popup.add(itemFontSize);

        itemFontSpacing = new JMenuItem("Font spacing");
        itemFontSpacing.addActionListener(this);
        popup.add(itemFontSpacing);

        itemFontAntialias = new JMenuItem("Font anti-alias");
        itemFontAntialias.addActionListener(this);
        popup.add(itemFontAntialias);
        
        itemGoogle = new JMenuItem("Google");
        itemGoogle.addActionListener(this);
        popup.add(itemGoogle);

        itemYoutube = new JMenuItem("Youtube");
        itemYoutube.addActionListener(this);
        popup.add(itemYoutube);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == itemOpen){
           cifraActions.openFile();
        }
        else if(e.getSource() == itemOpenLast){
           cifraActions.openLastFile();
        }
        else if(e.getSource() == itemFontSize){
           cifraActions.changeFont();
        }
        else if(e.getSource() == itemFontSpacing){
           cifraActions.changeFontSpacing();
        }
        else if(e.getSource() == itemFontAntialias){
           cifraActions.changeAntialiasing();
        }
        else if(e.getSource() == itemYoutube){
           cifraActions.searchYoutube();
        }
        else if(e.getSource() == itemGoogle){
           cifraActions.searchGoogle();
        }
        else if(e.getSource() == itemIncreaseTone){
           cifraActions.increaseTone();
        }
        else if(e.getSource() == itemDecreaseTone){
           cifraActions.decreaseTone();
        }

    }
}

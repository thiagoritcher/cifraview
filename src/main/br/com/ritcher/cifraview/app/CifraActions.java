package br.com.ritcher.cifraview.app;

import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


class CifraActions {
	
    private final CifraViewMain cifraViewMain;
	
    public CifraActions(CifraViewMain cifraViewMain) {
		this.cifraViewMain = cifraViewMain;
	}

    public void showHelp() {
       cifraViewMain.showHelp();
    }

    public void openLastFile() {
        if(this.cifraViewMain.file == null){
            return;
        }
        File folder = this.cifraViewMain.file.getParentFile();
        File[] files = folder.listFiles(new FileFilter(){
            public boolean accept(File f){
                return f.getName().endsWith(".txt");
            }
        });
        Arrays.sort(files, new FileModifiedComparator());
        this.cifraViewMain.file = files[0];
//        SimpleDateFormat format = new SimpleDateFormat();
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i].getName() + "\t" + format.format(new Date(files[i].lastModified())));
//        }
        this.cifraViewMain.cifraFile.openFile();
    }
    
    public void openFile() {
        JFileChooser jfc = new JFileChooser(this.cifraViewMain.file);
        
        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.cifraViewMain.file = jfc.getSelectedFile();
            this.cifraViewMain.cifraFile.openFile();
        }
    }


    public void changeFont() {
        String size = JOptionPane.showInputDialog(this.cifraViewMain.frame, "Digite o tamanho da fonte:(No inteiro)");
        try {
            if(size == null){
                return;
            }
            this.cifraViewMain.fontsize = Integer.parseInt(size);
            this.cifraViewMain.updateText();
            
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Valor invalido", "Erro", JOptionPane.ERROR_MESSAGE); 
            e2.printStackTrace();
        }

    }

    public void changeFontSpacing() {
        String size = JOptionPane.showInputDialog(this.cifraViewMain.frame, "Digite o espaçamento da fonte (Quanto maior menos espaço):");
        try {
            if(size == null){
                return;
            }
            this.cifraViewMain.lineSpacing = Integer.parseInt(size);
            this.cifraViewMain.updateText();
            
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Valor invalido", "Erro", JOptionPane.ERROR_MESSAGE); 
            e2.printStackTrace();
        }

    }

    public void changeAntialiasing() {
        String size = JOptionPane.showInputDialog(this.cifraViewMain.frame, "Configuração antialias (1-8):\n" +
                "1:ON 2:OFF 3:DEFAULT 4:GASP 5:LCD_HRGB 6:LCD_HBGR 7:LCD_VRGB 8:LCD_VBGR");
        try {
            if(size == null){
                return;
            }
            
            int sizei = Integer.parseInt(size);
            if(sizei < 1 || sizei > 8){
                JOptionPane.showMessageDialog(null, "Valor invalido", "Erro", JOptionPane.ERROR_MESSAGE); 
                return;
            }
            
            this.cifraViewMain.antialiasing =  sizei;
            this.cifraViewMain.updateText();
            
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Valor invalido", "Erro 2", JOptionPane.ERROR_MESSAGE); 
            e2.printStackTrace();
        }
    }

	public void increaseTone(){
    	this.cifraViewMain.getPanel().increaseTone();
	}
	
	public void decreaseTone(){
    	this.cifraViewMain.getPanel().decreaseTone();
	}
    
    public void searchGoogle() {
        String term = JOptionPane.showInputDialog(this.cifraViewMain.frame, "Input the search term:");
         try {
             if(term == null){
                return;
             }	
             term += " cifraclub";
             Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + URLEncoder.encode(term, "UTF-8")));
            
        } catch (IOException e1) {	
            JOptionPane.showMessageDialog(null, "Falha ao carregar", "Erro 1", JOptionPane.ERROR_MESSAGE); 
            e1.printStackTrace();
            
        } catch (URISyntaxException e1) {
            JOptionPane.showMessageDialog(null, "Valor invalido", "Erro 2", JOptionPane.ERROR_MESSAGE); 
            e1.printStackTrace();
        }
    }

    public void searchYoutube() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query=" + URLEncoder.encode(this.cifraViewMain.file.getName(), "UTF-8")));
            
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Problemas ao abrir", "Erro", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
            
        } catch (URISyntaxException e1) {
            JOptionPane.showMessageDialog(null, "Valor invalido", "Erro 2", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    public void cicleScreen() {
    	this.cifraViewMain.cicleScreen();
    }
}

package br.com.ritcher.cifraview.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class CifraViewMain {

	public static void main(String[] args) {
		CifraViewMain app = new CifraViewMain();
		app.createWindow();
		if(args.length > 0){
			app.file = new File(args[0]);
			app.cifraFile.openFile();
		}
	}
	
	JFrame frame;
	JPanel panel;
	List<String> lines;
	File file;
	
	int fontsize = 12;
	int lineSpacing = 0;
	int antialiasing = 2;
	
	CifraProperties cifraProperties = new CifraProperties(this);
	
	CifraFile cifraFile = new CifraFile(this);

	public void createWindow(){
		cifraProperties.loadProperties();
		
		frame = new JFrame("Guitar Tab Viewer");
		frame.setMinimumSize(new Dimension(300, 100));
		frame.setLayout(new BorderLayout());
		
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				super.componentResized(arg0);
				updateText();
			}
		});
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				cifraProperties.saveProperties();
			}
		});
		
		frame.addKeyListener(new CifraViewKeyListener(this));
		
		panel = new CifraPanel(this);
		//panel.setBackground(Color.white);
		GridLayout layout = new GridLayout();
		layout.setRows(1);
		
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);

		frame.add(panel);
		
		if(file != null){
			cifraFile.openFile();
		}
	}
	
	
	void updateText() {
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
}

package br.com.ritcher.cifraview.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.JPanel;


final class CifraPanel extends JPanel {
	/**
	 * 
	 */
	private final CifraViewMain cifraViewMain;

	/**
	 * @param cifraViewMain
	 */
	CifraPanel(CifraViewMain cifraViewMain) {
		this.cifraViewMain = cifraViewMain;
		this.chordLine = new ChordLine();	
		this.chordLine.setToneFile(cifraViewMain.cifraTone);
        this.reset();
	}

	private static final long serialVersionUID = 1L;

	static public Object[] textAntialiasOptions = {
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON,
		RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
		RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
		RenderingHints.VALUE_TEXT_ANTIALIAS_GASP,
		RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB,
		RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR,
		RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB,
		RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR
		
	};


	ChordLine chordLine;

	
	public void increaseTone(){
		this.chordLine.increaseTone();
		repaint();
	}
	
	public void decreaseTone(){
		this.chordLine.decreaseTone();
		repaint();
	}
	
	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		
		Graphics2D g = (Graphics2D) g1;
		if(this.cifraViewMain.lines == null){
			return;
		}
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             textAntialiasOptions[this.cifraViewMain.antialiasing - 1]);
		
		g.setRenderingHints(rh);
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		Font textFont = new Font(Font.MONOSPACED, Font.PLAIN, this.cifraViewMain.fontsize);
		Color textColor = Color.white;
		
		Font chordFont = new Font(Font.MONOSPACED, Font.BOLD, this.cifraViewMain.fontsize);
		Color chordColor = Color.YELLOW;
		
		FontMetrics fontMetrics = this.cifraViewMain.frame.getFontMetrics(textFont);
		int fontHeight = fontMetrics.getHeight() - this.cifraViewMain.lineSpacing;
		
		int y = fontHeight;
		Rectangle2D bounds;
		double maxx = 0;
		x = -xStart; 
		boolean hasNextCicle = false;
		
		for (Iterator<String> iterator = this.cifraViewMain.lines.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			boolean isChordLine = false;
			if(chordLine.isChordLine(string)){
				string = chordLine.getLine(string);

				g.setFont(chordFont);
				g.setColor(chordColor);
				isChordLine = true;
			}
			else {
				g.setFont(textFont);
				g.setColor(textColor);
			}
			
			g.drawString(string, x, y);
			
			
			y += fontHeight;
			
			bounds = fontMetrics.getStringBounds(string, 0, string.length(), g);
			maxx = Math.max(maxx, bounds.getWidth());
			
			double space = isChordLine ? fontHeight: fontHeight * 2;
			space += 10;
			
			if(y > getHeight() - space){
				x += maxx + 20;
				y = fontHeight;
				
				if(x + maxx > getWidth()) {
					nextcicle = xStart + x;
					hasNextCicle = true;
					//System.out.println("Break " + nextcicle);
					break;
				}
			}
		}
		
		if(!hasNextCicle) {
			nextcicle = -5;
//			System.out.println("Reseting ");
		}
		
		g.setFont(textFont);
		g.setColor(textColor);
		g.drawString(Integer.toString(chordLine.getTone()), getWidth() - 20, 20);
	}
	
	private int x, xStart;

	private int nextcicle;
	
    public void reset() {
         x = 0;
         xStart = -5;
         nextcicle = -5;

//		 this.chordLine.setTone(0);
		 repaint();
	}

	public void cicle() {
		 xStart = nextcicle - 5;
		 repaint();
	}
}

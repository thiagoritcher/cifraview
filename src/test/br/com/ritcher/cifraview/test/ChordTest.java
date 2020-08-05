package br.com.ritcher.cifraview.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.ritcher.cifraview.app.ChordLine;
import br.com.ritcher.cifraview.app.ToneSetting;

class ChordTest {
	
	@Test
	public void chordLine() {
		ChordLine cl = new ChordLine();
		cl.setToneFile(new ToneSetting() {
			int tone;
			public void setTone(int tone) {
				this.tone = tone;
			}
		});
		
		assertTrue(cl.isChordLine("  G/A Dm  Gb  CM7  F(9m) Gm/D G/D Gdim Fº/D"));
		assertTrue(cl.isChordLine("A A"));
		assertTrue(cl.isChordLine("A/A A"));
		
		assertFalse(cl.isChordLine("Como que vai ser"));
		assertFalse(cl.isChordLine("AA"));
		
		cl.setTone(-2);
		String param = "  G/A Dm  Gb  CM7  F(9m) Gm/D G/D Gdim";
		String result = cl.getLine(param);
		assertEquals("  F/G Cm  E  A#M7  D#(9m) Fm/C F/C Fdim", result);
	}

	@Test
	public void chord() {
		ChordLine cl = new ChordLine();
		assertEquals("Cm", cl.getChord("A#m", 2));
		assertEquals("G#m", cl.getChord("A#m", -2));
	}
}

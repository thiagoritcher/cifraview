package br.com.ritcher.cifraview.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import br.com.ritcher.cifraview.app.ChordLine;
import br.com.ritcher.cifraview.app.CifraToneFile;
import br.com.ritcher.cifraview.app.ToneSetting;

public class ChordLineTest {
	@Test
	public void chord() {
		ChordLine cl = new ChordLine();
		String cr = cl.getChord("A#m", 2);
		assertEquals("Cm", cl.getChord("A#m", 2));
		assertEquals("G#m", cl.getChord("A#m", -2));
	}

	@Test
	public void chordLine() {
		ChordLine cl = new ChordLine();
		cl.setToneFile(new ToneSetting() {
			int tone;
			@Override
			public void setTone(int tone) {
				this.tone = tone;
			}
		});
		
		cl.setTone(-2);
		String param = "  G/A Dm  Gb  CM7  F(9m) Gm/D G/D)";
		String result = cl.getLine(param);
		assertEquals("  F/G Cm  E  A#M7  D#(9m) Fm/C F/C)", result);
	}
}

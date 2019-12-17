package br.com.ritcher.cifraview.test;

import static org.junit.Assert.*;
import org.junit.Test;
import br.com.ritcher.cifraview.app.*;

public class ChordLineTest {
  @Test public void chord() {
  		ChordLine cl = new ChordLine();
		String cr = cl.getChord("A#m", 2);
		assertEquals("Cm", cl.getChord("A#m", 2));
		assertEquals("Gm", cl.getChord("A#m", -2));
  }

  @Test public void chordLine() {
  		ChordLine cl = new ChordLine();
		cl.setTone(-2);
		String param = "( Dm  Gb  CM7  F(9m) Gm/D G/D)";
		String result =	cl.getLine(param);
		assertEquals( "( Cm  E  A#M7  D#(9m) Fm/C F/C)", result);
	}
}

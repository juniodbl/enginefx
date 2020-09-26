package br.com.engine.input;

import java.awt.event.KeyEvent;

public enum KeyMap 
{
	UP     (KeyEvent.VK_UP),
	DOWN   (KeyEvent.VK_DOWN),
	LEFT   (KeyEvent.VK_LEFT),
	RIGHT  (KeyEvent.VK_RIGHT),
	SPACE  (KeyEvent.VK_SPACE),
	ESCAPE (KeyEvent.VK_ESCAPE),
	ENTER  (KeyEvent.VK_ENTER),
	CONTROL(KeyEvent.VK_CONTROL),
	SHIFT  (KeyEvent.VK_SHIFT),
	ALT    (KeyEvent.VK_ALT),
	TAB    (KeyEvent.VK_TAB),
	W      (KeyEvent.VK_W),
	A      (KeyEvent.VK_A),
	S      (KeyEvent.VK_S),
	D      (KeyEvent.VK_D),
	Q      (KeyEvent.VK_Q),
	E      (KeyEvent.VK_E),
	Z      (KeyEvent.VK_Z),
	X      (KeyEvent.VK_X),
	C      (KeyEvent.VK_C);
	
	private int key;
	
	KeyMap( int key )
	{
		this.key = key;
	}

	public int getKey( )
	{
		return key;
	}
}
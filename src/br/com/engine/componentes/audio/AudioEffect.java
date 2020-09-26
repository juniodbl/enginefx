package br.com.engine.componentes.audio;

import javafx.scene.media.AudioClip;
import br.com.engine.componentes.SimpleComponent;
import br.com.engine.resources.ResourceManager;

public class AudioEffect extends SimpleComponent 
{
	private AudioClip clip;
	private String fileName;
	
	public AudioEffect( String name )
	{
		this.fileName = name;
	}
	
	@Override
	public void setup( )
	{
		clip = ResourceManager.loadResource( fileName, ResourceManager.AUDIO, AudioClip.class );
	}
	
	public void play( )
	{
		clip.play( );
	}
	
	public void stop()
	{
		clip.stop( );
	}
	
	@Override 
	public void draw( ) { }
	@Override 
	public void update( long time ) { }
}
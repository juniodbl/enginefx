package br.com.engine.componentes.debug;

import javafx.scene.paint.Color;
import br.com.engine.componentes.SimpleComponent;
import br.com.engine.componentes.drawable.Sprite;
import br.com.engine.core.ControleBase;

public class SpriteDebug extends SimpleComponent 
{
	@Override
	public void setup( )
	{
		
	}

	@Override
	public void draw( )
	{
		Sprite[] sprites = getParent( ).getComponent( Sprite[].class );
		
		for( Sprite sprite : sprites )
		{
			int x = (int)getParent( ).getPosition( ).getX( ) + (int)sprite.getPosition( ) .getX( );
			int y = (int)getParent( ).getPosition( ).getY( ) + (int)sprite.getPosition( ) .getY( );
			
			ControleBase.getInstance( ).getGraphics2d( ).save( );
			ControleBase.getInstance( ).getGraphics2d( ).setStroke( Color.RED );
			ControleBase.getInstance( ).getGraphics2d( ).strokeRoundRect( x, y, sprite.getWidth( ), sprite.getHeight( ), 0, 0 );
			ControleBase.getInstance( ).getGraphics2d( ).restore( );
		}
	}
	
	@Override
	public void update( long time ) 
	{
	}
}
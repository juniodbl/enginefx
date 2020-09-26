package br.com.engine.componentes.debug;

import javafx.scene.paint.Color;
import br.com.engine.componentes.SimpleComponent;
import br.com.engine.componentes.physics.CustomCubeColisor;
import br.com.engine.componentes.physics.OvalCustomCubeColisor;
import br.com.engine.componentes.physics.SpriteColisor;
import br.com.engine.core.ControleBase;
import br.com.engine.interfaces.CubeColisor;

public class ColisorDebug extends SimpleComponent 
{
	@Override
	public void setup( )
	{
		
	}

	@Override
	public void draw( )
	{
		CubeColisor[] rectangles = getParent( ).getComponent( CubeColisor[].class );
		
		for( CubeColisor colisor : rectangles )
		{
			ControleBase.getInstance( ).getGraphics2d( ).save( );
			ControleBase.getInstance( ).getGraphics2d( ).setStroke( Color.GREEN );
			
			if( colisor instanceof CustomCubeColisor || colisor instanceof SpriteColisor )
			{
				ControleBase.getInstance( ).getGraphics2d( ).strokeRoundRect( colisor.getRectangle( ).getX( ), 
																			  colisor.getRectangle( ).getY( ), 
																			  colisor.getRectangle( ).getWidth( ), 
																			  colisor.getRectangle( ).getHeight( ), 0, 0 );
			}
			else if( colisor instanceof OvalCustomCubeColisor )
			{
				ControleBase.getInstance( ).getGraphics2d( ).strokeOval( colisor.getRectangle( ).getX( ), 
																		 colisor.getRectangle( ).getY( ), 
																		 colisor.getRectangle( ).getWidth( ), 
																		 colisor.getRectangle( ).getHeight( ) );
			}
			
			ControleBase.getInstance( ).getGraphics2d( ).restore( );
		}
	}

	@Override
	public void update(long time) 
	{
	}
}
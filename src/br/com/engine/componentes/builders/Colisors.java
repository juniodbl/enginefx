package br.com.engine.componentes.builders;

import br.com.engine.componentes.physics.CustomCubeColisor;
import br.com.engine.componentes.physics.OvalCustomCubeColisor;
import br.com.engine.componentes.physics.ShapeCubeColisor;
import br.com.engine.componentes.physics.SpriteColisor;
import br.com.engine.core.Vector2;
import br.com.engine.interfaces.CubeColisor;

public class Colisors 
{
	private Colisors( ) { }
	
	public static CubeColisor cube( )
	{
		return new ShapeCubeColisor( );
	}
	
	public static CubeColisor cube( String tag )
	{
		ShapeCubeColisor cube = (ShapeCubeColisor)Colisors.cube( );
		cube.setTag(tag);
		return cube;
	}
	
	public static CubeColisor custom( Vector2 position, int w, int h )
	{
		return new CustomCubeColisor( new Vector2(position.x, position.y), w, h );
	}
	
	public static CubeColisor ovalCustom( Vector2 position, int radius )
	{
		return new OvalCustomCubeColisor( position, radius );
	}
	
	public static CubeColisor simpleSprite( String tag )
	{
		SpriteColisor colisor = new SpriteColisor( );
		colisor.setTag( tag );
		
		return colisor;
	}
}
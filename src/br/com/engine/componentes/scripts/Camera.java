package br.com.engine.componentes.scripts;

import br.com.engine.componentes.SimpleComponent;
import br.com.engine.componentes.VectorMonitor;
import br.com.engine.core.ControleBase;
import br.com.engine.core.Vector2;

public class Camera extends SimpleComponent
{
	private ControleBase cb;
	
	@Override
	public void setup( )
	{
		cb = ControleBase.getInstance( );
		
		getParent().addComponente(new VectorMonitor((vec, OldVec) ->{
			Vector2 diff = OldVec.diff( vec );
//			diff.reverse( );
			
			cb.getGraphics2d( ).translate( diff.x, diff.y );
		}));
	}

	@Override
	public void update( long time )
	{
		
	}

	@Override
	public void draw( ) 
	{
	}
}
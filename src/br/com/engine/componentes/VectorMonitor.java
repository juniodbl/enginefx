package br.com.engine.componentes;

import br.com.engine.core.Vector2;

public class VectorMonitor extends SimpleComponent 
{
	private Vector2 oldVec;
	private OnChageAction action;

	public VectorMonitor( OnChageAction action )
	{
		this.oldVec = new Vector2( 0, 0 );
		this.action = action;
	}
	
	@Override
	public void setup( )
	{
		this.oldVec = new Vector2( getParent().getPosition().x, getParent().getPosition().y );
		
		action.action(getParent().getPosition(), oldVec);
	}

	@Override
	public void update( long time ) 
	{
		if( !oldVec.equals( getParent().getPosition() ) )
		{
			action.action( getParent().getPosition(), oldVec );
		}
		
		oldVec.setPosition( getParent().getPosition().x, getParent().getPosition().y );
	}

	@Override
	public void draw( )
	{
	}

	public interface OnChageAction
	{
		void action( Vector2 vec, Vector2 oldVec );
	}
}
package br.com.engine.componentes.physics;

import br.com.engine.componentes.SimpleComponent;
import br.com.engine.core.Vector2;
import br.com.engine.interfaces.CubeColisor;
import br.com.engine.interfaces.OnColisionAction;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class OvalCustomCubeColisor extends SimpleComponent implements CubeColisor
{
	private String tag;
	
	private Circle circle;
	
	private OnColisionAction onColisionAction;
	
	private Vector2 position;
	private int radius;
	
	public OvalCustomCubeColisor( Vector2 position, int radius )
	{
		this.position = position;
		this.radius = radius;
	}
	
	@Override
	public void setup( )
	{
		circle = new Circle( 0, 0, radius );
	}
	
	@Override
	public void onColision( CubeColisor colided )
	{
		if( onColisionAction != null )
		{
			onColisionAction.execute( colided );
		}
	}
	
	@Override
	public void setOnColisionAction( OnColisionAction onColisionAction ) 
	{
		this.onColisionAction = onColisionAction;
	}
	
	@Override
	public void draw( )
	{
		
	}
	
	@Override
	public Rectangle getRectangle( )
	{
		circle.setCenterX( getParent( ).getPosition( ).getX( )+position.getX( ) );
		circle.setCenterY( getParent( ).getPosition( ).getY( )+position.getY( ) );
		
		circle.intersects(0, 0, 0, 0);
		
		return null;//TODO: verificar
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}

	@Override
	public void update( long time ) 
	{
	}
}
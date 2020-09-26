package br.com.engine.input;

import java.util.ArrayList;
import java.util.List;

import br.com.engine.interfaces.IMouseClick;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Mouse  implements EventHandler<MouseEvent>
{
	private static Mouse instance;
	
	private List<IMouseClick> onClick = new ArrayList<>( );
	
	private Mouse( ){ }

	public static Mouse infInstace( )
	{
	    if( instance == null )
	    {
	        instance = new Mouse( );
	    }
	    
        return instance;
	}
	
	@Override
	public void handle( MouseEvent event ) 
	{
		onClick.forEach( r -> r.onClick( event ) );
	}
	
	public void addListener( IMouseClick onClick ) {
		this.onClick.add( onClick );
	}
}
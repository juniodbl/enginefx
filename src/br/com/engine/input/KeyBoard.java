package br.com.engine.input;

import java.util.Collection;
import java.util.HashSet;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyBoard implements EventHandler<KeyEvent>
{
    private static KeyBoard instance;

    private Collection<KeyCode> lstCurrent = new HashSet<KeyCode>( );
    
	public static KeyBoard infInstace( )
	{
	    if( instance == null )
	    {
	        instance = new KeyBoard( );
	    }
	    
        return instance;
	}
	
	private KeyBoard( )
	{
	    
    }

    @Override
    public void handle( KeyEvent event )
    {
        if( event.getEventType( ) == KeyEvent.KEY_PRESSED )
        {
            lstCurrent.add( event.getCode( ) );
        }
        else if( event.getEventType( ) == KeyEvent.KEY_RELEASED )
        {
            lstCurrent.removeIf( item -> item.ordinal( ) == event.getCode( ).ordinal( ) );
        }
    }

    public void ifKeyPressed( KeyCode key, Runnable run )
    {
        lstCurrent.stream( ).forEach( code -> 
        {
            if( key == code )
            {
                run.run( );
            }
        } );
    }
}
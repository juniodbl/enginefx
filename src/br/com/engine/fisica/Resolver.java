package br.com.engine.fisica;

import br.com.engine.core.Vector2;
import br.com.engine.input.KeyMap;

public class Resolver
{
    public static int getDirection( Vector2 old, Vector2 newPos )
    {
        if( old.getX( ) > newPos.getX( ) )
        {
            return KeyMap.LEFT.getKey( );
        }
        else if( old.getX( ) < newPos.getX( ) )
        {
            return KeyMap.RIGHT.getKey( );
        }
        else if( old.getY( ) > newPos.getY( ) )
        {
            return KeyMap.UP.getKey( );
        }
        else if( old.getY( ) < newPos.getY( ) )
        {
            return KeyMap.DOWN.getKey( );
        }
        
        return -1;
    }
}
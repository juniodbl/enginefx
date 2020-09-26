package br.com.engine.core;



public class Vector2
{
	public float x;
	public float y;

	public Vector2( ) 
    {
        x = 0;
        y = 0;
    }

    public Vector2( float x, float y ) 
    {
    	this.x = x;
    	this.y = y;
    }
	
    public void setPosition( float x, float y )
    {
        this.x = x;
        this.y = y;
    }
    
    public float getX( )
    {
        return x;
    }
    
    public float getY( )
    {
        return y;
    }
    
    public void plus( Vector2 vec )
    {
    	setPosition( x + vec.x, y + vec.y );
    }
    
    public void plus( float x, float y )
    {
    	setPosition( this.x + x, this.y + y );
    }
    
    public void less( Vector2 vec )
    {
    	setPosition( x - vec.x, y - vec.y );
    }
    
    public void less( float x, float y )
    {
    	setPosition( this.x - x, this.y - y );
    }

    public void reverse( )
    {
        reverseX( );
        reverseY( );
    }
    
    public void reverseX( )
    {
    	setPosition( x * -1, y );
    }
    
    public void reverseY( )
    {
    	setPosition( x, y * -1 );
    }
    
    @Override
    public String toString( )
    {
    	return String.format( "X:%s Y:%s", x, y );
    }
    
    public Vector2 diff(Vector2 position) {
		return new Vector2( this.x -position.x, this.y -position.y );
	}
    
    @Override
    public boolean equals(Object obj) {
    	return this.x == ((Vector2)obj).x &&
    			this.y == ((Vector2)obj).y;
    }
}
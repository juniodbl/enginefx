package br.com.engine.componentes.drawable;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.com.engine.componentes.SimpleComponent;
import br.com.engine.core.ControleBase;
import br.com.engine.core.Vector2;
import br.com.engine.resources.ResourceManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpriteFont extends SimpleComponent
{
	private String  texto    = "";
	private Vector2 position = new Vector2( 0, 0 ); 
	private Color   color    = Color.BLACK;
	private int     fontSize;
	
	private Font font;
	private Text text;
	
	private GraphicsContext graphics;   
	
	private String fontName;

	public SpriteFont( int fontSize )
	{
		this( );
		
		this.fontSize = fontSize;
	}
	
	public SpriteFont( String name )
	{
		this( );
		
		this.fontName = name;
	}
	
	public SpriteFont( String name, int fontSize )
	{
		this( );
		
		this.fontSize = fontSize;
		this.fontName = name;
		
		loadFont( );
	}
	
	public SpriteFont( )
	{
		fontSize = 20;
		
		loadFont( );
		
		text = new Text( getTexto( ) );
		text.setFont( font );
	}
	
	@Override
	public void setup( ) 
	{
		
	}

	@Override
	public void draw( ) 
	{
		this.graphics = ControleBase.getInstance( ).getGraphics2d( );
		
		graphics.save( );
		
		int x = (int)getParent( ).getPosition( ).getX( ) + (int)getPosition( ).getX( );
        int y = (int)getParent( ).getPosition( ).getY( ) + (int)getPosition( ).getY( );
		
		graphics.setTextBaseline(VPos.TOP);
		
		graphics.setFont( font );
		graphics.setFill( getColor( ) );
	    graphics.fillText( getTexto( ), x, y );
	    
	    graphics.restore( );
	}	
	
	public String getTexto( )
	{
		return texto;
	}

	public double getStringWidth( )
    {
	    return new Text( getTexto( ) ).getLayoutBounds( ).getWidth( );
    }
	
	public void setText( String text )
	{
		this.texto = text;
	}

	public Vector2 getPosition( )
	{
		return position;
	}
	
	public void setPosition( Vector2 position )
	{
		this.position = position;
	}
	
	public void setPosition( int x, int y )
	{
		this.position.setPosition( x, y );
	}

	public Color getColor( ) 
	{
		return color;
	}

	public void setColor( Color color )
	{
		this.color = color;
	}
	
	public int getFontSize( )
	{
		return fontSize;
	}
	
	public void setFontSize( int fontSize )
	{
		this.fontSize = fontSize;
		
		loadFont( ); 
	}
	
	private void loadFont( )
	{
		if( StringUtils.isBlank( getFontName() ) )
		{
			font = new Font( "Arial", fontSize );
		}
		else
		{
			Map<String, Object> emptyMap = new HashMap<String, Object>( );
			emptyMap.put( "size", fontSize );
			
			font = ResourceManager.loadResource( this.getFontName(), ResourceManager.FONT, Font.class, emptyMap ); 
		}
	}
	
	public int getWidth( )
	{
		return (int)this.text.getLayoutBounds( ).getWidth( );
	}
	
	public int getHeight( )
	{
		return (int)this.text.getLayoutBounds( ).getHeight( );
	}
	
	private String getFontName() {
		return fontName;
	}
	
	public void setFont( String fontName )
	{
		this.fontName = fontName;
		
		loadFont();
	}

	@Override
	public void update( long time ) 
	{
	}
}
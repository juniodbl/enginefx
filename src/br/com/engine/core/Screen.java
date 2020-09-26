package br.com.engine.core;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class Screen
{
	private Canvas canvas;
	
	public Screen( double width, double height )
	{
		canvas = new Canvas( width, height );
	}
	
	public Canvas getCanvas( )
	{
		return canvas;
	}
	
	public GraphicsContext getGraphicsContext( )
	{
		return canvas.getGraphicsContext2D( );
	}

	public double getWidth( )
	{
		return canvas.getWidth( );
	}

	public double getHeight( )
	{
		return canvas.getHeight( );
	}
}
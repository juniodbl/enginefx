package br.com.engine.resources;

import java.util.Properties;

public class ConfigurationsManager 
{
	private static ConfigurationsManager propertiesBean;
	
	private Properties properties;
	
	private ConfigurationsManager( ) 
	{ 
		try 
		{
			this.properties = (Properties)ContentLoader.loadContent( "config.properties" );
		} 
		catch( Exception e )
		{
			System.err.println("config file not finded.");
		}
	}
		
	public static ConfigurationsManager getInstance( )
	{
		if( propertiesBean == null )
			propertiesBean = new ConfigurationsManager( );
		
		return propertiesBean;
	}
	
	public Integer getWidth( )
	{
		Integer width = null;
		
		try
		{
			width = new Integer( this.properties.getProperty( TiposPropertiesManager.SIZE_W.getValor( ) ) );
		}
		catch( Exception e )
		{
			
		}
		
		return width;
	}
	
	public Integer getHeight( )
	{
		Integer height = null;
		
		try
		{
			height = new Integer( this.properties.getProperty( TiposPropertiesManager.SIZE_H.getValor( ) ) );
		}
		catch( Exception e )
		{
			
		}
		
		return height;
	}
	
	public boolean getDebugMode( )
	{
		boolean debug = false;
		
		try
		{
			debug = new Boolean( this.properties.getProperty( TiposPropertiesManager.DEBUG_MODE.getValor( ) ) ).booleanValue( );
		}
		catch( Exception e )
		{
			
		}
		
		return debug;
	}
}
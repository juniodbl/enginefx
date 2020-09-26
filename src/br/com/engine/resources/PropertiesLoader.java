package br.com.engine.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public class PropertiesLoader 
{
	public Map<String, String> load( )
	{
		Map<String, String> props = new HashMap<String, String>( );
		
		try 
		{
			Stream<Path> list = Files.find( Paths.get( "./res" ), Integer.MAX_VALUE, (p,b)-> p.getFileName().toString().contains(".properties") && 
																							 !p.getFileName().toString().equals("config.properties") );
			
			list.forEach( path -> 
			{
				try 
				{
					Properties p = new Properties( );
					p.load( new FileInputStream( path.toString( ) ) );
					p.entrySet().forEach( entry -> props.put( (String)entry.getKey( ), (String)entry.getValue( ) ) );
				} 
				catch( Exception e )
				{
					e.printStackTrace( );
				}
			});
			
			list.close( );
		} 
		catch( IOException e )
		{
			e.printStackTrace( );
		}
		
		return props;
	}
}
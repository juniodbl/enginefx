package br.com.engine.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.engine.core.Scene;

public class ScenesLoader 
{
	public ScenesLoader( )
	{
		super();
	} 
	
	public List<Scene> load( )
	{
		List<Scene> scenes = new ArrayList<Scene>( );
		
		ScenesDefinition[] ret = null;
		
		try 
		{
			JsonArray jsonArray = ((JsonObject)ContentLoader.loadContent( "list_scenes.json" )).get( "mapas" ).getAsJsonArray( );
			
			ret = new ScenesDefinition[jsonArray.size( )];
			
			for( int i = 0; i < ret.length; i++ )
			{
				JsonObject object = jsonArray.get( i ).getAsJsonObject( );
				
				ret[i] = new ScenesDefinition( object.get( "class" ).getAsString( ), object.get( "type" ).getAsString( ) );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		
		List<ScenesDefinition> lstMaps = Arrays.asList(ret);
		
		for( int i = 0; i < lstMaps.size( ); i++ )
		{
			scenes.add( lstMaps.get( i ).getNewScene( ) );
		}
		
		return scenes;
	}
}
package br.com.engine.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.mapeditor.io.TMXMapReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class ContentLoader {
	
	public static Object loadContent( String name, Map<String, Object> data )
	{
		try
		{
			Path srcPath = Paths.get("./res");
			
			List<Path> filesPaths = Files.find(srcPath, Integer.MAX_VALUE, (p,b)->{
				return !Files.isDirectory(p);
			} ).collect( Collectors.toList() );
			
			List<Path> listDiscovered = filesPaths.stream().filter( p ->{ 
				if( name.contains(".") ) {
					return p.getFileName( ).toString( ).equals( name );
				}
				else {
					return p.getFileName( ).toString( ).replaceAll("\\..*$", "").equals( name );
				}
			}).collect( Collectors.toList( ) );
			
			if(listDiscovered.size( ) == 0)
			{
				throw new Exception( "file "+name+" Not found!" );
			}
			else if( listDiscovered.size( ) == 1 )
			{
				Path file = listDiscovered.get( 0 );
				
				if( data != null )
					return discoveryAndLoad( file, data );
				else
					return discoveryAndLoad( file );
			}
			else
			{
				throw new Exception( "many files named "+ name +", format specification is required ex:(\"FileName.png\")");
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static Object loadContent( String name )
	{
		return loadContent( name, Collections.emptyMap( ) );
	}

	private static Object discoveryAndLoad(Path file, Map<String, Object> data) throws Exception
	{
		if( file.getFileName().toString().matches( ".*(\\.(gif|jpg|png))$" ) )
		{
			return loadImage(file);
		}
		else if( file.getFileName().toString().matches( ".*(\\.(properties))$" ) )
		{
			return loadConfigs(file);
		}
		else if( file.getFileName().toString().matches( ".*(\\.(mp3|wav))$" ) )
		{
			return loadAudio(file);
		}
		else if( file.getFileName().toString().matches( ".*(\\.(js))$" ) )
		{
			return loadScript( file, data );
		}
		else if( file.getFileName().toString().matches( ".*(\\.(ttf))$" ) )
		{
			return loadFont( file, data );
		}
		else if( file.getFileName().toString().matches( ".*(\\.(json))$" ) )
		{
			return loadJson( file );
		}
		else if( file.getFileName().toString().matches( ".*(\\.(tmx))$" ) )
		{
			return loadMap( file );
		}
		else
		{
			throw new Exception("File format is not supported, "+file.getFileName().toString().replaceAll( "^.*\\.", "" ) );
		}
	}
	
	private static Object discoveryAndLoad(Path file) throws Exception 
	{
		return discoveryAndLoad(file, Collections.emptyMap());
	}
	
	private static Object loadImage( Path path ) throws FileNotFoundException
	{
		Image image = null;
		
		image = new Image( new FileInputStream( path.toFile( ) ) );
		
		return image;
	}
	
	private static Object loadConfigs( Path path ) throws FileNotFoundException, IOException
	{
		Properties p = new Properties( );
		
		p.load( new FileInputStream( path.toFile( ) ) );
		
		return p;
	}
	
	private static Object loadAudio( Path path )
	{
		return new AudioClip( path.toUri().toString() );
	}
	
	private static Object loadScript( Path path, Map<String, Object> data ) throws FileNotFoundException, ScriptException
    {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager( );
        ScriptEngine nashorn = scriptEngineManager.getEngineByName( "nashorn" );
        
        if( data != null )
        {
            data.forEach( (key, value) ->
            {
                nashorn.put( key, value );
            } );
        }
        
        nashorn.eval( new FileReader( path.toFile( ) ) );
        
        return nashorn; 
    }
	
	private static Object loadFont( Path path, Map<String, Object> data ) throws FileNotFoundException
	{
		return Font.loadFont( new FileInputStream(  path.toFile( ) ), (int)data.get( "size" ) );
	}

	private static Object loadJson( Path path ) throws JsonSyntaxException, JsonIOException, FileNotFoundException
	{
		Gson gson = new GsonBuilder( ).create( );
		JsonObject jsonObject = gson.fromJson( new FileReader( path.toFile( ) ), JsonObject.class );
		
		return jsonObject;
	}

	private static org.mapeditor.core.Map loadMap( Path path ) throws FileNotFoundException, Exception
	{
		return new TMXMapReader( ).readMap( new FileInputStream( path.toFile( ) ) );
	}
	
	public static void main(String[] args) throws Exception {
		Image img = (Image)loadContent("Player.png");
		Properties conf = (Properties)loadContent("config");
		
		System.out.println(img.getWidth());
		
		conf.keySet().forEach( o -> System.out.println(o) );
	}
}
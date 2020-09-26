package br.com.engine.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.mapeditor.io.TMXMapReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class ResourceManager 
{
	public static final int IMAGEM        = 1;
	public static final int CONFIGURACOES = 2;
	public static final int XML           = 3;
	public static final int AUDIO         = 4;
	public static final int AUDIOW        = 5;
	public static final int SCENES        = 6;
	public static final int SCRIPT        = 7;
	public static final int FONT          = 8;
	public static final int MAP           = 9;
	public static final int PROPS         = 10;
	
	private static String imgPrefix = "res/imagens/";
	private static String imgSufix  = ".png";
	
	private static String configPrefix = "res/config/";
	private static String configSufix  = "config.json";
	
	private static String xmlPrefix = "res/mapas/";
	private static String xmlSufix  = ".xml";
	
	private static String audioPrefix = "res/audio/";
	
	private static String scenePrefix = "res/mapas/";
	private static String sceneSufix  = "list_scenes.json";
	
	private static String scriptPrefix = "res/scripts/";
    private static String scriptSufix  = ".js";
    
    private static String fontPrefix = "res/fonts/";
    private static String fontSufix  = ".ttf";
    
    private static String mapPrefix = "res/mapas/";
    private static String mapSufix  = ".tmx";
	
    @SuppressWarnings("unchecked")
    public static <T> T loadResource( String name, int nTipo, Class<T> tipo, Map<String, Object> data )
    {
        switch( nTipo )
        {
            case IMAGEM:
                return (T)carregaImagem( name );
            case CONFIGURACOES:
                return (T)carregaConfigs( );
            case XML:
                return (T)carregaXML( name );
            case AUDIO:
                return (T)loadAudio( name );
            case SCENES:
                return (T)carregarScenes( );
            case SCRIPT:
                return (T)carregaScript( name, data );
            case FONT:
                return (T)carregaFont( name, data );
            case MAP:
                return (T)loadMap( name );
            case PROPS:
            	return (T)carregaProps( );
        }
        
        return null;
    }
	
	private static org.mapeditor.core.Map loadMap( String name )
	{
		TMXMapReader reader = new TMXMapReader( );
		
		try 
		{
			return reader.readMap( mapPrefix + name + mapSufix );
		} 
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		
		return null;
	}

	public static <T> T loadResource( String nome, int nTipo, Class<T> tipo )
	{
	    return loadResource( nome, nTipo, tipo, null );
	}

	private static ScenesDefinition[] carregarScenes( )
	{
		ScenesDefinition[] ret = null;
		
		try 
		{
			Gson gson = new GsonBuilder( ).create( );
			JsonObject jsonObject = gson.fromJson( new FileReader( new File( scenePrefix.concat( sceneSufix ) ) ), JsonObject.class );
			
			JsonArray jsonArray = jsonObject.get( "mapas" ).getAsJsonArray( );
			
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
		
		return ret;
	}

	private static Object loadAudio( String name )
	{
		try 
		{
			return new AudioClip( Paths.get( audioPrefix + name ).toUri( ).toString( ) );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		
		return null;
	}

	private static Object carregaXML( String nome )
	{
		try 
		{
			StringBuilder sb = new StringBuilder( );
			
			Scanner sc = new Scanner( new FileInputStream( xmlPrefix.concat( nome.concat( xmlSufix ) ) ) );
			
			while( sc.hasNextLine( ) )
			{
				sb.append( sc.nextLine( ) );
			}
			
			sc.close( );
			
			return sb.toString( );
		} 
		catch( FileNotFoundException e )
		{
			e.printStackTrace( );
		}
		
		return "";
	}

	private static Object carregaConfigs( )
	{
		try 
		{
			Gson gson = new GsonBuilder( ).create( );
			Configurations configs = gson.fromJson( new FileReader( new File( configPrefix.concat( configSufix ) ) ), Configurations.class );
			
			return configs;
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		
		return null;
	}
	
	private static Map<String, String> carregaProps( )
	{
		Map<String, String> props = new HashMap<String, String>( );
		
		try 
		{
			Stream<Path> list = Files.list( Paths.get( "./res/mensages" ) );
			
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

	private static Object carregaImagem( String imageNome )
	{
		Image image = null;
		
		try
		{
			image = new Image( new FileInputStream( imgPrefix + imageNome + imgSufix ) );
		} 
		catch( IOException e ) 
		{
			e.printStackTrace( );
		}
		
		return image;
	}
	
	private static ScriptEngine carregaScript( String scriptNome, Map<String, Object> data )
    {
	    final ScriptEngine nashorn;
        
        try 
        {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager( );
            nashorn = scriptEngineManager.getEngineByName( "nashorn" );
            
            if( data != null )
            {
                data.forEach( (key, value) ->
                {
                    nashorn.put( key, value );
                } );
            }
            
            nashorn.eval( new FileReader( new File( scriptPrefix + scriptNome + scriptSufix ) ) );
            
            return nashorn; 
        } 
        catch( Exception e )
        {
            e.printStackTrace( );
        }
        
        return null;
    }
	
	private static Font carregaFont( String nome, Map<String, Object> data )
	{
		try 
		{
			return Font.loadFont( new FileInputStream(  fontPrefix + nome + fontSufix ), (int)data.get( "size" ) );
		} 
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
package br.com.engine.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataManager implements Serializable
{
	private static final long serialVersionUID = 7851512707760366061L;
	
	private static DataManager data;
	
	Map<String, Object> map = new HashMap<String, Object>( );
	
	public static DataManager getInstance( )
	{
		if( DataManager.data == null )
		{
			try 
			{
				ObjectInputStream os = new ObjectInputStream( new FileInputStream( "./data.obj" ) );
				DataManager.data = (DataManager)os.readObject( ); 
				os.close( );
			} 
			catch( IOException | ClassNotFoundException e )
			{
				DataManager.data = new DataManager( );
			}
		}
		
		return DataManager.data;
	}
	
	public void put( String key, Object value )
	{
		map.put( key, value );
		saveState( );
	}

	public void saveState( )
	{
		try 
		{
			ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream( "./data.obj" ) );
			os.writeObject( this ); 
			os.close( );
		} 
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}

	public Object get( String key) {
		return map.get(key);
	}

	public Object get(String key, Object defaultValue) {
		return map.containsKey(key) ? map.get(key) : defaultValue;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Object get( String key, Class<T> classe ) {
		return (T)map.get(key);
	}

	@SuppressWarnings("unchecked")
	public <T> Object get(String key, Object defaultValue, Class<T> classe ) {
		return (T)(map.containsKey(key) ? map.get(key) : defaultValue);
	}
}
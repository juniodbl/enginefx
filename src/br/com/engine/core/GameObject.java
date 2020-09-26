package br.com.engine.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.engine.componentes.SimpleComponent;
import br.com.engine.componentes.TypeComponents;
import br.com.engine.componentes.VectorMonitor;
import br.com.engine.interfaces.IComponent;

public class GameObject
{
	private String tag = "";
	
	private Vector2 previusPosition;
	private Vector2 position;
	
	private List<IComponent> componentes;
	
	private List<GameObject> objetosFilhos;
	
	private GameObject pai;

	private boolean bDestroy;
	
	public GameObject( )
	{
		componentes     = new ArrayList<IComponent>( );
		objetosFilhos   = new ArrayList<GameObject>( );
		position        = new Vector2( );
		previusPosition = new Vector2( );
		
		bDestroy = false;
	}
	
	public void setup( )
	{
		VectorMonitor monitor = new VectorMonitor((vec, oldVec)->{
			Vector2 diff = vec.diff(oldVec);
			
			previusPosition.setPosition(oldVec.x, oldVec.y);
			
			objetosFilhos.forEach( g ->{
				g.getPosition( ).plus( diff );
			});
		});
		
		addComponente(monitor);
		
		List<IComponent> filter = new ArrayList<>( );
		
		while(true)
		{
			List<IComponent> componentsTemp = filter.size( ) > 0 ? filter : componentes.stream().collect( Collectors.toList( ) );
			componentsTemp.forEach( c -> c.setup( ) );
			
			filter = (filter.size( ) > 0 ? filter : componentes).stream().filter( c -> !componentsTemp.contains( c ) ).collect( Collectors.toList( ) );
			
			if( filter.size( ) == 0 )
			{
				break;
			}	
		}
	}
	
	public GameObject( String tag )
	{
		this( );
		this.tag = tag;
	}
	
	public Vector2 getPosition( )
	{
		return position;
	}
	
	public Vector2 getPreviusPosition( )
	{
		return previusPosition;
	}

	public List<IComponent> getComponentes( )
	{
		return componentes;
	}

	public void addComponente( IComponent componente ) 
	{
	    if( componente instanceof SimpleComponent )
	    {
	        ((SimpleComponent)componente).setParent( this );
	    }
	    
		this.componentes.add( componente );
	}
		
	public Object getComponent( TypeComponents type )
	{
		return getComponent(type.getValue());
	}
	
	public Object getComponent( String name )
	{
		return getComponent(TypeComponents.valueOf(name.toUpperCase()));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getComponent( Class<T> item )
	{
		IComponent       obj  = null;
		List<IComponent> objs = new ArrayList<IComponent>( );
		
		for( IComponent comp : componentes )
		{
		    if( item.isArray( ) )
		    {
		        if( item.getComponentType( ).isInstance( comp ) )
                {
		            objs.add( comp );
                }
		    }
		    else
		    {
		        if( item.isInstance( comp ) )
		        {
		            obj = comp;
		        }
		    }
		}
		
		if( item.isArray( ) )
		{
		    Object[] newInstance = (Object[])Array.newInstance(item.getComponentType( ), objs.size());
	        
	        for( int i = 0; i < newInstance.length; i++ )
	        {
	            newInstance[i] = objs.get( i );
	        }
	        
		    return (T)newInstance;
		}
        else
            return (T)obj;
	}
	
	public String getTag( )
	{
		return tag;
	}
	
	public void setTag( String tag )
	{
		this.tag = tag;
	}
	
	public void destroy( )
	{
		this.bDestroy = true;
	}
	
	public boolean isDestroy( )
	{
		return bDestroy;
	}
	
	@Override
	public String toString( )
	{
		return ToStringBuilder.reflectionToString( this );
	}

	public GameObject getPai( )
	{
		return pai;
	}

	public void setPai( GameObject pai )
	{
		pai.objetosFilhos.add( this );
		
		this.pai = pai;
	}
}
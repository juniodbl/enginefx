package br.com.engine.componentes.scripts;

import java.util.ArrayList;
import java.util.List;

import br.com.engine.componentes.SimpleComponent;
import br.com.engine.componentes.drawable.Sprite;

public class Animator extends SimpleComponent
{
	private	List<Sequence> lstSequencias = new ArrayList<Sequence>( );
	
	private Sequence currentSequence;
	private long     interval;
	
	private long lastTimeAnimation;
	
	public enum AnimationType
	{
		UNIQUE,
		UNIQUE_DESTROY,
		INFITY
	}
    
	public Animator( int timeInterval )
	{
		setInterval( timeInterval );
	}
		
	public void createAnimation( String nome, int initialFrame, int lastFrame )
	{
		createAnimation(nome, initialFrame, lastFrame, AnimationType.INFITY );
	}
	
	public void createAnimation( String nome, int initialFrame, int lastFrame, AnimationType animationType )
	{
		lstSequencias.add( new Sequence( nome, initialFrame, lastFrame - 1, animationType ) );
	}
	
	public void setInterval( long interval )
	{
		this.interval = interval;
	}
	
	public void execute( String nameAnimation )
	{
		lstSequencias
			.stream( )
			.filter( s -> s.getNome( ).equals( nameAnimation ) )
			.findFirst( ) .ifPresent( sequence ->{
				currentSequence = sequence;
		        currentSequence.setLock( false );
			});
	}
	
	public void stop( )
	{
		currentSequence.setLock( true );
	}
	
	@Override
	public void update( long time )
	{
		lastTimeAnimation += time;
        
        if( lastTimeAnimation > interval )
        {
        	getParent( ).getComponent( Sprite.class ).setSprite( currentSequence.getCurrent( ) );

            lastTimeAnimation = 0;
        }
	}
	
	@Override
	public void setup( )
	{
	}

	@Override
	public void draw( )
	{
	}
	
	private class Sequence
	{
		private String        nome;
		private int           initialFrame;
		private int           lastFrame;
		private int           current;
		private AnimationType animationType;
		private boolean       lock;
		
		public Sequence( String nome, int initialFrame, int lastFrame, AnimationType animationType )
		{
			setNome         ( nome         );
			setInitialFrame ( initialFrame );
			setLastFrame    ( lastFrame    );
			setCurrent      ( initialFrame );
			setAnimationType( animationType );
		}

		public int getLastFrame( )
		{
			return lastFrame;
		}
		
		public void setLastFrame( int length ) 
		{
			this.lastFrame = length;
		}
		
		public String getNome( )
		{
			return nome;
		}
		
		public void setNome( String nome )
		{
			this.nome = nome;
		}
		
		public void setInitialFrame( int initialFrame ) 
		{
			this.initialFrame = initialFrame;
		}
		
		public int getInitialFrame( )
		{
			return initialFrame;
		}

		public int getCurrent( )
		{
			int ret = current;
			
			adjustCurrent( );
			
			return ret;
		}

		public void setCurrent( int current )
		{
			this.current = current;
		}
		
		public boolean isLock( )
		{
			return lock;
		}

		public void setLock( boolean lock )
		{
			this.lock = lock;
		}
		
		public void adjustCurrent( )
		{
			if( !isLock( ) )
			{
				if( current < getLastFrame( ) )
				{
					current++;
				}
				else if( current == getLastFrame( ) )
				{
					switch( getAnimationType( ) ) 
					{
						case INFITY:
							current = getInitialFrame( );
						break;
						case UNIQUE:
							current = getInitialFrame( );
							setLock( true );
						break;
						case UNIQUE_DESTROY:
							getParent( ).destroy( );
						break;
					}
				}
			}
		}

		public AnimationType getAnimationType( )
		{
			return animationType;
		}

		public void setAnimationType( AnimationType animationType )
		{
			this.animationType = animationType;
		}
	}
}
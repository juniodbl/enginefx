package br.com.engine.main;

import br.com.engine.core.ControleBase;
import br.com.engine.input.KeyBoard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Executor extends Application 
{
	public static void loadGame( String[] args ) 
	{
	    launch( args );
	}

    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        Group root = new Group( );
        
        root.getChildren( ).add( ControleBase.getInstance( ).getScreen( ).getCanvas( ) );
        
        primaryStage.setTitle( "Enginefx" );
        primaryStage.setScene( new Scene( root ) );
        primaryStage.addEventHandler( KeyEvent.ANY, KeyBoard.infInstace( ) );
        
        ControleBase.getInstance( ).startMainLoop( );
        primaryStage.show( );
    }
}
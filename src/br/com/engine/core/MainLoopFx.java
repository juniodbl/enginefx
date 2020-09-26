package br.com.engine.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import br.com.engine.interfaces.LoopSteps;

public class MainLoopFx implements Runnable
{
    private LoopSteps game;

    public MainLoopFx( LoopSteps loopSteps )
    {
        super( );
        
        this.game = loopSteps;
    }

    /**
     * Runs the main loop. This method is not thread safe and should not be
     * called more than once.
     */
    public void run( )
    {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setDelay(Duration.seconds(1.0/60.0));        
        
        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.seconds(1.0/60.0);
        
        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = 
            actionEvent ->
            {
                try
                {
                    game.processLogics( );
                    game.renderGraphics( );
                    game.paintScreen( );
                }
                catch( Exception e )
                {
                    e.printStackTrace( );
                    game.tearDown( );
                    System.exit( 0 );
                    throw new RuntimeException( "Exception during game loop", e );
                }
            };

        KeyFrame keyFrame = new KeyFrame( duration, onFinished , null, null );

        //add the keyframe to the timeline
        timeline.getKeyFrames( ).add( keyFrame );

        game.setup( );
        timeline.playFromStart( );
    }

    public void stop( )
    {
        
    }
}

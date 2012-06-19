/*
 * (c) Copyright 2012 by Martin Daser
 */
package net.mdaser.vloop;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;
import android.media.MediaPlayer;
/**
import android.widget.MediaController;
**/


public class VloopActivity extends Activity 
						   implements MediaPlayer.OnCompletionListener,  
						    MediaPlayer.OnPreparedListener {
	
	private static final String TAG = "vloop";
	// private static final String uri = "android.resource://net.mdaser.vloop/" + R.raw.video;
	// "http://www.androidbook.com/akc/filestorage/android/documentfiles/3389/movie.mp4"
	private static final String path = "/sdcard/Videos/video.mp4";
	
	VideoView mVideoView;
	Uri       mUri;
	
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        
        setFullScreen(true);
        
        this.setContentView(R.layout.main);
 
        mVideoView = (VideoView)this.findViewById(R.id.videoView);

        /** not really required for playing in a loop
        MediaController mc = new MediaController(this);
        mVideoView.setMediaController(mc);
        **/
        
        // mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);

        // startVideo(path);
        
        mVideoView.requestFocus();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
    	
    	menu.add(0, 1, 0, R.string.setVideo);
    	menu.add(0, 2, 1, R.string.start);
    	menu.add(0, 3, 2, R.string.stop);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	boolean ret = false;
    	int i = item.getItemId();
    	
    	switch(i)
    	{
    	case 1: ret = true;
    			break;
    	case 2: startVideo(path);
    			ret = true;
    			break;
    	case 3: stopVideo();
    			ret = true;
    		 	break;
    	default:
    			ret = super.onOptionsItemSelected(item);
    			break;
    	}
    	
    	return ret;
    }
    
    private void startVideo(String path)
    {
    	Log.i(TAG, "StartVideo");
    	mVideoView.setVideoPath(path);
    	mVideoView.start();
    }
    
    private void stopVideo()
    {
    	Log.i(TAG, "StopVideo");
    	mVideoView.stopPlayback();
    }
    
    private void setFullScreen(boolean fullScreen) {
    	int featureId = fullScreen ? Window.FEATURE_NO_TITLE : Window.FEATURE_LEFT_ICON;
    	int flag      = fullScreen ? WindowManager.LayoutParams.FLAG_FULLSCREEN : WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
    	int mask      = fullScreen ? WindowManager.LayoutParams.FLAG_FULLSCREEN : WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
    	
        requestWindowFeature(featureId);

        getWindow().setFlags(flag, mask); 
    }
    
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		
		Log.i(TAG, "onCompletion");
		
		startVideo(path);
		
		/**
		Log.i(TAG, "seek to 0");
		mVideoView.seekTo(0);
		mp.seekTo(0);
		
		Log.i(TAG, "re-start");
		mVideoView.start();
		mp.start();
		**/
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.i(TAG, "set media player to looping state");
		mp.setLooping(true);
	}
}
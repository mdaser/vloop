/*
 * (c) Copyright 2012 by Martin Daser
 */
package net.mdaser.vloop;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

        // mUri = Uri.parse(uri);
        // mVideoView.setVideoURI(mUri);             
 
        mVideoView.setVideoPath(path);
        
        // mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);

        mVideoView.requestFocus();
        mVideoView.start();
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
		
		Log.i(TAG, "video view: new uri and start");
		// mVideoView.setVideoURI(mUri);
		mVideoView.setVideoPath(path);
		mVideoView.start();
		
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
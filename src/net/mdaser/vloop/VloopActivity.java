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
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class VloopActivity extends Activity {
	
	private static final String TAG = "vloop";
	
	class CompletionHandler implements MediaPlayer.OnCompletionListener
	{	
		@Override
		public void onCompletion(MediaPlayer mp) {
			
			// show a toast ... restarting
			/**
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, R.string.Restart, Toast.LENGTH_SHORT);
			toast.show();
			**/
			
			Log.i(TAG, "seek to begin and restart media player");
			
			// restart the media player
			mp.seekTo(0);
			mp.start();
		}
    }
	
	
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        
        setFullScreen(true);
        
        this.setContentView(R.layout.main);

        
        VideoView videoView = (VideoView)this.findViewById(R.id.videoView);
        
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        
        String path = "android.resource://net.mdaser.vloop/" + R.raw.video;
        // "http://www.androidbook.com/akc/filestorage/android/documentfiles/3389/movie.mp4"));
        videoView.setVideoURI(Uri.parse(path));             
        // videoView.setVideoPath("/sdcard/movie.mp4");
        
        videoView.setOnCompletionListener(new CompletionHandler());
        
        videoView.requestFocus();
        videoView.start();
    }
    
    private void setFullScreen(boolean fullScreen) {
    	int featureId = fullScreen ? Window.FEATURE_NO_TITLE : Window.FEATURE_LEFT_ICON;
    	int flag      = fullScreen ? WindowManager.LayoutParams.FLAG_FULLSCREEN : WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
    	int mask      = fullScreen ? WindowManager.LayoutParams.FLAG_FULLSCREEN : WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
    	
        requestWindowFeature(featureId);

        getWindow().setFlags(flag, mask); 
    }
}
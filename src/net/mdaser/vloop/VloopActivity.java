/*
 * (c) Copyright 2012 by Martin Daser
 */
package net.mdaser.vloop;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class VloopActivity extends Activity {
	
	class CompletionHandler implements MediaPlayer.OnCompletionListener
	{
		@Override
		public void onCompletion(MediaPlayer mp) {
			
			// show a toast ... restarting
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, R.string.Restart, Toast.LENGTH_SHORT);
			toast.show();
			
			// restart the media player
			mp.start();
		}
    }
	
	
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
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
    
}
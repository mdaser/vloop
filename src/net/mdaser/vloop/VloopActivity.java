package net.mdaser.vloop;

import android.R.string;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VloopActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        this.setContentView(R.layout.main);

        VideoView videoView = (VideoView)this.findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        
        String path = "android.resource://net.mdaser.vloop/" + R.raw.video;
        videoView.setVideoURI(Uri.parse(path));
        //      "http://www.androidbook.com/akc/filestorage/android/documentfiles/3389/movie.mp4"));
              
        // videoView.setVideoPath("/sdcard/movie.mp4");
        videoView.requestFocus();
        videoView.start();
    }
    
}
package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import unitedapps.com.googleandroidcourses.R;

/**
    Created by dasse on 15-Oct-17.
 */

public class MediaPlayerApp extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_mp_activity_main);

        setOnclickAndOnSeekBarChange();
    }

    private void setOnclickAndOnSeekBarChange() {
        Button play_btn, pause_btn, skip_btn;
        SeekBar volume_sb;

        play_btn = findViewById(R.id.ab_ma_mp_play_btn);
        pause_btn = findViewById(R.id.ab_ma_mp_pause_btn);
        skip_btn = findViewById(R.id.ab_ma_mp_skip_to_middle_btn);
        volume_sb = findViewById(R.id.ab_ma_mp_volume_sb);

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer == null)
                    mediaPlayer =  MediaPlayer.create(getApplicationContext(), R.raw.culture_code_make_me_move);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MediaPlayerApp.this, "Am Done ^_^", Toast.LENGTH_LONG).show();
                        releaseMediaPlayer();
                    }
                });
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null)
                    mediaPlayer.pause();
            }
        });

        volume_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int maxVolume = 10;
                float log1 =(float)(Math.log(maxVolume- (i) )/Math.log(maxVolume));
                mediaPlayer.setVolume(1-log1, 1-log1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null)
                    mediaPlayer.seekTo(mediaPlayer.getDuration()/2);
            }
        });
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}

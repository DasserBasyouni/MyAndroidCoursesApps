package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import unitedapps.com.googleandroidcourses.R;

/**
    Created by dasse on 15-Oct-17.
 */

public class MediaPlayerApp extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_mp_activity_main);

        setBtnsOnclick();
    }

    private void setBtnsOnclick() {
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
                mediaPlayer.setVolume(i/10,i/10);
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
}

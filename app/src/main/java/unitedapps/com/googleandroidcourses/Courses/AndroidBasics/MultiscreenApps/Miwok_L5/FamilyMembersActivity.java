package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L5;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.R;

/**
  Created by dasse on 05-Oct-17.
 */

public class FamilyMembersActivity extends AppCompatActivity {

    AudioManager mAudioManager;
    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override public void onCompletion(MediaPlayer mediaPlayer) {releaseMediaPlayer();}};
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                Log.i("TAG", "AUDIOFOCUS_GAIN");
                //restart/resume your sound
                if (mediaPlayer != null)
                    mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                Log.e("TAG", "AUDIOFOCUS_LOSS");
                //Loss of audio focus for a long time
                //Stop playing the sound
                if (mediaPlayer != null)
                    mediaPlayer.stop();
                releaseMediaPlayer();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                    || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                Log.e("TAG", "AUDIOFOCUS_LOSS_TRANSIENT or CAN_DUCK");
                //Loss of audio focus for a short time
                //Pause playing the sound
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_m5_words_list);

        final ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("father", "әpә", R.drawable.ab_ma_ml2_family_father, R.raw.ab_ma_m5_family_father));
        words.add(new Words("mother", "әṭa", R.drawable.ab_ma_ml2_family_mother, R.raw.ab_ma_m5_family_mother));
        words.add(new Words("son", "angsi", R.drawable.ab_ma_ml2_family_son, R.raw.ab_ma_m5_family_son));
        words.add(new Words("daughter", "tune", R.drawable.ab_ma_ml2_family_daughter, R.raw.ab_ma_m5_family_daughter));
        words.add(new Words("older brother", "taachi", R.drawable.ab_ma_ml2_family_older_brother, R.raw.ab_ma_m5_family_older_brother));
        words.add(new Words("younger brother", "chalitti", R.drawable.ab_ma_ml2_family_younger_brother, R.raw.ab_ma_m5_family_younger_brother));
        words.add(new Words("older sister", "teṭe", R.drawable.ab_ma_ml2_family_older_sister, R.raw.ab_ma_m5_family_older_sister));
        words.add(new Words("younger sister", "kolliti", R.drawable.ab_ma_ml2_family_younger_sister, R.raw.ab_ma_m5_family_younger_brother));
        words.add(new Words("grandmother", "ama", R.drawable.ab_ma_ml2_family_grandmother, R.raw.ab_ma_m5_family_grandmother));
        words.add(new Words("grandfather", "paapa", R.drawable.ab_ma_ml2_family_grandfather, R.raw.ab_ma_m5_family_grandfather));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words, R.color.ab_ma_category_family);
        ListView listView = findViewById(R.id.ab_ma_m5_list_view);
        listView.setAdapter(wordsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                mAudioManager = (AudioManager) FamilyMembersActivity.this.getSystemService(Context.AUDIO_SERVICE);

                // Request audio focus for playback
                assert mAudioManager != null;
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.d("AudioFocus", "Audio focus received");
                    mediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, words.get(i).getRawResourceId());
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                    mediaPlayer.start();
                } else {
                    Log.d("AudioFocus", "Audio focus NOT received");
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
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

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
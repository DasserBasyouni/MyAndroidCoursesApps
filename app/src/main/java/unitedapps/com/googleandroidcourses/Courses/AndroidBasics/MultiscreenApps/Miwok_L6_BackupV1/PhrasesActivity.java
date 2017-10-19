package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L6_BackupV1;

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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new Words("Where are you going?", "minto wuksus", R.raw.ab_ma_m5_phrase_where_are_you_going));
        words.add(new Words("What is your name?", "tinnә oyaase'nә", R.raw.ab_ma_m5_phrase_what_is_your_name));
        words.add(new Words("My name is...","oyaaset...", R.raw.ab_ma_m5_phrase_my_name_is));
        words.add(new Words("How are you feeling?", "michәksәs?", R.raw.ab_ma_m5_phrase_how_are_you_feeling));
        words.add(new Words("I’m feeling good.", "kuchi achit", R.raw.ab_ma_m5_phrase_im_feeling_good));
        words.add(new Words("Are you coming?", "әәnәs'aa?", R.raw.ab_ma_m5_phrase_are_you_coming));
        words.add(new Words("Yes, I’m coming.","hәә’ әәnәm", R.raw.ab_ma_m5_phrase_yes_im_coming));
        words.add(new Words("I’m coming.", "әәnәm", R.raw.ab_ma_m5_phrase_im_coming));
        words.add(new Words("Let’s go.", "yoowutis", R.raw.ab_ma_m5_phrase_lets_go));
        words.add(new Words("Come here.", "әnni'nem", R.raw.ab_ma_m5_phrase_come_here));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words, R.color.ab_ma_category_phrases);
        ListView listView = findViewById(R.id.ab_ma_m5_list_view);
        listView.setAdapter(wordsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                mAudioManager = (AudioManager) PhrasesActivity.this.getSystemService(Context.AUDIO_SERVICE);

                // Request audio focus for playback
                assert mAudioManager != null;
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.d("AudioFocus", "Audio focus received");
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(i).getRawResourceId());
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
        Log.d("MediaPlayer", "done ^_^");
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
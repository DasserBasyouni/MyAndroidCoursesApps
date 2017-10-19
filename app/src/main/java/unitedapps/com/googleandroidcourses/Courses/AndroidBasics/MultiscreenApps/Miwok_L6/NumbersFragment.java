package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L6;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.R;

/**
  Created by dasse on 05-Oct-17.
 */

public class NumbersFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ab_ma_m5_words_list, container, false);

        final ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("One", "Lutti", R.drawable.ab_ma_ml2_number_one, R.raw.ab_ma_m5_number_one));
        words.add(new Words("Two", "Otiiko", R.drawable.ab_ma_ml2_number_two, R.raw.ab_ma_m5_number_two));
        words.add(new Words("Three", "Tolookosu", R.drawable.ab_ma_ml2_number_three, R.raw.ab_ma_m5_number_three));
        words.add(new Words("Four", "Oyyisa", R.drawable.ab_ma_ml2_number_four, R.raw.ab_ma_m5_number_four));
        words.add(new Words("Five", "Massokka", R.drawable.ab_ma_ml2_number_five, R.raw.ab_ma_m5_number_five));
        words.add(new Words("Six", "Temmokka", R.drawable.ab_ma_ml2_number_six, R.raw.ab_ma_m5_number_six));
        words.add(new Words("Seven", "Kenekaku", R.drawable.ab_ma_ml2_number_seven, R.raw.ab_ma_m5_number_seven));
        words.add(new Words("Eight", "Kawinta", R.drawable.ab_ma_ml2_number_eight, R.raw.ab_ma_m5_number_eight));
        words.add(new Words("Nine", "wo’e", R.drawable.ab_ma_ml2_number_nine, R.raw.ab_ma_m5_number_nine));
        words.add(new Words("Ten", "na’aacha", R.drawable.ab_ma_ml2_number_ten, R.raw.ab_ma_m5_number_ten));

        WordsAdapter wordsAdapter = new WordsAdapter(getActivity(), words, R.color.ab_ma_category_numbers);
        ListView listView = rootView.findViewById(R.id.ab_ma_m5_list_view);
        listView.setAdapter(wordsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

                // Request audio focus for playback
                assert mAudioManager != null;
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.d("AudioFocus", "Audio focus received");
                    mediaPlayer = MediaPlayer.create(getActivity(), words.get(i).getRawResourceId());
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                    mediaPlayer.start();
                } else {
                    Log.d("AudioFocus", "Audio focus NOT received");
                }
            }
        });

        return rootView;
    }


    @Override
    public void onStop() {
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
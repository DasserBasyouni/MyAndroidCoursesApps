package unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

public class CourtCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.court_counter);
    }

    int TeamAScore = 0, TeamBScore = 0;

    private void DisplayScoreA(int A) {
        TextView ScoreA = (TextView) findViewById(R.id.ScoreTeamA);
        ScoreA.setText("" + A);
    }

    private void DisplayScoreB(int B) {
        TextView ScoreB = (TextView) findViewById(R.id.ScoreTeamB);
        ScoreB.setText("" + B);
    }

    public void FreeThrowB(View view) {
        TeamBScore = 1 + TeamBScore;
        DisplayScoreB(TeamBScore);
    }

    public void PlusTwoB(View view) {
        TeamBScore = 2 + TeamBScore;
        DisplayScoreB(TeamBScore);
    }

    public void PlusThreeB(View view) {
        TeamBScore = 3 + TeamBScore;
        DisplayScoreB(TeamBScore);
    }

    public void FreeThrowA(View view) {
        TeamAScore = 1 + TeamAScore;
        DisplayScoreA(TeamAScore);
    }

    public void PlusTwoA(View view) {
        TeamAScore = 2 + TeamAScore;
        DisplayScoreA(TeamAScore);
    }

    public void PlusThreeA(View view) {
        TeamAScore = 3 + TeamAScore;
        DisplayScoreA(TeamAScore);
    }

    public void Reset(View view) {
        TeamAScore = 0;
        TeamBScore = 0;
        DisplayScoreB(0);
        DisplayScoreA(0);
    }
}


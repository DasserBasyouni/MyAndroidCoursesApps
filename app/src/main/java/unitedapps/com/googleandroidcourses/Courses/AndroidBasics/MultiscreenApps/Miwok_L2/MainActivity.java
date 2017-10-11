/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.ab_ma_main_activity);

        TextView number_tv = findViewById(R.id.numbers_tv);
        TextView family_tv = findViewById(R.id.family_tv);
        TextView colors_tv = findViewById(R.id.colors_tv);
        TextView phrases_tv = findViewById(R.id.phrases_tv);

        number_tv.setOnClickListener(new OpenClassListView(NumbersActivity.class));
        family_tv.setOnClickListener(new OpenClassListView(FamilyMembersActivity.class));
        colors_tv.setOnClickListener(new OpenClassListView(ColorsActivity.class));
        phrases_tv.setOnClickListener(new OpenClassListView(PhrasesActivity.class));

    }


    private class OpenClassListView implements View.OnClickListener {
        Class aClass;

        OpenClassListView(Class aClass) {
           this.aClass = aClass;
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), aClass);
            startActivity(i);
        }
    }
}
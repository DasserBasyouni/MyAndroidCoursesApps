package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L2;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 07-Oct-17.
 */

class Words {
    private String EnglishWord, MiwokWord;

    Words(String englishWord, String miwokWord) {
        this.MiwokWord = miwokWord;
        this.EnglishWord = englishWord;
    }

    String getMiwokTranslation() {
        return MiwokWord;
    }

    String getDefaultTranslation() {
        return EnglishWord;
    }
}

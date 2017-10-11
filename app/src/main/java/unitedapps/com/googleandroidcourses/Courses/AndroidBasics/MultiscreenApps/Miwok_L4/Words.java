package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L4;

/**
   Created by dasse on 07-Oct-17.
 */

class Words {
    private String defaultWord, miwokWord;
    private int DrawableRes;

    Words(String defaultWord, String miwokWord) {
        this.miwokWord = miwokWord;
        this.defaultWord = defaultWord;
    }

    Words(String defaultWord, String miwokWord, int DrawableRes) {
        this.miwokWord = miwokWord;
        this.defaultWord = defaultWord;
        this.DrawableRes = DrawableRes;
    }

    String getMiwokTranslation() {
        return miwokWord;
    }

    String getDefaultTranslation() {
        return defaultWord;
    }

    int getImageResourceId() {
        return DrawableRes;
    }

}

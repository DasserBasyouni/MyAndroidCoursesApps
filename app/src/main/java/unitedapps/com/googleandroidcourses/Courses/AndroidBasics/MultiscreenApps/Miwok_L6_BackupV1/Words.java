package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L6_BackupV1;

/**
   Created by dasse on 07-Oct-17.
 */

class Words {
    private String defaultWord, miwokWord;
    private int drawableRes, rawRes;

    Words(String defaultWord, String miwokWord, int rawRes) {
        this.miwokWord = miwokWord;
        this.defaultWord = defaultWord;
        this.rawRes = rawRes;
    }

    Words(String defaultWord, String miwokWord, int drawableRes, int rawRes) {
        this.miwokWord = miwokWord;
        this.defaultWord = defaultWord;
        this.drawableRes = drawableRes;
        this.rawRes = rawRes;
    }

    String getMiwokTranslation() {
        return miwokWord;
    }

    String getDefaultTranslation() {
        return defaultWord;
    }

    int getImageResourceId() {
        return drawableRes;
    }

    int getRawResourceId() {
        return rawRes;
    }

    @Override
    public String toString() {
        return "Words{" +
                "defaultWord='" + defaultWord + '\'' +
                ", miwokWord='" + miwokWord + '\'' +
                ", drawableRes=" + drawableRes +
                ", rawRes=" + rawRes +
                '}';
    }
}

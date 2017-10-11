package unitedapps.com.googleandroidcourses.OldClasses.AppsList;

import android.widget.ImageView;

/**
     Created by Dasser  on 03-Jun-17.
*/

class AppsListDataObject {

    private String text;
    private Class[] aClass;
    private ImageView imageView;

    AppsListDataObject(String text, Class[] aClass, ImageView imageView){
        this.text = text;
        this.aClass = aClass;
        this.imageView = imageView;
    }

    String getAppName() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    Class getaClass(int position) {
        return aClass[position];
    }

    public void setaClass(Class[] aClass) {
        this.aClass = aClass;
    }

    ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

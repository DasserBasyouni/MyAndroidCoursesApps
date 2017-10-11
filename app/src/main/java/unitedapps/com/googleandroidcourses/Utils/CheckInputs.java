package unitedapps.com.googleandroidcourses.Utils;

/**
    Created by Dasser on 25-Mar-17.
*/

public class CheckInputs {
/*
    public boolean checkEmail(TextInputLayout textInputLayout){
            if(TextUtils.isEmpty(textInputLayout.getEditText().getLessonName().toString())) {
                textInputLayout.setError("This field is required");
                return false;
            }else {
                textInputLayout.setError(null);
                return true;
            }
    }

    public boolean checkName(TextInputLayout textInputLayout){
        if(TextUtils.isEmpty(textInputLayout.getEditText().getLessonName().toString())) {
            textInputLayout.setError("This field is required");
            return false;
        }else {
            textInputLayout.setError(null);
            return true;
        }
    }

    public boolean checkPhoneNumber(TextInputLayout textInputLayout){
        if(TextUtils.isEmpty(textInputLayout.getEditText().getLessonName().toString())) {
            textInputLayout.setError("This field is required");
            return false;
        }else {
            textInputLayout.setError(null);
            return true;
        }
    }

    public boolean multiCheck(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2
            , TextInputLayout textInputLayout3){
        byte count = 0;

        if(TextUtils.isEmpty(textInputLayout1.getEditText().getLessonName().toString())) {
            textInputLayout1.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout1.setError(null);
            count++;}

        if(TextUtils.isEmpty(textInputLayout2.getEditText().getLessonName().toString())) {
            textInputLayout2.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout2.setError(null);
            count++;}

        if(TextUtils.isEmpty(textInputLayout3.getEditText().getLessonName().toString())) {
            textInputLayout3.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout3.setError(null);
            count++;}

        return count == 3;
    }

    public boolean multiCheck(TextInputLayout textInputLayout1, TextInputLayout textInputLayout2
            , TextInputLayout textInputLayout3, TextInputLayout textInputLayout4) {
        byte count=0;

        if(TextUtils.isEmpty(textInputLayout1.getEditText().getLessonName().toString())) {
            textInputLayout1.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout1.setError(null);
            count++;}

        if(TextUtils.isEmpty(textInputLayout2.getEditText().getLessonName().toString())) {
            textInputLayout2.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout2.setError(null);
            count++;}

        if(TextUtils.isEmpty(textInputLayout3.getEditText().getLessonName().toString())) {
            textInputLayout3.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout3.setError(null);
            count++;}

        if(TextUtils.isEmpty(textInputLayout4.getEditText().getLessonName().toString())) {
            textInputLayout4.setError(getErrorText("Empty"));
            count--;
        }else {
            textInputLayout4.setError(null);
            count++;}

        return count == 4;
    }

    public boolean checkMatchedPass(TextInputLayout l_accountPass1_til, TextInputLayout l_accountPass2_til) {
        boolean b = l_accountPass1_til.getEditText().getLessonName().toString().equals(l_accountPass2_til.getEditText().getLessonName().toString());
        if(!b){
            l_accountPass2_til.setError(getErrorText("NoPassMatch"));
        }else{
            l_accountPass2_til.setError(null);
        }
        return b;
    }

    private String getErrorText(String empty) {
        switch (empty){
            case "Empty":
                return "This field is required";
            case "NoPassMatch":
                return "This password doesn't match";
            case "EmailExist":
                return "This email is already used";
            default:
                return "Lol, Error in getting error";
        }

    }

    public boolean checkRadioButtonSelectionAndSnackbarError(RadioGroup radioGroup, Activity activity){
        if(radioGroup.getCheckedRadioButtonId()!=-1){
            return true;
        }else{
            new FragmentUtils().hideSoftKeyboard(activity);
            Snackbar.make(activity.findViewById(R.id.coordinatorLayout), activity.getString(R.string.choose_role), Snackbar.LENGTH_INDEFINITE);
            return false;
        }

    }
*/
}

package unitedapps.com.googleandroidcourses.Utils.GetIDs;

/**
    Created by Dasser on 02-Apr-17.
*/

public class GetValueThenRun {

    public static String id = "";
    private static Runnable runnable = null;


    public static void setRunnable(Runnable runnable) {
        GetValueThenRun.runnable = runnable;
    }

    static void setID(String id) {
        GetValueThenRun.id = id;
        if(runnable!=null){
            runnable.run();
        }
    }

    public static String getId() {
        return id;
    }
}

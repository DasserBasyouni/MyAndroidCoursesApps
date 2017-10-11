package unitedapps.com.googleandroidcourses.Utils.GetIDs;

/**
 * Created by dasse on 02-Apr-17.
 */

public class GetID {
/*
    public void getAdsIDBackground(final Context context){
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                AdvertisingIdClient.Info idInfo = null;
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                } catch (GooglePlayServicesNotAvailableException | IOException | GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
                String advertId = null;
                try{
                    advertId = idInfo.getId();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                GetValueThenRun.setID(advertId);
                return advertId;
            }

            @Override
            protected void onPostExecute(String advertId) {
                Toast.makeText(context, advertId, Toast.LENGTH_SHORT).show();
            }

        };
        task.execute();
    }

    public String getAdsID(Context context){
        AdvertisingIdClient.Info idInfo = null;
        try {
            idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (GooglePlayServicesNotAvailableException | IOException | GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        String advertId = null;
        try{
            advertId = idInfo.getId();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        GetValueThenRun.setID(advertId);
        return advertId;
    }
*/
}

package unitedapps.com.googleandroidcourses.Utils.CheckInternetConnection;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *  Created by Dasser on 08-Apr-17.
*/

class AsyncTaskCheckConnection extends AsyncTask<Void, Void, Boolean> {

    private Runnable TrueRunnable, NoInternetRunnable;


    AsyncTaskCheckConnection(Runnable TrueRunnable, Runnable NoInternetRunnable) {
        this.TrueRunnable = TrueRunnable;
        this.NoInternetRunnable = NoInternetRunnable;
    }

    @Override
    protected Boolean doInBackground(Void ... params) {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) { return false; }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            TrueRunnable.run();
        }else {
            NoInternetRunnable.run();
        }
    }
}


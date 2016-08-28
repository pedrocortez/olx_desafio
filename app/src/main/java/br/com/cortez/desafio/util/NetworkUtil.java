package br.com.cortez.desafio.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.com.cortez.desafio.ChallengeApplication;

/**
 * Created by Pedro on 8/28/16.
 */

public class NetworkUtil {

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) ChallengeApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

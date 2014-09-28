package com.coding4lif3.sunshine.network;

import android.content.Context;
import android.util.Log;

import com.coding4lif3.sunshine.util.Constants;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by federicomonaco on 9/27/14.
 */
public class NetworkManager {

    private final String TAG = "NetworkManager";
    private final long SIZE_OF_CACHE = 1024;

    private static NetworkManager mInstance = null;
    private RestAdapter mRestAdapter;

    private NetworkManager(Context mContext) {
        // Create an HTTP client that uses a cache on the file system. Android applications should use
        // their Context to get a cache directory.
        OkHttpClient okHttpClient = new OkHttpClient();

        try {
            Cache responseCache = new Cache(mContext.getCacheDir(), SIZE_OF_CACHE);
            okHttpClient.setCache(responseCache);
        } catch (Exception e) {
            Log.d(TAG, "Unable to set http cache", e);
        }
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);

        // Create a very simple REST adapter which points the GitHub API endpoint.
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.NetworkConfig.NEWTWORK_ENDPOINT).setClient(new OkClient(okHttpClient))
                .build();

    }

    public static NetworkManager getInstance(Context ctx) {
        if (mInstance != null) {
            return mInstance;
        }
        return new NetworkManager(ctx);
    }

    public RestAdapter getAdapter() {
        return mRestAdapter;
    }

}

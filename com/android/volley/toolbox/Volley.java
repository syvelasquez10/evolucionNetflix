// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import android.content.pm.PackageManager$NameNotFoundException;
import com.android.volley.Network;
import com.android.volley.Cache;
import org.apache.http.client.HttpClient;
import android.net.http.AndroidHttpClient;
import android.os.Build$VERSION;
import java.io.File;
import com.android.volley.RequestQueue;
import android.content.Context;

public class Volley
{
    private static final String DEFAULT_CACHE_DIR = "volley";
    
    public static RequestQueue newRequestQueue(final Context context) {
        return newRequestQueue(context, null);
    }
    
    public static RequestQueue newRequestQueue(final Context context, final HttpStack httpStack) {
        final File file = new File(context.getCacheDir(), "volley");
        String string = "volley/0";
        while (true) {
            try {
                final String packageName = context.getPackageName();
                string = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
                HttpStack httpStack2 = httpStack;
                if (httpStack == null) {
                    if (Build$VERSION.SDK_INT >= 9) {
                        httpStack2 = new HurlStack();
                    }
                    else {
                        httpStack2 = new HttpClientStack((HttpClient)AndroidHttpClient.newInstance(string));
                    }
                }
                final RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), new BasicNetwork(httpStack2));
                requestQueue.start();
                return requestQueue;
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
}

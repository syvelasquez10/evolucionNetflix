// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Collections;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.InputStream;
import android.os.Parcelable;
import android.util.Log;
import java.util.HashSet;
import java.util.Collection;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import com.facebook.FacebookException;
import org.json.JSONTokener;
import java.lang.reflect.Method;
import com.facebook.Settings;
import android.provider.Settings$Secure;
import com.facebook.Request$Callback;
import com.facebook.Session;
import com.facebook.Request;
import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.io.IOException;
import java.io.Closeable;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import org.json.JSONObject;
import android.content.Context;
import com.facebook.model.GraphObject;
import android.os.AsyncTask;

final class Utility$1 extends AsyncTask<Void, Void, GraphObject>
{
    final /* synthetic */ String val$applicationId;
    final /* synthetic */ Context val$context;
    final /* synthetic */ String val$settingsKey;
    
    Utility$1(final String val$applicationId, final Context val$context, final String val$settingsKey) {
        this.val$applicationId = val$applicationId;
        this.val$context = val$context;
        this.val$settingsKey = val$settingsKey;
    }
    
    protected GraphObject doInBackground(final Void... array) {
        return getAppSettingsQueryResponse(this.val$applicationId);
    }
    
    protected void onPostExecute(final GraphObject graphObject) {
        if (graphObject != null) {
            final JSONObject innerJSONObject = graphObject.getInnerJSONObject();
            parseAppSettingsFromJSON(this.val$applicationId, innerJSONObject);
            this.val$context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).edit().putString(this.val$settingsKey, innerJSONObject.toString()).apply();
        }
        Utility.initialAppSettingsLoadTask = null;
    }
}

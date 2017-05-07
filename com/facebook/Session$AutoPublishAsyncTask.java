// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.ActivityNotFoundException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.app.Fragment;
import android.app.Activity;
import java.util.Collection;
import com.facebook.internal.SessionAuthorizationType;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphMultiResult;
import android.content.Intent;
import android.os.Looper;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import java.util.Date;
import android.os.Handler;
import java.util.List;
import android.os.Bundle;
import java.util.Set;
import java.io.Serializable;
import com.facebook.internal.Utility;
import android.content.Context;
import android.os.AsyncTask;

class Session$AutoPublishAsyncTask extends AsyncTask<Void, Void, Void>
{
    private final Context mApplicationContext;
    private final String mApplicationId;
    final /* synthetic */ Session this$0;
    
    public Session$AutoPublishAsyncTask(final Session this$0, final String mApplicationId, final Context context) {
        this.this$0 = this$0;
        this.mApplicationId = mApplicationId;
        this.mApplicationContext = context.getApplicationContext();
    }
    
    protected Void doInBackground(final Void... array) {
        try {
            Settings.publishInstallAndWaitForResponse(this.mApplicationContext, this.mApplicationId, true);
            return null;
        }
        catch (Exception ex) {
            Utility.logd("Facebook-publish", ex);
            return null;
        }
    }
    
    protected void onPostExecute(final Void void1) {
        synchronized (this.this$0) {
            this.this$0.autoPublishAsyncTask = null;
        }
    }
}

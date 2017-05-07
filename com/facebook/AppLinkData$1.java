// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Iterator;
import android.os.Parcelable;
import org.json.JSONArray;
import com.facebook.internal.Utility;
import org.json.JSONException;
import android.util.Log;
import android.content.Intent;
import com.facebook.internal.Validate;
import android.app.Activity;
import android.net.Uri;
import org.json.JSONObject;
import android.os.Bundle;
import android.content.Context;

final class AppLinkData$1 implements Runnable
{
    final /* synthetic */ Context val$applicationContext;
    final /* synthetic */ String val$applicationIdCopy;
    final /* synthetic */ AppLinkData$CompletionHandler val$completionHandler;
    
    AppLinkData$1(final Context val$applicationContext, final String val$applicationIdCopy, final AppLinkData$CompletionHandler val$completionHandler) {
        this.val$applicationContext = val$applicationContext;
        this.val$applicationIdCopy = val$applicationIdCopy;
        this.val$completionHandler = val$completionHandler;
    }
    
    @Override
    public void run() {
        fetchDeferredAppLinkFromServer(this.val$applicationContext, this.val$applicationIdCopy, this.val$completionHandler);
    }
}

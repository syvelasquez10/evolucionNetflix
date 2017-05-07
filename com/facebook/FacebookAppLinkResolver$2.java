// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;
import bolts.Continuation;
import bolts.Task;
import java.util.HashMap;
import bolts.AppLinkResolver;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Iterator;
import com.facebook.model.GraphObject;
import org.json.JSONException;
import java.util.List;
import bolts.AppLink;
import bolts.AppLink$Target;
import java.util.ArrayList;
import android.net.Uri;
import java.util.HashSet;
import bolts.Task$TaskCompletionSource;
import java.util.Map;

class FacebookAppLinkResolver$2 implements Request$Callback
{
    final /* synthetic */ FacebookAppLinkResolver this$0;
    final /* synthetic */ Map val$appLinkResults;
    final /* synthetic */ Task$TaskCompletionSource val$taskCompletionSource;
    final /* synthetic */ HashSet val$urisToRequest;
    
    FacebookAppLinkResolver$2(final FacebookAppLinkResolver this$0, final Task$TaskCompletionSource val$taskCompletionSource, final Map val$appLinkResults, final HashSet val$urisToRequest) {
        this.this$0 = this$0;
        this.val$taskCompletionSource = val$taskCompletionSource;
        this.val$appLinkResults = val$appLinkResults;
        this.val$urisToRequest = val$urisToRequest;
    }
    
    @Override
    public void onCompleted(Response innerJSONObject) {
        final FacebookRequestError error = ((Response)innerJSONObject).getError();
        if (error != null) {
            this.val$taskCompletionSource.setError(error.getException());
            return;
        }
        final GraphObject graphObject = ((Response)innerJSONObject).getGraphObject();
        if (graphObject != null) {
            innerJSONObject = graphObject.getInnerJSONObject();
        }
        else {
            innerJSONObject = null;
        }
        if (innerJSONObject == null) {
            this.val$taskCompletionSource.setResult(this.val$appLinkResults);
            return;
        }
    Label_0261:
        for (final Uri uri : this.val$urisToRequest) {
            if (((JSONObject)innerJSONObject).has(uri.toString())) {
                while (true) {
                    while (true) {
                        int n = 0;
                        Label_0273: {
                            try {
                                final JSONObject jsonObject = ((JSONObject)innerJSONObject).getJSONObject(uri.toString()).getJSONObject("app_links");
                                final JSONArray jsonArray = jsonObject.getJSONArray("android");
                                final int length = jsonArray.length();
                                final ArrayList list = new ArrayList<AppLink$Target>(length);
                                n = 0;
                                if (n < length) {
                                    final AppLink$Target access$000 = getAndroidTargetFromJson(jsonArray.getJSONObject(n));
                                    if (access$000 != null) {
                                        list.add(access$000);
                                    }
                                    break Label_0273;
                                }
                                else {
                                    final AppLink appLink = new AppLink(uri, (List<AppLink$Target>)list, getWebFallbackUriFromJson(uri, jsonObject));
                                    this.val$appLinkResults.put(uri, appLink);
                                    synchronized (this.this$0.cachedAppLinks) {
                                        this.this$0.cachedAppLinks.put(uri, appLink);
                                    }
                                }
                            }
                            catch (JSONException ex) {
                                break;
                            }
                            break Label_0261;
                        }
                        ++n;
                        continue;
                    }
                }
            }
        }
        this.val$taskCompletionSource.setResult(this.val$appLinkResults);
    }
}

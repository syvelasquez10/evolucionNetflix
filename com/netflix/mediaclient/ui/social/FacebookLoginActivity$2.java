// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social;

import com.netflix.mediaclient.Log;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.facebook.Request$GraphUserCallback;

class FacebookLoginActivity$2 implements Request$GraphUserCallback
{
    final /* synthetic */ FacebookLoginActivity this$0;
    
    FacebookLoginActivity$2(final FacebookLoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCompleted(final GraphUser graphUser, final Response response) {
        if (graphUser == null) {
            Log.v("FacebookLoginActivity", "Me request user: null");
        }
        else {
            Log.v("FacebookLoginActivity", "Me request user: " + graphUser.getId() + " " + graphUser.getName() + " " + graphUser.getInnerJSONObject());
        }
        Log.v("FacebookLoginActivity", "Me response: " + response);
    }
}

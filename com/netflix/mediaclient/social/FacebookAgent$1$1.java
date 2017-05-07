// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.social;

import android.content.Intent;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.netflix.mediaclient.event.service.AuthorizationFailed;
import com.facebook.android.DialogError;
import com.netflix.mediaclient.event.service.FacebookAuthorized;
import android.os.Bundle;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.service.AuthorizationCanceled;
import com.netflix.mediaclient.event.service.Service;
import com.netflix.mediaclient.Log;
import com.facebook.android.Facebook$DialogListener;

class FacebookAgent$1$1 implements Facebook$DialogListener
{
    final /* synthetic */ FacebookAgent$1 this$1;
    
    FacebookAgent$1$1(final FacebookAgent$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onCancel() {
        Log.e("FacebookAgent", "onCancel:: user canceled facebook SSO.");
        if (!this.this$1.this$0.resultReported) {
            this.this$1.val$activity.getNetflixApplication().publishEvent(new AuthorizationCanceled(Service.facebook));
            this.this$1.this$0.resultReported = true;
            return;
        }
        Log.w("FacebookAgent", "Result already reported!");
    }
    
    @Override
    public void onComplete(final Bundle bundle) {
        if (Log.isLoggable("FacebookAgent", 3)) {
            Log.d("FacebookAgent", "onComplete:: we are authorized to access Facebook. Save credentials and send event to UI");
            Log.d("FacebookAgent", "Access token from Facebook " + this.this$1.this$0.facebook.getAccessToken());
            Log.d("FacebookAgent", "Expires from Facebook " + this.this$1.this$0.facebook.getAccessExpires());
        }
        if (!this.this$1.this$0.resultReported) {
            this.this$1.val$activity.getNetflixApplication().publishEvent(new FacebookAuthorized(this.this$1.this$0.facebook.getAccessToken()));
            this.this$1.this$0.resultReported = true;
            return;
        }
        Log.w("FacebookAgent", "Result already reported!");
    }
    
    @Override
    public void onError(final DialogError dialogError) {
        Log.e("FacebookAgent", "onError:: facebook client is not installed, webview is used for SSO.", dialogError);
        if (!this.this$1.this$0.resultReported) {
            this.this$1.val$activity.getNetflixApplication().publishEvent(new AuthorizationFailed(Service.facebook));
            this.this$1.this$0.resultReported = true;
            return;
        }
        Log.w("FacebookAgent", "Result already reported!");
    }
    
    @Override
    public void onFacebookError(final FacebookError facebookError) {
        Log.e("FacebookAgent", "onFacebookError:: Facebook client was used for SSO", facebookError);
        if (!this.this$1.this$0.resultReported) {
            this.this$1.val$activity.getNetflixApplication().publishEvent(new AuthorizationFailed(Service.facebook));
            this.this$1.this$0.resultReported = true;
            return;
        }
        Log.w("FacebookAgent", "Result already reported!");
    }
}

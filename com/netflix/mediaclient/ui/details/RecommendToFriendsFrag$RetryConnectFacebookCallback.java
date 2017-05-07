// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.social.FacebookLoginActivity;
import android.app.Activity;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

final class RecommendToFriendsFrag$RetryConnectFacebookCallback implements ErrorWrapper$Callback
{
    private final Activity mActivity;
    final /* synthetic */ RecommendToFriendsFrag this$0;
    
    public RecommendToFriendsFrag$RetryConnectFacebookCallback(final RecommendToFriendsFrag this$0, final Activity mActivity) {
        this.this$0 = this$0;
        this.mActivity = mActivity;
    }
    
    @Override
    public void onRetryRequested() {
        FacebookLoginActivity.show(this.mActivity);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.SocialLogging$Channel;
import android.app.Activity;
import com.netflix.mediaclient.ui.social.FacebookLoginActivity;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class RecommendToFriendsFrag$2$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ RecommendToFriendsFrag$2 this$0;
    
    RecommendToFriendsFrag$2$1(final RecommendToFriendsFrag$2 this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (this.this$0.val$activity != null && !this.this$0.val$activity.isFinishing()) {
            FacebookLoginActivity.show(this.this$0.val$activity);
            SocialLoggingUtils.reportStartSocialConnectSession((Context)this.this$0.val$activity, SocialLogging$Channel.Facebook);
        }
    }
}

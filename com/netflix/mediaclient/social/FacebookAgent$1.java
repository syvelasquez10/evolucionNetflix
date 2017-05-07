// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.social;

import android.content.Intent;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.Log;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook$DialogListener;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class FacebookAgent$1 implements Runnable
{
    final /* synthetic */ FacebookAgent this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String[] val$perm;
    
    FacebookAgent$1(final FacebookAgent this$0, final NetflixActivity val$activity, final String[] val$perm) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$perm = val$perm;
    }
    
    @Override
    public void run() {
        this.this$0.facebook.authorize(this.val$activity, this.val$perm, new FacebookAgent$1$1(this));
    }
}

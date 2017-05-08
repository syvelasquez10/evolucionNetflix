// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.app.Activity;
import com.netflix.mediaclient.ui.offline.OfflineActivity;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.view.View$OnClickListener;

class ErrorWrapper$1 implements View$OnClickListener
{
    final /* synthetic */ ErrorWrapper this$0;
    
    ErrorWrapper$1(final ErrorWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(view.getContext(), NetflixActivity.class);
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
            netflixActivity.startActivity(OfflineActivity.showAllDownloads(netflixActivity));
        }
    }
}

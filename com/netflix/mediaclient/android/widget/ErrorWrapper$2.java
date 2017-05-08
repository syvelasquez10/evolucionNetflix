// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.content.Context;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class ErrorWrapper$2 implements View$OnClickListener
{
    final /* synthetic */ ErrorWrapper this$0;
    
    ErrorWrapper$2(final ErrorWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Log.v("ErrorWrapper", "Retry requested");
        if (this.this$0.callback != null) {
            this.this$0.callback.onRetryRequested();
        }
    }
}

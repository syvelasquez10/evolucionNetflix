// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import com.netflix.mediaclient.ui.home.HomeActivity;
import com.android.volley.Request$Priority;
import android.widget.ImageView;

public class InteractiveTracker$TTRTracker extends InteractiveTracker
{
    public boolean shouldTrack(final ImageView imageView, final Request$Priority request$Priority) {
        return super.shouldTrack(imageView) && imageView.getContext() instanceof HomeActivity && request$Priority == Request$Priority.NORMAL;
    }
}

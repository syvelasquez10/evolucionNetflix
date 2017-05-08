// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import android.app.Application$ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.LinkedList;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow$OnDismissListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import android.os.SystemClock;

class Advisor$1 implements Runnable
{
    final /* synthetic */ Advisor this$0;
    
    Advisor$1(final Advisor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.timeSinceLastShow = SystemClock.uptimeMillis();
        if (this.this$0.anchor != null) {
            this.this$0.popupWindow.showAsDropDown(this.this$0.anchor, 0, 0);
        }
        else {
            this.this$0.popupWindow.showAtLocation(this.this$0.context.findViewById(16908290), 8388659, 0, 0);
        }
        this.this$0.context.getHandler().postDelayed(this.this$0.dismissPopup, (long)this.this$0.durationInSeconds * TimeUnit.SECONDS.toMillis(1L));
    }
}

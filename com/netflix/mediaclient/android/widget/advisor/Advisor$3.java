// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import android.os.SystemClock;
import android.app.Application$ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.Queue;
import android.widget.PopupWindow$OnDismissListener;

class Advisor$3 implements PopupWindow$OnDismissListener
{
    final /* synthetic */ Advisor this$0;
    
    Advisor$3(final Advisor this$0) {
        this.this$0 = this$0;
    }
    
    public void onDismiss() {
        this.this$0.context.getHandler().removeCallbacks(this.this$0.showPopup);
        this.this$0.context.getHandler().removeCallbacks(this.this$0.dismissPopup);
        processNextInQueue(this.this$0.isHardDismiss);
    }
}

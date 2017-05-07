// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import android.view.KeyEvent;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.MenuItem;
import com.viewpagerindicator.android.osp.ViewPager;
import android.view.ViewParent;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.widget.ListView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.database.DataSetObserver;
import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class LoLoMoFocusHandler$1 implements View$OnTouchListener
{
    final /* synthetic */ LoLoMoFocusHandler this$0;
    
    LoLoMoFocusHandler$1(final LoLoMoFocusHandler this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (!this.this$0.touchEnabled) {
            Log.v("LoLoMoFocusHandler", "enabling touch");
        }
        this.this$0.touchEnabled = true;
        return false;
    }
}

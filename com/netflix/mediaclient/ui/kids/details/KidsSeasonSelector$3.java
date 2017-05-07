// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.util.StringUtils;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.ArrayList;
import android.view.View$OnTouchListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import java.util.List;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.TextView;
import android.view.View$OnClickListener;

class KidsSeasonSelector$3 implements View$OnClickListener
{
    final /* synthetic */ KidsSeasonSelector this$0;
    final /* synthetic */ TextView val$tv;
    
    KidsSeasonSelector$3(final KidsSeasonSelector this$0, final TextView val$tv) {
        this.this$0 = this$0;
        this.val$tv = val$tv;
    }
    
    public void onClick(final View view) {
        Log.v("KidsSeasonSelector", "Clicked on: " + (Object)this.val$tv.getText());
        this.this$0.adapter.selectSeasonByNumber((int)this.val$tv.getTag());
    }
}

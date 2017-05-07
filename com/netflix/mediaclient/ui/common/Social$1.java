// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageButton;
import android.app.Activity;
import com.netflix.mediaclient.ui.Section;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class Social$1 implements View$OnClickListener
{
    final /* synthetic */ Social this$0;
    
    Social$1(final Social this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Log.d("playcard", "Touch facebook!");
        this.this$0.toggleMessageVisibility();
    }
}

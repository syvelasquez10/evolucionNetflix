// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.Menu;
import android.view.View$OnTouchListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class Social$1 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ Social this$0;
    
    Social$1(final Social this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        Log.d("playcard", "Touch facebook!");
        this.this$0.toggleMessageVisibility();
        return true;
    }
}

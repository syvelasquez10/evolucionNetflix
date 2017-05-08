// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import com.netflix.mediaclient.ui.offline.OfflineActivity;
import android.view.View;
import android.view.View$OnClickListener;

class StandardSlidingMenu$4 implements View$OnClickListener
{
    final /* synthetic */ StandardSlidingMenu this$0;
    
    StandardSlidingMenu$4(final StandardSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(OfflineActivity.showAllDownloads(this.this$0.activity));
        this.this$0.closeDrawers();
    }
}

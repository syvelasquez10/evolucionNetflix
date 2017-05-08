// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Intent;
import android.view.View;
import android.view.View$OnClickListener;

class StandardSlidingMenu$8 implements View$OnClickListener
{
    final /* synthetic */ StandardSlidingMenu this$0;
    
    StandardSlidingMenu$8(final StandardSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final Intent startIntent = HomeActivity.createStartIntent(this.this$0.activity);
        startIntent.addFlags(67108864);
        this.this$0.activity.startActivity(startIntent);
        this.this$0.closeDrawersWithDelay();
    }
}

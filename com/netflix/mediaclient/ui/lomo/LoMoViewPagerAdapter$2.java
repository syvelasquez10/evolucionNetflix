// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.View;
import android.view.View$OnClickListener;

class LoMoViewPagerAdapter$2 implements View$OnClickListener
{
    final /* synthetic */ LoMoViewPagerAdapter this$0;
    
    LoMoViewPagerAdapter$2(final LoMoViewPagerAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.reload();
    }
}

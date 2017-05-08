// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.View;
import android.view.View$OnClickListener;

class MiniPlayerControlsFrag$3$1 implements View$OnClickListener
{
    final /* synthetic */ MiniPlayerControlsFrag$3 this$1;
    
    MiniPlayerControlsFrag$3$1(final MiniPlayerControlsFrag$3 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.this$0.getNetflixActivity().onBackPressed();
    }
}

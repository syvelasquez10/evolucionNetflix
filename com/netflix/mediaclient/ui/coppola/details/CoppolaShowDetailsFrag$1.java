// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class CoppolaShowDetailsFrag$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ CoppolaShowDetailsFrag this$0;
    
    CoppolaShowDetailsFrag$1(final CoppolaShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return (View)new CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView(this.this$0, (Context)this.this$0.getActivity());
    }
}

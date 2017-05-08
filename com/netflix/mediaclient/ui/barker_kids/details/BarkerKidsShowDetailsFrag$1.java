// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class BarkerKidsShowDetailsFrag$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ BarkerKidsShowDetailsFrag this$0;
    
    BarkerKidsShowDetailsFrag$1(final BarkerKidsShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return (View)new BarkerKidsShowDetailsFrag$BarkerKidsEpisodeView(this.this$0, (Context)this.this$0.getActivity(), 2130903165);
    }
}

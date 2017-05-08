// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

class BarkerShowDetailsFrag$3 implements BarkerSeasonsDialogAdapter$OnSeasonSelectedListener
{
    final /* synthetic */ BarkerShowDetailsFrag this$0;
    
    BarkerShowDetailsFrag$3(final BarkerShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onSeasonSelected(final int n) {
        this.this$0.switchSeason(n, false);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

class KubrickShowDetailsFrag$3 implements KubrickSeasonsDialogAdapter$OnSeasonSelectedListener
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    KubrickShowDetailsFrag$3(final KubrickShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onSeasonSelected(final int n) {
        this.this$0.switchSeason(n, false);
    }
}

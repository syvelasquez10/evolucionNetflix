// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.view.View;
import android.view.View$OnClickListener;

class BarkerVideoDetailsViewGroup$DataSelectorButtonListener implements View$OnClickListener
{
    final /* synthetic */ BarkerVideoDetailsViewGroup this$0;
    
    BarkerVideoDetailsViewGroup$DataSelectorButtonListener(final BarkerVideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (view.getId() == 2131689635) {
            ((BarkerShowDetailsFrag)((BarkerShowDetailsActivity)this.this$0.getContext()).getPrimaryFrag()).showRelatedTitles();
            this.this$0.setRelatedTextAsSelected();
        }
        else if (view.getId() == 2131689633) {
            ((BarkerShowDetailsFrag)((BarkerShowDetailsActivity)this.this$0.getContext()).getPrimaryFrag()).showCurrentSeason();
            this.this$0.setEpisodesTextAsSelected();
        }
    }
}

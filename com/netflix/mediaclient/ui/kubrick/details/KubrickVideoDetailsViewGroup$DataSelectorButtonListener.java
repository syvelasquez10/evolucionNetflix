// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.view.View;
import android.view.View$OnClickListener;

class KubrickVideoDetailsViewGroup$DataSelectorButtonListener implements View$OnClickListener
{
    final /* synthetic */ KubrickVideoDetailsViewGroup this$0;
    
    KubrickVideoDetailsViewGroup$DataSelectorButtonListener(final KubrickVideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (view.getId() == 2131427603) {
            ((KubrickShowDetailsFrag)((KubrickShowDetailsActivity)this.this$0.getContext()).getPrimaryFrag()).showRelatedTitles();
            this.this$0.setRelatedTextAsSelected();
        }
        else if (view.getId() == 2131427601) {
            ((KubrickShowDetailsFrag)((KubrickShowDetailsActivity)this.this$0.getContext()).getPrimaryFrag()).showCurrentSeason();
            this.this$0.setEpisodesTextAsSelected();
        }
    }
}

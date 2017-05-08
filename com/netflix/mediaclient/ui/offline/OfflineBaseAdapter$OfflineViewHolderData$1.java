// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.View;
import android.view.View$OnClickListener;

class OfflineBaseAdapter$OfflineViewHolderData$1 implements View$OnClickListener
{
    final /* synthetic */ OfflineBaseAdapter$OfflineViewHolderData this$1;
    
    OfflineBaseAdapter$OfflineViewHolderData$1(final OfflineBaseAdapter$OfflineViewHolderData this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.this$0.mRowClickListener.onRowClicked(this.this$1.getAdapterPosition(), this.this$1.playIcon.getVisibility() == 0);
    }
}

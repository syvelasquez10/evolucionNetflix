// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.View;
import android.view.View$OnLongClickListener;

class OfflineBaseAdapter$OfflineViewHolderData$3 implements View$OnLongClickListener
{
    final /* synthetic */ OfflineBaseAdapter$OfflineViewHolderData this$1;
    
    OfflineBaseAdapter$OfflineViewHolderData$3(final OfflineBaseAdapter$OfflineViewHolderData this$1) {
        this.this$1 = this$1;
    }
    
    public boolean onLongClick(final View view) {
        final int adapterPosition = this.this$1.getAdapterPosition();
        this.this$1.this$0.toggleChecked(adapterPosition, this.this$1.this$0.getPlayableId(adapterPosition));
        this.this$1.this$0.setSelectionMode(true);
        return true;
    }
}

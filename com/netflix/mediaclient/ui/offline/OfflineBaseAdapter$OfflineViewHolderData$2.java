// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class OfflineBaseAdapter$OfflineViewHolderData$2 implements View$OnClickListener
{
    final /* synthetic */ OfflineBaseAdapter$OfflineViewHolderData this$1;
    
    OfflineBaseAdapter$OfflineViewHolderData$2(final OfflineBaseAdapter$OfflineViewHolderData this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        final int adapterPosition = this.this$1.getAdapterPosition();
        if (adapterPosition != -1) {
            this.this$1.this$0.toggleChecked(adapterPosition, this.this$1.this$0.getPlayableId(adapterPosition));
            return;
        }
        Log.i("OfflineBaseAdapter", "checkmarkClickListener position=%d", adapterPosition);
    }
}

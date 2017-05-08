// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class OfflineBaseAdapter$OfflineViewHolderData$1 implements View$OnClickListener
{
    final /* synthetic */ OfflineBaseAdapter$OfflineViewHolderData this$1;
    
    OfflineBaseAdapter$OfflineViewHolderData$1(final OfflineBaseAdapter$OfflineViewHolderData this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        boolean b = true;
        final int adapterPosition = this.this$1.getAdapterPosition();
        if (adapterPosition != -1) {
            final OfflineBaseAdapter$RowClickListener mRowClickListener = this.this$1.this$0.mRowClickListener;
            if (this.this$1.playIcon.getVisibility() != 0) {
                b = false;
            }
            mRowClickListener.onRowClicked(adapterPosition, b);
            return;
        }
        Log.i("OfflineBaseAdapter", "clickListener position=%d", adapterPosition);
    }
}

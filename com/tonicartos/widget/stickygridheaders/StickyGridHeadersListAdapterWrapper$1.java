// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.database.DataSetObserver;

class StickyGridHeadersListAdapterWrapper$1 extends DataSetObserver
{
    final /* synthetic */ StickyGridHeadersListAdapterWrapper this$0;
    
    StickyGridHeadersListAdapterWrapper$1(final StickyGridHeadersListAdapterWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        this.this$0.notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        this.this$0.notifyDataSetInvalidated();
    }
}

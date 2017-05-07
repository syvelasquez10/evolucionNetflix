// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.BaseAdapter;
import android.database.DataSetObserver;

class StickyGridHeadersBaseAdapterWrapper$1 extends DataSetObserver
{
    final /* synthetic */ StickyGridHeadersBaseAdapterWrapper this$0;
    
    StickyGridHeadersBaseAdapterWrapper$1(final StickyGridHeadersBaseAdapterWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        this.this$0.updateCount();
        this.this$0.notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        this.this$0.mCounted = false;
        this.this$0.notifyDataSetInvalidated();
    }
}

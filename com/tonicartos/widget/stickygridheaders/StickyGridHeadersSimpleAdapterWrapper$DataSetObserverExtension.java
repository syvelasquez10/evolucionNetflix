// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.BaseAdapter;
import android.database.DataSetObserver;

final class StickyGridHeadersSimpleAdapterWrapper$DataSetObserverExtension extends DataSetObserver
{
    final /* synthetic */ StickyGridHeadersSimpleAdapterWrapper this$0;
    
    private StickyGridHeadersSimpleAdapterWrapper$DataSetObserverExtension(final StickyGridHeadersSimpleAdapterWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        this.this$0.mHeaders = this.this$0.generateHeaderList(this.this$0.mDelegate);
        this.this$0.notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        this.this$0.mHeaders = this.this$0.generateHeaderList(this.this$0.mDelegate);
        this.this$0.notifyDataSetInvalidated();
    }
}

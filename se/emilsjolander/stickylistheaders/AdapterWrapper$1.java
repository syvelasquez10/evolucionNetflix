// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.widget.Checkable;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import java.util.LinkedList;
import android.view.View;
import java.util.List;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.widget.BaseAdapter;
import android.database.DataSetObserver;

class AdapterWrapper$1 extends DataSetObserver
{
    final /* synthetic */ AdapterWrapper this$0;
    
    AdapterWrapper$1(final AdapterWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        AdapterWrapper.access$201(this.this$0);
    }
    
    public void onInvalidated() {
        this.this$0.mHeaderCache.clear();
        AdapterWrapper.access$101(this.this$0);
    }
}

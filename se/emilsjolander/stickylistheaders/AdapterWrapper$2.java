// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.widget.Checkable;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;
import android.graphics.drawable.Drawable;
import android.database.DataSetObserver;
import android.content.Context;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.View$OnClickListener;

class AdapterWrapper$2 implements View$OnClickListener
{
    final /* synthetic */ AdapterWrapper this$0;
    final /* synthetic */ int val$position;
    
    AdapterWrapper$2(final AdapterWrapper this$0, final int val$position) {
        this.this$0 = this$0;
        this.val$position = val$position;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mOnHeaderClickListener != null) {
            this.this$0.mOnHeaderClickListener.onHeaderClick(view, this.val$position, this.this$0.mDelegate.getHeaderId(this.val$position));
        }
    }
}

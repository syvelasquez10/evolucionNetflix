// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.database.DataSetObserver;

class CursorAdapter$MyDataSetObserver extends DataSetObserver
{
    final /* synthetic */ CursorAdapter this$0;
    
    CursorAdapter$MyDataSetObserver(final CursorAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        this.this$0.mDataValid = true;
        this.this$0.notifyDataSetChanged();
    }
    
    public void onInvalidated() {
        this.this$0.mDataValid = false;
        this.this$0.notifyDataSetInvalidated();
    }
}

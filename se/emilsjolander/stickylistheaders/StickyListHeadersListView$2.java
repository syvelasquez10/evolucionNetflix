// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class StickyListHeadersListView$2 implements View$OnTouchListener
{
    final /* synthetic */ StickyListHeadersListView this$0;
    final /* synthetic */ View$OnTouchListener val$l;
    
    StickyListHeadersListView$2(final StickyListHeadersListView this$0, final View$OnTouchListener val$l) {
        this.this$0 = this$0;
        this.val$l = val$l;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return this.val$l.onTouch((View)this.this$0, motionEvent);
    }
}

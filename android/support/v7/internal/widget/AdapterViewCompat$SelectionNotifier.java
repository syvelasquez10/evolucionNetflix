// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View$OnClickListener;
import android.view.ViewDebug$CapturedViewProperty;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$LayoutParams;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewDebug$ExportedProperty;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

class AdapterViewCompat$SelectionNotifier implements Runnable
{
    final /* synthetic */ AdapterViewCompat this$0;
    
    private AdapterViewCompat$SelectionNotifier(final AdapterViewCompat this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mDataChanged) {
            if (this.this$0.getAdapter() != null) {
                this.this$0.post((Runnable)this);
            }
            return;
        }
        this.this$0.fireOnSelected();
    }
}

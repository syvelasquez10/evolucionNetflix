// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class AdapterViewCompat$OnItemClickListenerWrapper implements AdapterView$OnItemClickListener
{
    private final AdapterViewCompat$OnItemClickListener mWrappedListener;
    final /* synthetic */ AdapterViewCompat this$0;
    
    public AdapterViewCompat$OnItemClickListenerWrapper(final AdapterViewCompat this$0, final AdapterViewCompat$OnItemClickListener mWrappedListener) {
        this.this$0 = this$0;
        this.mWrappedListener = mWrappedListener;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.mWrappedListener.onItemClick(this.this$0, view, n, n2);
    }
}

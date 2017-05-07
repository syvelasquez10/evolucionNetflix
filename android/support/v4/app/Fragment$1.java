// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;

class Fragment$1 implements FragmentContainer
{
    final /* synthetic */ Fragment this$0;
    
    Fragment$1(final Fragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View findViewById(final int n) {
        if (this.this$0.mView == null) {
            throw new IllegalStateException("Fragment does not have a view");
        }
        return this.this$0.mView.findViewById(n);
    }
    
    @Override
    public boolean hasView() {
        return this.this$0.mView != null;
    }
}

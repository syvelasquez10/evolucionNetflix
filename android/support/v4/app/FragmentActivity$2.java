// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.Window;
import android.view.View;

class FragmentActivity$2 implements FragmentContainer
{
    final /* synthetic */ FragmentActivity this$0;
    
    FragmentActivity$2(final FragmentActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View findViewById(final int n) {
        return this.this$0.findViewById(n);
    }
    
    @Override
    public boolean hasView() {
        final Window window = this.this$0.getWindow();
        return window != null && window.peekDecorView() != null;
    }
}

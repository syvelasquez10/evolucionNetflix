// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;

class ActionBarActivityDelegateBase$4 implements Runnable
{
    final /* synthetic */ ActionBarActivityDelegateBase this$0;
    
    ActionBarActivityDelegateBase$4(final ActionBarActivityDelegateBase this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mActionModePopup.showAtLocation((View)this.this$0.mActionModeView, 55, 0, 0);
    }
}

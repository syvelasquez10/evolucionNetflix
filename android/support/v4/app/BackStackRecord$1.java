// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.View;

class BackStackRecord$1 implements FragmentTransitionCompat21$ViewRetriever
{
    final /* synthetic */ BackStackRecord this$0;
    final /* synthetic */ Fragment val$inFragment;
    
    BackStackRecord$1(final BackStackRecord this$0, final Fragment val$inFragment) {
        this.this$0 = this$0;
        this.val$inFragment = val$inFragment;
    }
    
    @Override
    public View getView() {
        return this.val$inFragment.getView();
    }
}

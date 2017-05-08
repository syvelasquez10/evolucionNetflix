// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.ArrayList;

class DefaultItemAnimator$2 implements Runnable
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ArrayList val$changes;
    
    DefaultItemAnimator$2(final DefaultItemAnimator this$0, final ArrayList val$changes) {
        this.this$0 = this$0;
        this.val$changes = val$changes;
    }
    
    @Override
    public void run() {
        final Iterator<DefaultItemAnimator$ChangeInfo> iterator = this.val$changes.iterator();
        while (iterator.hasNext()) {
            this.this$0.animateChangeImpl(iterator.next());
        }
        this.val$changes.clear();
        this.this$0.mChangesList.remove(this.val$changes);
    }
}

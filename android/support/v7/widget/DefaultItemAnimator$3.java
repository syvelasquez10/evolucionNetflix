// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Collection;
import android.support.v4.animation.AnimatorCompatHelper;
import java.util.List;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import java.util.Iterator;
import java.util.ArrayList;

class DefaultItemAnimator$3 implements Runnable
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ArrayList val$additions;
    
    DefaultItemAnimator$3(final DefaultItemAnimator this$0, final ArrayList val$additions) {
        this.this$0 = this$0;
        this.val$additions = val$additions;
    }
    
    @Override
    public void run() {
        final Iterator<RecyclerView$ViewHolder> iterator = this.val$additions.iterator();
        while (iterator.hasNext()) {
            this.this$0.animateAddImpl(iterator.next());
        }
        this.val$additions.clear();
        this.this$0.mAdditionsList.remove(this.val$additions);
    }
}

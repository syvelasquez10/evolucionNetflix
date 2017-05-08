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

class DefaultItemAnimator$1 implements Runnable
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ArrayList val$moves;
    
    DefaultItemAnimator$1(final DefaultItemAnimator this$0, final ArrayList val$moves) {
        this.this$0 = this$0;
        this.val$moves = val$moves;
    }
    
    @Override
    public void run() {
        for (final DefaultItemAnimator$MoveInfo defaultItemAnimator$MoveInfo : this.val$moves) {
            this.this$0.animateMoveImpl(defaultItemAnimator$MoveInfo.holder, defaultItemAnimator$MoveInfo.fromX, defaultItemAnimator$MoveInfo.fromY, defaultItemAnimator$MoveInfo.toX, defaultItemAnimator$MoveInfo.toY);
        }
        this.val$moves.clear();
        this.this$0.mMovesList.remove(this.val$moves);
    }
}

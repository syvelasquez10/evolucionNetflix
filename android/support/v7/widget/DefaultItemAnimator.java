// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;

public class DefaultItemAnimator extends RecyclerView$ItemAnimator
{
    private ArrayList<RecyclerView$ViewHolder> mAddAnimations;
    private ArrayList<ArrayList<RecyclerView$ViewHolder>> mAdditionsList;
    private ArrayList<RecyclerView$ViewHolder> mChangeAnimations;
    private ArrayList<ArrayList<DefaultItemAnimator$ChangeInfo>> mChangesList;
    private ArrayList<RecyclerView$ViewHolder> mMoveAnimations;
    private ArrayList<ArrayList<DefaultItemAnimator$MoveInfo>> mMovesList;
    private ArrayList<RecyclerView$ViewHolder> mPendingAdditions;
    private ArrayList<DefaultItemAnimator$ChangeInfo> mPendingChanges;
    private ArrayList<DefaultItemAnimator$MoveInfo> mPendingMoves;
    private ArrayList<RecyclerView$ViewHolder> mPendingRemovals;
    private ArrayList<RecyclerView$ViewHolder> mRemoveAnimations;
    
    public DefaultItemAnimator() {
        this.mPendingRemovals = new ArrayList<RecyclerView$ViewHolder>();
        this.mPendingAdditions = new ArrayList<RecyclerView$ViewHolder>();
        this.mPendingMoves = new ArrayList<DefaultItemAnimator$MoveInfo>();
        this.mPendingChanges = new ArrayList<DefaultItemAnimator$ChangeInfo>();
        this.mAdditionsList = new ArrayList<ArrayList<RecyclerView$ViewHolder>>();
        this.mMovesList = new ArrayList<ArrayList<DefaultItemAnimator$MoveInfo>>();
        this.mChangesList = new ArrayList<ArrayList<DefaultItemAnimator$ChangeInfo>>();
        this.mAddAnimations = new ArrayList<RecyclerView$ViewHolder>();
        this.mMoveAnimations = new ArrayList<RecyclerView$ViewHolder>();
        this.mRemoveAnimations = new ArrayList<RecyclerView$ViewHolder>();
        this.mChangeAnimations = new ArrayList<RecyclerView$ViewHolder>();
    }
    
    private void animateAddImpl(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final View itemView = recyclerView$ViewHolder.itemView;
        this.mAddAnimations.add(recyclerView$ViewHolder);
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(itemView);
        animate.alpha(1.0f).setDuration(this.getAddDuration()).setListener(new DefaultItemAnimator$5(this, recyclerView$ViewHolder, animate)).start();
    }
    
    private void animateChangeImpl(final DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo) {
        View itemView = null;
        final RecyclerView$ViewHolder oldHolder = defaultItemAnimator$ChangeInfo.oldHolder;
        View itemView2;
        if (oldHolder == null) {
            itemView2 = null;
        }
        else {
            itemView2 = oldHolder.itemView;
        }
        final RecyclerView$ViewHolder newHolder = defaultItemAnimator$ChangeInfo.newHolder;
        if (newHolder != null) {
            itemView = newHolder.itemView;
        }
        if (itemView2 != null) {
            this.mChangeAnimations.add(defaultItemAnimator$ChangeInfo.oldHolder);
            final ViewPropertyAnimatorCompat setDuration = ViewCompat.animate(itemView2).setDuration(this.getChangeDuration());
            setDuration.translationX(defaultItemAnimator$ChangeInfo.toX - defaultItemAnimator$ChangeInfo.fromX);
            setDuration.translationY(defaultItemAnimator$ChangeInfo.toY - defaultItemAnimator$ChangeInfo.fromY);
            setDuration.alpha(0.0f).setListener(new DefaultItemAnimator$7(this, defaultItemAnimator$ChangeInfo, setDuration)).start();
        }
        if (itemView != null) {
            this.mChangeAnimations.add(defaultItemAnimator$ChangeInfo.newHolder);
            final ViewPropertyAnimatorCompat animate = ViewCompat.animate(itemView);
            animate.translationX(0.0f).translationY(0.0f).setDuration(this.getChangeDuration()).alpha(1.0f).setListener(new DefaultItemAnimator$8(this, defaultItemAnimator$ChangeInfo, animate, itemView)).start();
        }
    }
    
    private void animateMoveImpl(final RecyclerView$ViewHolder recyclerView$ViewHolder, int n, int n2, final int n3, final int n4) {
        final View itemView = recyclerView$ViewHolder.itemView;
        n = n3 - n;
        n2 = n4 - n2;
        if (n != 0) {
            ViewCompat.animate(itemView).translationX(0.0f);
        }
        if (n2 != 0) {
            ViewCompat.animate(itemView).translationY(0.0f);
        }
        this.mMoveAnimations.add(recyclerView$ViewHolder);
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(itemView);
        animate.setDuration(this.getMoveDuration()).setListener(new DefaultItemAnimator$6(this, recyclerView$ViewHolder, n, n2, animate)).start();
    }
    
    private void animateRemoveImpl(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(recyclerView$ViewHolder.itemView);
        animate.setDuration(this.getRemoveDuration()).alpha(0.0f).setListener(new DefaultItemAnimator$4(this, recyclerView$ViewHolder, animate)).start();
        this.mRemoveAnimations.add(recyclerView$ViewHolder);
    }
    
    private void dispatchFinishedWhenDone() {
        if (!this.isRunning()) {
            this.dispatchAnimationsFinished();
        }
    }
    
    private void endChangeAnimation(final List<DefaultItemAnimator$ChangeInfo> list, final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        for (int i = list.size() - 1; i >= 0; --i) {
            final DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo = list.get(i);
            if (this.endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo, recyclerView$ViewHolder) && defaultItemAnimator$ChangeInfo.oldHolder == null && defaultItemAnimator$ChangeInfo.newHolder == null) {
                list.remove(defaultItemAnimator$ChangeInfo);
            }
        }
    }
    
    private void endChangeAnimationIfNecessary(final DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo) {
        if (defaultItemAnimator$ChangeInfo.oldHolder != null) {
            this.endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo, defaultItemAnimator$ChangeInfo.oldHolder);
        }
        if (defaultItemAnimator$ChangeInfo.newHolder != null) {
            this.endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo, defaultItemAnimator$ChangeInfo.newHolder);
        }
    }
    
    private boolean endChangeAnimationIfNecessary(final DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo, final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final boolean b = false;
        boolean b2 = false;
        if (defaultItemAnimator$ChangeInfo.newHolder == recyclerView$ViewHolder) {
            defaultItemAnimator$ChangeInfo.newHolder = null;
        }
        else {
            final boolean b3 = b;
            if (defaultItemAnimator$ChangeInfo.oldHolder != recyclerView$ViewHolder) {
                return b3;
            }
            defaultItemAnimator$ChangeInfo.oldHolder = null;
            b2 = true;
        }
        ViewCompat.setAlpha(recyclerView$ViewHolder.itemView, 1.0f);
        ViewCompat.setTranslationX(recyclerView$ViewHolder.itemView, 0.0f);
        ViewCompat.setTranslationY(recyclerView$ViewHolder.itemView, 0.0f);
        this.dispatchChangeFinished(recyclerView$ViewHolder, b2);
        return true;
    }
    
    @Override
    public boolean animateAdd(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.endAnimation(recyclerView$ViewHolder);
        ViewCompat.setAlpha(recyclerView$ViewHolder.itemView, 0.0f);
        this.mPendingAdditions.add(recyclerView$ViewHolder);
        return true;
    }
    
    @Override
    public boolean animateChange(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ViewHolder recyclerView$ViewHolder2, final int n, final int n2, final int n3, final int n4) {
        final float translationX = ViewCompat.getTranslationX(recyclerView$ViewHolder.itemView);
        final float translationY = ViewCompat.getTranslationY(recyclerView$ViewHolder.itemView);
        final float alpha = ViewCompat.getAlpha(recyclerView$ViewHolder.itemView);
        this.endAnimation(recyclerView$ViewHolder);
        final int n5 = (int)(n3 - n - translationX);
        final int n6 = (int)(n4 - n2 - translationY);
        ViewCompat.setTranslationX(recyclerView$ViewHolder.itemView, translationX);
        ViewCompat.setTranslationY(recyclerView$ViewHolder.itemView, translationY);
        ViewCompat.setAlpha(recyclerView$ViewHolder.itemView, alpha);
        if (recyclerView$ViewHolder2 != null && recyclerView$ViewHolder2.itemView != null) {
            this.endAnimation(recyclerView$ViewHolder2);
            ViewCompat.setTranslationX(recyclerView$ViewHolder2.itemView, -n5);
            ViewCompat.setTranslationY(recyclerView$ViewHolder2.itemView, -n6);
            ViewCompat.setAlpha(recyclerView$ViewHolder2.itemView, 0.0f);
        }
        this.mPendingChanges.add(new DefaultItemAnimator$ChangeInfo(recyclerView$ViewHolder, recyclerView$ViewHolder2, n, n2, n3, n4, null));
        return true;
    }
    
    @Override
    public boolean animateMove(final RecyclerView$ViewHolder recyclerView$ViewHolder, int n, int n2, final int n3, final int n4) {
        final View itemView = recyclerView$ViewHolder.itemView;
        n += (int)ViewCompat.getTranslationX(recyclerView$ViewHolder.itemView);
        n2 += (int)ViewCompat.getTranslationY(recyclerView$ViewHolder.itemView);
        this.endAnimation(recyclerView$ViewHolder);
        final int n5 = n3 - n;
        final int n6 = n4 - n2;
        if (n5 == 0 && n6 == 0) {
            this.dispatchMoveFinished(recyclerView$ViewHolder);
            return false;
        }
        if (n5 != 0) {
            ViewCompat.setTranslationX(itemView, -n5);
        }
        if (n6 != 0) {
            ViewCompat.setTranslationY(itemView, -n6);
        }
        this.mPendingMoves.add(new DefaultItemAnimator$MoveInfo(recyclerView$ViewHolder, n, n2, n3, n4, null));
        return true;
    }
    
    @Override
    public boolean animateRemove(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.endAnimation(recyclerView$ViewHolder);
        this.mPendingRemovals.add(recyclerView$ViewHolder);
        return true;
    }
    
    void cancelAll(final List<RecyclerView$ViewHolder> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            ViewCompat.animate(list.get(i).itemView).cancel();
        }
    }
    
    @Override
    public void endAnimation(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final View itemView = recyclerView$ViewHolder.itemView;
        ViewCompat.animate(itemView).cancel();
        for (int i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            if (this.mPendingMoves.get(i).holder == recyclerView$ViewHolder) {
                ViewCompat.setTranslationY(itemView, 0.0f);
                ViewCompat.setTranslationX(itemView, 0.0f);
                this.dispatchMoveFinished(recyclerView$ViewHolder);
                this.mPendingMoves.remove(i);
            }
        }
        this.endChangeAnimation(this.mPendingChanges, recyclerView$ViewHolder);
        if (this.mPendingRemovals.remove(recyclerView$ViewHolder)) {
            ViewCompat.setAlpha(itemView, 1.0f);
            this.dispatchRemoveFinished(recyclerView$ViewHolder);
        }
        if (this.mPendingAdditions.remove(recyclerView$ViewHolder)) {
            ViewCompat.setAlpha(itemView, 1.0f);
            this.dispatchAddFinished(recyclerView$ViewHolder);
        }
        for (int j = this.mChangesList.size() - 1; j >= 0; --j) {
            final ArrayList<DefaultItemAnimator$ChangeInfo> list = this.mChangesList.get(j);
            this.endChangeAnimation(list, recyclerView$ViewHolder);
            if (list.isEmpty()) {
                this.mChangesList.remove(j);
            }
        }
        for (int k = this.mMovesList.size() - 1; k >= 0; --k) {
            final ArrayList<DefaultItemAnimator$MoveInfo> list2 = this.mMovesList.get(k);
            int l = list2.size() - 1;
            while (l >= 0) {
                if (list2.get(l).holder == recyclerView$ViewHolder) {
                    ViewCompat.setTranslationY(itemView, 0.0f);
                    ViewCompat.setTranslationX(itemView, 0.0f);
                    this.dispatchMoveFinished(recyclerView$ViewHolder);
                    list2.remove(l);
                    if (list2.isEmpty()) {
                        this.mMovesList.remove(k);
                        break;
                    }
                    break;
                }
                else {
                    --l;
                }
            }
        }
        for (int n = this.mAdditionsList.size() - 1; n >= 0; --n) {
            final ArrayList<RecyclerView$ViewHolder> list3 = this.mAdditionsList.get(n);
            if (list3.remove(recyclerView$ViewHolder)) {
                ViewCompat.setAlpha(itemView, 1.0f);
                this.dispatchAddFinished(recyclerView$ViewHolder);
                if (list3.isEmpty()) {
                    this.mAdditionsList.remove(n);
                }
            }
        }
        if (this.mRemoveAnimations.remove(recyclerView$ViewHolder)) {}
        if (this.mAddAnimations.remove(recyclerView$ViewHolder)) {}
        if (this.mChangeAnimations.remove(recyclerView$ViewHolder)) {}
        if (this.mMoveAnimations.remove(recyclerView$ViewHolder)) {}
        this.dispatchFinishedWhenDone();
    }
    
    @Override
    public void endAnimations() {
        for (int i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            final DefaultItemAnimator$MoveInfo defaultItemAnimator$MoveInfo = this.mPendingMoves.get(i);
            final View itemView = defaultItemAnimator$MoveInfo.holder.itemView;
            ViewCompat.setTranslationY(itemView, 0.0f);
            ViewCompat.setTranslationX(itemView, 0.0f);
            this.dispatchMoveFinished(defaultItemAnimator$MoveInfo.holder);
            this.mPendingMoves.remove(i);
        }
        for (int j = this.mPendingRemovals.size() - 1; j >= 0; --j) {
            this.dispatchRemoveFinished(this.mPendingRemovals.get(j));
            this.mPendingRemovals.remove(j);
        }
        for (int k = this.mPendingAdditions.size() - 1; k >= 0; --k) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mPendingAdditions.get(k);
            ViewCompat.setAlpha(recyclerView$ViewHolder.itemView, 1.0f);
            this.dispatchAddFinished(recyclerView$ViewHolder);
            this.mPendingAdditions.remove(k);
        }
        for (int l = this.mPendingChanges.size() - 1; l >= 0; --l) {
            this.endChangeAnimationIfNecessary(this.mPendingChanges.get(l));
        }
        this.mPendingChanges.clear();
        if (!this.isRunning()) {
            return;
        }
        for (int n = this.mMovesList.size() - 1; n >= 0; --n) {
            final ArrayList<DefaultItemAnimator$MoveInfo> list = this.mMovesList.get(n);
            for (int n2 = list.size() - 1; n2 >= 0; --n2) {
                final DefaultItemAnimator$MoveInfo defaultItemAnimator$MoveInfo2 = list.get(n2);
                final View itemView2 = defaultItemAnimator$MoveInfo2.holder.itemView;
                ViewCompat.setTranslationY(itemView2, 0.0f);
                ViewCompat.setTranslationX(itemView2, 0.0f);
                this.dispatchMoveFinished(defaultItemAnimator$MoveInfo2.holder);
                list.remove(n2);
                if (list.isEmpty()) {
                    this.mMovesList.remove(list);
                }
            }
        }
        for (int n3 = this.mAdditionsList.size() - 1; n3 >= 0; --n3) {
            final ArrayList<RecyclerView$ViewHolder> list2 = this.mAdditionsList.get(n3);
            for (int n4 = list2.size() - 1; n4 >= 0; --n4) {
                final RecyclerView$ViewHolder recyclerView$ViewHolder2 = list2.get(n4);
                ViewCompat.setAlpha(recyclerView$ViewHolder2.itemView, 1.0f);
                this.dispatchAddFinished(recyclerView$ViewHolder2);
                list2.remove(n4);
                if (list2.isEmpty()) {
                    this.mAdditionsList.remove(list2);
                }
            }
        }
        for (int n5 = this.mChangesList.size() - 1; n5 >= 0; --n5) {
            final ArrayList<DefaultItemAnimator$ChangeInfo> list3 = this.mChangesList.get(n5);
            for (int n6 = list3.size() - 1; n6 >= 0; --n6) {
                this.endChangeAnimationIfNecessary(list3.get(n6));
                if (list3.isEmpty()) {
                    this.mChangesList.remove(list3);
                }
            }
        }
        this.cancelAll(this.mRemoveAnimations);
        this.cancelAll(this.mMoveAnimations);
        this.cancelAll(this.mAddAnimations);
        this.cancelAll(this.mChangeAnimations);
        this.dispatchAnimationsFinished();
    }
    
    @Override
    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }
    
    @Override
    public void runPendingAnimations() {
        boolean b;
        if (!this.mPendingRemovals.isEmpty()) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (!this.mPendingMoves.isEmpty()) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (!this.mPendingChanges.isEmpty()) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4;
        if (!this.mPendingAdditions.isEmpty()) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (b || b2 || b4 || b3) {
            final Iterator<RecyclerView$ViewHolder> iterator = this.mPendingRemovals.iterator();
            while (iterator.hasNext()) {
                this.animateRemoveImpl(iterator.next());
            }
            this.mPendingRemovals.clear();
            if (b2) {
                final ArrayList<DefaultItemAnimator$MoveInfo> list = new ArrayList<DefaultItemAnimator$MoveInfo>();
                list.addAll(this.mPendingMoves);
                this.mMovesList.add(list);
                this.mPendingMoves.clear();
                final DefaultItemAnimator$1 defaultItemAnimator$1 = new DefaultItemAnimator$1(this, list);
                if (b) {
                    ViewCompat.postOnAnimationDelayed(list.get(0).holder.itemView, defaultItemAnimator$1, this.getRemoveDuration());
                }
                else {
                    defaultItemAnimator$1.run();
                }
            }
            if (b3) {
                final ArrayList<DefaultItemAnimator$ChangeInfo> list2 = new ArrayList<DefaultItemAnimator$ChangeInfo>();
                list2.addAll(this.mPendingChanges);
                this.mChangesList.add(list2);
                this.mPendingChanges.clear();
                final DefaultItemAnimator$2 defaultItemAnimator$2 = new DefaultItemAnimator$2(this, list2);
                if (b) {
                    ViewCompat.postOnAnimationDelayed(list2.get(0).oldHolder.itemView, defaultItemAnimator$2, this.getRemoveDuration());
                }
                else {
                    defaultItemAnimator$2.run();
                }
            }
            if (b4) {
                final ArrayList<RecyclerView$ViewHolder> list3 = new ArrayList<RecyclerView$ViewHolder>();
                list3.addAll(this.mPendingAdditions);
                this.mAdditionsList.add(list3);
                this.mPendingAdditions.clear();
                final DefaultItemAnimator$3 defaultItemAnimator$3 = new DefaultItemAnimator$3(this, list3);
                if (b || b2 || b3) {
                    long removeDuration;
                    if (b) {
                        removeDuration = this.getRemoveDuration();
                    }
                    else {
                        removeDuration = 0L;
                    }
                    long moveDuration;
                    if (b2) {
                        moveDuration = this.getMoveDuration();
                    }
                    else {
                        moveDuration = 0L;
                    }
                    long changeDuration;
                    if (b3) {
                        changeDuration = this.getChangeDuration();
                    }
                    else {
                        changeDuration = 0L;
                    }
                    ViewCompat.postOnAnimationDelayed(list3.get(0).itemView, defaultItemAnimator$3, removeDuration + Math.max(moveDuration, changeDuration));
                    return;
                }
                defaultItemAnimator$3.run();
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.GravityCompat;
import android.view.ViewParent;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup$MarginLayoutParams;

public class CoordinatorLayout$LayoutParams extends ViewGroup$MarginLayoutParams
{
    public int anchorGravity;
    public int dodgeInsetEdges;
    public int gravity;
    public int insetEdge;
    public int keyline;
    View mAnchorDirectChild;
    int mAnchorId;
    View mAnchorView;
    CoordinatorLayout$Behavior mBehavior;
    boolean mBehaviorResolved;
    Object mBehaviorTag;
    private boolean mDidAcceptNestedScroll;
    private boolean mDidBlockInteraction;
    private boolean mDidChangeAfterNestedScroll;
    int mInsetOffsetX;
    int mInsetOffsetY;
    final Rect mLastChildRect;
    
    public CoordinatorLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.mBehaviorResolved = false;
        this.gravity = 0;
        this.anchorGravity = 0;
        this.keyline = -1;
        this.mAnchorId = -1;
        this.insetEdge = 0;
        this.dodgeInsetEdges = 0;
        this.mLastChildRect = new Rect();
    }
    
    CoordinatorLayout$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.mBehaviorResolved = false;
        this.gravity = 0;
        this.anchorGravity = 0;
        this.keyline = -1;
        this.mAnchorId = -1;
        this.insetEdge = 0;
        this.dodgeInsetEdges = 0;
        this.mLastChildRect = new Rect();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.CoordinatorLayout_Layout);
        this.gravity = obtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
        this.mAnchorId = obtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
        this.anchorGravity = obtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
        this.keyline = obtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
        this.insetEdge = obtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
        this.dodgeInsetEdges = obtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
        this.mBehaviorResolved = obtainStyledAttributes.hasValue(R$styleable.CoordinatorLayout_Layout_layout_behavior);
        if (this.mBehaviorResolved) {
            this.mBehavior = CoordinatorLayout.parseBehavior(context, set, obtainStyledAttributes.getString(R$styleable.CoordinatorLayout_Layout_layout_behavior));
        }
        obtainStyledAttributes.recycle();
        if (this.mBehavior != null) {
            this.mBehavior.onAttachedToLayoutParams(this);
        }
    }
    
    public CoordinatorLayout$LayoutParams(final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams) {
        super((ViewGroup$MarginLayoutParams)coordinatorLayout$LayoutParams);
        this.mBehaviorResolved = false;
        this.gravity = 0;
        this.anchorGravity = 0;
        this.keyline = -1;
        this.mAnchorId = -1;
        this.insetEdge = 0;
        this.dodgeInsetEdges = 0;
        this.mLastChildRect = new Rect();
    }
    
    public CoordinatorLayout$LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super(viewGroup$LayoutParams);
        this.mBehaviorResolved = false;
        this.gravity = 0;
        this.anchorGravity = 0;
        this.keyline = -1;
        this.mAnchorId = -1;
        this.insetEdge = 0;
        this.dodgeInsetEdges = 0;
        this.mLastChildRect = new Rect();
    }
    
    public CoordinatorLayout$LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        super(viewGroup$MarginLayoutParams);
        this.mBehaviorResolved = false;
        this.gravity = 0;
        this.anchorGravity = 0;
        this.keyline = -1;
        this.mAnchorId = -1;
        this.insetEdge = 0;
        this.dodgeInsetEdges = 0;
        this.mLastChildRect = new Rect();
    }
    
    private void resolveAnchorView(final View view, final CoordinatorLayout coordinatorLayout) {
        this.mAnchorView = coordinatorLayout.findViewById(this.mAnchorId);
        if (this.mAnchorView != null) {
            if (this.mAnchorView != coordinatorLayout) {
                View mAnchorView = this.mAnchorView;
                ViewParent viewParent = this.mAnchorView.getParent();
                while (viewParent != coordinatorLayout && viewParent != null) {
                    if (viewParent == view) {
                        if (coordinatorLayout.isInEditMode()) {
                            this.mAnchorDirectChild = null;
                            this.mAnchorView = null;
                            return;
                        }
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    else {
                        if (viewParent instanceof View) {
                            mAnchorView = (View)viewParent;
                        }
                        viewParent = viewParent.getParent();
                    }
                }
                this.mAnchorDirectChild = mAnchorView;
                return;
            }
            if (coordinatorLayout.isInEditMode()) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return;
            }
            throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
        }
        else {
            if (coordinatorLayout.isInEditMode()) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return;
            }
            throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.mAnchorId) + " to anchor view " + view);
        }
    }
    
    private boolean shouldDodge(final View view, final int n) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(((CoordinatorLayout$LayoutParams)view.getLayoutParams()).insetEdge, n);
        return absoluteGravity != 0 && (GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, n) & absoluteGravity) == absoluteGravity;
    }
    
    private boolean verifyAnchorView(final View view, final CoordinatorLayout coordinatorLayout) {
        if (this.mAnchorView.getId() != this.mAnchorId) {
            return false;
        }
        View mAnchorView = this.mAnchorView;
        for (ViewParent viewParent = this.mAnchorView.getParent(); viewParent != coordinatorLayout; viewParent = viewParent.getParent()) {
            if (viewParent == null || viewParent == view) {
                this.mAnchorDirectChild = null;
                this.mAnchorView = null;
                return false;
            }
            if (viewParent instanceof View) {
                mAnchorView = (View)viewParent;
            }
        }
        this.mAnchorDirectChild = mAnchorView;
        return true;
    }
    
    void acceptNestedScroll(final boolean mDidAcceptNestedScroll) {
        this.mDidAcceptNestedScroll = mDidAcceptNestedScroll;
    }
    
    boolean checkAnchorChanged() {
        return this.mAnchorView == null && this.mAnchorId != -1;
    }
    
    boolean dependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
        return view2 == this.mAnchorDirectChild || this.shouldDodge(view2, ViewCompat.getLayoutDirection((View)coordinatorLayout)) || (this.mBehavior != null && this.mBehavior.layoutDependsOn(coordinatorLayout, view, view2));
    }
    
    boolean didBlockInteraction() {
        if (this.mBehavior == null) {
            this.mDidBlockInteraction = false;
        }
        return this.mDidBlockInteraction;
    }
    
    View findAnchorView(final CoordinatorLayout coordinatorLayout, final View view) {
        if (this.mAnchorId == -1) {
            this.mAnchorDirectChild = null;
            return this.mAnchorView = null;
        }
        if (this.mAnchorView == null || !this.verifyAnchorView(view, coordinatorLayout)) {
            this.resolveAnchorView(view, coordinatorLayout);
        }
        return this.mAnchorView;
    }
    
    public int getAnchorId() {
        return this.mAnchorId;
    }
    
    public CoordinatorLayout$Behavior getBehavior() {
        return this.mBehavior;
    }
    
    boolean getChangedAfterNestedScroll() {
        return this.mDidChangeAfterNestedScroll;
    }
    
    Rect getLastChildRect() {
        return this.mLastChildRect;
    }
    
    void invalidateAnchor() {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
    }
    
    boolean isBlockingInteractionBelow(final CoordinatorLayout coordinatorLayout, final View view) {
        return this.mDidBlockInteraction || (this.mDidBlockInteraction |= (this.mBehavior != null && this.mBehavior.blocksInteractionBelow(coordinatorLayout, view)));
    }
    
    boolean isNestedScrollAccepted() {
        return this.mDidAcceptNestedScroll;
    }
    
    void resetChangedAfterNestedScroll() {
        this.mDidChangeAfterNestedScroll = false;
    }
    
    void resetNestedScroll() {
        this.mDidAcceptNestedScroll = false;
    }
    
    void resetTouchBehaviorTracking() {
        this.mDidBlockInteraction = false;
    }
    
    public void setAnchorId(final int mAnchorId) {
        this.invalidateAnchor();
        this.mAnchorId = mAnchorId;
    }
    
    public void setBehavior(final CoordinatorLayout$Behavior mBehavior) {
        if (this.mBehavior != mBehavior) {
            if (this.mBehavior != null) {
                this.mBehavior.onDetachedFromLayoutParams();
            }
            this.mBehavior = mBehavior;
            this.mBehaviorTag = null;
            this.mBehaviorResolved = true;
            if (mBehavior != null) {
                mBehavior.onAttachedToLayoutParams(this);
            }
        }
    }
    
    void setChangedAfterNestedScroll(final boolean mDidChangeAfterNestedScroll) {
        this.mDidChangeAfterNestedScroll = mDidChangeAfterNestedScroll;
    }
    
    void setLastChildRect(final Rect rect) {
        this.mLastChildRect.set(rect);
    }
}

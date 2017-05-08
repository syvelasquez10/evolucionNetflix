// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;

class DrawerLayout$ViewDragCallback extends ViewDragHelper$Callback
{
    private final int mAbsGravity;
    private ViewDragHelper mDragger;
    private final Runnable mPeekRunnable;
    final /* synthetic */ DrawerLayout this$0;
    
    DrawerLayout$ViewDragCallback(final DrawerLayout this$0, final int mAbsGravity) {
        this.this$0 = this$0;
        this.mPeekRunnable = new DrawerLayout$ViewDragCallback$1(this);
        this.mAbsGravity = mAbsGravity;
    }
    
    private void closeOtherDrawer() {
        int n = 3;
        if (this.mAbsGravity == 3) {
            n = 5;
        }
        final View drawerWithGravity = this.this$0.findDrawerWithGravity(n);
        if (drawerWithGravity != null) {
            this.this$0.closeDrawer(drawerWithGravity);
        }
    }
    
    @Override
    public int clampViewPositionHorizontal(final View view, final int n, int width) {
        if (this.this$0.checkDrawerViewAbsoluteGravity(view, 3)) {
            return Math.max(-view.getWidth(), Math.min(n, 0));
        }
        width = this.this$0.getWidth();
        return Math.max(width - view.getWidth(), Math.min(n, width));
    }
    
    @Override
    public int clampViewPositionVertical(final View view, final int n, final int n2) {
        return view.getTop();
    }
    
    @Override
    public int getViewHorizontalDragRange(final View view) {
        if (this.this$0.isDrawerView(view)) {
            return view.getWidth();
        }
        return 0;
    }
    
    @Override
    public void onEdgeDragStarted(final int n, final int n2) {
        View view;
        if ((n & 0x1) == 0x1) {
            view = this.this$0.findDrawerWithGravity(3);
        }
        else {
            view = this.this$0.findDrawerWithGravity(5);
        }
        if (view != null && this.this$0.getDrawerLockMode(view) == 0) {
            this.mDragger.captureChildView(view, n2);
        }
    }
    
    @Override
    public boolean onEdgeLock(final int n) {
        return false;
    }
    
    @Override
    public void onEdgeTouched(final int n, final int n2) {
        this.this$0.postDelayed(this.mPeekRunnable, 160L);
    }
    
    @Override
    public void onViewCaptured(final View view, final int n) {
        ((DrawerLayout$LayoutParams)view.getLayoutParams()).isPeeking = false;
        this.closeOtherDrawer();
    }
    
    @Override
    public void onViewDragStateChanged(final int n) {
        this.this$0.updateDrawerState(this.mAbsGravity, n, this.mDragger.getCapturedView());
    }
    
    @Override
    public void onViewPositionChanged(final View view, int visibility, int width, final int n, final int n2) {
        width = view.getWidth();
        float n3;
        if (this.this$0.checkDrawerViewAbsoluteGravity(view, 3)) {
            n3 = (width + visibility) / width;
        }
        else {
            n3 = (this.this$0.getWidth() - visibility) / width;
        }
        this.this$0.setDrawerViewOffset(view, n3);
        if (n3 == 0.0f) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        view.setVisibility(visibility);
        this.this$0.invalidate();
    }
    
    @Override
    public void onViewReleased(final View view, final float n, float drawerViewOffset) {
        drawerViewOffset = this.this$0.getDrawerViewOffset(view);
        final int width = view.getWidth();
        int n2 = 0;
        Label_0049: {
            if (this.this$0.checkDrawerViewAbsoluteGravity(view, 3)) {
                if (n > 0.0f || (n == 0.0f && drawerViewOffset > 0.5f)) {
                    n2 = 0;
                }
                else {
                    n2 = -width;
                }
            }
            else {
                final int width2 = this.this$0.getWidth();
                if (n >= 0.0f) {
                    n2 = width2;
                    if (n != 0.0f) {
                        break Label_0049;
                    }
                    n2 = width2;
                    if (drawerViewOffset <= 0.5f) {
                        break Label_0049;
                    }
                }
                n2 = width2 - width;
            }
        }
        this.mDragger.settleCapturedViewAt(n2, view.getTop());
        this.this$0.invalidate();
    }
    
    void peekDrawer() {
        int n = 0;
        final int edgeSize = this.mDragger.getEdgeSize();
        boolean b;
        if (this.mAbsGravity == 3) {
            b = true;
        }
        else {
            b = false;
        }
        View view;
        int n2;
        if (b) {
            view = this.this$0.findDrawerWithGravity(3);
            if (view != null) {
                n = -view.getWidth();
            }
            n2 = n + edgeSize;
        }
        else {
            view = this.this$0.findDrawerWithGravity(5);
            n2 = this.this$0.getWidth() - edgeSize;
        }
        if (view != null && ((b && view.getLeft() < n2) || (!b && view.getLeft() > n2)) && this.this$0.getDrawerLockMode(view) == 0) {
            final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)view.getLayoutParams();
            this.mDragger.smoothSlideViewTo(view, n2, view.getTop());
            drawerLayout$LayoutParams.isPeeking = true;
            this.this$0.invalidate();
            this.closeOtherDrawer();
            this.this$0.cancelChildViewTouch();
        }
    }
    
    public void removeCallbacks() {
        this.this$0.removeCallbacks(this.mPeekRunnable);
    }
    
    public void setDragger(final ViewDragHelper mDragger) {
        this.mDragger = mDragger;
    }
    
    @Override
    public boolean tryCaptureView(final View view, final int n) {
        return this.this$0.isDrawerView(view) && this.this$0.checkDrawerViewAbsoluteGravity(view, this.mAbsGravity) && this.this$0.getDrawerLockMode(view) == 0;
    }
}

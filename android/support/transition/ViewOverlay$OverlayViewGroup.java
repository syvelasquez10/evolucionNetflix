// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.ViewParent;
import android.view.MotionEvent;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.graphics.drawable.Drawable$Callback;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.lang.reflect.Method;
import android.view.ViewGroup;

class ViewOverlay$OverlayViewGroup extends ViewGroup
{
    static Method sInvalidateChildInParentFastMethod;
    ArrayList<Drawable> mDrawables;
    ViewGroup mHostView;
    View mRequestingView;
    ViewOverlay mViewOverlay;
    
    static {
        try {
            ViewOverlay$OverlayViewGroup.sInvalidateChildInParentFastMethod = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
        }
        catch (NoSuchMethodException ex) {}
    }
    
    ViewOverlay$OverlayViewGroup(final Context context, final ViewGroup mHostView, final View mRequestingView, final ViewOverlay mViewOverlay) {
        super(context);
        this.mDrawables = null;
        this.mHostView = mHostView;
        this.mRequestingView = mRequestingView;
        this.setRight(mHostView.getWidth());
        this.setBottom(mHostView.getHeight());
        mHostView.addView((View)this);
        this.mViewOverlay = mViewOverlay;
    }
    
    private void getOffset(final int[] array) {
        final int[] array2 = new int[2];
        final int[] array3 = new int[2];
        final ViewGroup viewGroup = (ViewGroup)this.getParent();
        this.mHostView.getLocationOnScreen(array2);
        this.mRequestingView.getLocationOnScreen(array3);
        array[0] = array3[0] - array2[0];
        array[1] = array3[1] - array2[1];
    }
    
    public void add(final Drawable drawable) {
        if (this.mDrawables == null) {
            this.mDrawables = new ArrayList<Drawable>();
        }
        if (!this.mDrawables.contains(drawable)) {
            this.mDrawables.add(drawable);
            this.invalidate(drawable.getBounds());
            drawable.setCallback((Drawable$Callback)this);
        }
    }
    
    public void add(final View view) {
        if (view.getParent() instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view.getParent();
            if (viewGroup != this.mHostView && viewGroup.getParent() != null) {
                final int[] array = new int[2];
                final int[] array2 = new int[2];
                viewGroup.getLocationOnScreen(array);
                this.mHostView.getLocationOnScreen(array2);
                ViewCompat.offsetLeftAndRight(view, array[0] - array2[0]);
                ViewCompat.offsetTopAndBottom(view, array[1] - array2[1]);
            }
            viewGroup.removeView(view);
            if (view.getParent() != null) {
                viewGroup.removeView(view);
            }
        }
        super.addView(view, this.getChildCount() - 1);
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        int i = 0;
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        final ViewGroup viewGroup = (ViewGroup)this.getParent();
        this.mHostView.getLocationOnScreen(array);
        this.mRequestingView.getLocationOnScreen(array2);
        canvas.translate((float)(array2[0] - array[0]), (float)(array2[1] - array[1]));
        canvas.clipRect(new Rect(0, 0, this.mRequestingView.getWidth(), this.mRequestingView.getHeight()));
        super.dispatchDraw(canvas);
        int size;
        if (this.mDrawables == null) {
            size = 0;
        }
        else {
            size = this.mDrawables.size();
        }
        while (i < size) {
            this.mDrawables.get(i).draw(canvas);
            ++i;
        }
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        return false;
    }
    
    public ViewParent invalidateChildInParent(final int[] array, final Rect rect) {
        if (this.mHostView != null) {
            rect.offset(array[0], array[1]);
            if (this.mHostView instanceof ViewGroup) {
                array[1] = (array[0] = 0);
                final int[] array2 = new int[2];
                this.getOffset(array2);
                rect.offset(array2[0], array2[1]);
                return super.invalidateChildInParent(array, rect);
            }
            this.invalidate(rect);
        }
        return null;
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        this.invalidate(drawable.getBounds());
    }
    
    boolean isEmpty() {
        return this.getChildCount() == 0 && (this.mDrawables == null || this.mDrawables.size() == 0);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
    }
    
    public void remove(final Drawable drawable) {
        if (this.mDrawables != null) {
            this.mDrawables.remove(drawable);
            this.invalidate(drawable.getBounds());
            drawable.setCallback((Drawable$Callback)null);
        }
    }
    
    public void remove(final View view) {
        super.removeView(view);
        if (this.isEmpty()) {
            this.mHostView.removeView((View)this);
        }
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || (this.mDrawables != null && this.mDrawables.contains(drawable));
    }
}

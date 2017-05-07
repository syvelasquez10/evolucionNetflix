// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewTreeObserver;
import android.content.Context;
import android.app.Activity;
import android.view.Display;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnAttachStateChangeListener;
import android.view.View;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;

public class fq
{
    protected fl te;
    protected a uI;
    
    private fq(final fl te, final int n) {
        this.te = te;
        this.aF(n);
    }
    
    public static fq a(final fl fl, final int n) {
        if (fg.cE()) {
            return new b(fl, n);
        }
        return new fq(fl, n);
    }
    
    protected void aF(final int n) {
        this.uI = new a(n, (IBinder)new Binder());
    }
    
    public void dl() {
        this.te.a(this.uI.uJ, this.uI.do());
    }
    
    public Bundle dm() {
        return this.uI.do();
    }
    
    public IBinder dn() {
        return this.uI.uJ;
    }
    
    public void e(final View view) {
    }
    
    public void setGravity(final int gravity) {
        this.uI.gravity = gravity;
    }
    
    public static final class a
    {
        public int bottom;
        public int gravity;
        public int left;
        public int right;
        public int top;
        public IBinder uJ;
        public int uK;
        
        private a(final int gravity, final IBinder uj) {
            this.uK = -1;
            this.left = 0;
            this.top = 0;
            this.right = 0;
            this.bottom = 0;
            this.gravity = gravity;
            this.uJ = uj;
        }
        
        public Bundle do() {
            final Bundle bundle = new Bundle();
            bundle.putInt("popupLocationInfo.gravity", this.gravity);
            bundle.putInt("popupLocationInfo.displayId", this.uK);
            bundle.putInt("popupLocationInfo.left", this.left);
            bundle.putInt("popupLocationInfo.top", this.top);
            bundle.putInt("popupLocationInfo.right", this.right);
            bundle.putInt("popupLocationInfo.bottom", this.bottom);
            return bundle;
        }
    }
    
    private static final class b extends fq implements View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener
    {
        private boolean tT;
        private WeakReference<View> uL;
        
        protected b(final fl fl, final int n) {
            super(fl, n, null);
            this.tT = false;
        }
        
        private void f(final View view) {
            int displayId = -1;
            if (fg.cI()) {
                final Display display = view.getDisplay();
                displayId = displayId;
                if (display != null) {
                    displayId = display.getDisplayId();
                }
            }
            final IBinder windowToken = view.getWindowToken();
            final int[] array = new int[2];
            view.getLocationInWindow(array);
            final int width = view.getWidth();
            final int height = view.getHeight();
            this.uI.uK = displayId;
            this.uI.uJ = windowToken;
            this.uI.left = array[0];
            this.uI.top = array[1];
            this.uI.right = array[0] + width;
            this.uI.bottom = array[1] + height;
            if (this.tT) {
                this.dl();
                this.tT = false;
            }
        }
        
        @Override
        protected void aF(final int n) {
            this.uI = new a(n, (IBinder)null);
        }
        
        @Override
        public void dl() {
            if (this.uI.uJ != null) {
                super.dl();
                return;
            }
            this.tT = (this.uL != null);
        }
        
        @Override
        public void e(View view) {
            this.te.df();
            if (this.uL != null) {
                final View view2 = this.uL.get();
                final Context context = this.te.getContext();
                View decorView;
                if ((decorView = view2) == null) {
                    decorView = view2;
                    if (context instanceof Activity) {
                        decorView = ((Activity)context).getWindow().getDecorView();
                    }
                }
                if (decorView != null) {
                    decorView.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
                    final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                    if (fg.cH()) {
                        viewTreeObserver.removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                    else {
                        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                }
            }
            this.uL = null;
            final Context context2 = this.te.getContext();
            View view3;
            if ((view3 = view) == null) {
                view3 = view;
                if (context2 instanceof Activity) {
                    if ((view = ((Activity)context2).findViewById(16908290)) == null) {
                        view = ((Activity)context2).getWindow().getDecorView();
                    }
                    fn.c("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
                    view3 = view;
                }
            }
            if (view3 != null) {
                this.f(view3);
                this.uL = new WeakReference<View>(view3);
                view3.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
                view3.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                return;
            }
            fn.d("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
        }
        
        public void onGlobalLayout() {
            if (this.uL != null) {
                final View view = this.uL.get();
                if (view != null) {
                    this.f(view);
                }
            }
        }
        
        public void onViewAttachedToWindow(final View view) {
            this.f(view);
        }
        
        public void onViewDetachedFromWindow(final View view) {
            this.te.df();
            view.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
        }
    }
}

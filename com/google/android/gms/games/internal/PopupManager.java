// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

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
import com.google.android.gms.internal.kc;

public class PopupManager
{
    protected GamesClientImpl XO;
    protected PopupLocationInfo XP;
    
    private PopupManager(final GamesClientImpl xo, final int n) {
        this.XO = xo;
        this.dG(n);
    }
    
    public static PopupManager a(final GamesClientImpl gamesClientImpl, final int n) {
        if (kc.hC()) {
            return new PopupManagerHCMR1(gamesClientImpl, n);
        }
        return new PopupManager(gamesClientImpl, n);
    }
    
    protected void dG(final int n) {
        this.XP = new PopupLocationInfo(n, (IBinder)new Binder());
    }
    
    public void kJ() {
        this.XO.a(this.XP.XQ, this.XP.kM());
    }
    
    public Bundle kK() {
        return this.XP.kM();
    }
    
    public IBinder kL() {
        return this.XP.XQ;
    }
    
    public void l(final View view) {
    }
    
    public void setGravity(final int gravity) {
        this.XP.gravity = gravity;
    }
    
    public static final class PopupLocationInfo
    {
        public IBinder XQ;
        public int XR;
        public int bottom;
        public int gravity;
        public int left;
        public int right;
        public int top;
        
        private PopupLocationInfo(final int gravity, final IBinder xq) {
            this.XR = -1;
            this.left = 0;
            this.top = 0;
            this.right = 0;
            this.bottom = 0;
            this.gravity = gravity;
            this.XQ = xq;
        }
        
        public Bundle kM() {
            final Bundle bundle = new Bundle();
            bundle.putInt("popupLocationInfo.gravity", this.gravity);
            bundle.putInt("popupLocationInfo.displayId", this.XR);
            bundle.putInt("popupLocationInfo.left", this.left);
            bundle.putInt("popupLocationInfo.top", this.top);
            bundle.putInt("popupLocationInfo.right", this.right);
            bundle.putInt("popupLocationInfo.bottom", this.bottom);
            return bundle;
        }
    }
    
    private static final class PopupManagerHCMR1 extends PopupManager implements View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener
    {
        private boolean Wn;
        private WeakReference<View> XS;
        
        protected PopupManagerHCMR1(final GamesClientImpl gamesClientImpl, final int n) {
            super(gamesClientImpl, n, null);
            this.Wn = false;
        }
        
        private void m(final View view) {
            int displayId = -1;
            if (kc.hG()) {
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
            this.XP.XR = displayId;
            this.XP.XQ = windowToken;
            this.XP.left = array[0];
            this.XP.top = array[1];
            this.XP.right = array[0] + width;
            this.XP.bottom = array[1] + height;
            if (this.Wn) {
                this.kJ();
                this.Wn = false;
            }
        }
        
        @Override
        protected void dG(final int n) {
            this.XP = new PopupLocationInfo(n, (IBinder)null);
        }
        
        @Override
        public void kJ() {
            if (this.XP.XQ != null) {
                super.kJ();
                return;
            }
            this.Wn = (this.XS != null);
        }
        
        @Override
        public void l(View view) {
            this.XO.ku();
            if (this.XS != null) {
                final View view2 = this.XS.get();
                final Context context = this.XO.getContext();
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
                    if (kc.hF()) {
                        viewTreeObserver.removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                    else {
                        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                }
            }
            this.XS = null;
            final Context context2 = this.XO.getContext();
            View view3;
            if ((view3 = view) == null) {
                view3 = view;
                if (context2 instanceof Activity) {
                    if ((view = ((Activity)context2).findViewById(16908290)) == null) {
                        view = ((Activity)context2).getWindow().getDecorView();
                    }
                    GamesLog.p("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
                    view3 = view;
                }
            }
            if (view3 != null) {
                this.m(view3);
                this.XS = new WeakReference<View>(view3);
                view3.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
                view3.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                return;
            }
            GamesLog.q("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
        }
        
        public void onGlobalLayout() {
            if (this.XS != null) {
                final View view = this.XS.get();
                if (view != null) {
                    this.m(view);
                }
            }
        }
        
        public void onViewAttachedToWindow(final View view) {
            this.m(view);
        }
        
        public void onViewDetachedFromWindow(final View view) {
            this.XO.ku();
            view.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
        }
    }
}

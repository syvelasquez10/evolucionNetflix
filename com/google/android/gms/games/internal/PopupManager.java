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
import android.os.Bundle;
import android.view.View;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.internal.gr;

public class PopupManager
{
    protected GamesClientImpl JK;
    protected PopupLocationInfo JL;
    
    private PopupManager(final GamesClientImpl jk, final int n) {
        this.JK = jk;
        this.bc(n);
    }
    
    public static PopupManager a(final GamesClientImpl gamesClientImpl, final int n) {
        if (gr.fv()) {
            return new PopupManagerHCMR1(gamesClientImpl, n);
        }
        return new PopupManager(gamesClientImpl, n);
    }
    
    protected void bc(final int n) {
        this.JL = new PopupLocationInfo(n, (IBinder)new Binder());
    }
    
    public void g(final View view) {
    }
    
    public void gS() {
        this.JK.a(this.JL.JM, this.JL.gV());
    }
    
    public Bundle gT() {
        return this.JL.gV();
    }
    
    public IBinder gU() {
        return this.JL.JM;
    }
    
    public void setGravity(final int gravity) {
        this.JL.gravity = gravity;
    }
    
    public static final class PopupLocationInfo
    {
        public IBinder JM;
        public int JN;
        public int bottom;
        public int gravity;
        public int left;
        public int right;
        public int top;
        
        private PopupLocationInfo(final int gravity, final IBinder jm) {
            this.JN = -1;
            this.left = 0;
            this.top = 0;
            this.right = 0;
            this.bottom = 0;
            this.gravity = gravity;
            this.JM = jm;
        }
        
        public Bundle gV() {
            final Bundle bundle = new Bundle();
            bundle.putInt("popupLocationInfo.gravity", this.gravity);
            bundle.putInt("popupLocationInfo.displayId", this.JN);
            bundle.putInt("popupLocationInfo.left", this.left);
            bundle.putInt("popupLocationInfo.top", this.top);
            bundle.putInt("popupLocationInfo.right", this.right);
            bundle.putInt("popupLocationInfo.bottom", this.bottom);
            return bundle;
        }
    }
    
    private static final class PopupManagerHCMR1 extends PopupManager implements View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener
    {
        private boolean Iz;
        private WeakReference<View> JO;
        
        protected PopupManagerHCMR1(final GamesClientImpl gamesClientImpl, final int n) {
            super(gamesClientImpl, n, null);
            this.Iz = false;
        }
        
        private void h(final View view) {
            int displayId = -1;
            if (gr.fz()) {
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
            this.JL.JN = displayId;
            this.JL.JM = windowToken;
            this.JL.left = array[0];
            this.JL.top = array[1];
            this.JL.right = array[0] + width;
            this.JL.bottom = array[1] + height;
            if (this.Iz) {
                this.gS();
                this.Iz = false;
            }
        }
        
        @Override
        protected void bc(final int n) {
            this.JL = new PopupLocationInfo(n, (IBinder)null);
        }
        
        @Override
        public void g(View view) {
            this.JK.gF();
            if (this.JO != null) {
                final View view2 = this.JO.get();
                final Context context = this.JK.getContext();
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
                    if (gr.fy()) {
                        viewTreeObserver.removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                    else {
                        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                }
            }
            this.JO = null;
            final Context context2 = this.JK.getContext();
            View view3;
            if ((view3 = view) == null) {
                view3 = view;
                if (context2 instanceof Activity) {
                    if ((view = ((Activity)context2).findViewById(16908290)) == null) {
                        view = ((Activity)context2).getWindow().getDecorView();
                    }
                    GamesLog.g("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
                    view3 = view;
                }
            }
            if (view3 != null) {
                this.h(view3);
                this.JO = new WeakReference<View>(view3);
                view3.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
                view3.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                return;
            }
            GamesLog.h("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
        }
        
        @Override
        public void gS() {
            if (this.JL.JM != null) {
                super.gS();
                return;
            }
            this.Iz = (this.JO != null);
        }
        
        public void onGlobalLayout() {
            if (this.JO != null) {
                final View view = this.JO.get();
                if (view != null) {
                    this.h(view);
                }
            }
        }
        
        public void onViewAttachedToWindow(final View view) {
            this.h(view);
        }
        
        public void onViewDetachedFromWindow(final View view) {
            this.JK.gF();
            view.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
        }
    }
}

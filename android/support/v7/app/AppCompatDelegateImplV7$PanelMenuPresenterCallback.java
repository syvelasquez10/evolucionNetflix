// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.internal.view.StandaloneActionMode;
import android.support.v7.internal.widget.ViewStubCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.content.res.Configuration;
import android.support.v4.view.LayoutInflaterCompat;
import android.app.Dialog;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.app.Activity;
import android.view.LayoutInflater$Factory;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$color;
import android.view.ViewGroup$MarginLayoutParams;
import android.util.AndroidRuntimeException;
import android.view.KeyCharacterMap;
import android.support.v7.internal.app.ToolbarActionBar;
import android.view.ViewParent;
import android.view.WindowManager$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.media.AudioManager;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.content.res.Resources$Theme;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.internal.widget.FitWindowsViewGroup$OnFitSystemWindowsListener;
import android.support.v7.internal.widget.FitWindowsViewGroup;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.support.v7.appcompat.R$id;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.appcompat.R$layout;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.view.Window;
import android.content.Context;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.app.AppCompatViewInflater;
import android.support.v7.internal.widget.ActionBarContextView;
import android.widget.PopupWindow;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.view.menu.j;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.Window$Callback;
import android.view.Menu;
import android.support.v7.internal.view.menu.i;
import android.support.v7.internal.view.menu.y;

final class AppCompatDelegateImplV7$PanelMenuPresenterCallback implements y
{
    final /* synthetic */ AppCompatDelegateImplV7 this$0;
    
    private AppCompatDelegateImplV7$PanelMenuPresenterCallback(final AppCompatDelegateImplV7 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCloseMenu(i i, final boolean b) {
        final Object p2 = i.p();
        boolean b2;
        if (p2 != i) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final AppCompatDelegateImplV7 this$0 = this.this$0;
        if (b2) {
            i = (i)p2;
        }
        final AppCompatDelegateImplV7$PanelFeatureState access$700 = this$0.findMenuPanel((Menu)i);
        if (access$700 != null) {
            if (!b2) {
                this.this$0.closePanel(access$700, b);
                return;
            }
            this.this$0.callOnPanelClosed(access$700.featureId, access$700, (Menu)p2);
            this.this$0.closePanel(access$700, true);
        }
    }
    
    @Override
    public boolean onOpenSubMenu(final i i) {
        if (i == null && this.this$0.mHasActionBar) {
            final Window$Callback windowCallback = this.this$0.getWindowCallback();
            if (windowCallback != null && !this.this$0.isDestroyed()) {
                windowCallback.onMenuOpened(108, (Menu)i);
            }
        }
        return true;
    }
}
// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

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
import android.support.v7.internal.view.menu.y;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.internal.widget.FitWindowsViewGroup$OnFitSystemWindowsListener;
import android.support.v7.internal.widget.FitWindowsViewGroup;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.os.Build$VERSION;
import android.support.v7.appcompat.R$id;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.appcompat.R$layout;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.Window$Callback;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.view.Menu;
import android.support.v7.internal.view.menu.i;
import android.view.Window;
import android.content.Context;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.app.AppCompatViewInflater;
import android.support.v7.internal.widget.ActionBarContextView;
import android.widget.PopupWindow;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.view.menu.j;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.view.View;

class AppCompatDelegateImplV7$4 implements Runnable
{
    final /* synthetic */ AppCompatDelegateImplV7 this$0;
    
    AppCompatDelegateImplV7$4(final AppCompatDelegateImplV7 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mActionModePopup.showAtLocation((View)this.this$0.mActionModeView, 55, 0, 0);
        this.this$0.endOnGoingFadeAnimation();
        ViewCompat.setAlpha((View)this.this$0.mActionModeView, 0.0f);
        (this.this$0.mFadeAnim = ViewCompat.animate((View)this.this$0.mActionModeView).alpha(1.0f)).setListener(new AppCompatDelegateImplV7$4$1(this));
    }
}

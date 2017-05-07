// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.graphics.drawable.Drawable;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.AnimationUtils;
import android.support.v4.view.ViewCompat;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.appcompat.R$id;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Build$VERSION;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.view.ActionMode$Callback;
import android.support.v7.view.ActionMode;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.internal.widget.ActionBarContextView;
import android.content.Context;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.internal.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;

class WindowDecorActionBar$3 implements ViewPropertyAnimatorUpdateListener
{
    final /* synthetic */ WindowDecorActionBar this$0;
    
    WindowDecorActionBar$3(final WindowDecorActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationUpdate(final View view) {
        ((View)this.this$0.mContainerView.getParent()).invalidate();
    }
}

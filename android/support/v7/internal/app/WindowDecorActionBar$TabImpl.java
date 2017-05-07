// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.app;

import android.support.v7.internal.widget.AdapterViewCompat$OnItemSelectedListener;
import android.support.v7.app.ActionBar$OnNavigationListener;
import android.widget.SpinnerAdapter;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.app.ActionBar$LayoutParams;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.AnimationUtils;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.appcompat.R$id;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Build$VERSION;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.app.ActionBar$OnMenuVisibilityListener;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.app.Dialog;
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
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v7.app.ActionBar$TabListener;
import android.support.v7.app.ActionBar$Tab;

public class WindowDecorActionBar$TabImpl extends ActionBar$Tab
{
    private ActionBar$TabListener mCallback;
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    private int mPosition;
    private Object mTag;
    private CharSequence mText;
    final /* synthetic */ WindowDecorActionBar this$0;
    
    public WindowDecorActionBar$TabImpl(final WindowDecorActionBar this$0) {
        this.this$0 = this$0;
        this.mPosition = -1;
    }
    
    public ActionBar$TabListener getCallback() {
        return this.mCallback;
    }
    
    @Override
    public CharSequence getContentDescription() {
        return this.mContentDesc;
    }
    
    @Override
    public View getCustomView() {
        return this.mCustomView;
    }
    
    @Override
    public Drawable getIcon() {
        return this.mIcon;
    }
    
    @Override
    public int getPosition() {
        return this.mPosition;
    }
    
    @Override
    public Object getTag() {
        return this.mTag;
    }
    
    @Override
    public CharSequence getText() {
        return this.mText;
    }
    
    @Override
    public void select() {
        this.this$0.selectTab(this);
    }
    
    @Override
    public ActionBar$Tab setContentDescription(final int n) {
        return this.setContentDescription(this.this$0.mContext.getResources().getText(n));
    }
    
    @Override
    public ActionBar$Tab setContentDescription(final CharSequence mContentDesc) {
        this.mContentDesc = mContentDesc;
        if (this.mPosition >= 0) {
            this.this$0.mTabScrollView.updateTab(this.mPosition);
        }
        return this;
    }
    
    @Override
    public ActionBar$Tab setCustomView(final int n) {
        return this.setCustomView(LayoutInflater.from(this.this$0.getThemedContext()).inflate(n, (ViewGroup)null));
    }
    
    @Override
    public ActionBar$Tab setCustomView(final View mCustomView) {
        this.mCustomView = mCustomView;
        if (this.mPosition >= 0) {
            this.this$0.mTabScrollView.updateTab(this.mPosition);
        }
        return this;
    }
    
    @Override
    public ActionBar$Tab setIcon(final int n) {
        return this.setIcon(this.this$0.getTintManager().getDrawable(n));
    }
    
    @Override
    public ActionBar$Tab setIcon(final Drawable mIcon) {
        this.mIcon = mIcon;
        if (this.mPosition >= 0) {
            this.this$0.mTabScrollView.updateTab(this.mPosition);
        }
        return this;
    }
    
    public void setPosition(final int mPosition) {
        this.mPosition = mPosition;
    }
    
    @Override
    public ActionBar$Tab setTabListener(final ActionBar$TabListener mCallback) {
        this.mCallback = mCallback;
        return this;
    }
    
    @Override
    public ActionBar$Tab setTag(final Object mTag) {
        this.mTag = mTag;
        return this;
    }
    
    @Override
    public ActionBar$Tab setText(final int n) {
        return this.setText(this.this$0.mContext.getResources().getText(n));
    }
    
    @Override
    public ActionBar$Tab setText(final CharSequence mText) {
        this.mText = mText;
        if (this.mPosition >= 0) {
            this.this$0.mTabScrollView.updateTab(this.mPosition);
        }
        return this;
    }
}

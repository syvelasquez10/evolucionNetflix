// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.PorterDuff$Mode;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.MenuItem;
import android.app.Activity;
import java.security.InvalidParameterException;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.netflix.mediaclient.Log;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup;

public class NetflixActionBar
{
    private static final int HIDE_ANIMATION_DURATION_MS = 300;
    private static final String TAG = "NetflixActionBar";
    private ViewGroup actionBarGroup;
    protected final NetflixActivity activity;
    private boolean hasUpAction;
    private View homeView;
    protected ActionBar systemActionBar;
    protected Toolbar toolbar;
    
    public NetflixActionBar(final NetflixActivity netflixActivity, final boolean hasUpAction) {
        if (Log.isLoggable()) {
            Log.v("NetflixActionBar", "Creating action bar of type: " + this.getClass().getSimpleName());
        }
        this.hasUpAction = hasUpAction;
        this.activity = netflixActivity;
        this.attachToolBarToViewHierarchy();
        this.setToolBarAsActionBar(netflixActivity);
        this.init(netflixActivity, hasUpAction);
        this.findHomeView();
    }
    
    private void applyUpButtonTint() {
        if (this.toolbar != null) {
            final Drawable navigationIcon = this.toolbar.getNavigationIcon();
            if (Log.isLoggable()) {
                Log.v("NetflixActionBar", "Applying tint to navIcon: " + navigationIcon);
            }
            if (navigationIcon != null) {
                DrawableCompat.setTint(navigationIcon, -1);
            }
        }
    }
    
    private void attachToolBarToViewHierarchy() {
        this.actionBarGroup = (ViewGroup)LayoutInflater.from((Context)this.activity).inflate(this.getLayoutId(), (ViewGroup)null);
        if (this.actionBarGroup == null) {
            Log.e("NetflixActionBar", "actionBarGroup is null");
        }
        else {
            this.toolbar = (Toolbar)this.actionBarGroup.findViewById(2131689585);
            if (this.toolbar == null) {
                Log.e("NetflixActionBar", "toolBar is null");
                return;
            }
            final ViewGroup viewGroup = (ViewGroup)this.activity.findViewById(this.activity.getActionBarParentViewId());
            if (viewGroup != null) {
                viewGroup.addView((View)this.actionBarGroup, new ViewGroup$LayoutParams(-1, -2));
            }
        }
    }
    
    @SuppressLint({ "DefaultLocale" })
    private void findHomeView() {
        if (this.toolbar != null && this.toolbar.getChildCount() > 1) {
            for (int i = 0; i < this.toolbar.getChildCount(); ++i) {
                final View child = this.toolbar.getChildAt(i);
                final String s = (String)child.getContentDescription();
                if (s != null && (s.toLowerCase().contains("nav") || s.toLowerCase().contains("toggle"))) {
                    this.homeView = child;
                    break;
                }
            }
        }
    }
    
    private void init(final NetflixActivity netflixActivity, final boolean displayHomeAsUpEnabled) {
        this.systemActionBar.setDisplayShowTitleEnabled(true);
        this.systemActionBar.setDisplayShowHomeEnabled(true);
        this.systemActionBar.setDisplayUseLogoEnabled(true);
        this.systemActionBar.setHomeButtonEnabled(true);
        this.systemActionBar.setLogo(2130837504);
        this.setupFocusability();
        this.setLogoType(NetflixActionBar$LogoType.FULL_SIZE);
        this.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }
    
    private boolean performUpAction() {
        if (this.activity != null && this.hasUpAction) {
            Log.v("NetflixActionBar", "performing up action");
            this.activity.performUpAction();
            return true;
        }
        return false;
    }
    
    private void setToolBarAsActionBar(final NetflixActivity netflixActivity) {
        if (this.toolbar != null) {
            netflixActivity.setSupportActionBar(this.toolbar);
        }
        this.systemActionBar = netflixActivity.getSupportActionBar();
        if (this.systemActionBar == null) {
            throw new InvalidParameterException("ActionBar is null");
        }
    }
    
    private void setupFocusability() {
        final View viewById = this.activity.findViewById(16908332);
        if (viewById != null) {
            final ViewGroup viewGroup = (ViewGroup)viewById.getParent();
            viewGroup.setFocusable(false);
            final View view = (View)viewGroup.getParent();
            if (view != null) {
                view.setFocusable(false);
            }
        }
    }
    
    public void bringToFront() {
        if (this.actionBarGroup != null) {
            this.actionBarGroup.bringToFront();
        }
    }
    
    protected Activity getActivity() {
        return this.activity;
    }
    
    protected int getFullSizeLogoId() {
        return 2130837504;
    }
    
    public View getHomeView() {
        return this.homeView;
    }
    
    protected int getLayoutId() {
        return 2130903064;
    }
    
    public Toolbar getToolbar() {
        return this.toolbar;
    }
    
    public boolean handleHomeButtonSelected(final MenuItem menuItem) {
        Log.v("NetflixActionBar", "handleHomeButtonSelected, id: " + menuItem.getItemId());
        return menuItem.getItemId() == 16908332 && this.performUpAction();
    }
    
    public void hide(final boolean b) {
        if (b) {
            final TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float)(-this.activity.getActionBarHeight()));
            translateAnimation.setDuration(300L);
            this.toolbar.startAnimation((Animation)translateAnimation);
        }
        this.systemActionBar.hide();
    }
    
    public void hidelogo() {
        this.systemActionBar.setDisplayUseLogoEnabled(false);
    }
    
    public boolean isShowing() {
        return this.systemActionBar != null && this.systemActionBar.isShowing();
    }
    
    public void onManagerReady() {
    }
    
    public void setAlpha(final float alpha) {
        if (this.toolbar != null) {
            this.toolbar.setAlpha(alpha);
        }
    }
    
    public void setAlphaWithAnimation(final float n, final int n2) {
        if (this.toolbar != null) {
            this.toolbar.animate().alpha(n).setDuration((long)n2);
        }
    }
    
    public void setDisplayHomeAsUpEnabled(final boolean b) {
        this.hasUpAction = b;
        this.systemActionBar.setDisplayHomeAsUpEnabled(b);
        this.findHomeView();
        if (b) {
            this.applyUpButtonTint();
        }
    }
    
    public void setLogoType(final NetflixActionBar$LogoType netflixActionBar$LogoType) {
        if (this.systemActionBar == null) {
            Log.e("NetflixActionBar", "system actionBar is null");
            return;
        }
        if (netflixActionBar$LogoType == NetflixActionBar$LogoType.GONE) {
            this.systemActionBar.setDisplayUseLogoEnabled(false);
            this.systemActionBar.setDisplayShowTitleEnabled(true);
            return;
        }
        this.systemActionBar.setDisplayShowTitleEnabled(false);
        int fullSizeLogoId;
        if (netflixActionBar$LogoType == NetflixActionBar$LogoType.FULL_SIZE || netflixActionBar$LogoType == NetflixActionBar$LogoType.MONOCHROME) {
            fullSizeLogoId = this.getFullSizeLogoId();
        }
        else {
            fullSizeLogoId = -1;
        }
        Log.v("NetflixActionBar", "set logo: " + fullSizeLogoId);
        if (netflixActionBar$LogoType == NetflixActionBar$LogoType.MONOCHROME) {
            final Drawable drawable = this.getActivity().getResources().getDrawable(fullSizeLogoId);
            if (drawable != null) {
                drawable.setColorFilter(-1, PorterDuff$Mode.SRC_ATOP);
                this.systemActionBar.setLogo(drawable);
            }
        }
        else {
            this.systemActionBar.setLogo(fullSizeLogoId);
        }
        this.systemActionBar.setDisplayUseLogoEnabled(true);
    }
    
    public void setSandwichIcon(final boolean b) {
        final Toolbar toolbar = this.toolbar;
        int navigationIcon;
        if (b) {
            navigationIcon = 2130837717;
        }
        else {
            navigationIcon = 2130837715;
        }
        toolbar.setNavigationIcon(navigationIcon);
    }
    
    public void setTitle(final String title) {
        Log.v("NetflixActionBar", "set title: " + title);
        if (this.systemActionBar == null) {
            Log.e("NetflixActionBar", "system actionBar is null");
            return;
        }
        this.systemActionBar.setTitle(title);
    }
    
    public void show(final boolean b) {
        if (b) {
            final TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float)(-this.activity.getActionBarHeight()), 0.0f);
            translateAnimation.setDuration(300L);
            this.toolbar.startAnimation((Animation)translateAnimation);
        }
        this.systemActionBar.show();
    }
    
    protected void showDropShadowIfAvailable() {
        final View viewById = this.actionBarGroup.findViewById(2131689600);
        if (viewById != null) {
            viewById.setVisibility(0);
        }
    }
}

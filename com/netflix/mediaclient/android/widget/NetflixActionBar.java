// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.MenuItem;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import android.view.View$OnClickListener;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.graphics.drawable.Drawable;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.BitmapDrawable;
import java.security.InvalidParameterException;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.view.TouchDelegate;
import android.graphics.Rect;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.app.ActionBar;
import android.widget.ImageView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class NetflixActionBar
{
    private static final float CARET_TOUCH_SLOP_SCALE_FACTOR = 2.5f;
    private static final String TAG = "NetflixActionBar";
    protected final NetflixActivity activity;
    protected final View content;
    private final ViewTreeObserver$OnGlobalLayoutListener globalLayoutListener;
    protected boolean hasUpAction;
    private View homeView;
    protected final ImageView logo;
    protected final ActionBar systemActionBar;
    protected final TextView title;
    
    public NetflixActionBar(final NetflixActivity activity, final boolean b) {
        this.globalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            private void applyUpButtonTouchDelegate() {
                final View homeView = this.getHomeView((View)NetflixActionBar.this.content.getParent());
                if (homeView == null) {
                    Log.d("NetflixActionBar", "Could not find home view");
                    return;
                }
                View view = homeView;
                if (!homeView.isClickable()) {
                    view = (View)homeView.getParent();
                }
                view.setFocusable(false);
                Log.v("NetflixActionBar", "caret width: " + view.getWidth());
                final Rect rect = new Rect();
                view.getHitRect(rect);
                rect.right *= (int)2.5f;
                Log.v("NetflixActionBar", "touch delegate rect: " + rect.toString());
                NetflixActionBar.this.content.setTouchDelegate(new TouchDelegate(rect, view));
            }
            
            private View findHomeView(final View view) {
                Log.v("NetflixActionBar", "Examining view: " + view.getClass().getSimpleName());
                if ("HomeView".equals(view.getClass().getSimpleName())) {
                    return view;
                }
                if (view instanceof ViewGroup) {
                    final ViewGroup viewGroup = (ViewGroup)view;
                    for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                        final View homeView = this.findHomeView(viewGroup.getChildAt(i));
                        if (homeView != null) {
                            return homeView;
                        }
                    }
                    return null;
                }
                return null;
            }
            
            private View getHomeView(final View view) {
                if (NetflixActionBar.this.homeView == null) {
                    NetflixActionBar.this.homeView = this.findHomeView(view);
                }
                return NetflixActionBar.this.homeView;
            }
            
            public void onGlobalLayout() {
                ViewUtils.removeGlobalLayoutListener(NetflixActionBar.this.content, (ViewTreeObserver$OnGlobalLayoutListener)this);
                this.applyUpButtonTouchDelegate();
            }
        };
        this.activity = activity;
        this.systemActionBar = activity.getActionBar();
        this.hasUpAction = b;
        if (this.systemActionBar == null) {
            throw new InvalidParameterException("ActionBar is null");
        }
        this.systemActionBar.setCustomView(this.getLayoutId());
        this.systemActionBar.setDisplayShowCustomEnabled(true);
        this.systemActionBar.setDisplayShowHomeEnabled(true);
        this.systemActionBar.setDisplayShowTitleEnabled(true);
        this.systemActionBar.setDisplayUseLogoEnabled(true);
        this.systemActionBar.setHomeButtonEnabled(true);
        this.systemActionBar.setBackgroundDrawable(activity.getResources().getDrawable(2130837596));
        this.systemActionBar.setLogo(2131296304);
        this.systemActionBar.setTitle((CharSequence)"");
        this.content = this.systemActionBar.getCustomView();
        this.logo = (ImageView)this.content.findViewById(2131165283);
        this.title = (TextView)this.content.findViewById(2131165284);
        this.fixBackgroundRepeat(this.content);
        this.setupFocusability();
        this.setLogoType(LogoType.FULL_SIZE);
        this.setDisplayHomeAsUpEnabled(b);
    }
    
    private void fixBackgroundRepeat(final View view) {
        final Drawable background = view.getBackground();
        if (background != null && background instanceof BitmapDrawable) {
            ((BitmapDrawable)background).setTileModeXY(Shader$TileMode.REPEAT, Shader$TileMode.REPEAT);
        }
    }
    
    private boolean performUpAction() {
        if (this.activity != null && this.hasUpAction) {
            Log.v("NetflixActionBar", "performing up action");
            this.activity.performUpAction();
            return true;
        }
        return false;
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
    
    protected void configureBackButtonIfNecessary(final boolean b) {
        if (KidsUtils.shouldShowBackNavigationAffordance(this.activity) && !(this.activity instanceof HomeActivity)) {
            final ActionBar actionBar = this.activity.getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            if (!b || DeviceUtils.getScreenResolutionDpi(this.activity) >= 320) {
                Log.v("NetflixActionBar", "Configuring action bar 'up' affordance for back behavior");
                final View viewById = this.content.findViewById(2131165282);
                final ViewGroup$LayoutParams layoutParams = viewById.getLayoutParams();
                final int actionBarHeight = this.activity.getActionBarHeight();
                layoutParams.width = actionBarHeight;
                if (b) {
                    layoutParams.width *= (int)0.75f;
                    viewById.setPadding(viewById.getPaddingLeft() / 2, viewById.getPaddingTop(), viewById.getPaddingRight() / 2, viewById.getPaddingBottom());
                }
                layoutParams.height = actionBarHeight;
                viewById.setVisibility(0);
                viewById.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        LogUtils.reportUIViewCommand((Context)NetflixActionBar.this.activity, UIViewLogging.UIViewCommandName.actionBarBackButton, NetflixActionBar.this.activity.getUiScreen(), NetflixActionBar.this.activity.getDataContext());
                        NetflixActionBar.this.activity.finish();
                    }
                });
            }
        }
    }
    
    protected Activity getActivity() {
        return this.activity;
    }
    
    protected View getContentView() {
        return this.content;
    }
    
    protected int getFullSizeLogoId() {
        return 2130837504;
    }
    
    public View getHomeView() {
        return this.homeView;
    }
    
    protected int getLayoutId() {
        return 2130903063;
    }
    
    public boolean handleHomeButtonSelected(final MenuItem menuItem) {
        Log.v("NetflixActionBar", "handleHomeButtonSelected, id: " + menuItem.getItemId());
        return menuItem.getItemId() == 16908332 && this.performUpAction();
    }
    
    public void hide() {
        this.content.setVisibility(8);
    }
    
    public void onManagerReady() {
    }
    
    public void setBackgroundResource(final int n) {
        this.systemActionBar.setBackgroundDrawable(this.activity.getResources().getDrawable(n));
    }
    
    public void setDisplayHomeAsUpEnabled(final boolean b) {
        this.hasUpAction = b;
        this.systemActionBar.setDisplayHomeAsUpEnabled(b);
        if (this.hasUpAction) {
            this.content.getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
            return;
        }
        ViewUtils.removeGlobalLayoutListener(this.content, this.globalLayoutListener);
    }
    
    public void setLogoType(final LogoType logoType) {
        if (this.logo == null) {
            return;
        }
        if (logoType == LogoType.GONE) {
            this.logo.setVisibility(8);
            this.title.setVisibility(0);
            return;
        }
        this.title.setVisibility(8);
        int fullSizeLogoId = -1;
        if (logoType == LogoType.FULL_SIZE) {
            fullSizeLogoId = this.getFullSizeLogoId();
        }
        Log.v("NetflixActionBar", "set logo: " + fullSizeLogoId);
        this.logo.setImageResource(fullSizeLogoId);
        this.logo.setVisibility(0);
    }
    
    public void setTitle(final String text) {
        Log.v("NetflixActionBar", "set title: " + text);
        this.title.setText((CharSequence)text);
    }
    
    public void show() {
        this.content.setVisibility(0);
    }
    
    public enum LogoType
    {
        FULL_SIZE, 
        GONE;
    }
    
    protected static class PerformUpActionOnClickListener implements View$OnClickListener
    {
        private final NetflixActivity activity;
        
        public PerformUpActionOnClickListener(final NetflixActivity activity) {
            this.activity = activity;
        }
        
        public void onClick(final View view) {
            this.activity.performUpAction();
        }
    }
}

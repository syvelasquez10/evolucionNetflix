// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.MenuItem;
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
import android.app.Activity;

public class NetflixActionBar
{
    private static final float CARET_TOUCH_SLOP_SCALE_FACTOR = 2.5f;
    private static final String TAG = "NetflixActionBar";
    private final Activity activity;
    private final View content;
    private final ViewTreeObserver$OnGlobalLayoutListener globalLayoutListener;
    private View homeView;
    private final ImageView logo;
    private final ActionBar systemActionBar;
    private final TextView title;
    private final AccessibilityRunnable upRunnable;
    
    public NetflixActionBar(final Activity activity, final ActionBar systemActionBar, final AccessibilityRunnable upRunnable) {
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
        if (systemActionBar == null) {
            throw new InvalidParameterException("ActionBar is null");
        }
        this.activity = activity;
        this.systemActionBar = systemActionBar;
        this.upRunnable = upRunnable;
        systemActionBar.setCustomView(this.getLayoutId());
        systemActionBar.setDisplayHomeAsUpEnabled(upRunnable != null);
        systemActionBar.setDisplayShowCustomEnabled(true);
        systemActionBar.setDisplayShowHomeEnabled(true);
        systemActionBar.setDisplayShowTitleEnabled(true);
        systemActionBar.setDisplayUseLogoEnabled(true);
        systemActionBar.setHomeButtonEnabled(true);
        systemActionBar.setBackgroundDrawable(activity.getResources().getDrawable(2130837596));
        systemActionBar.setLogo(2131296304);
        systemActionBar.setTitle((CharSequence)"");
        this.content = systemActionBar.getCustomView();
        this.logo = (ImageView)this.content.findViewById(2131165277);
        this.title = (TextView)this.content.findViewById(2131165278);
        this.fixBackgroundRepeat(this.content);
        this.setupFocusability();
        this.setLogoType(LogoType.FULL_SIZE);
        if (upRunnable != null) {
            this.content.getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        }
    }
    
    private void fixBackgroundRepeat(final View view) {
        final Drawable background = view.getBackground();
        if (background != null && background instanceof BitmapDrawable) {
            ((BitmapDrawable)background).setTileModeXY(Shader$TileMode.REPEAT, Shader$TileMode.REPEAT);
        }
    }
    
    private boolean performUpAction() {
        if (this.upRunnable != null) {
            Log.v("NetflixActionBar", "perform up action");
            this.activity.runOnUiThread((Runnable)this.upRunnable);
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
    
    public void setBackgroundResource(final int n) {
        this.systemActionBar.setBackgroundDrawable(this.activity.getResources().getDrawable(n));
    }
    
    public void setLogoType(final LogoType logoType) {
        if (this.logo == null) {
            return;
        }
        if (logoType == LogoType.GONE) {
            this.logo.setVisibility(8);
            return;
        }
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
}

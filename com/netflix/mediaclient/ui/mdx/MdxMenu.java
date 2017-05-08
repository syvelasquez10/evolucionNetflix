// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.MdxUtils;
import android.graphics.drawable.AnimationDrawable;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxMenu
{
    private static final String TAG = "MdxMenu";
    private final NetflixActivity activity;
    private final Handler handler;
    private final MenuItem mdxItem;
    private final boolean useDarkIcon;
    
    private MdxMenu(final NetflixActivity activity, final Menu menu, final boolean useDarkIcon) {
        Log.v("MdxMenu", "creating");
        this.activity = activity;
        this.useDarkIcon = useDarkIcon;
        final IMiniPlayerFrag mdxMiniPlayerFrag = this.activity.getMdxMiniPlayerFrag();
        this.handler = new Handler();
        if (mdxMiniPlayerFrag == null) {
            throw new IllegalArgumentException("Activity that uses MdxMenu must own a reference to mdxFrag!");
        }
        mdxMiniPlayerFrag.attachMenuItem(this);
        (this.mdxItem = menu.add((CharSequence)this.activity.getString(2131231229))).setShowAsAction(1);
        this.mdxItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MdxMenu$1(this));
        this.setEnabled(mdxMiniPlayerFrag.isMdxMenuEnabled());
        this.update();
    }
    
    public static void addSelectPlayTarget(final NetflixActivity netflixActivity, final Menu menu, final boolean b) {
        new MdxMenu(netflixActivity, menu, b);
    }
    
    private void animateMdxIcon(final AnimationDrawable animationDrawable) {
        this.handler.post((Runnable)new MdxMenu$2(this, animationDrawable));
    }
    
    private int getIcon() {
        if (this.activity.isConnectingToTarget()) {
            return 2130837712;
        }
        if (MdxUtils.isTargetReadyToControl(this.activity.getServiceManager())) {
            return 2130837711;
        }
        if (this.useDarkIcon) {
            return 2130837749;
        }
        return 2130837716;
    }
    
    private void updateAlpha() {
        final Drawable icon = this.mdxItem.getIcon();
        if (icon != null) {
            int alpha;
            if (this.mdxItem.isEnabled()) {
                alpha = 255;
            }
            else {
                alpha = 128;
            }
            icon.setAlpha(alpha);
        }
    }
    
    void setEnabled(final boolean enabled) {
        Log.v("MdxMenu", "Setting mdx menu item enabled %b: ", enabled);
        this.mdxItem.setEnabled(enabled);
        this.updateAlpha();
    }
    
    void update() {
        if (!this.activity.shouldAddMdxToMenu()) {
            Log.w("MdxMenu", "Service manager or mdx are null");
            this.mdxItem.setVisible(false);
            return;
        }
        this.mdxItem.setIcon(this.getIcon());
        if (this.mdxItem.getIcon() instanceof AnimationDrawable) {
            this.animateMdxIcon((AnimationDrawable)this.mdxItem.getIcon());
        }
        this.updateAlpha();
    }
}

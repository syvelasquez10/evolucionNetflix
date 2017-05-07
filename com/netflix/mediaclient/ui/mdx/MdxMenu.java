// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.graphics.drawable.Drawable;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxMenu
{
    private static final String TAG = "MdxMenu";
    private final NetflixActivity activity;
    private final MenuItem mdxItem;
    
    private MdxMenu(final NetflixActivity activity, final Menu menu) {
        Log.v("MdxMenu", "creating");
        this.activity = activity;
        final MdxMiniPlayerFrag mdxMiniPlayerFrag = this.activity.getMdxMiniPlayerFrag();
        if (mdxMiniPlayerFrag == null) {
            throw new IllegalArgumentException("Activity that uses MdxMenu must own a reference to mdxFrag!");
        }
        mdxMiniPlayerFrag.attachMenuItem(this);
        (this.mdxItem = menu.add((CharSequence)this.activity.getString(2131493220))).setShowAsAction(1);
        this.mdxItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MdxMenu$1(this));
        this.setEnabled(mdxMiniPlayerFrag.isMdxMenuEnabled());
        this.update();
    }
    
    public static void addSelectPlayTarget(final NetflixActivity netflixActivity, final Menu menu) {
        new MdxMenu(netflixActivity, menu);
    }
    
    private int getIcon() {
        if (MdxUtils.isTargetReadyToControl(this.activity.getServiceManager())) {
            return 2130837767;
        }
        return 2130837662;
    }
    
    private boolean isAnyMdxTargetAvailable() {
        final ServiceManager serviceManager = this.activity.getServiceManager();
        if (serviceManager == null) {
            Log.e("MdxMenu", "Manager is null inside isAnyMdxTargetAvailable()...returning false!");
            return false;
        }
        if (!serviceManager.getMdx().isReady()) {
            Log.d("MdxMenu", "MDX service is NOT ready");
            return false;
        }
        final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            Log.d("MdxMenu", "No MDX remote targets found");
            return false;
        }
        if (Log.isLoggable("MdxMenu", 3)) {
            Log.d("MdxMenu", "MDX remote targets found: " + targetList.length);
        }
        return true;
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
        Log.v("MdxMenu", "Setting mdx menu item enabled: " + enabled);
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
        this.mdxItem.setVisible(this.isAnyMdxTargetAvailable());
        this.updateAlpha();
    }
}

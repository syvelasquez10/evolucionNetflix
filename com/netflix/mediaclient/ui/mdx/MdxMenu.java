// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.app.Dialog;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxMenu
{
    private static final String TAG = "MdxMenu";
    private final NetflixActivity activity;
    private final ServiceManager manager;
    private final MenuItem mdxItem;
    
    private MdxMenu(final MdxMiniPlayerFrag mdxMiniPlayerFrag, final Menu menu, final boolean enabled) {
        Log.v("MdxMenu", "created");
        mdxMiniPlayerFrag.attachMenuItem(this);
        this.activity = (NetflixActivity)mdxMiniPlayerFrag.getActivity();
        this.manager = this.activity.getServiceManager();
        (this.mdxItem = menu.add((CharSequence)this.activity.getString(2131493258))).setShowAsAction(1);
        this.mdxItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                MdxMenu.this.activity.displayDialog((Dialog)MdxUtils.createMdxTargetSelectionDialog(MdxMenu.this.activity, (MdxUtils.MdxTargetSelectionDialogInterface)MdxMenu.this.activity.getMdxMiniPlayerFrag()));
                return true;
            }
        });
        this.setEnabled(enabled);
        this.update();
    }
    
    public static void addSelectPlayTarget(final MdxMiniPlayerFrag mdxMiniPlayerFrag, final Menu menu) {
        new MdxMenu(mdxMiniPlayerFrag, menu, mdxMiniPlayerFrag.isMdxMenuEnabled());
    }
    
    private int getIcon() {
        if (MdxUtils.isTargetReadyToControl(this.manager)) {
            return 2130837767;
        }
        return 2130837667;
    }
    
    private boolean isAnyMdxTargetAvailable() {
        if (!this.manager.getMdx().isReady()) {
            Log.d("MdxMenu", "MDX service is NOT ready");
            return false;
        }
        final Pair<String, String>[] targetList = this.manager.getMdx().getTargetList();
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

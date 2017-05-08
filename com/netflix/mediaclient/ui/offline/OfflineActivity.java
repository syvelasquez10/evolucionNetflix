// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.app.Dialog;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class OfflineActivity extends FragmentHostActivity implements PlayContextProvider
{
    public static final String PLAYABLE_ID = "playable_id";
    public static final String PROFILE_ID = "profile_id";
    private static final String TAG = "nf_offline";
    public static final String TITLE_ID = "title_id";
    private Dialog deleteDialog;
    
    private boolean isInSelectionMode() {
        return this.getPrimaryFrag() instanceof OfflineFragment && ((OfflineFragment)this.getPrimaryFrag()).isEditMode();
    }
    
    public static Intent showAllDownloads(final Activity activity) {
        return showAllDownloads((Context)activity, false);
    }
    
    public static Intent showAllDownloads(final Context context, final boolean b) {
        return showAllDownloadsForPlayable(context, null, b);
    }
    
    public static Intent showAllDownloadsForPlayable(final Context context, final String s, final boolean b) {
        final Intent intent = new Intent(context, (Class)OfflineActivity.class);
        if (StringUtils.isNotEmpty(s)) {
            intent.putExtra("playable_id", s);
        }
        if (b) {
            intent.addFlags(872415232);
        }
        return intent;
    }
    
    public static Intent showAllDownloadsForTitle(final Context context, final String s, final String s2, final boolean b) {
        final Intent intent = new Intent(context, (Class)OfflineActivity.class);
        if (StringUtils.isNotEmpty(s)) {
            intent.putExtra("title_id", s);
        }
        if (StringUtils.isNotEmpty(s2)) {
            intent.putExtra("profile_id", s2);
        }
        if (b) {
            intent.addFlags(872415232);
        }
        return intent;
    }
    
    @Override
    protected boolean canApplyBrowseExperience() {
        return true;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new OfflineActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return OfflineFragment.create();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903226;
    }
    
    @Override
    public PlayContext getPlayContext() {
        return PlayContext.OFFLINE_MY_DOWNLOADS_CONTEXT;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.offlineShows;
    }
    
    @Override
    protected boolean handleBackPressed() {
        if (this.isInSelectionMode()) {
            ((OfflineFragment)this.getPrimaryFrag()).switchToEditMode(false);
            this.invalidateOptionsMenu();
            return true;
        }
        return false;
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        super.onCreateOptionsMenu(menu, menu2);
        if (this.isInSelectionMode()) {
            if (((OfflineFragment)this.getPrimaryFrag()).getSelectedItemsCount() > 0) {
                final MenuItem add = menu.add(2131230949);
                add.setIcon(2130837776);
                add.setShowAsAction(2);
                add.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new OfflineActivity$2(this));
            }
        }
        else if (this.getPrimaryFrag() instanceof OfflineFragment && ((OfflineFragment)this.getPrimaryFrag()).areDownloadsPreset()) {
            final MenuItem add2 = menu.add(2131231165);
            Drawable icon = ContextCompat.getDrawable((Context)this, 2130837740);
            if (BrowseExperience.showKidsExperience()) {
                icon = ViewUtils.tintAndGet(icon, ContextCompat.getColor((Context)this, 2131624056));
            }
            add2.setIcon(icon);
            add2.setShowAsAction(2);
            add2.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new OfflineActivity$3(this));
        }
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        if (this.getPrimaryFrag() instanceof OfflineFragment) {
            ((OfflineFragment)this.getPrimaryFrag()).refreshAdapter();
        }
    }
    
    @Override
    public void performUpAction() {
        final OfflineFragment offlineFragment = (OfflineFragment)this.getPrimaryFrag();
        if (this.isInSelectionMode()) {
            offlineFragment.switchToEditMode(false);
            this.invalidateOptionsMenu();
            return;
        }
        if (offlineFragment.isEpisodesLevel() && this.isTaskRoot()) {
            this.setIntent(new Intent((Context)this, (Class)OfflineActivity.class));
            offlineFragment.refreshAdapter();
            return;
        }
        if (this.isTaskRoot()) {
            this.startActivity(HomeActivity.createShowIntent(this));
            this.finish();
            return;
        }
        super.onBackPressed();
    }
}

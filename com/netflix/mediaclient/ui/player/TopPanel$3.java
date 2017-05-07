// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthDialog;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthLogging$InvokeLocation;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.MenuItem;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import android.view.MenuItem$OnMenuItemClickListener;

class TopPanel$3 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    final /* synthetic */ DataSaveConfigData val$dataSaveConfigData;
    
    TopPanel$3(final TopPanel this$0, final DataSaveConfigData val$dataSaveConfigData) {
        this.this$0 = this$0;
        this.val$dataSaveConfigData = val$dataSaveConfigData;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        PreferenceUtils.putIntPref((Context)this.this$0.playerFragment.getActivity(), "user_bw_quick_action_shown", 2);
        BandwidthDialog.createBwDialog(this.this$0.playerFragment.getNetflixActivity(), this.val$dataSaveConfigData, BandwidthLogging$InvokeLocation.FROM_PLAYER).show(this.this$0.activity.getFragmentManager(), "frag_dialog");
        return true;
    }
}

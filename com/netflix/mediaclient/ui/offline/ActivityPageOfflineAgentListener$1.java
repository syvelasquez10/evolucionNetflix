// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.graphics.Typeface;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.StatusCode;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.android.widgetry.buffet.BuffetBar$Callback;
import android.support.design.widget.CoordinatorLayout;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.android.app.Status;
import android.text.style.ImageSpan;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Html;
import android.view.ViewGroup;
import com.netflix.android.widgetry.buffet.BuffetBar;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.view.View$OnClickListener;

class ActivityPageOfflineAgentListener$1 implements View$OnClickListener
{
    final /* synthetic */ ActivityPageOfflineAgentListener this$0;
    
    ActivityPageOfflineAgentListener$1(final ActivityPageOfflineAgentListener this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(view.getContext(), NetflixActivity.class);
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
            final OfflineAgentInterface offlineAgentOrNull = NetflixActivity.getOfflineAgentOrNull(netflixActivity);
            if (offlineAgentOrNull != null && offlineAgentOrNull.getLatestOfflinePlayableList().numberOfIncompleteItems() == 0) {
                Log.i("ActivityPageOfflineAgentListener", "launchMyDownloads dismissing");
                this.this$0.dismissBuffetBar();
                this.this$0.resetSnackbarSession(netflixActivity);
            }
            netflixActivity.startActivity(OfflineActivity.showAllDownloads(netflixActivity));
        }
    }
}

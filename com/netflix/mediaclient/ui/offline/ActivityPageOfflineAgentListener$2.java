// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
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
import android.view.View;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.support.design.widget.CoordinatorLayout;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.android.app.Status;
import android.text.style.ImageSpan;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Html;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.android.widgetry.buffet.BuffetBar;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.android.widgetry.buffet.BuffetBar$Callback;

class ActivityPageOfflineAgentListener$2 extends BuffetBar$Callback
{
    final /* synthetic */ ActivityPageOfflineAgentListener this$0;
    final /* synthetic */ NetflixActivity val$activity;
    
    ActivityPageOfflineAgentListener$2(final ActivityPageOfflineAgentListener this$0, final NetflixActivity val$activity) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
    }
    
    @Override
    public void onDismissed(final BuffetBar buffetBar, final int n) {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.val$activity)) {
            return;
        }
        if (n == 0 || n == 1) {
            this.this$0.resetSnackbarSession(this.val$activity);
        }
        this.this$0.buffetBar = null;
    }
}

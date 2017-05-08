// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.ViewTreeObserver$OnPreDrawListener;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.SharedPreferences$Editor;
import com.netflix.android.tooltips.Tooltip$Callback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.design.widget.CoordinatorLayout;
import android.app.Activity;
import android.view.View;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView$OnScrollListener;

class TutorialHelper$1 extends RecyclerView$OnScrollListener
{
    final /* synthetic */ TutorialHelper this$0;
    final /* synthetic */ RecyclerView val$recyclerView;
    final /* synthetic */ TutorialHelper$Tutorialable val$tutor;
    
    TutorialHelper$1(final TutorialHelper this$0, final RecyclerView val$recyclerView, final TutorialHelper$Tutorialable val$tutor) {
        this.this$0 = this$0;
        this.val$recyclerView = val$recyclerView;
        this.val$tutor = val$tutor;
    }
    
    @Override
    public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
        if (2 == n) {
            this.val$recyclerView.removeOnScrollListener(this);
            this.this$0.tooltip = null;
            this.this$0.createTooltip(this.val$tutor);
            if (this.this$0.tooltip != null) {
                this.this$0.scrollToDownloadButton(this.val$recyclerView, this.this$0.tooltip.getTarget());
                this.this$0.tooltip.show();
            }
        }
    }
}

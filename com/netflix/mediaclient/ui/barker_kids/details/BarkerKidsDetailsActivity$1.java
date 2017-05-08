// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import com.netflix.mediaclient.ui.mdx.CastMenu;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.debug.DebugMenuItems;
import android.view.Menu;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Fragment;
import android.view.View;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$DPTTRTracker;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class BarkerKidsDetailsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ BarkerKidsDetailsActivity this$0;
    
    BarkerKidsDetailsActivity$1(final BarkerKidsDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.setupInteractiveTracking(new InteractiveTracker$DPTTRTracker(), this.this$0.createTTRListener());
        ((ManagerStatusListener)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
        this.this$0.registerLoadingStatusCallback();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        ((ManagerStatusListener)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
    }
}

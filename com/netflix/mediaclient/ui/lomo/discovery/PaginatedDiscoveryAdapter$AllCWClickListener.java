// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import com.netflix.mediaclient.ui.lomo.discovery.extended.BaseExtendedDiscoveryFrag;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter$Memento;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.content.res.Resources;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import android.app.DialogFragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lomo.discovery.extended.CWExtendedDiscoveryFrag;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View$OnClickListener;

class PaginatedDiscoveryAdapter$AllCWClickListener implements View$OnClickListener
{
    final /* synthetic */ PaginatedDiscoveryAdapter this$0;
    private final PlayContext trackable;
    
    public PaginatedDiscoveryAdapter$AllCWClickListener(final PaginatedDiscoveryAdapter this$0, final Trackable trackable, final int n) {
        this.this$0 = this$0;
        this.trackable = new PlayContextImp(trackable.getRequestId(), trackable.getHeroTrackId(), trackable.getListPos(), n);
    }
    
    public void onClick(final View view) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getActivity())) {
            final CWExtendedDiscoveryFrag cwExtendedDiscoveryFrag = new CWExtendedDiscoveryFrag();
            cwExtendedDiscoveryFrag.setRetainInstance(true);
            ((NetflixActivity)this.this$0.getActivity()).showDialog(cwExtendedDiscoveryFrag);
            cwExtendedDiscoveryFrag.updatePage(((NetflixActivity)this.this$0.getActivity()).getServiceManager(), this.trackable, this.this$0.animator);
        }
    }
}

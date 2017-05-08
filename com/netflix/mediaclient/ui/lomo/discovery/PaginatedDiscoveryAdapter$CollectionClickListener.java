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
import com.netflix.mediaclient.ui.lomo.discovery.extended.TurboExtendedDiscoveryFrag;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View$OnClickListener;

class PaginatedDiscoveryAdapter$CollectionClickListener implements View$OnClickListener
{
    final /* synthetic */ PaginatedDiscoveryAdapter this$0;
    private final String title;
    private final PlayContext trackable;
    private final long turboCollectionId;
    
    public PaginatedDiscoveryAdapter$CollectionClickListener(final PaginatedDiscoveryAdapter this$0, final long turboCollectionId, final String title, final Trackable trackable, final int n, final int n2) {
        this.this$0 = this$0;
        this.turboCollectionId = turboCollectionId;
        this.title = title;
        this.trackable = new PlayContextImp(trackable.getRequestId(), n2, n, -1);
    }
    
    public void onClick(final View view) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getActivity())) {
            final TurboExtendedDiscoveryFrag turboExtendedDiscoveryFrag = new TurboExtendedDiscoveryFrag();
            ((NetflixActivity)this.this$0.getActivity()).showDialog(turboExtendedDiscoveryFrag);
            turboExtendedDiscoveryFrag.updatePage(((NetflixActivity)this.this$0.getActivity()).getServiceManager(), this.turboCollectionId, this.title, this.trackable, this.this$0.animator);
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KidsCharacterDetailsFrag$FetchCharacterDetailsCallback extends LoggingManagerCallback
{
    private final Boolean isRefresh;
    private final long requestId;
    final /* synthetic */ KidsCharacterDetailsFrag this$0;
    
    public KidsCharacterDetailsFrag$FetchCharacterDetailsCallback(final KidsCharacterDetailsFrag this$0, final long requestId, final Boolean isRefresh) {
        this.this$0 = this$0;
        super("KidsCharacterDetailsFrag");
        this.requestId = requestId;
        this.isRefresh = isRefresh;
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        super.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
        if (this.isRefresh && !b) {
            Log.v("KidsCharacterDetailsFrag", "refreshCase data not changed - nothing to do");
            return;
        }
        long n = this.this$0.requestId;
        if (this.isRefresh) {
            n = this.this$0.refreshRequestId;
        }
        if (this.requestId != n) {
            Log.v("KidsCharacterDetailsFrag", "Ignoring stale callback");
            return;
        }
        if (status.isError()) {
            Log.w("KidsCharacterDetailsFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (kidsCharacterDetails == null) {
            Log.v("KidsCharacterDetailsFrag", "No details in response");
            this.this$0.showErrorView();
            return;
        }
        if (this.isRefresh) {
            this.this$0.detailsViewGroup.updateDetails(kidsCharacterDetails);
            return;
        }
        this.this$0.updateCharacterDetails(kidsCharacterDetails);
    }
}

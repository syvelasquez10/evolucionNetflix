// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.app.Status;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.TextView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class KidsMovieDetailsFrag$1 implements ErrorWrapper$Callback
{
    final /* synthetic */ KidsMovieDetailsFrag this$0;
    
    KidsMovieDetailsFrag$1(final KidsMovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        this.this$0.showLoadingView();
        this.this$0.fetchMovieDetails();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.widget.GridView;
import android.view.View;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

class KubrickMovieDetailsFrag$1 implements Runnable
{
    final /* synthetic */ KubrickMovieDetailsFrag this$0;
    
    KubrickMovieDetailsFrag$1(final KubrickMovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.gridView.setSelection(this.this$0.gridPosition);
    }
}

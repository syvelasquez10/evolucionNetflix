// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.app.DialogFragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class ShowDetailsActivity$1 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ ShowDetailsActivity this$0;
    
    ShowDetailsActivity$1(final ShowDetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final NetflixDialogFrag create = EpisodeListFrag.create(this.this$0.getVideoId(), this.this$0.getEpisodeId(), false);
        create.onManagerReady(this.this$0.getServiceManager(), CommonStatus.OK);
        create.setCancelable(true);
        this.this$0.showDialog(create);
        return true;
    }
}

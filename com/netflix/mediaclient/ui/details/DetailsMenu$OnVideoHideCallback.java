// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.search.SearchMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class DetailsMenu$OnVideoHideCallback extends LoggingManagerCallback
{
    final /* synthetic */ DetailsMenu this$0;
    
    public DetailsMenu$OnVideoHideCallback(final DetailsMenu this$0) {
        this.this$0 = this$0;
        super("DetailsMenu");
    }
    
    @Override
    public void onVideoHide(final Status status) {
        super.onVideoHide(status);
        this.this$0.shareItem.setEnabled(true);
        if (status.isError()) {
            Log.w("DetailsMenu", "Invalid status code");
            Toast.makeText((Context)this.this$0.activity, 2131493132, 1).show();
            return;
        }
        Toast.makeText((Context)this.this$0.activity, 2131493129, 1).show();
        this.this$0.updateShareItemAsUnshared();
    }
}

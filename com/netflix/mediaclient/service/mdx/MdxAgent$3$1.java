// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.List;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class MdxAgent$3$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ MdxAgent$3 this$1;
    
    MdxAgent$3$1(final MdxAgent$3 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
        if (Log.isLoggable("nf_mdx_agent", 2)) {
            Log.v("nf_mdx_agent", "onPostPlayVideosFetched, res: " + status);
        }
        if (status.isSucces() && list.size() > 0) {
            final String id = list.get(0).getId();
            if (!TextUtils.isEmpty((CharSequence)id)) {
                this.this$1.this$0.updateMdxNotificationAndLockscreenWithNextSeries(id);
            }
        }
    }
}

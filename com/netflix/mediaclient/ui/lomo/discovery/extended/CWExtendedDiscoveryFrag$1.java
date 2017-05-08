// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import com.netflix.mediaclient.ui.lomo.discovery.PaginatedDiscoveryAdapter$BlurredStoryArtProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class CWExtendedDiscoveryFrag$1 extends LoggingManagerCallback
{
    final /* synthetic */ CWExtendedDiscoveryFrag this$0;
    final /* synthetic */ ServiceManager val$sm;
    
    CWExtendedDiscoveryFrag$1(final CWExtendedDiscoveryFrag this$0, final String s, final ServiceManager val$sm) {
        this.this$0 = this$0;
        this.val$sm = val$sm;
        super(s);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        super.onVideosFetched(list, status);
        if (list == null || list.size() < 1) {
            String string;
            final String s = string = "SPY-10074 - lolomoService didn't return titles for All Continue Watching";
            if (this.val$sm != null) {
                string = s;
                if (this.val$sm.getContext() != null) {
                    string = "SPY-10074 - Coppola2 cell " + PersistentConfig.getCoppola2ABTestCell(this.val$sm.getContext()).ordinal() + " lolomoService didn't return titles for All Continue Watching";
                }
            }
            String string2 = string;
            if (list == null) {
                string2 = string + " with null requestedVideos";
            }
            Log.w("CWExtendedDiscoveryFrag", string2);
            ErrorLoggingManager.logHandledException(string2);
        }
        else {
            this.this$0.collectionData = list;
            if (this.this$0.adapter != null) {
                this.this$0.adapter.notifyDataSetChanged();
            }
        }
        if (this.this$0.leWrapper != null) {
            this.this$0.leWrapper.hide(true);
        }
    }
}

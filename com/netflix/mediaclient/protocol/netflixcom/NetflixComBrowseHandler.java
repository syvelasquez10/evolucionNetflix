// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import java.util.List;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class NetflixComBrowseHandler implements NetflixComHandler
{
    private static final String GENRE_SUFFIX = "genre";
    private static final String TAG = "NetflixComBrowseHandler";
    
    private NflxHandler$Response handle(final String s, final NetflixActivity netflixActivity) {
        netflixActivity.getServiceManager().getBrowse().fetchLoLoMoSummary(s, new NetflixComBrowseHandler$1FetchLoLoMoSummaryCallback());
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
    
    private boolean isGenreLink(final List<String> list) {
        return list.size() > 2 && "genre".equalsIgnoreCase(list.get(1));
    }
    
    @Override
    public boolean canHandle(final List<String> list) {
        return list.size() == 1 || this.isGenreLink(list);
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, final String s) {
        NflxHandler$Response nflxHandler$Response = NflxHandler$Response.NOT_HANDLING;
        if (list.size() == 1) {
            NetflixComUtils.startHomeActivity(netflixActivity);
            nflxHandler$Response = NflxHandler$Response.HANDLING;
        }
        else if (this.isGenreLink(list)) {
            return this.handle(list.get(2), netflixActivity);
        }
        return nflxHandler$Response;
    }
}

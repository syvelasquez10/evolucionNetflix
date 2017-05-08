// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import java.util.List;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class NetflixComVideoDetailsHandler implements NetflixComHandler
{
    public static final String DEEP_LINK = "DeepLink";
    public static final String HANDLER_DETAILS_URL;
    private static final String TAG = "NetflixComVideoDetailsHandler";
    
    static {
        HANDLER_DETAILS_URL = String.format("%s://%s/%s/", "http", "www.netflix.com", "title");
    }
    
    private void fetchFalkorVideo(final String s, final NetflixActivity netflixActivity, final String s2) {
        netflixActivity.getServiceManager().getBrowse().fetchFalkorVideo(s, new NetflixComVideoDetailsHandler$1(this, s, netflixActivity, s2));
    }
    
    private NflxHandler$Response handle(final String s, final NetflixActivity netflixActivity, final String s2) {
        this.fetchFalkorVideo(s, netflixActivity, s2);
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
    
    @Override
    public boolean canHandle(final List<String> list) {
        return list.size() > 1;
    }
    
    protected DetailsActivity$Action getAction() {
        return null;
    }
    
    protected String getActionToken() {
        return null;
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, final String s) {
        Log.v("NetflixComVideoDetailsHandler", "Starting Details activity");
        this.handle(list.get(1), netflixActivity, s);
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import android.app.Activity;
import com.netflix.mediaclient.ui.search.SearchActivity;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.List;

public class NetflixComSearchHandler implements NetflixComHandler
{
    @Override
    public boolean canHandle(final List<String> list) {
        return true;
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, String s) {
        s = null;
        if (list.size() > 1) {
            s = list.get(1);
        }
        SearchActivity.search((Activity)netflixActivity, s);
        return NflxHandler$Response.HANDLING;
    }
}

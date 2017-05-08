// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.falkor.PQL;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.falkor.CachedModelProxy;

public class RefreshDiscoveryTask extends RefreshLomoTask
{
    public RefreshDiscoveryTask(final CachedModelProxy cachedModelProxy, final String s, final String s2, final String s3) {
        super(cachedModelProxy, s, s2, s3);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(8);
        list.add(new DataUtil$StringPair("param", this.urlEncode(String.format("'%s'", this.lomoId))));
        list.add(new DataUtil$StringPair("param", this.urlEncode(this.lomoIndex)));
        list.add(new DataUtil$StringPair("param", this.urlEncode("'cwDiscovery'")));
        final Map range = PQL.range(0, 6);
        final String string = PQL.create("discoveryEvidence", range, PQL.range(0, 2)).append("summary").toString();
        final String string2 = PQL.create("videoEvidence", range).append(PQL.create(PQL.array("summary", "detail", "bookmark", "offlineAvailable", "vertStoryArt"))).toString();
        final String string3 = PQL.create("videoEvidence", range).append(CmpUtils.CW_CURR_EPISODE_PQL).toString();
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(string2)));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(string3)));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(string)));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode("['summary']")));
        return list;
    }
    
    @Override
    protected void notifyOfRefresh() {
        Log.i("CachedModelProxy", "RefreshDiscoveryTask was finished. DISCOVERY_LIST_UPDATED broadcast sent");
        LocalBroadcastManager.getInstance(this.modelProxy.getContext()).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.DISCOVERY_LIST_UPDATED"));
    }
}

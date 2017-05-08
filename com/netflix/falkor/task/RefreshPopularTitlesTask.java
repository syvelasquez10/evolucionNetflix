// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.falkor.CachedModelProxy;

public class RefreshPopularTitlesTask extends RefreshLomoTask
{
    public RefreshPopularTitlesTask(final CachedModelProxy cachedModelProxy, final String s, final String s2, final String s3) {
        super(cachedModelProxy, s, s2, s3);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(8);
        list.add(new DataUtil$StringPair("param", this.urlEncode(String.format("'%s'", this.lomoId))));
        list.add(new DataUtil$StringPair("param", this.urlEncode(this.lomoIndex)));
        list.add(new DataUtil$StringPair("param", this.urlEncode("'popularTitles'")));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(PQL.create(PQL.range(this.modelProxy.getLastPrefetchFromVideo(), this.modelProxy.getLastPrefetchToVideo()), PQL.array("summary", "kubrick")).toString())));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode("['summary']")));
        return list;
    }
    
    @Override
    protected void notifyOfRefresh(final Status status) {
        ServiceManager.sendPopularTitlesRefreshBrodcast(this.modelProxy.getContext());
    }
}

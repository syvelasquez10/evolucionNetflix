// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import com.netflix.falkor.PQL;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.falkor.CachedModelProxy;

public class RefreshCwTask extends RefreshLomoTask
{
    public RefreshCwTask(final CachedModelProxy cachedModelProxy, final String s, final String s2, final String s3) {
        super(cachedModelProxy, s, s2, s3);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(8);
        list.add(new DataUtil$StringPair("param", this.urlEncode(String.format("'%s'", this.lomoId))));
        list.add(new DataUtil$StringPair("param", this.urlEncode(this.lomoIndex)));
        list.add(new DataUtil$StringPair("param", this.urlEncode("'continueWatching'")));
        final Map range = PQL.range(this.modelProxy.getLastPrefetchFromVideo(), this.modelProxy.getLastPrefetchToVideo());
        final String string = PQL.create(range).append(CmpUtils.CW_VIDEO_LEAF_PQL).toString();
        final String string2 = PQL.create(range).append(CmpUtils.CW_CURR_EPISODE_PQL).toString();
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(string)));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(string2)));
        list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode("['summary']")));
        return list;
    }
    
    @Override
    protected void notifyOfRefresh(final Status status) {
        ServiceManager.sendCwRefreshBrodcast(this.modelProxy.getContext());
        this.modelProxy.sendDetailPageReloadBroadcast();
        PreAppAgent.informCwUpdated(this.modelProxy.getContext());
        PrefetchLolomoABTestUtils.handleCWRefreshBroadcast(this.modelProxy.getContext(), status);
    }
}

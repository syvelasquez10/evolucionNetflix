// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchLoMosTask extends CmpTask
{
    private static final int FALLBACK_LOMO_VIDEOS_TO_RANGE = 9;
    private final int fromLomo;
    private final int toLomo;
    
    public FetchLoMosTask(final CachedModelProxy cachedModelProxy, final int fromLomo, final int toLomo, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.fromLomo = fromLomo;
        this.toLomo = toLomo;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        final String currLolomoId = this.modelProxy.getCurrLolomoId();
        PQL pql;
        if (StringUtils.isEmpty(currLolomoId)) {
            pql = PQL.create("lolomo", PQL.range(this.fromLomo, this.toLomo));
        }
        else {
            pql = PQL.create("lolomos", currLolomoId, PQL.range(this.fromLomo, this.toLomo));
        }
        list.add(pql.append("summary"));
        final NetflixService service = this.modelProxy.getServiceProvider().getService();
        if (service == null) {
            Log.w("CachedModelProxy", "FetchLoMosTask.buildPqlList: service is null, using fallback range %d", 9);
            list.add(pql.append(PQL.create(PQL.range(0, 9), "summary")));
            return;
        }
        list.add(pql.append(PQL.create(PQL.range(LomoConfig.computeNumVideosToFetchPerBatch((Context)service, LoMoType.STANDARD) - 1), "summary")));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onLoMosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onLoMosFetched(this.modelProxy.getLists(cachedModelProxy$GetResult.pqls), CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldCollapseMissingPql(final List<PQL> list) {
        return true;
    }
}

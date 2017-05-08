// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import android.util.Pair;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;

public class FetchVideosTask extends CmpTask
{
    private final boolean fetchByLomoType;
    private final int fromVideo;
    private final boolean includeKubrick;
    private final boolean includePreApp;
    private final LoMo lomo;
    private final int toVideo;
    private final boolean useCacheOnly;
    
    public FetchVideosTask(final CachedModelProxy cachedModelProxy, final LoMo lomo, final int fromVideo, final int toVideo, final boolean useCacheOnly, final boolean includeKubrick, final boolean fetchByLomoType, final boolean includePreApp, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.lomo = lomo;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.useCacheOnly = useCacheOnly;
        this.includeKubrick = includeKubrick;
        this.fetchByLomoType = fetchByLomoType;
        this.includePreApp = includePreApp;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        String s2;
        final String s = s2 = this.lomo.getId();
        if (this.fetchByLomoType) {
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "Trying to fetch videos for lomo by type: " + this.lomo);
            }
            final Pair currLomoByType = this.modelProxy.getCurrLomoByType(this.lomo.getType());
            s2 = s;
            if (currLomoByType != null) {
                s2 = s;
                if (currLomoByType.first != null) {
                    final String s3 = s2 = ((LoMo)currLomoByType.first).getId();
                    if (Log.isLoggable()) {
                        Log.v("CachedModelProxy", "Found current " + this.lomo.getType() + " lomo id as: " + s3);
                        s2 = s3;
                    }
                }
            }
        }
        String s4;
        if (LoMoType.FLAT_GENRE == this.lomo.getType()) {
            s4 = "flatGenre";
        }
        else {
            s4 = "lists";
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        list2.add("summary");
        if (this.includeKubrick) {
            list2.add("kubrick");
            list2.add("rating");
        }
        if (this.includePreApp) {
            list2.add("detail");
        }
        list.add(PQL.create(s4, s2, PQL.range(this.fromVideo, this.toVideo), list2));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onVideosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onVideosFetched(this.modelProxy.getItemsAsList(cachedModelProxy$GetResult.pqls), CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldCollapseMissingPql(final List<PQL> list) {
        return true;
    }
    
    @Override
    protected boolean shouldUseCacheOnly() {
        return this.useCacheOnly;
    }
}

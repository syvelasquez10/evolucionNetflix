// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.branches.MementoVideoSwatch;
import java.util.LinkedHashSet;
import java.io.IOException;
import java.io.Flushable;
import com.netflix.mediaclient.service.falkor.Falkor$SimilarRequestType;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.leafs.Video$InQueue;
import com.netflix.model.branches.FalkorVideo;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.util.JsonUtils;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.Map;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.mediaclient.util.AlphanumComparator;
import java.util.ArrayList;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
import com.google.gson.JsonObject;
import com.netflix.model.branches.FalkorExpiringContent;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.branches.FalkorObject;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.Collection;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;
import android.os.Looper;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import android.os.Handler;
import com.google.gson.JsonParser;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.List;

public class CachedModelProxy$FetchTurboCollectionVideosTask extends CachedModelProxy$BaseCmpTask
{
    private final int fromVideo;
    private final PQL pql;
    private final int toVideo;
    private final long turboCollectionId;
    
    public CachedModelProxy$FetchTurboCollectionVideosTask(final long turboCollectionId, final int fromVideo, final int toVideo) {
        super(null);
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.turboCollectionId = turboCollectionId;
        this.pql = PQL.create("turboCollection", turboCollectionId, PQL.range(fromVideo, toVideo), PQL.array("summary", "detail"));
    }
    
    @Override
    public void buildPqlList(final List<PQL> list) {
        list.add(this.pql);
        list.add(CachedModelProxy.CW_VIDEO_LEAF_PQL.prepend(PQL.create("turboCollection", this.turboCollectionId, PQL.range(this.fromVideo, this.toVideo))));
        list.add(CachedModelProxy.CW_CURR_EPISODE_PQL.prepend(PQL.create("turboCollection", this.turboCollectionId, PQL.range(this.fromVideo, this.toVideo))));
    }
    
    @Override
    public void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onVideosFetched(null, status);
    }
    
    @Override
    public void fetchResultsAndCallbackForSuccess(final CachedModelProxy cachedModelProxy, final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onVideosFetched(cachedModelProxy.getItemsAsList(this.pql), CommonStatus.OK);
    }
}

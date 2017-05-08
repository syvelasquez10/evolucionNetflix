// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;

public class FetchPostPlayVideosTask extends CmpTask
{
    private final String POST_PLAY_CREATE_NEW_POST_PLAY_CONTEXT;
    private final PostPlayRequestContext context;
    private final VideoType type;
    private final String videoId;
    
    public FetchPostPlayVideosTask(final CachedModelProxy cachedModelProxy, final String videoId, final VideoType type, final PostPlayRequestContext context, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.POST_PLAY_CREATE_NEW_POST_PLAY_CONTEXT = "ppNewContext";
        this.videoId = videoId;
        this.type = type;
        this.context = context;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        final String value = this.type.getValue();
        final String videoId = this.videoId;
        String s;
        if (this.type == VideoType.EPISODE) {
            s = "detail";
        }
        else {
            s = "summary";
        }
        list.add(PQL.create(value, videoId, s));
        final PQL create = PQL.create(this.type.getValue(), this.videoId, "postPlayExperience");
        list.add(create.append("experienceData"));
        list.add(create.append(PQL.create("playbackVideos", PQL.range(0, 4), PQL.range(0, 4), PQL.array("detail", "summary"))));
        list.add(PQL.create(this.type.getValue(), this.videoId, "interactive", "postplay"));
        this.modelProxy.invalidate(PQL.create(this.type.getValue(), this.videoId, "postPlayExperience"));
        this.modelProxy.invalidate(PQL.create("postPlayExperiences", this.videoId, PQL.array("experienceData", "playbackVideos")));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onPostPlayVideosFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onPostPlayVideosFetched((PostPlayVideosProvider)this.modelProxy.getVideo(PQL.create(this.type.getValue(), this.videoId, "summary")), CommonStatus.OK);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(1);
        String s;
        if (this.context.equals((Object)PostPlayRequestContext.POST_PLAY)) {
            s = "false";
        }
        else {
            s = "true";
        }
        list.add(new DataUtil$StringPair("ppNewContext", s));
        return list;
    }
}

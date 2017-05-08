// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.falkor.PQL;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchKidsCharacterDetailsTask extends CmpTask
{
    private final String characterId;
    
    public FetchKidsCharacterDetailsTask(final CachedModelProxy cachedModelProxy, final String characterId, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.characterId = characterId;
        this.modelProxy.invalidate(PQL.create("characters", characterId, "watchNext"));
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("characters", this.characterId, PQL.array("summary", "detail")));
        list.add(PQL.create("characters", this.characterId, "watchNext", PQL.array("summary", "detail", "bookmark", "offlineAvailable")));
        list.add(PQL.create("characters", this.characterId, "gallery", "summary"));
        list.add(CmpUtils.buildKidsCharacterVideoGalleryPql(this.characterId));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onKidsCharacterDetailsFetched(null, false, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onKidsCharacterDetailsFetched((KidsCharacterDetails)this.modelProxy.getVideo(PQL.create("characters", this.characterId)), true, CommonStatus.OK);
    }
}

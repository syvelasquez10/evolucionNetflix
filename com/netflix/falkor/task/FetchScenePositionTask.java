// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.model.branches.FalkorScene;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public class FetchScenePositionTask extends CmpTask
{
    private final String playableId;
    private final String scene;
    private final VideoType videoType;
    
    public FetchScenePositionTask(final CachedModelProxy cachedModelProxy, final VideoType videoType, final String playableId, final String scene, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.videoType = videoType;
        this.playableId = playableId;
        this.scene = scene;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(CmpUtils.buildScenePql(this.videoType.getValue(), this.playableId, this.scene));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onScenePositionFetched(-1, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final FalkorScene falkorScene = (FalkorScene)this.modelProxy.getVideo(CmpUtils.buildScenePql(this.videoType.getValue(), this.playableId, this.scene));
        int scenePosition;
        if (falkorScene == null) {
            scenePosition = 0;
        }
        else {
            scenePosition = falkorScene.getScenePosition();
        }
        Log.d("CachedModelProxy", String.format("FetchScenePositionTask rtn: %s", falkorScene));
        browseAgentCallback.onScenePositionFetched(scenePosition, CommonStatus.OK);
    }
}

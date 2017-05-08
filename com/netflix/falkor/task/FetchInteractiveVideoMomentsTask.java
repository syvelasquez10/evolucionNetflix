// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.details.InteractiveMoments;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.falkor.PQL;

public class FetchInteractiveVideoMomentsTask extends CmpTask
{
    private static final String URL_PARAM_INTERACTIVE_ORIGINALS_AUDIO_LOCALE = "ioal";
    private static final String URL_PARAM_INTERACTIVE_ORIGINALS_DEVICE_DPI_CATEGORY = "iodpi";
    private static final String URL_PARAM_INTERACTIVE_ORIGINALS_DEVICE_YEAR_CLASS = "ioyc";
    private final String audioLanguage;
    private final int deviceDpiCategory;
    private final int deviceYearClass;
    private final PQL momentsDataPQL;
    private final VideoType type;
    private final String videoId;
    
    public FetchInteractiveVideoMomentsTask(final CachedModelProxy cachedModelProxy, final VideoType type, final String videoId, final String audioLanguage, final int deviceYearClass, final int deviceDpiCategory, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.type = type;
        this.videoId = videoId;
        this.audioLanguage = audioLanguage;
        this.deviceYearClass = deviceYearClass;
        this.deviceDpiCategory = deviceDpiCategory;
        final PQL create = PQL.create(type.getValue(), videoId, "interactiveMoments");
        this.momentsDataPQL = create;
        final InteractiveMoments interactiveMoments = (InteractiveMoments)this.modelProxy.getValue(create);
        InteractivePlaybackMoments interactiveMoments2;
        if (interactiveMoments != null) {
            interactiveMoments2 = interactiveMoments.getInteractiveMoments();
        }
        else {
            interactiveMoments2 = null;
        }
        if (interactiveMoments2 != null) {
            final InteractiveMomentsModel data = interactiveMoments2.getData();
            if (audioLanguage == null || data == null || !audioLanguage.equals(data.getAudioLanguage())) {
                if (Log.isLoggable()) {
                    Log.d("CachedModelProxy", FetchInteractiveVideoMomentsTask.class.getSimpleName() + ": Need to fetch new Interactive Video Moments for audioLanguage=" + audioLanguage);
                }
                this.modelProxy.invalidate(create);
            }
            else if (Log.isLoggable()) {
                Log.d("CachedModelProxy", FetchInteractiveVideoMomentsTask.class.getSimpleName() + ": Don't need to fetch new Interactive Video Moments for audioLanguage=" + audioLanguage);
            }
        }
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(this.momentsDataPQL);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onInteractiveMomentsFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onInteractiveMomentsFetched((InteractiveMoments)this.modelProxy.getValue(PQL.create(this.type.getValue(), this.videoId)), CommonStatus.OK);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(3);
        list.add(new DataUtil$StringPair("iodpi", Integer.toString(this.deviceDpiCategory)));
        list.add(new DataUtil$StringPair("ioyc", Integer.toString(this.deviceYearClass)));
        if (this.audioLanguage != null) {
            list.add(new DataUtil$StringPair("ioal", this.audioLanguage));
        }
        return list;
    }
}

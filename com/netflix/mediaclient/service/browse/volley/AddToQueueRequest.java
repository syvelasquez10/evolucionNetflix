// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class AddToQueueRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    public static final String FIELD_VALUE = "value";
    public static final String TAG = "nf_service_browse_addtoqueuerequest";
    public static final String optionalParam;
    private final BrowseWebClientCache browseCache;
    private boolean canMakeRequest;
    private final int fromVideo;
    private final String iqLoMoId;
    private final String iqLoMoIndex;
    private final String lolomoId;
    private final String mVideoId;
    private final String messageToken;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    private final int trackId;
    
    static {
        optionalParam = "&" + FalcorParseUtils.getParamNameParam() + "=";
    }
    
    public AddToQueueRequest(final Context context, final BrowseWebClientCache browseCache, final String mVideoId, final int fromVideo, final int toVideo, final int trackId, final String messageToken, final BrowseAgentCallback responseCallback) {
        super(context);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.mVideoId = mVideoId;
        this.trackId = trackId;
        this.browseCache = browseCache;
        this.messageToken = messageToken;
        if (!(this.canMakeRequest = browseCache.areIqIdsInCache())) {}
        this.iqLoMoId = browseCache.getIQLoMoId();
        this.lolomoId = browseCache.getLoLoMoId();
        this.iqLoMoIndex = browseCache.getIQLoMoIndex();
        this.pqlQuery = String.format("['lolomos', '%s', 'add']", this.lolomoId);
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    private static void clearMdpAndSdpMyListStatus(final int n, final int n2, final BrowseWebClientCache browseWebClientCache) {
        final Iterator<Video> iterator = ((List)BrowseWebClientCache.getIQVideosFromBrowseCache(n, n2, browseWebClientCache)).iterator();
        while (iterator.hasNext()) {
            browseWebClientCache.updateInQueueCacheRecord(iterator.next().getId(), false);
        }
    }
    
    public static void parseRefreshIqVideosAndUpdateCache(final JsonObject jsonObject, final int n, final int n2, final BrowseWebClientCache browseWebClientCache) {
        clearMdpAndSdpMyListStatus(n, n2, browseWebClientCache);
        final ArrayList<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary> list = new ArrayList<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary>();
        for (int i = n; i <= n2; ++i) {
            final String string = Integer.toString(i);
            if (jsonObject.has(string)) {
                final com.netflix.mediaclient.service.webclient.model.branches.Video.Summary summary = FalcorParseUtils.getPropertyObject(jsonObject.getAsJsonObject(string), "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class);
                browseWebClientCache.updateInQueueCacheRecord(summary.getId(), true);
                list.add(summary);
            }
        }
        final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(jsonObject, "summary", ListOfMoviesSummary.class);
        browseWebClientCache.putIQLoMoId(listOfMoviesSummary.getId());
        updateIQLoMoSummaryObject(browseWebClientCache, listOfMoviesSummary);
        updateIQVideoLists(browseWebClientCache, n, n2, (List<Video>)list);
    }
    
    static void updateIQLoMoSummaryObject(final BrowseWebClientCache browseWebClientCache, final ListOfMoviesSummary listOfMoviesSummary) {
        // monitorenter(AddToQueueRequest.class)
        if (listOfMoviesSummary != null) {
            try {
                Log.d("nf_service_browse_addtoqueuerequest", "updateIQLoMoSummaryObject newList id:" + listOfMoviesSummary.getId() + " length:" + listOfMoviesSummary.getLength());
                ListOfMoviesSummary iqLoMoSummary;
                if ((iqLoMoSummary = browseWebClientCache.getIQLoMoSummary()) == null) {
                    browseWebClientCache.putIQLoMoSummary(listOfMoviesSummary);
                    iqLoMoSummary = listOfMoviesSummary;
                }
                Log.d("nf_service_browse_addtoqueuerequest", "updateIQLoMoSummaryObject oldList id:" + iqLoMoSummary.getId() + " length:" + iqLoMoSummary.getLength());
                iqLoMoSummary.setId(listOfMoviesSummary.getId());
                iqLoMoSummary.setLength(listOfMoviesSummary.getLength());
            }
            finally {
            }
            // monitorexit(AddToQueueRequest.class)
        }
    }
    // monitorexit(AddToQueueRequest.class)
    
    static void updateIQVideoLists(final BrowseWebClientCache browseWebClientCache, final int n, final int n2, final List<Video> list) {
        synchronized (AddToQueueRequest.class) {
            for (final String s : browseWebClientCache.getIqKeysCache()) {
                Log.d("nf_service_browse_addtoqueuerequest", "removing entry for key:" + s);
                if (browseWebClientCache.getSoftCache().remove(s) == null) {
                    browseWebClientCache.getHardCache().remove(s);
                }
            }
        }
        final BrowseWebClientCache browseWebClientCache2;
        browseWebClientCache2.getIqKeysCache().clear();
        browseWebClientCache2.putIQVideosInBrowseCache(list, n, n2);
    }
    // monitorexit(AddToQueueRequest.class)
    
    public boolean canProceed() {
        return this.canMakeRequest;
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @SuppressLint({ "DefaultLocale" })
    @Override
    protected String getOptionalParams() {
        final String format = String.format("['videos','%s']", this.mVideoId);
        final String format2 = String.format("[{'from':%d,'to':%d},'summary']", this.fromVideo, this.toVideo);
        final String format3 = String.format("'%s'", this.iqLoMoId);
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), format3));
        sb.append(AddToQueueRequest.optionalParam).append(this.iqLoMoIndex);
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), format));
        sb.append(AddToQueueRequest.optionalParam).append(this.trackId);
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        if (StringUtils.isNotEmpty(this.messageToken)) {
            sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("signature", this.messageToken));
        }
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 3)) {
            Log.d("nf_service_browse_addtoqueuerequest", " getOptionalParams: " + sb.toString());
        }
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequest finished onFailure statusCode=" + status);
            this.responseCallback.onQueueAdd(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            if (s != null && s.equals(Integer.toString(StatusCode.ALREADY_IN_QUEUE.getValue()))) {
                Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequest finished with alreadyInQueue");
                this.responseCallback.onQueueAdd(CommonStatus.ALREADY_IN_QUEUE);
            }
            else {
                if (s != null && s.equals(Integer.toString(StatusCode.NOT_VALID.getValue()))) {
                    Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequest finished with not valid requst");
                    this.responseCallback.onQueueAdd(CommonStatus.NOT_VALID);
                    return;
                }
                Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequest finished onSuccess");
                this.responseCallback.onQueueAdd(CommonStatus.OK);
            }
        }
    }
    
    @Override
    protected String parseFalcorResponse(String errorMessage) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + errorMessage);
        }
        JsonObject asJsonObject = null;
        Label_0165: {
            try {
                asJsonObject = new JsonParser().parse(errorMessage).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0165;
                }
                errorMessage = FalcorParseUtils.getErrorMessage(asJsonObject);
                if (FalcorParseUtils.isAlreadyInQueue(errorMessage)) {
                    Log.v("nf_service_browse_addtoqueuerequest", "AlreadyInQueue");
                    return Integer.toString(StatusCode.ALREADY_IN_QUEUE.getValue());
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + errorMessage);
                throw new FalcorParseException("Error in creating JsonObject", ex);
            }
            if (FalcorParseUtils.wasRequestNotValid(errorMessage)) {
                Log.v("nf_service_browse_addtoqueuerequest", "Add to Queue Request not valid");
                return Integer.toString(StatusCode.NOT_VALID.getValue());
            }
            throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            parseRefreshIqVideosAndUpdateCache(asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.iqLoMoIndex), this.fromVideo, this.toVideo, this.browseCache);
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + errorMessage);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}

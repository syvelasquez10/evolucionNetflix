// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.StatusCodeError;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public class AddToQueueTask extends CmpTask
{
    private static final String PARAM = "param";
    private static final String SUFFIX = "pathSuffix";
    private final String iqLomoId;
    private final String iqLomoIndex;
    private final boolean isIqLomoValid;
    private final String lolomoId;
    private final String messageToken;
    private final String trackId;
    private final VideoType type;
    private final String videoId;
    
    public AddToQueueTask(final CachedModelProxy cachedModelProxy, final String videoId, final VideoType type, final String lolomoId, final String iqLomoId, final String iqLomoIndex, final int n, final String messageToken, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.videoId = videoId;
        this.type = type;
        this.lolomoId = lolomoId;
        this.iqLomoId = iqLomoId;
        this.iqLomoIndex = iqLomoIndex;
        this.trackId = String.valueOf(n);
        this.messageToken = messageToken;
        this.isIqLomoValid = StringUtils.isNotEmpty(iqLomoId);
    }
    
    private void doDebugValidation() {
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        if (this.isIqLomoValid) {
            list.add(PQL.create("lolomos", this.lolomoId, "add"));
            return;
        }
        list.add(PQL.create(this.type.getValue(), this.videoId, "addToQueue"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        String s;
        if (status.getStatusCode() == StatusCode.ALREADY_IN_QUEUE) {
            Log.v("CachedModelProxy", "Add to queue failed - video already in queue");
            s = this.modelProxy.getContext().getString(2131296833);
        }
        else {
            Log.v("CachedModelProxy", "Add to queue failed - general error");
            s = this.modelProxy.getContext().getString(2131296649);
        }
        UserActionLogUtils.reportAddToQueueActionEnded(this.modelProxy.getContext(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError), (Integer)null);
        browseAgentCallback.onQueueAdd(status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        Log.v("CachedModelProxy", "Add to queue was successful");
        int n;
        if (StringUtils.isNotEmpty(this.lolomoId) && !this.isIqLomoValid) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (this.isIqLomoValid) {
            this.modelProxy.invalidate(PQL.create("lists", this.iqLomoId));
        }
        ServiceManager.sendIqRefreshBrodcast(this.modelProxy.getContext());
        UserActionLogUtils.reportAddToQueueActionEnded(this.modelProxy.getContext(), IClientLogging$CompletionReason.success, (UIError)null, (Integer)null);
        browseAgentCallback.onQueueAdd(CommonStatus.OK);
        if (n != 0) {
            Log.d("CachedModelProxy", "addToQueue new user case");
            this.modelProxy.getServiceProvider().getService().getFalkorAgent().refreshLolomo();
        }
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(8);
        if (this.isIqLomoValid) {
            this.doDebugValidation();
            final String format = String.format("'%s'", this.iqLomoId);
            final String format2 = String.format("['%s','%s']", this.type.getValue(), this.videoId);
            final String format3 = String.format("[{'from':%d,'to':%d}]", this.modelProxy.getLastPrefetchFromVideo(), this.modelProxy.getLastPrefetchToVideo());
            list.add(new DataUtil$StringPair("param", this.urlEncode(format)));
            list.add(new DataUtil$StringPair("param", this.iqLomoIndex));
            list.add(new DataUtil$StringPair("param", this.urlEncode(format2)));
            list.add(new DataUtil$StringPair("param", this.trackId));
            list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(format3)));
            list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode("['summary']")));
        }
        else {
            list.add(new DataUtil$StringPair("param", this.trackId));
        }
        if (StringUtils.isNotEmpty(this.messageToken)) {
            list.add(new DataUtil$StringPair("signature", this.urlEncode(this.messageToken)));
        }
        return list;
    }
    
    @Override
    protected VolleyError handleJsonError(final JsonObject jsonObject) {
        final String errorMessage = FalkorParseUtils.getErrorMessage(jsonObject, "CachedModelProxy");
        if (FalkorParseUtils.isAlreadyInQueue(errorMessage)) {
            Log.v("CachedModelProxy", "Video already in queue");
            return new StatusCodeError(StatusCode.ALREADY_IN_QUEUE);
        }
        if (FalkorParseUtils.wasRequestNotValid(errorMessage)) {
            Log.v("CachedModelProxy", "Add to Queue Request not valid");
            return new StatusCodeError(StatusCode.NOT_VALID);
        }
        return new FalkorException(errorMessage);
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return true;
    }
}

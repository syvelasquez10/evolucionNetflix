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

public class RemoveFromQueueTask extends CmpTask
{
    private final String iqLomoId;
    private final String iqLomoIndex;
    private final boolean isIqLomoValid;
    private final String lolomoId;
    private final String messageToken;
    private final VideoType type;
    private final String videoId;
    
    public RemoveFromQueueTask(final CachedModelProxy cachedModelProxy, final String videoId, final VideoType type, final String lolomoId, final String iqLomoId, final String iqLomoIndex, final String messageToken, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.videoId = videoId;
        this.type = type;
        this.lolomoId = lolomoId;
        this.iqLomoId = iqLomoId;
        this.iqLomoIndex = iqLomoIndex;
        this.messageToken = messageToken;
        this.isIqLomoValid = StringUtils.isNotEmpty(iqLomoId);
    }
    
    private void doDebugValidation() {
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        if (this.isIqLomoValid) {
            list.add(PQL.create("lolomos", this.lolomoId, "remove"));
            return;
        }
        list.add(PQL.create(this.type.getValue(), this.videoId, "removeFromQueue"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        String s;
        if (status.getStatusCode() == StatusCode.NOT_IN_QUEUE) {
            Log.v("CachedModelProxy", "Remove from queue failed - video was not in queue");
            s = this.modelProxy.getContext().getString(2131296725);
        }
        else {
            Log.v("CachedModelProxy", "Remove from queue failed - general error");
            s = this.modelProxy.getContext().getString(2131296652);
        }
        UserActionLogUtils.reportRemoveFromQueueActionEnded(this.modelProxy.getContext(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, s, ActionOnUIError.displayedError));
        browseAgentCallback.onQueueRemove(status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        Log.v("CachedModelProxy", "Remove from queue was successful");
        if (this.isIqLomoValid) {
            this.modelProxy.invalidate(PQL.create("lists", this.iqLomoId));
        }
        ServiceManager.sendIqRefreshBrodcast(this.modelProxy.getContext());
        UserActionLogUtils.reportRemoveFromQueueActionEnded(this.modelProxy.getContext(), IClientLogging$CompletionReason.success, (UIError)null);
        browseAgentCallback.onQueueRemove(CommonStatus.OK);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        final ArrayList<DataUtil$StringPair> list = new ArrayList<DataUtil$StringPair>(8);
        if (this.isIqLomoValid) {
            this.doDebugValidation();
            final String format = String.format("['%s','%s']", this.type.getValue(), this.videoId);
            final String format2 = String.format("[{'from':%d,'to':%d}]", this.modelProxy.getLastPrefetchFromVideo(), this.modelProxy.getLastPrefetchToVideo());
            list.add(new DataUtil$StringPair("param", this.iqLomoIndex));
            list.add(new DataUtil$StringPair("param", this.urlEncode(format)));
            list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode(format2)));
            list.add(new DataUtil$StringPair("pathSuffix", this.urlEncode("['summary']")));
        }
        if (StringUtils.isNotEmpty(this.messageToken)) {
            list.add(new DataUtil$StringPair("signature", this.urlEncode(this.messageToken)));
        }
        return list;
    }
    
    @Override
    protected VolleyError handleJsonError(final JsonObject jsonObject) {
        final String errorMessage = FalkorParseUtils.getErrorMessage(jsonObject, "CachedModelProxy");
        if (FalkorParseUtils.isNotInQueue(errorMessage)) {
            return new StatusCodeError(StatusCode.NOT_IN_QUEUE);
        }
        return new FalkorException(errorMessage);
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return true;
    }
}

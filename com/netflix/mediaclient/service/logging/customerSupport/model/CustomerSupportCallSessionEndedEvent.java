// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport.model;

import java.util.Iterator;
import org.json.JSONArray;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.customerSupport.CustomerSupportCallSession;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.service.logging.customerSupport.CustomerSupportCallSession$CallQualitySegment;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public class CustomerSupportCallSessionEndedEvent extends SessionEndedEvent
{
    public static final String CALL_QUALITY = "callQuality";
    public static final String CONNECTION_TIME = "connectionTime";
    public static final String DIAL_CONFIRMATION_SCREEN_DISPLAYED = "dialConfirmationDialogDisplayed";
    public static final String ERROR = "error";
    public static final String REASON = "reason";
    private static final String SESSION_NAME = "customerSupportCall";
    public static final String SHARED_SESSION_UUID = "sessionID";
    public static final String TERMINATION_REASON = "terminationReason";
    private IClientLogging$CompletionReason mCompletionReason;
    private int mConnectionTimeInSec;
    private boolean mDialConfirmationDialogDisplayed;
    private Error mError;
    private String mSharedSessionId;
    private List<CustomerSupportCallSession$CallQualitySegment> mStates;
    private CustomerServiceLogging$TerminationReason mTerminationReason;
    
    public CustomerSupportCallSessionEndedEvent(final CustomerSupportCallSession customerSupportCallSession, final int mConnectionTimeInSec, final CustomerServiceLogging$TerminationReason mTerminationReason, final IClientLogging$CompletionReason mCompletionReason, final Error mError) {
        super("customerSupportCall", customerSupportCallSession.getId(), System.currentTimeMillis() - customerSupportCallSession.getStarted());
        this.mTerminationReason = mTerminationReason;
        this.mCompletionReason = mCompletionReason;
        this.mError = mError;
        this.category = customerSupportCallSession.getCategory();
        this.mConnectionTimeInSec = mConnectionTimeInSec;
        this.mStates = customerSupportCallSession.getQualityStates();
        this.mSharedSessionId = customerSupportCallSession.getSharedSessionId();
        this.mDialConfirmationDialogDisplayed = customerSupportCallSession.isDialConfirmationDialogDisplayed();
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data = super.getData();
        if (data == null) {
            data = new JSONObject();
        }
        if (this.mTerminationReason != null) {
            data.put("terminationReason", (Object)this.mTerminationReason.name());
        }
        if (this.mCompletionReason != null) {
            data.put("reason", (Object)this.mCompletionReason.name());
        }
        if (this.mError != null) {
            data.put("error", (Object)this.mError.toJSONObject());
        }
        data.put("connectionTime", this.mConnectionTimeInSec);
        data.put("dialConfirmationDialogDisplayed", this.mDialConfirmationDialogDisplayed);
        if (StringUtils.isNotEmpty(this.mSharedSessionId)) {
            data.put("sessionID", (Object)this.mSharedSessionId);
        }
        if (this.mStates != null) {
            final JSONArray jsonArray = new JSONArray();
            data.put("callQuality", (Object)jsonArray);
            final Iterator<CustomerSupportCallSession$CallQualitySegment> iterator = this.mStates.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJson());
            }
        }
        return data;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport;

import com.netflix.mediaclient.service.logging.customerSupport.model.CustomerSupportCallSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;

public final class CustomerSupportCallSession extends BaseCustomerSupportSession
{
    public static final String NAME = "customerSupportCall";
    private static final String TAG = "customerSupportCall";
    private long mCallConnectedTimeInMs;
    private long mCallStartedTimeInMs;
    private CustomerServiceLogging$CallQuality mCurrentCallQuality;
    private long mCurrentCallQualitySegmentStartTimeInMs;
    private boolean mDialConfirmationDialogDisplayed;
    private String mSharedSessionId;
    private List<CustomerSupportCallSession$CallQualitySegment> mStates;
    
    public CustomerSupportCallSession(final String mSharedSessionId, final boolean mDialConfirmationDialogDisplayed) {
        this.mStates = new ArrayList<CustomerSupportCallSession$CallQualitySegment>();
        this.mCallStartedTimeInMs = System.currentTimeMillis();
        this.mSharedSessionId = mSharedSessionId;
        this.mDialConfirmationDialogDisplayed = mDialConfirmationDialogDisplayed;
    }
    
    private void addCurrentQualitySegment() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.mCurrentCallQualitySegmentStartTimeInMs == 0L || this.mCurrentCallQuality == null) {
            Log.w("customerSupportCall", "Call connected did not called yet!");
            this.mCurrentCallQualitySegmentStartTimeInMs = currentTimeMillis;
            return;
        }
        this.mStates.add(new CustomerSupportCallSession$CallQualitySegment((int)(currentTimeMillis - this.mCurrentCallQualitySegmentStartTimeInMs), this.mCurrentCallQuality));
        this.mCurrentCallQualitySegmentStartTimeInMs = currentTimeMillis;
    }
    
    public void callConnected(final CustomerServiceLogging$CallQuality mCurrentCallQuality) {
        if (Log.isLoggable()) {
            Log.d("customerSupportCall", "callConnected:: Sets call quality to " + mCurrentCallQuality);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.mCallConnectedTimeInMs = currentTimeMillis;
        this.mCurrentCallQualitySegmentStartTimeInMs = currentTimeMillis;
        this.mCurrentCallQuality = mCurrentCallQuality;
    }
    
    public CustomerSupportCallSessionEndedEvent createCustomerSupportCallSessionEndedEvent(CustomerServiceLogging$TerminationReason canceledByUserBeforeConnected, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        this.addCurrentQualitySegment();
        int n;
        if ((n = (int)((this.mCallConnectedTimeInMs - this.mCallStartedTimeInMs) / 1000L)) < 0) {
            n = 0;
            final boolean b = false;
            if (canceledByUserBeforeConnected == CustomerServiceLogging$TerminationReason.canceledByUserAfterConnected) {
                canceledByUserBeforeConnected = CustomerServiceLogging$TerminationReason.canceledByUserBeforeConnected;
                n = (b ? 1 : 0);
            }
        }
        return new CustomerSupportCallSessionEndedEvent(this, n, canceledByUserBeforeConnected, clientLogging$CompletionReason, error);
    }
    
    @Override
    public String getName() {
        return "customerSupportCall";
    }
    
    public List<CustomerSupportCallSession$CallQualitySegment> getQualityStates() {
        return this.mStates;
    }
    
    public String getSharedSessionId() {
        return this.mSharedSessionId;
    }
    
    public boolean isDialConfirmationDialogDisplayed() {
        return this.mDialConfirmationDialogDisplayed;
    }
    
    public void setCallQuality(final CustomerServiceLogging$CallQuality mCurrentCallQuality) {
        if (Log.isLoggable()) {
            Log.d("customerSupportCall", "setCallQuality:: Sets call quality to " + mCurrentCallQuality);
        }
        if (this.mCurrentCallQuality == mCurrentCallQuality) {
            return;
        }
        this.addCurrentQualitySegment();
        this.mCurrentCallQuality = mCurrentCallQuality;
    }
}

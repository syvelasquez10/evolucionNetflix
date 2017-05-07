// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport;

import com.netflix.mediaclient.service.logging.customerSupport.model.HelpRequestSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;

public final class HelpRequestSession extends BaseCustomerSupportSession
{
    public static final String NAME = "helpRequest";
    private CustomerServiceLogging$EntryPoint mEntry;
    
    public HelpRequestSession(final CustomerServiceLogging$EntryPoint mEntry) {
        this.mEntry = mEntry;
    }
    
    public HelpRequestSessionEndedEvent createHelpRequestSessionEndedEvent(final CustomerServiceLogging$Action customerServiceLogging$Action, final String s, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        return new HelpRequestSessionEndedEvent(this, this.mEntry, customerServiceLogging$Action, s, clientLogging$CompletionReason, error);
    }
    
    @Override
    public String getName() {
        return "helpRequest";
    }
}

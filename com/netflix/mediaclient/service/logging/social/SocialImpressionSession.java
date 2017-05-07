// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social;

import com.netflix.mediaclient.service.logging.social.model.SocialImpressionSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public class SocialImpressionSession extends BaseSocialSession
{
    public static final String NAME = "impression";
    private String mOriginatingRequestGuid;
    private String mStoryId;
    private int mTrackId;
    private IClientLogging$ModalView mView;
    
    public SocialImpressionSession(final IClientLogging$ModalView mView, final String mOriginatingRequestGuid, final String mStoryId, final int mTrackId) {
        this.mView = mView;
        this.mOriginatingRequestGuid = mOriginatingRequestGuid;
        this.mStoryId = mStoryId;
        this.mTrackId = mTrackId;
    }
    
    public SocialImpressionSessionEndedEvent createEndEvent(final boolean b, final Error error) {
        final SocialImpressionSessionEndedEvent socialImpressionSessionEndedEvent = new SocialImpressionSessionEndedEvent(this.mId, System.currentTimeMillis() - this.mStarted, this.mView, this.mStoryId, this.mOriginatingRequestGuid, this.mTrackId, b, error);
        socialImpressionSessionEndedEvent.setCategory(this.getCategory());
        socialImpressionSessionEndedEvent.setSessionId(this.mId);
        return socialImpressionSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "impression";
    }
}

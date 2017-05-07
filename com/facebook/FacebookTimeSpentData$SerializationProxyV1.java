// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.Serializable;

class FacebookTimeSpentData$SerializationProxyV1 implements Serializable
{
    private static final long serialVersionUID = 6L;
    private final int interruptionCount;
    private final long lastResumeTime;
    private final long lastSuspendTime;
    private final long millisecondsSpentInSession;
    
    FacebookTimeSpentData$SerializationProxyV1(final long lastResumeTime, final long lastSuspendTime, final long millisecondsSpentInSession, final int interruptionCount) {
        this.lastResumeTime = lastResumeTime;
        this.lastSuspendTime = lastSuspendTime;
        this.millisecondsSpentInSession = millisecondsSpentInSession;
        this.interruptionCount = interruptionCount;
    }
    
    private Object readResolve() {
        return new FacebookTimeSpentData(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount, (FacebookTimeSpentData$1)null);
    }
}

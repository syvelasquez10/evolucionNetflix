// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public interface TargetStateManager$TargetStateManagerListener
{
    void removeEvents(final TargetStateManager$TargetContextEvent p0);
    
    void scheduleEvent(final TargetStateManager$TargetContextEvent p0, final int p1);
    
    void stateHasError(final TargetStateManager$TargetState p0);
    
    void stateHasExhaustedRetry(final TargetStateManager$TargetState p0);
    
    void stateHasTimedOut(final TargetStateManager$TargetState p0);
}

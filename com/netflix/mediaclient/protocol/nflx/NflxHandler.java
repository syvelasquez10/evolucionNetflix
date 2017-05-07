// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

public interface NflxHandler
{
    public static final String INTENT_RESULT = "com.netflix.mediaclient.intent.action.HANDLER_RESULT";
    
    Response handle();
    
    public enum Response
    {
        HANDLING, 
        HANDLING_WITH_DELAY, 
        NOT_HANDLING;
    }
}

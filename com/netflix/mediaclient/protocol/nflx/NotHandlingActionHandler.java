// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

public class NotHandlingActionHandler implements NflxHandler
{
    @Override
    public Response handle() {
        return Response.NOT_HANDLING;
    }
}

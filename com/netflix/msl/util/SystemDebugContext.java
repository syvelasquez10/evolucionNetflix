// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import com.netflix.msl.msg.ErrorHeader;
import com.netflix.msl.msg.Header;
import com.netflix.msl.msg.MessageDebugContext;

public class SystemDebugContext implements MessageDebugContext
{
    @Override
    public void receivedHeader(final Header header) {
        if (header instanceof ErrorHeader) {
            final ErrorHeader errorHeader = (ErrorHeader)header;
        }
        System.out.println("Received: " + header.toJSONString());
    }
    
    @Override
    public void sentHeader(final Header header) {
        System.out.println("Sent: " + header.toJSONString());
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.transport;

import com.netflix.mediaclient.javabridge.NrdProxy;
import com.netflix.mediaclient.javabridge.Bridge;

public class TransportFactory
{
    public static Transport createTransport(final String s, final Bridge bridge, final NrdProxy nrdProxy) {
        return new NativeTransport(bridge, nrdProxy);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

public class ClientMslContext$SystemClockProvider implements ClientMslContext$ClockProvider
{
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

public class MslControl$MslChannel
{
    public final MessageInputStream input;
    public final MessageOutputStream output;
    
    protected MslControl$MslChannel(final MessageInputStream input, final MessageOutputStream output) {
        this.input = input;
        this.output = output;
    }
}

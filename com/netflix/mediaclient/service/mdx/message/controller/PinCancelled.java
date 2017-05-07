// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PinCancelled extends MdxMessage
{
    public PinCancelled() {
        super("PIN_VERIFICATION_CANCEL");
        this.createObj();
    }
    
    private void createObj() {
    }
}

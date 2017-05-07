// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PinConfirmed extends MdxMessage
{
    public PinConfirmed() {
        super("PIN_VERIFICATION_RESPONSE");
        this.createObj();
    }
    
    private void createObj() {
    }
}

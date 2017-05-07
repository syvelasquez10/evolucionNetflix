// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class Handshake extends MdxMessage
{
    private static final String PROPERTY_contractVersion = "contractVersion";
    private static final int contractVersion = 1;
    
    public Handshake() {
        super("HANDSHAKE");
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("contractVersion", 1);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}

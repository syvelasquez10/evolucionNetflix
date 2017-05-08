// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import org.json.JSONObject;

public class CustomEvent extends Event
{
    JSONObject customData;
    
    public CustomEvent(final String name, final IClientLogging$ModalView modalView, final JSONObject customData) {
        this.name = name;
        this.modalView = modalView;
        if (customData != null) {
            this.customData = customData;
        }
    }
    
    @Override
    protected JSONObject getCustomData() {
        return this.customData;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;

public class CustomEvent extends Event
{
    public CustomEvent(final String name, final IClientLogging$ModalView modalView) {
        this.name = name;
        this.modalView = modalView;
    }
}

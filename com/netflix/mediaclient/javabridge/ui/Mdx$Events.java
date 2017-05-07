// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public enum Mdx$Events
{
    mdx_discovery_devicefound("devicefound"), 
    mdx_discovery_devicelost("devicelost"), 
    mdx_discovery_remoteDeviceReady("remoteDeviceReady"), 
    mdx_init("init"), 
    mdx_initerror("initerror"), 
    mdx_mdxstate("mdxstate"), 
    mdx_pair_pairingdeleted("pairingdeleted"), 
    mdx_pair_pairingresponse("pairingresponse"), 
    mdx_pair_regpairresponse("regpairresponse"), 
    mdx_session_message("message"), 
    mdx_session_messagedelivered("messagedelivered"), 
    mdx_session_messagingerror("messagingerror"), 
    mdx_session_sessionended("sessionended"), 
    mdx_session_startSessionResponse("startSessionResponse"), 
    mdx_targetrestarting("targetrestarting");
    
    protected String name;
    
    private Mdx$Events(final String name) {
        this.name = name;
    }
    
    public final String getName() {
        return this.name;
    }
}

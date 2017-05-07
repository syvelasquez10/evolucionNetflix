// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationServerParameters$Parameter;
import com.google.ads.mediation.MediationServerParameters;

public final class CustomEventServerParameters extends MediationServerParameters
{
    @MediationServerParameters$Parameter(name = "class_name", required = true)
    public String className;
    @MediationServerParameters$Parameter(name = "label", required = true)
    public String label;
    @MediationServerParameters$Parameter(name = "parameter", required = false)
    public String parameter;
    
    public CustomEventServerParameters() {
        this.parameter = null;
    }
}

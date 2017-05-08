// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.util.StringUtils;

public class Logblob$CommonParams
{
    public String deviceModel;
    public String esn;
    
    public Logblob$CommonParams(final String esn, final String s, final String s2, final String deviceModel) {
        this.esn = esn;
        this.deviceModel = deviceModel;
        this.isValid();
    }
    
    private boolean isValid() {
        if (StringUtils.isEmpty(this.esn)) {
            throw new IllegalStateException("ESN is missing");
        }
        if (StringUtils.isEmpty(this.deviceModel)) {
            throw new IllegalStateException("deviceModel is missing");
        }
        return true;
    }
}

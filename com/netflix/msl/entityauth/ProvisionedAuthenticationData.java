// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONObject;

public class ProvisionedAuthenticationData extends EntityAuthenticationData
{
    private String identity;
    
    public ProvisionedAuthenticationData() {
        super(EntityAuthenticationScheme.PROVISIONED);
        this.identity = "";
    }
    
    public ProvisionedAuthenticationData(final JSONObject jsonObject) {
        super(EntityAuthenticationScheme.PROVISIONED);
        this.identity = "";
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ProvisionedAuthenticationData && super.equals(o));
    }
    
    @Override
    public JSONObject getAuthData() {
        return new JSONObject();
    }
    
    @Override
    public String getIdentity() {
        return this.identity;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    void setIdentity(final String identity) {
        this.identity = identity;
    }
}

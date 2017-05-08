// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONStringer;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.android.org.json.JSONString;

public abstract class EntityAuthenticationData implements JSONString
{
    private static final String KEY_AUTHDATA = "authdata";
    private static final String KEY_SCHEME = "scheme";
    private final EntityAuthenticationScheme scheme;
    
    protected EntityAuthenticationData(final EntityAuthenticationScheme scheme) {
        this.scheme = scheme;
    }
    
    public static EntityAuthenticationData create(final MslContext mslContext, final JSONObject jsonObject) {
        EntityAuthenticationScheme entityAuthenticationScheme;
        try {
            final String string = jsonObject.getString("scheme");
            entityAuthenticationScheme = mslContext.getEntityAuthenticationScheme(string);
            if (entityAuthenticationScheme == null) {
                throw new MslEntityAuthException(MslError.UNIDENTIFIED_ENTITYAUTH_SCHEME, string);
            }
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "entityauthdata " + jsonObject.toString(), ex);
        }
        final JSONObject jsonObject2 = jsonObject.getJSONObject("authdata");
        final EntityAuthenticationFactory entityAuthenticationFactory = mslContext.getEntityAuthenticationFactory(entityAuthenticationScheme);
        if (entityAuthenticationFactory == null) {
            throw new MslEntityAuthException(MslError.ENTITYAUTH_FACTORY_NOT_FOUND, entityAuthenticationScheme.name());
        }
        return entityAuthenticationFactory.createData(mslContext, jsonObject2);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof EntityAuthenticationData && this.scheme.equals(((EntityAuthenticationData)o).scheme));
    }
    
    public abstract JSONObject getAuthData();
    
    public abstract String getIdentity();
    
    public EntityAuthenticationScheme getScheme() {
        return this.scheme;
    }
    
    @Override
    public int hashCode() {
        return this.scheme.hashCode();
    }
    
    @Override
    public final String toJSONString() {
        try {
            return new JSONStringer().object().key("scheme").value(this.scheme.name()).key("authdata").value(this.getAuthData()).endObject().toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
        catch (MslEncodingException ex2) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex2);
        }
    }
}

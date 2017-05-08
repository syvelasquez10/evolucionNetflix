// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.entityauth;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class UnauthenticatedSuffixedAuthenticationData extends EntityAuthenticationData
{
    private static final String CONCAT_CHAR = ".";
    private static final String KEY_ROOT = "root";
    private static final String KEY_SUFFIX = "suffix";
    private final String root;
    private final String suffix;
    
    UnauthenticatedSuffixedAuthenticationData(final JSONObject jsonObject) {
        super(EntityAuthenticationScheme.NONE_SUFFIXED);
        try {
            this.root = jsonObject.getString("root");
            this.suffix = jsonObject.getString("suffix");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "unauthenticated suffixed authdata " + jsonObject.toString(), ex);
        }
    }
    
    public UnauthenticatedSuffixedAuthenticationData(final String root, final String suffix) {
        super(EntityAuthenticationScheme.NONE_SUFFIXED);
        this.root = root;
        this.suffix = suffix;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnauthenticatedSuffixedAuthenticationData)) {
            return false;
        }
        final UnauthenticatedSuffixedAuthenticationData unauthenticatedSuffixedAuthenticationData = (UnauthenticatedSuffixedAuthenticationData)o;
        return super.equals(o) && this.root.equals(unauthenticatedSuffixedAuthenticationData.root) && this.suffix.equals(unauthenticatedSuffixedAuthenticationData.suffix);
    }
    
    @Override
    public JSONObject getAuthData() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("root", this.root);
            jsonObject.put("suffix", this.suffix);
            return jsonObject;
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_ENCODE_ERROR, "unauthenticated suffixed authdata", ex);
        }
    }
    
    @Override
    public String getIdentity() {
        return this.root + "." + this.suffix;
    }
    
    public String getRoot() {
        return this.root;
    }
    
    public String getSuffix() {
        return this.suffix;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.root.hashCode() ^ this.suffix.hashCode();
    }
}

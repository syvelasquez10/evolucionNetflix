// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.util.Pair;

public final class MdxTarget
{
    private static final String TAG = "nf_mdx";
    private String mFriendlyName;
    private boolean mLocal;
    private String mUUID;
    
    private MdxTarget(final String muuid, final String mFriendlyName, final boolean mLocal) {
        this.mUUID = muuid;
        this.mFriendlyName = mFriendlyName;
        this.mLocal = mLocal;
    }
    
    public static MdxTarget createLocalTarget() {
        return new MdxTarget(null, null, true);
    }
    
    public static MdxTarget createRemoteTarget(final Pair<String, String> pair) {
        return new MdxTarget((String)pair.first, (String)pair.second, false);
    }
    
    public String getFriendlyName() {
        return this.mFriendlyName;
    }
    
    public String getUUID() {
        return this.mUUID;
    }
    
    public boolean isLocal() {
        return this.mLocal;
    }
    
    @Override
    public String toString() {
        return "MdxTarget [TAG=nf_mdx, mUUID=" + this.mUUID + ", friendlyName=" + this.mFriendlyName + ", local=" + this.mLocal + "]";
    }
}

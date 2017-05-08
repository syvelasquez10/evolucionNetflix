// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.google.gson.annotations.SerializedName;

class ExternalStorageInfo
{
    @SerializedName("dbgInfo")
    public final String mDebugInfo;
    @SerializedName("emulated")
    public final boolean mIsEmulated;
    @SerializedName("primary")
    public final boolean mIsPrimaryStorage;
    @SerializedName("removable")
    public final boolean mIsRemovable;
    @SerializedName("writable")
    public final boolean mIsWritable;
    
    public ExternalStorageInfo(final boolean mIsRemovable, final boolean mIsPrimaryStorage, final boolean mIsWritable, final boolean mIsEmulated, final String mDebugInfo) {
        this.mIsRemovable = mIsRemovable;
        this.mIsPrimaryStorage = mIsPrimaryStorage;
        this.mIsWritable = mIsWritable;
        this.mIsEmulated = mIsEmulated;
        this.mDebugInfo = mDebugInfo;
    }
}

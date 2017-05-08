// 
// Decompiled by Procyon v0.5.30
// 

package org.chromium.net;

import java.io.File;
import android.util.Log;
import java.util.LinkedList;
import java.util.List;
import android.content.Context;
import java.util.regex.Pattern;

public class CronetEngine$Builder
{
    private static final Pattern INVALID_PKP_HOST_NAME;
    private final Context mContext;
    private boolean mDisableCache;
    private boolean mHttp2Enabled;
    private long mHttpCacheMaxSize;
    private int mHttpCacheMode;
    private boolean mLegacyModeEnabled;
    private String mLibraryName;
    private long mMockCertVerifier;
    private boolean mNetworkQualityEstimatorEnabled;
    private final List<Object> mPkps;
    private boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
    private boolean mQuicEnabled;
    private final List<Object> mQuicHints;
    private boolean mSdchEnabled;
    private String mStoragePath;
    private String mUserAgent;
    
    static {
        INVALID_PKP_HOST_NAME = Pattern.compile("^[0-9\\.]*$");
    }
    
    public CronetEngine$Builder(final Context mContext) {
        this.mQuicHints = new LinkedList<Object>();
        this.mPkps = new LinkedList<Object>();
        this.mContext = mContext;
        this.setLibraryName("cronet");
        this.enableLegacyMode(false);
        this.enableQuic(false);
        this.enableHttp2(true);
        this.enableSdch(false);
        this.enableHttpCache(0, 0L);
        this.enableNetworkQualityEstimator(false);
        this.enablePublicKeyPinningBypassForLocalTrustAnchors(true);
    }
    
    public CronetEngine build() {
        if (this.getUserAgent() == null) {
            this.setUserAgent(this.getDefaultUserAgent());
        }
        CronetEngine access$000 = null;
        if (!this.legacyMode()) {
            access$000 = CronetEngine.access$000(this);
        }
        Object o;
        if ((o = access$000) == null) {
            o = new JavaCronetEngine(this.getUserAgent());
        }
        Log.i("UrlRequestFactory", "Using network stack: " + ((CronetEngine)o).getVersionString());
        this.mMockCertVerifier = 0L;
        return (CronetEngine)o;
    }
    
    public CronetEngine$Builder enableHttp2(final boolean mHttp2Enabled) {
        this.mHttp2Enabled = mHttp2Enabled;
        return this;
    }
    
    public CronetEngine$Builder enableHttpCache(final int n, final long mHttpCacheMaxSize) {
        if (n == 3 || n == 2) {
            if (this.storagePath() == null) {
                throw new IllegalArgumentException("Storage path must be set");
            }
        }
        else if (this.storagePath() != null) {
            throw new IllegalArgumentException("Storage path must not be set");
        }
        this.mDisableCache = (n == 0 || n == 2);
        this.mHttpCacheMaxSize = mHttpCacheMaxSize;
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown cache mode");
            }
            case 0: {
                this.mHttpCacheMode = 0;
                return this;
            }
            case 2:
            case 3: {
                this.mHttpCacheMode = 1;
                return this;
            }
            case 1: {
                this.mHttpCacheMode = 2;
                return this;
            }
        }
    }
    
    @Deprecated
    public CronetEngine$Builder enableLegacyMode(final boolean mLegacyModeEnabled) {
        this.mLegacyModeEnabled = mLegacyModeEnabled;
        return this;
    }
    
    public CronetEngine$Builder enableNetworkQualityEstimator(final boolean mNetworkQualityEstimatorEnabled) {
        this.mNetworkQualityEstimatorEnabled = mNetworkQualityEstimatorEnabled;
        return this;
    }
    
    public CronetEngine$Builder enablePublicKeyPinningBypassForLocalTrustAnchors(final boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled) {
        this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        return this;
    }
    
    public CronetEngine$Builder enableQuic(final boolean mQuicEnabled) {
        this.mQuicEnabled = mQuicEnabled;
        return this;
    }
    
    public CronetEngine$Builder enableSdch(final boolean mSdchEnabled) {
        this.mSdchEnabled = mSdchEnabled;
        return this;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public String getDefaultUserAgent() {
        return UserAgent.from(this.mContext);
    }
    
    public String getUserAgent() {
        return this.mUserAgent;
    }
    
    public boolean legacyMode() {
        return this.mLegacyModeEnabled;
    }
    
    public CronetEngine$Builder setLibraryName(final String mLibraryName) {
        this.mLibraryName = mLibraryName;
        return this;
    }
    
    public CronetEngine$Builder setStoragePath(final String mStoragePath) {
        if (!new File(mStoragePath).isDirectory()) {
            throw new IllegalArgumentException("Storage path must be set to existing directory");
        }
        this.mStoragePath = mStoragePath;
        return this;
    }
    
    public CronetEngine$Builder setUserAgent(final String mUserAgent) {
        this.mUserAgent = mUserAgent;
        return this;
    }
    
    public String storagePath() {
        return this.mStoragePath;
    }
}

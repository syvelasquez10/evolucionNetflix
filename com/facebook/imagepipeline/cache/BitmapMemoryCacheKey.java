// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import java.util.Locale;
import com.facebook.common.internal.Objects;
import android.net.Uri;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.HashCodeUtil;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.cache.common.CacheKey;

public class BitmapMemoryCacheKey implements CacheKey
{
    private final boolean mAutoRotated;
    private final long mCacheTime;
    private final Object mCallerContext;
    private final int mHash;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final CacheKey mPostprocessorCacheKey;
    private final String mPostprocessorName;
    private final ResizeOptions mResizeOptions;
    private final String mSourceString;
    
    public BitmapMemoryCacheKey(final String s, final ResizeOptions mResizeOptions, final boolean mAutoRotated, final ImageDecodeOptions mImageDecodeOptions, final CacheKey mPostprocessorCacheKey, final String mPostprocessorName, final Object mCallerContext) {
        this.mSourceString = Preconditions.checkNotNull(s);
        this.mResizeOptions = mResizeOptions;
        this.mAutoRotated = mAutoRotated;
        this.mImageDecodeOptions = mImageDecodeOptions;
        this.mPostprocessorCacheKey = mPostprocessorCacheKey;
        this.mPostprocessorName = mPostprocessorName;
        final int hashCode = s.hashCode();
        int hashCode2;
        if (mResizeOptions != null) {
            hashCode2 = mResizeOptions.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int n;
        if (mAutoRotated) {
            n = Boolean.TRUE.hashCode();
        }
        else {
            n = Boolean.FALSE.hashCode();
        }
        this.mHash = HashCodeUtil.hashCode(hashCode, hashCode2, n, this.mImageDecodeOptions, this.mPostprocessorCacheKey, mPostprocessorName);
        this.mCallerContext = mCallerContext;
        this.mCacheTime = RealtimeSinceBootClock.get().now();
    }
    
    @Override
    public boolean containsUri(final Uri uri) {
        return this.getSourceUriString().contains(uri.toString());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof BitmapMemoryCacheKey) {
            final BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey)o;
            if (this.mHash == bitmapMemoryCacheKey.mHash && this.mSourceString.equals(bitmapMemoryCacheKey.mSourceString) && Objects.equal(this.mResizeOptions, bitmapMemoryCacheKey.mResizeOptions) && this.mAutoRotated == bitmapMemoryCacheKey.mAutoRotated && Objects.equal(this.mImageDecodeOptions, bitmapMemoryCacheKey.mImageDecodeOptions) && Objects.equal(this.mPostprocessorCacheKey, bitmapMemoryCacheKey.mPostprocessorCacheKey) && Objects.equal(this.mPostprocessorName, bitmapMemoryCacheKey.mPostprocessorName)) {
                return true;
            }
        }
        return false;
    }
    
    public String getSourceUriString() {
        return this.mSourceString;
    }
    
    @Override
    public int hashCode() {
        return this.mHash;
    }
    
    @Override
    public String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", this.mSourceString, this.mResizeOptions, Boolean.toString(this.mAutoRotated), this.mImageDecodeOptions, this.mPostprocessorCacheKey, this.mPostprocessorName, this.mHash);
    }
}

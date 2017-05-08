// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.internal.Preconditions;
import java.util.Collection;
import java.util.ArrayList;
import com.facebook.common.logging.FLog;
import java.util.HashMap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.cache.common.CacheKey;
import java.util.Map;

public class StagingArea
{
    private static final Class<?> TAG;
    private Map<CacheKey, EncodedImage> mMap;
    
    static {
        TAG = StagingArea.class;
    }
    
    private StagingArea() {
        this.mMap = new HashMap<CacheKey, EncodedImage>();
    }
    
    public static StagingArea getInstance() {
        return new StagingArea();
    }
    
    private void logStats() {
        synchronized (this) {
            FLog.v(StagingArea.TAG, "Count = %d", (Object)this.mMap.size());
        }
    }
    
    public void clearAll() {
        synchronized (this) {
            final ArrayList<EncodedImage> list = (ArrayList<EncodedImage>)new ArrayList<Object>(this.mMap.values());
            this.mMap.clear();
            // monitorexit(this)
            for (int i = 0; i < list.size(); ++i) {
                final EncodedImage encodedImage = list.get(i);
                if (encodedImage != null) {
                    encodedImage.close();
                }
            }
        }
    }
    
    public boolean containsKey(final CacheKey cacheKey) {
        synchronized (this) {
            Preconditions.checkNotNull(cacheKey);
            boolean containsKey = this.mMap.containsKey(cacheKey);
            if (!containsKey) {
                containsKey = false;
            }
            else {
                final EncodedImage encodedImage = this.mMap.get(cacheKey);
                synchronized (encodedImage) {
                    if (!EncodedImage.isValid(encodedImage)) {
                        this.mMap.remove(cacheKey);
                        FLog.w(StagingArea.TAG, "Found closed reference %d for key %s (%d)", System.identityHashCode(encodedImage), cacheKey.toString(), System.identityHashCode(cacheKey));
                        // monitorexit(encodedImage)
                        containsKey = false;
                        return containsKey;
                    }
                    // monitorexit(encodedImage)
                    containsKey = true;
                    return containsKey;
                }
            }
            return containsKey;
        }
    }
    
    public EncodedImage get(final CacheKey cacheKey) {
        synchronized (this) {
            Preconditions.checkNotNull(cacheKey);
            EncodedImage cloneOrNull;
            final EncodedImage encodedImage = cloneOrNull = this.mMap.get(cacheKey);
            if (encodedImage == null) {
                return cloneOrNull;
            }
            synchronized (encodedImage) {
                if (!EncodedImage.isValid(encodedImage)) {
                    this.mMap.remove(cacheKey);
                    FLog.w(StagingArea.TAG, "Found closed reference %d for key %s (%d)", System.identityHashCode(encodedImage), cacheKey.toString(), System.identityHashCode(cacheKey));
                    // monitorexit(encodedImage)
                    cloneOrNull = null;
                }
                else {
                    cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
                }
                return cloneOrNull;
            }
        }
    }
    
    public void put(final CacheKey cacheKey, final EncodedImage encodedImage) {
        synchronized (this) {
            Preconditions.checkNotNull(cacheKey);
            Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
            EncodedImage.closeSafely(this.mMap.put(cacheKey, EncodedImage.cloneOrNull(encodedImage)));
            this.logStats();
        }
    }
    
    public boolean remove(final CacheKey cacheKey, EncodedImage byteBufferRef) {
        synchronized (this) {
            Preconditions.checkNotNull(cacheKey);
            Preconditions.checkNotNull(byteBufferRef);
            Preconditions.checkArgument(EncodedImage.isValid(byteBufferRef));
            final EncodedImage encodedImage = this.mMap.get(cacheKey);
            boolean b = false;
            if (encodedImage == null) {
                b = false;
            }
            else {
                final CloseableReference<PooledByteBuffer> byteBufferRef2 = encodedImage.getByteBufferRef();
                byteBufferRef = (EncodedImage)byteBufferRef.getByteBufferRef();
                Label_0086: {
                    if (byteBufferRef2 == null || byteBufferRef == null) {
                        break Label_0086;
                    }
                    try {
                        if (byteBufferRef2.get() != ((CloseableReference<PooledByteBuffer>)byteBufferRef).get()) {
                            CloseableReference.closeSafely((CloseableReference<?>)byteBufferRef);
                            CloseableReference.closeSafely(byteBufferRef2);
                            EncodedImage.closeSafely(encodedImage);
                            b = false;
                            return b;
                        }
                        this.mMap.remove(cacheKey);
                        CloseableReference.closeSafely((CloseableReference<?>)byteBufferRef);
                        CloseableReference.closeSafely(byteBufferRef2);
                        EncodedImage.closeSafely(encodedImage);
                        this.logStats();
                        b = true;
                        return b;
                    }
                    finally {
                        CloseableReference.closeSafely((CloseableReference<?>)byteBufferRef);
                        CloseableReference.closeSafely(byteBufferRef2);
                        EncodedImage.closeSafely(encodedImage);
                    }
                }
            }
            return b;
        }
    }
}

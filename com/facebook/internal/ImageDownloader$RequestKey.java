// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.net.URI;

class ImageDownloader$RequestKey
{
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    URI uri;
    
    ImageDownloader$RequestKey(final URI uri, final Object tag) {
        this.uri = uri;
        this.tag = tag;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o != null) {
            b2 = b;
            if (o instanceof ImageDownloader$RequestKey) {
                final ImageDownloader$RequestKey imageDownloader$RequestKey = (ImageDownloader$RequestKey)o;
                b2 = b;
                if (imageDownloader$RequestKey.uri == this.uri) {
                    b2 = b;
                    if (imageDownloader$RequestKey.tag == this.tag) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (this.uri.hashCode() + 1073) * 37 + this.tag.hashCode();
    }
}

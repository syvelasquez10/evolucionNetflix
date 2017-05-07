// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.net.URL;

class ImageDownloader$RequestKey
{
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    URL url;
    
    ImageDownloader$RequestKey(final URL url, final Object tag) {
        this.url = url;
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
                if (imageDownloader$RequestKey.url == this.url) {
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
        return (this.url.hashCode() + 1073) * 37 + this.tag.hashCode();
    }
}

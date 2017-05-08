// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.util.Arrays;
import com.google.android.exoplayer.util.Assertions;
import android.net.Uri;

public final class DataSpec
{
    public final long absoluteStreamPosition;
    public final int flags;
    public final String key;
    public final long length;
    public final long position;
    public final byte[] postBody;
    public final Uri uri;
    
    public DataSpec(final Uri uri, final int n) {
        this(uri, 0L, -1L, null, n);
    }
    
    public DataSpec(final Uri uri, final long n, final long n2, final long n3, final String s, final int n4) {
        this(uri, null, n, n2, n3, s, n4);
    }
    
    public DataSpec(final Uri uri, final long n, final long n2, final String s) {
        this(uri, n, n, n2, s, 0);
    }
    
    public DataSpec(final Uri uri, final long n, final long n2, final String s, final int n3) {
        this(uri, n, n, n2, s, n3);
    }
    
    public DataSpec(final Uri uri, final byte[] postBody, final long absoluteStreamPosition, final long position, final long length, final String key, final int flags) {
        Assertions.checkArgument(absoluteStreamPosition >= 0L);
        Assertions.checkArgument(position >= 0L);
        Assertions.checkArgument(length > 0L || length == -1L);
        this.uri = uri;
        this.postBody = postBody;
        this.absoluteStreamPosition = absoluteStreamPosition;
        this.position = position;
        this.length = length;
        this.key = key;
        this.flags = flags;
    }
    
    @Override
    public String toString() {
        return "DataSpec[" + this.uri + ", " + Arrays.toString(this.postBody) + ", " + this.absoluteStreamPosition + ", " + this.position + ", " + this.length + ", " + this.key + ", " + this.flags + "]";
    }
}

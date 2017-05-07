// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public abstract class UrlTileProvider implements TileProvider
{
    private final int kr;
    private final int ks;
    
    public UrlTileProvider(final int kr, final int ks) {
        this.kr = kr;
        this.ks = ks;
    }
    
    private static long a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[4096];
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    private static byte[] a(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    @Override
    public final Tile getTile(final int n, final int n2, final int n3) {
        final URL tileUrl = this.getTileUrl(n, n2, n3);
        if (tileUrl == null) {
            return UrlTileProvider.NO_TILE;
        }
        try {
            return new Tile(this.kr, this.ks, a(tileUrl.openStream()));
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public abstract URL getTileUrl(final int p0, final int p1, final int p2);
}

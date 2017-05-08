// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.EOFException;
import java.io.IOException;
import android.content.Context;
import java.io.InputStream;
import android.content.res.AssetManager;

public final class AssetDataSource implements UriDataSource
{
    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener listener;
    private boolean opened;
    private String uriString;
    
    public AssetDataSource(final Context context, final TransferListener listener) {
        this.assetManager = context.getAssets();
        this.listener = listener;
    }
    
    @Override
    public void close() {
        this.uriString = null;
        if (this.inputStream == null) {
            return;
        }
        try {
            this.inputStream.close();
        }
        catch (IOException ex) {
            throw new AssetDataSource$AssetDataSourceException(ex);
        }
        finally {
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                if (this.listener != null) {
                    this.listener.onTransferEnd();
                }
            }
        }
    }
    
    @Override
    public String getUri() {
        return this.uriString;
    }
    
    @Override
    public long open(final DataSpec dataSpec) {
        while (true) {
            while (true) {
                String path;
                try {
                    this.uriString = dataSpec.uri.toString();
                    path = dataSpec.uri.getPath();
                    if (path.startsWith("/android_asset/")) {
                        final String s = path.substring(15);
                        this.uriString = dataSpec.uri.toString();
                        this.inputStream = this.assetManager.open(s, 1);
                        if (this.inputStream.skip(dataSpec.position) < dataSpec.position) {
                            throw new EOFException();
                        }
                        break;
                    }
                }
                catch (IOException ex) {
                    throw new AssetDataSource$AssetDataSourceException(ex);
                }
                String s = path;
                if (path.startsWith("/")) {
                    s = path.substring(1);
                    continue;
                }
                continue;
            }
        }
        if (dataSpec.length != -1L) {
            this.bytesRemaining = dataSpec.length;
        }
        else {
            this.bytesRemaining = this.inputStream.available();
            if (this.bytesRemaining == 2147483647L) {
                this.bytesRemaining = -1L;
            }
        }
        this.opened = true;
        if (this.listener != null) {
            this.listener.onTransferStart();
        }
        return this.bytesRemaining;
    }
    
    @Override
    public int read(final byte[] array, int n, int read) {
        if (this.bytesRemaining == 0L) {
            n = -1;
        }
        else {
            try {
                if (this.bytesRemaining != -1L) {
                    read = (int)Math.min(this.bytesRemaining, read);
                }
                read = this.inputStream.read(array, n, read);
                if ((n = read) > 0) {
                    if (this.bytesRemaining != -1L) {
                        this.bytesRemaining -= read;
                    }
                    n = read;
                    if (this.listener != null) {
                        this.listener.onBytesTransferred(read);
                        return read;
                    }
                }
            }
            catch (IOException ex) {
                throw new AssetDataSource$AssetDataSourceException(ex);
            }
        }
        return n;
    }
}

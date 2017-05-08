// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import android.content.Context;
import android.content.ContentResolver;
import java.io.InputStream;
import android.content.res.AssetFileDescriptor;

public final class ContentDataSource implements UriDataSource
{
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener listener;
    private boolean opened;
    private final ContentResolver resolver;
    private String uriString;
    
    public ContentDataSource(final Context context, final TransferListener listener) {
        this.resolver = context.getContentResolver();
        this.listener = listener;
    }
    
    @Override
    public void close() {
        this.uriString = null;
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            this.inputStream = null;
            try {
                if (this.assetFileDescriptor != null) {
                    this.assetFileDescriptor.close();
                }
            }
            catch (IOException ex) {
                throw new ContentDataSource$ContentDataSourceException(ex);
            }
            finally {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd();
                    }
                }
            }
        }
        catch (IOException ex2) {
            throw new ContentDataSource$ContentDataSourceException(ex2);
        }
        finally {
            this.inputStream = null;
            try {
                if (this.assetFileDescriptor != null) {
                    this.assetFileDescriptor.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd();
                    }
                }
            }
            catch (IOException ex3) {
                throw new ContentDataSource$ContentDataSourceException(ex3);
            }
            finally {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd();
                    }
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
        try {
            this.uriString = dataSpec.uri.toString();
            this.assetFileDescriptor = this.resolver.openAssetFileDescriptor(dataSpec.uri, "r");
            this.inputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
            if (this.inputStream.skip(dataSpec.position) < dataSpec.position) {
                throw new EOFException();
            }
        }
        catch (IOException ex) {
            throw new ContentDataSource$ContentDataSourceException(ex);
        }
        if (dataSpec.length != -1L) {
            this.bytesRemaining = dataSpec.length;
        }
        else {
            this.bytesRemaining = this.inputStream.available();
            if (this.bytesRemaining == 0L) {
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
                throw new ContentDataSource$ContentDataSourceException(ex);
            }
        }
        return n;
    }
}

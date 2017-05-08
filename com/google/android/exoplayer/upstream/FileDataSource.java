// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileDataSource implements UriDataSource
{
    private long bytesRemaining;
    private RandomAccessFile file;
    private final TransferListener listener;
    private boolean opened;
    private String uriString;
    
    public FileDataSource() {
        this(null);
    }
    
    public FileDataSource(final TransferListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void close() {
        this.uriString = null;
        if (this.file == null) {
            return;
        }
        try {
            this.file.close();
        }
        catch (IOException ex) {
            throw new FileDataSource$FileDataSourceException(ex);
        }
        finally {
            this.file = null;
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
                try {
                    this.uriString = dataSpec.uri.toString();
                    (this.file = new RandomAccessFile(dataSpec.uri.getPath(), "r")).seek(dataSpec.position);
                    if (dataSpec.length == -1L) {
                        final long length = this.file.length() - dataSpec.position;
                        this.bytesRemaining = length;
                        if (this.bytesRemaining < 0L) {
                            throw new EOFException();
                        }
                        break;
                    }
                }
                catch (IOException ex) {
                    throw new FileDataSource$FileDataSourceException(ex);
                }
                final long length = dataSpec.length;
                continue;
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
                read = this.file.read(array, n, (int)Math.min(this.bytesRemaining, read));
                if ((n = read) > 0) {
                    this.bytesRemaining -= read;
                    n = read;
                    if (this.listener != null) {
                        this.listener.onBytesTransferred(read);
                        return read;
                    }
                }
            }
            catch (IOException ex) {
                throw new FileDataSource$FileDataSourceException(ex);
            }
        }
        return n;
    }
}

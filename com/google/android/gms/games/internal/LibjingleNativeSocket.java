// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import java.io.IOException;
import android.os.ParcelFileDescriptor$AutoCloseOutputStream;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;

public final class LibjingleNativeSocket implements RealTimeSocket
{
    private static final String TAG;
    private final ParcelFileDescriptor Cj;
    private final InputStream JI;
    private final OutputStream JJ;
    
    static {
        TAG = LibjingleNativeSocket.class.getSimpleName();
    }
    
    LibjingleNativeSocket(final ParcelFileDescriptor cj) {
        this.Cj = cj;
        this.JI = (InputStream)new ParcelFileDescriptor$AutoCloseInputStream(cj);
        this.JJ = (OutputStream)new ParcelFileDescriptor$AutoCloseOutputStream(cj);
    }
    
    @Override
    public void close() throws IOException {
        this.Cj.close();
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return this.JI;
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.JJ;
    }
    
    @Override
    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        return this.Cj;
    }
    
    @Override
    public boolean isClosed() {
        try {
            this.JI.available();
            return false;
        }
        catch (IOException ex) {
            return true;
        }
    }
}

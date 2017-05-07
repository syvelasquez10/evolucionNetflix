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
    private final ParcelFileDescriptor Kx;
    private final InputStream XM;
    private final OutputStream XN;
    
    static {
        TAG = LibjingleNativeSocket.class.getSimpleName();
    }
    
    LibjingleNativeSocket(final ParcelFileDescriptor kx) {
        this.Kx = kx;
        this.XM = (InputStream)new ParcelFileDescriptor$AutoCloseInputStream(kx);
        this.XN = (OutputStream)new ParcelFileDescriptor$AutoCloseOutputStream(kx);
    }
    
    @Override
    public void close() {
        this.Kx.close();
    }
    
    @Override
    public InputStream getInputStream() {
        return this.XM;
    }
    
    @Override
    public OutputStream getOutputStream() {
        return this.XN;
    }
    
    @Override
    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.Kx;
    }
    
    @Override
    public boolean isClosed() {
        try {
            this.XM.available();
            return false;
        }
        catch (IOException ex) {
            return true;
        }
    }
}

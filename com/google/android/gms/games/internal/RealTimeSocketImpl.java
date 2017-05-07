// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import android.net.LocalSocket;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;

final class RealTimeSocketImpl implements RealTimeSocket
{
    private ParcelFileDescriptor Kx;
    private final LocalSocket XT;
    private final String Xg;
    
    RealTimeSocketImpl(final LocalSocket xt, final String xg) {
        this.XT = xt;
        this.Xg = xg;
    }
    
    @Override
    public void close() throws IOException {
        this.XT.close();
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return this.XT.getInputStream();
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.XT.getOutputStream();
    }
    
    @Override
    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        if (this.Kx == null && !this.isClosed()) {
            final Parcel obtain = Parcel.obtain();
            obtain.writeFileDescriptor(this.XT.getFileDescriptor());
            obtain.setDataPosition(0);
            this.Kx = obtain.readFileDescriptor();
        }
        return this.Kx;
    }
    
    @Override
    public boolean isClosed() {
        return !this.XT.isConnected() && !this.XT.isBound();
    }
}

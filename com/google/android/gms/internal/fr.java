// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import android.net.LocalSocket;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;

final class fr implements RealTimeSocket
{
    private ParcelFileDescriptor om;
    private final LocalSocket uM;
    private final String up;
    
    fr(final LocalSocket um, final String up) {
        this.uM = um;
        this.up = up;
    }
    
    @Override
    public void close() throws IOException {
        this.uM.close();
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return this.uM.getInputStream();
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.uM.getOutputStream();
    }
    
    @Override
    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        if (this.om == null && !this.isClosed()) {
            final Parcel obtain = Parcel.obtain();
            obtain.writeFileDescriptor(this.uM.getFileDescriptor());
            obtain.setDataPosition(0);
            this.om = obtain.readFileDescriptor();
        }
        return this.om;
    }
    
    @Override
    public boolean isClosed() {
        return !this.uM.isConnected() && !this.uM.isBound();
    }
}

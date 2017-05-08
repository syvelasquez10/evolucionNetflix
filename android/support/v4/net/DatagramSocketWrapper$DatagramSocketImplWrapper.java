// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.io.FileDescriptor;
import java.net.DatagramSocket;
import java.net.SocketImpl;

class DatagramSocketWrapper$DatagramSocketImplWrapper extends SocketImpl
{
    public DatagramSocketWrapper$DatagramSocketImplWrapper(final DatagramSocket datagramSocket, final FileDescriptor fd) {
        this.localport = datagramSocket.getLocalPort();
        this.fd = fd;
    }
    
    @Override
    protected void accept(final SocketImpl socketImpl) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected int available() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void bind(final InetAddress inetAddress, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void close() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void connect(final String s, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void connect(final InetAddress inetAddress, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void connect(final SocketAddress socketAddress, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void create(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected InputStream getInputStream() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object getOption(final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected OutputStream getOutputStream() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void listen(final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void sendUrgentData(final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setOption(final int n, final Object o) {
        throw new UnsupportedOperationException();
    }
}

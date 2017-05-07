// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileDescriptor;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.net.SocketImpl;

final class ac$1 extends SocketImpl
{
    @Override
    protected final void accept(final SocketImpl socketImpl) {
    }
    
    @Override
    protected final int available() {
        return 0;
    }
    
    @Override
    protected final void bind(final InetAddress inetAddress, final int n) {
    }
    
    @Override
    protected final void close() {
    }
    
    @Override
    protected final void connect(final String s, final int n) {
    }
    
    @Override
    protected final void connect(final InetAddress inetAddress, final int n) {
    }
    
    @Override
    protected final void connect(final SocketAddress socketAddress, final int n) {
    }
    
    @Override
    protected final void create(final boolean b) {
    }
    
    @Override
    protected final FileDescriptor getFileDescriptor() {
        return null;
    }
    
    @Override
    protected final InetAddress getInetAddress() {
        return null;
    }
    
    @Override
    protected final InputStream getInputStream() {
        return null;
    }
    
    @Override
    protected final int getLocalPort() {
        return 0;
    }
    
    @Override
    public final Object getOption(final int n) {
        return null;
    }
    
    @Override
    protected final OutputStream getOutputStream() {
        return null;
    }
    
    @Override
    protected final int getPort() {
        return 0;
    }
    
    @Override
    protected final void listen(final int n) {
    }
    
    @Override
    protected final void sendUrgentData(final int n) {
    }
    
    @Override
    public final void setOption(final int n, final Object o) {
    }
    
    @Override
    protected final void setPerformancePreferences(final int n, final int n2, final int n3) {
    }
    
    @Override
    protected final void shutdownInput() {
    }
    
    @Override
    protected final void shutdownOutput() {
    }
    
    @Override
    protected final boolean supportsUrgentData() {
        return false;
    }
    
    @Override
    public final String toString() {
        return null;
    }
}

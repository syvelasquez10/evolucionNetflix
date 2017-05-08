// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.net.Socket;
import java.net.DatagramSocket;

class TrafficStatsCompat$IcsTrafficStatsCompatImpl implements TrafficStatsCompat$TrafficStatsCompatImpl
{
    @Override
    public void clearThreadStatsTag() {
        TrafficStatsCompatIcs.clearThreadStatsTag();
    }
    
    @Override
    public int getThreadStatsTag() {
        return TrafficStatsCompatIcs.getThreadStatsTag();
    }
    
    @Override
    public void incrementOperationCount(final int n) {
        TrafficStatsCompatIcs.incrementOperationCount(n);
    }
    
    @Override
    public void incrementOperationCount(final int n, final int n2) {
        TrafficStatsCompatIcs.incrementOperationCount(n, n2);
    }
    
    @Override
    public void setThreadStatsTag(final int threadStatsTag) {
        TrafficStatsCompatIcs.setThreadStatsTag(threadStatsTag);
    }
    
    @Override
    public void tagDatagramSocket(final DatagramSocket datagramSocket) {
        TrafficStatsCompatIcs.tagDatagramSocket(datagramSocket);
    }
    
    @Override
    public void tagSocket(final Socket socket) {
        TrafficStatsCompatIcs.tagSocket(socket);
    }
    
    @Override
    public void untagDatagramSocket(final DatagramSocket datagramSocket) {
        TrafficStatsCompatIcs.untagDatagramSocket(datagramSocket);
    }
    
    @Override
    public void untagSocket(final Socket socket) {
        TrafficStatsCompatIcs.untagSocket(socket);
    }
}

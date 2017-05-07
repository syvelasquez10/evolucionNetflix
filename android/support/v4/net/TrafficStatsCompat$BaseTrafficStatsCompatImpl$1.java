// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

class TrafficStatsCompat$BaseTrafficStatsCompatImpl$1 extends ThreadLocal<TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags>
{
    final /* synthetic */ TrafficStatsCompat$BaseTrafficStatsCompatImpl this$0;
    
    TrafficStatsCompat$BaseTrafficStatsCompatImpl$1(final TrafficStatsCompat$BaseTrafficStatsCompatImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    protected TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags initialValue() {
        return new TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags(null);
    }
}

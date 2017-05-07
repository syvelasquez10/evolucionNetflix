// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.List;

public interface NetflixComHandler
{
    boolean canHandle(final List<String> p0);
    
    NflxHandler$Response tryHandle(final NetflixActivity p0, final List<String> p1, final String p2);
}

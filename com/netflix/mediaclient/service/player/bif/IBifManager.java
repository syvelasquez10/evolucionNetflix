// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bif;

import java.nio.ByteBuffer;

public interface IBifManager
{
    ByteBuffer getIndexFrame(final int p0);
    
    void release();
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import com.netflix.mediaclient.ui.common.PlayContext;

public interface PdsPlayInterface
{
    PdsPlaySessionInterface createPdsPlaySession(final String p0, final String p1, final long p2, final PlayContext p3);
}

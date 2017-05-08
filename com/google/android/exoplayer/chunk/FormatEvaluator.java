// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import java.util.List;

public interface FormatEvaluator
{
    void disable();
    
    void enable();
    
    void evaluate(final List<? extends MediaChunk> p0, final long p1, final Format[] p2, final FormatEvaluator$Evaluation p3);
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public interface voOSVideoPerformance
{
    int AverageDecodeTime();
    
    int AverageRenderTime();
    
    int CPULoad();
    
    int CodecDropNum();
    
    int[] CodecErrors();
    
    int CodecErrorsNum();
    
    int CodecTimeNum();
    
    int DecodedNum();
    
    int Frequency();
    
    int JitterNum();
    
    int LastTime();
    
    int MaxFrequency();
    
    int RenderDropNum();
    
    int RenderNum();
    
    int RenderTimeNum();
    
    int SourceDropNum();
    
    int SourceTimeNum();
    
    int TotalCPULoad();
    
    int WorstDecodeTime();
    
    int WorstRenderTime();
}

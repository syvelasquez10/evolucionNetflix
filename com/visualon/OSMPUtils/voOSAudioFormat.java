// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public interface voOSAudioFormat
{
    int Channels();
    
    int SampleBits();
    
    int SampleRate();
    
    boolean parse(final Parcel p0);
}

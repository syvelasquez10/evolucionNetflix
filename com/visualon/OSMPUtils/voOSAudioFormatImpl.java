// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public class voOSAudioFormatImpl implements voOSAudioFormat
{
    private int mChannels;
    private int mSampleBits;
    private int mSampleRate;
    
    public voOSAudioFormatImpl() {
    }
    
    public voOSAudioFormatImpl(final int mSampleRate, final int mChannels, final int mSampleBits) {
        this.mSampleRate = mSampleRate;
        this.mChannels = mChannels;
        this.mSampleBits = mSampleBits;
    }
    
    @Override
    public int Channels() {
        return this.mChannels;
    }
    
    @Override
    public int SampleBits() {
        return this.mSampleBits;
    }
    
    @Override
    public int SampleRate() {
        return this.mSampleRate;
    }
    
    @Override
    public boolean parse(final Parcel parcel) {
        this.mSampleRate = parcel.readInt();
        this.mChannels = parcel.readInt();
        this.mSampleBits = parcel.readInt();
        return true;
    }
}

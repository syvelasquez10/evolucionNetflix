// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public class voOSStreamInfoImpl implements voOSStreamInfo
{
    int Bitrate;
    int SelInfo;
    int StreamID;
    int TrackCount;
    private voOSTrackInfo[] TrackInfo;
    
    public voOSStreamInfoImpl() {
    }
    
    public voOSStreamInfoImpl(final int streamID, final int selInfo, final int bitrate, final int trackCount, final voOSTrackInfo[] trackInfo) {
        this.StreamID = streamID;
        this.SelInfo = selInfo;
        this.Bitrate = bitrate;
        this.TrackCount = trackCount;
        this.TrackInfo = trackInfo;
    }
    
    @Override
    public int getBitrate() {
        return this.Bitrate;
    }
    
    @Override
    public int getSelInfo() {
        return this.SelInfo;
    }
    
    @Override
    public int getStreamID() {
        return this.StreamID;
    }
    
    @Override
    public int getTrackCount() {
        return this.TrackCount;
    }
    
    @Override
    public voOSTrackInfo[] getTrackInfo() {
        return this.TrackInfo;
    }
    
    @Override
    public boolean parse(final Parcel parcel) {
        this.StreamID = parcel.readInt();
        this.SelInfo = parcel.readInt();
        this.Bitrate = parcel.readInt();
        this.TrackCount = parcel.readInt();
        if (this.TrackCount > 0) {
            this.TrackInfo = new voOSTrackInfo[this.TrackCount];
            for (int i = 0; i < this.TrackCount; ++i) {
                (this.TrackInfo[i] = new voOSTrackInfoImpl()).parse(parcel);
            }
        }
        return true;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.internal.fq;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class RealTimeMessage implements Parcelable
{
    public static final Parcelable$Creator<RealTimeMessage> CREATOR;
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String MH;
    private final byte[] MI;
    private final int MJ;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<RealTimeMessage>() {
            public RealTimeMessage aw(final Parcel parcel) {
                return new RealTimeMessage(parcel, null);
            }
            
            public RealTimeMessage[] bp(final int n) {
                return new RealTimeMessage[n];
            }
        };
    }
    
    private RealTimeMessage(final Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }
    
    public RealTimeMessage(final String s, final byte[] array, final int mj) {
        this.MH = fq.f(s);
        this.MI = fq.f(array).clone();
        this.MJ = mj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public byte[] getMessageData() {
        return this.MI;
    }
    
    public String getSenderParticipantId() {
        return this.MH;
    }
    
    public boolean isReliable() {
        return this.MJ == 1;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.MH);
        parcel.writeByteArray(this.MI);
        parcel.writeInt(this.MJ);
    }
}

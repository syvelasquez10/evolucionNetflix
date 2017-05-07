// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.internal.eg;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class RealTimeMessage implements Parcelable
{
    public static final Parcelable$Creator<RealTimeMessage> CREATOR;
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String wA;
    private final byte[] wB;
    private final int wC;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<RealTimeMessage>() {
            public RealTimeMessage[] aK(final int n) {
                return new RealTimeMessage[n];
            }
            
            public RealTimeMessage ac(final Parcel parcel) {
                return new RealTimeMessage(parcel, null);
            }
        };
    }
    
    private RealTimeMessage(final Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }
    
    public RealTimeMessage(final String s, final byte[] array, final int wc) {
        this.wA = eg.f(s);
        this.wB = eg.f(array).clone();
        this.wC = wc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public byte[] getMessageData() {
        return this.wB;
    }
    
    public String getSenderParticipantId() {
        return this.wA;
    }
    
    public boolean isReliable() {
        return this.wC == 1;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.wA);
        parcel.writeByteArray(this.wB);
        parcel.writeInt(this.wC);
    }
}

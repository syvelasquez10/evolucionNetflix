// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class RealTimeMessage implements Parcelable
{
    public static final Parcelable$Creator<RealTimeMessage> CREATOR;
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String aca;
    private final byte[] acb;
    private final int acc;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<RealTimeMessage>() {
            public RealTimeMessage cn(final Parcel parcel) {
                return new RealTimeMessage(parcel, null);
            }
            
            public RealTimeMessage[] dU(final int n) {
                return new RealTimeMessage[n];
            }
        };
    }
    
    private RealTimeMessage(final Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }
    
    public RealTimeMessage(final String s, final byte[] array, final int acc) {
        this.aca = n.i(s);
        this.acb = n.i(array).clone();
        this.acc = acc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public byte[] getMessageData() {
        return this.acb;
    }
    
    public String getSenderParticipantId() {
        return this.aca;
    }
    
    public boolean isReliable() {
        return this.acc == 1;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.aca);
        parcel.writeByteArray(this.acb);
        parcel.writeInt(this.acc);
    }
}

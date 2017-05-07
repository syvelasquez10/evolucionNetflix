// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.internal.gd;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ParticipantResult implements SafeParcelable
{
    public static final ParticipantResultCreator CREATOR;
    public static final int MATCH_RESULT_DISAGREED = 5;
    public static final int MATCH_RESULT_DISCONNECT = 4;
    public static final int MATCH_RESULT_LOSS = 1;
    public static final int MATCH_RESULT_NONE = 3;
    public static final int MATCH_RESULT_TIE = 2;
    public static final int MATCH_RESULT_UNINITIALIZED = -1;
    public static final int MATCH_RESULT_WIN = 0;
    public static final int PLACING_UNINITIALIZED = -1;
    private final int kg;
    private final String up;
    private final int wy;
    private final int wz;
    
    static {
        CREATOR = new ParticipantResultCreator();
    }
    
    public ParticipantResult(final int kg, final String s, final int wy, final int wz) {
        this.kg = kg;
        this.up = eg.f(s);
        eg.p(gd.isValid(wy));
        this.wy = wy;
        this.wz = wz;
    }
    
    public ParticipantResult(final String s, final int n, final int n2) {
        this(1, s, n, n2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getParticipantId() {
        return this.up;
    }
    
    public int getPlacing() {
        return this.wz;
    }
    
    public int getResult() {
        return this.wy;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ParticipantResultCreator.a(this, parcel, n);
    }
}

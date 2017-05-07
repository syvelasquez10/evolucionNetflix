// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.internal.constants.MatchResult;
import com.google.android.gms.common.internal.n;
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
    private final int BR;
    private final String Xg;
    private final int abY;
    private final int abZ;
    
    static {
        CREATOR = new ParticipantResultCreator();
    }
    
    public ParticipantResult(final int br, final String s, final int abY, final int abZ) {
        this.BR = br;
        this.Xg = n.i(s);
        n.I(MatchResult.isValid(abY));
        this.abY = abY;
        this.abZ = abZ;
    }
    
    public ParticipantResult(final String s, final int n, final int n2) {
        this(1, s, n, n2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getParticipantId() {
        return this.Xg;
    }
    
    public int getPlacing() {
        return this.abZ;
    }
    
    public int getResult() {
        return this.abY;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ParticipantResultCreator.a(this, parcel, n);
    }
}

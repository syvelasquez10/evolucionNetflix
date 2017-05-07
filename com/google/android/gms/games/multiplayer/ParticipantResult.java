// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.internal.constants.MatchResult;
import com.google.android.gms.internal.fq;
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
    private final String Jg;
    private final int MF;
    private final int MG;
    private final int xH;
    
    static {
        CREATOR = new ParticipantResultCreator();
    }
    
    public ParticipantResult(final int xh, final String s, final int mf, final int mg) {
        this.xH = xh;
        this.Jg = fq.f(s);
        fq.x(MatchResult.isValid(mf));
        this.MF = mf;
        this.MG = mg;
    }
    
    public ParticipantResult(final String s, final int n, final int n2) {
        this(1, s, n, n2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getParticipantId() {
        return this.Jg;
    }
    
    public int getPlacing() {
        return this.MG;
    }
    
    public int getResult() {
        return this.MF;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ParticipantResultCreator.a(this, parcel, n);
    }
}

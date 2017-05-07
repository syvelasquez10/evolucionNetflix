// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.m$a;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;

public final class ScoreSubmissionData
{
    private static final String[] abh;
    private int HF;
    private String Vz;
    private HashMap<Integer, ScoreSubmissionData$Result> abN;
    private String abj;
    
    static {
        abh = new String[] { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
    }
    
    public ScoreSubmissionData(final DataHolder dataHolder) {
        this.HF = dataHolder.getStatusCode();
        this.abN = new HashMap<Integer, ScoreSubmissionData$Result>();
        final int count = dataHolder.getCount();
        n.K(count == 3);
        for (int i = 0; i < count; ++i) {
            final int ar = dataHolder.ar(i);
            if (i == 0) {
                this.abj = dataHolder.c("leaderboardId", i, ar);
                this.Vz = dataHolder.c("playerId", i, ar);
            }
            if (dataHolder.d("hasResult", i, ar)) {
                this.a(new ScoreSubmissionData$Result(dataHolder.a("rawScore", i, ar), dataHolder.c("formattedScore", i, ar), dataHolder.c("scoreTag", i, ar), dataHolder.d("newBest", i, ar)), dataHolder.b("timeSpan", i, ar));
            }
        }
    }
    
    private void a(final ScoreSubmissionData$Result scoreSubmissionData$Result, final int n) {
        this.abN.put(n, scoreSubmissionData$Result);
    }
    
    public String getLeaderboardId() {
        return this.abj;
    }
    
    public String getPlayerId() {
        return this.Vz;
    }
    
    public ScoreSubmissionData$Result getScoreResult(final int n) {
        return this.abN.get(n);
    }
    
    @Override
    public String toString() {
        final m$a a = m.h(this).a("PlayerId", this.Vz).a("StatusCode", this.HF);
        for (int i = 0; i < 3; ++i) {
            final ScoreSubmissionData$Result scoreSubmissionData$Result = this.abN.get(i);
            a.a("TimesSpan", TimeSpan.dH(i));
            String string;
            if (scoreSubmissionData$Result == null) {
                string = "null";
            }
            else {
                string = scoreSubmissionData$Result.toString();
            }
            a.a("Result", string);
        }
        return a.toString();
    }
}

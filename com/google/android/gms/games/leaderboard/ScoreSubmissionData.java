// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

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
    private HashMap<Integer, Result> abN;
    private String abj;
    
    static {
        abh = new String[] { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
    }
    
    public ScoreSubmissionData(final DataHolder dataHolder) {
        this.HF = dataHolder.getStatusCode();
        this.abN = new HashMap<Integer, Result>();
        final int count = dataHolder.getCount();
        n.K(count == 3);
        for (int i = 0; i < count; ++i) {
            final int ar = dataHolder.ar(i);
            if (i == 0) {
                this.abj = dataHolder.c("leaderboardId", i, ar);
                this.Vz = dataHolder.c("playerId", i, ar);
            }
            if (dataHolder.d("hasResult", i, ar)) {
                this.a(new Result(dataHolder.a("rawScore", i, ar), dataHolder.c("formattedScore", i, ar), dataHolder.c("scoreTag", i, ar), dataHolder.d("newBest", i, ar)), dataHolder.b("timeSpan", i, ar));
            }
        }
    }
    
    private void a(final Result result, final int n) {
        this.abN.put(n, result);
    }
    
    public String getLeaderboardId() {
        return this.abj;
    }
    
    public String getPlayerId() {
        return this.Vz;
    }
    
    public Result getScoreResult(final int n) {
        return this.abN.get(n);
    }
    
    @Override
    public String toString() {
        final m.a a = m.h(this).a("PlayerId", this.Vz).a("StatusCode", this.HF);
        for (int i = 0; i < 3; ++i) {
            final Result result = this.abN.get(i);
            a.a("TimesSpan", TimeSpan.dH(i));
            String string;
            if (result == null) {
                string = "null";
            }
            else {
                string = result.toString();
            }
            a.a("Result", string);
        }
        return a.toString();
    }
    
    public static final class Result
    {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;
        
        public Result(final long rawScore, final String formattedScore, final String scoreTag, final boolean newBest) {
            this.rawScore = rawScore;
            this.formattedScore = formattedScore;
            this.scoreTag = scoreTag;
            this.newBest = newBest;
        }
        
        @Override
        public String toString() {
            return m.h(this).a("RawScore", this.rawScore).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", this.newBest).toString();
        }
    }
}

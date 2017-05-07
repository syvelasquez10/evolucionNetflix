// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.internal.ge;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;

@Deprecated
public final class SubmitScoreResult
{
    private static final String[] wh;
    private int mC;
    private String tC;
    private String vD;
    private HashMap<Integer, Result> wi;
    
    static {
        wh = new String[] { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
    }
    
    public SubmitScoreResult(final int n, final String s, final String s2) {
        this(n, s, s2, new HashMap<Integer, Result>());
    }
    
    public SubmitScoreResult(final int mc, final String vd, final String tc, final HashMap<Integer, Result> wi) {
        this.mC = mc;
        this.vD = vd;
        this.tC = tc;
        this.wi = wi;
    }
    
    public SubmitScoreResult(final DataHolder dataHolder) {
        this.mC = dataHolder.getStatusCode();
        this.wi = new HashMap<Integer, Result>();
        final int count = dataHolder.getCount();
        eg.r(count == 3);
        for (int i = 0; i < count; ++i) {
            final int c = dataHolder.C(i);
            if (i == 0) {
                this.vD = dataHolder.getString("leaderboardId", i, c);
                this.tC = dataHolder.getString("playerId", i, c);
            }
            if (dataHolder.getBoolean("hasResult", i, c)) {
                this.a(new Result(dataHolder.getLong("rawScore", i, c), dataHolder.getString("formattedScore", i, c), dataHolder.getString("scoreTag", i, c), dataHolder.getBoolean("newBest", i, c)), dataHolder.getInteger("timeSpan", i, c));
            }
        }
    }
    
    private void a(final Result result, final int n) {
        this.wi.put(n, result);
    }
    
    public String getLeaderboardId() {
        return this.vD;
    }
    
    public String getPlayerId() {
        return this.tC;
    }
    
    public Result getScoreResult(final int n) {
        return this.wi.get(n);
    }
    
    public int getStatusCode() {
        return this.mC;
    }
    
    @Override
    public String toString() {
        final ee.a a = ee.e(this).a("PlayerId", this.tC).a("StatusCode", this.mC);
        for (int i = 0; i < 3; ++i) {
            final Result result = this.wi.get(i);
            a.a("TimesSpan", ge.aG(i));
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
            return ee.e(this).a("RawScore", this.rawScore).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", this.newBest).toString();
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.fq;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;

public final class ScoreSubmissionData
{
    private static final String[] LN;
    private int Ah;
    private String Ie;
    private String LP;
    private HashMap<Integer, Result> Mt;
    
    static {
        LN = new String[] { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
    }
    
    public ScoreSubmissionData(final DataHolder dataHolder) {
        this.Ah = dataHolder.getStatusCode();
        this.Mt = new HashMap<Integer, Result>();
        final int count = dataHolder.getCount();
        fq.z(count == 3);
        for (int i = 0; i < count; ++i) {
            final int g = dataHolder.G(i);
            if (i == 0) {
                this.LP = dataHolder.getString("leaderboardId", i, g);
                this.Ie = dataHolder.getString("playerId", i, g);
            }
            if (dataHolder.getBoolean("hasResult", i, g)) {
                this.a(new Result(dataHolder.getLong("rawScore", i, g), dataHolder.getString("formattedScore", i, g), dataHolder.getString("scoreTag", i, g), dataHolder.getBoolean("newBest", i, g)), dataHolder.getInteger("timeSpan", i, g));
            }
        }
    }
    
    private void a(final Result result, final int n) {
        this.Mt.put(n, result);
    }
    
    public String getLeaderboardId() {
        return this.LP;
    }
    
    public String getPlayerId() {
        return this.Ie;
    }
    
    public Result getScoreResult(final int n) {
        return this.Mt.get(n);
    }
    
    @Override
    public String toString() {
        final fo.a a = fo.e(this).a("PlayerId", this.Ie).a("StatusCode", this.Ah);
        for (int i = 0; i < 3; ++i) {
            final Result result = this.Mt.get(i);
            a.a("TimesSpan", TimeSpan.bd(i));
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
            return fo.e(this).a("RawScore", this.rawScore).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", this.newBest).toString();
        }
    }
}

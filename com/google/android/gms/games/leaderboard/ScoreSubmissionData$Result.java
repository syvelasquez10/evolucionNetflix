// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.m;

public final class ScoreSubmissionData$Result
{
    public final String formattedScore;
    public final boolean newBest;
    public final long rawScore;
    public final String scoreTag;
    
    public ScoreSubmissionData$Result(final long rawScore, final String formattedScore, final String scoreTag, final boolean newBest) {
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

// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.data;

import android.net.Uri;

public final class GamesDataChangeUris
{
    private static final Uri Lq;
    public static final Uri Lr;
    public static final Uri Ls;
    
    static {
        Lq = Uri.parse("content://com.google.android.gms.games/").buildUpon().appendPath("data_change").build();
        Lr = GamesDataChangeUris.Lq.buildUpon().appendPath("invitations").build();
        Ls = GamesDataChangeUris.Lq.buildUpon().appendEncodedPath("players").build();
    }
}

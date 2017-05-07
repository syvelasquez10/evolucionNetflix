// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.data;

import android.net.Uri;

public final class GamesDataChangeUris
{
    private static final Uri aac;
    public static final Uri aad;
    public static final Uri aae;
    
    static {
        aac = Uri.parse("content://com.google.android.gms.games/").buildUpon().appendPath("data_change").build();
        aad = GamesDataChangeUris.aac.buildUpon().appendPath("invitations").build();
        aae = GamesDataChangeUris.aac.buildUpon().appendEncodedPath("players").build();
    }
}

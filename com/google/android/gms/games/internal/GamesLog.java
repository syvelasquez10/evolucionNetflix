// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.h;

public final class GamesLog
{
    private static final h OS;
    
    static {
        OS = new h("Games");
    }
    
    public static void a(final String s, final String s2, final Throwable t) {
        GamesLog.OS.a(s, s2, t);
    }
    
    public static void b(final String s, final String s2, final Throwable t) {
        GamesLog.OS.b(s, s2, t);
    }
    
    public static void c(final String s, final String s2, final Throwable t) {
        GamesLog.OS.c(s, s2, t);
    }
    
    public static void o(final String s, final String s2) {
        GamesLog.OS.o(s, s2);
    }
    
    public static void p(final String s, final String s2) {
        GamesLog.OS.p(s, s2);
    }
    
    public static void q(final String s, final String s2) {
        GamesLog.OS.q(s, s2);
    }
}

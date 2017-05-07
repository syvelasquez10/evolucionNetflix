// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.internal.fj;

public final class GamesLog
{
    private static final fj JH;
    
    static {
        JH = new fj("Games");
    }
    
    public static void a(final String s, final String s2, final Throwable t) {
        GamesLog.JH.a(s, s2, t);
    }
    
    public static void f(final String s, final String s2) {
        GamesLog.JH.f(s, s2);
    }
    
    public static void g(final String s, final String s2) {
        GamesLog.JH.g(s, s2);
    }
    
    public static void h(final String s, final String s2) {
        GamesLog.JH.h(s, s2);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import android.content.Context;

public class ae implements ad
{
    private final dz lC;
    
    public ae(final Context context, final dx dx) {
        this.lC = dz.a(context, new ak(), false, false, null, dx);
    }
    
    @Override
    public void a(final a a) {
        this.lC.bI().a((ea.a)new ea.a() {
            @Override
            public void a(final dz dz) {
                a.ay();
            }
        });
    }
    
    @Override
    public void a(final String s, final bb bb) {
        this.lC.bI().a(s, bb);
    }
    
    @Override
    public void a(final String s, final JSONObject jsonObject) {
        this.lC.a(s, jsonObject);
    }
    
    @Override
    public void d(final String s) {
        this.lC.loadUrl(s);
    }
    
    @Override
    public void e(final String s) {
        this.lC.bI().a(s, null);
    }
}

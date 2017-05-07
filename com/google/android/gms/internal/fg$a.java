// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.content.Context;

@ez
public final class fg$a extends fg
{
    private final Context mContext;
    
    public fg$a(final Context mContext, final fi fi, final ff$a ff$a) {
        super(fi, ff$a);
        this.mContext = mContext;
    }
    
    @Override
    public void cD() {
    }
    
    @Override
    public fm cE() {
        final Bundle bd = gb.bD();
        return fr.a(this.mContext, new bm(bd.getString("gads:sdk_core_location"), bd.getString("gads:sdk_core_experiment_id"), bd.getString("gads:block_autoclicks_experiment_id")), new cj(), new fy());
    }
}

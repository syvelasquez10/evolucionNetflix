// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.common.internal.n;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.fitness.data.DataSource;

public class ViewDataIntentBuilder
{
    private long KL;
    private DataSource Sh;
    private long Si;
    private String Sj;
    private final Context mContext;
    
    public ViewDataIntentBuilder(final Context mContext) {
        this.mContext = mContext;
    }
    
    private Intent i(final Intent intent) {
        boolean b = false;
        if (this.Sj != null) {
            final Intent setPackage = new Intent(intent).setPackage(this.Sj);
            if (this.mContext.getPackageManager().resolveActivity(setPackage, 0) != null) {
                b = true;
            }
            if (b) {
                return setPackage;
            }
        }
        return intent;
    }
    
    public Intent build() {
        final boolean b = true;
        n.a(this.Sh != null, (Object)"Data source must be set");
        n.a(this.KL > 0L, (Object)"Start time must be set");
        n.a(this.Si > this.KL && b, (Object)"End time must be set and after start time");
        final Intent intent = new Intent("vnd.google.fitness.VIEW");
        intent.setType(FitnessIntents.getDataTypeMimeType(this.Sh.getDataType()));
        intent.putExtra("vnd.google.fitness.start_time", this.KL);
        intent.putExtra("vnd.google.fitness.end_time", this.Si);
        c.a(this.Sh, intent, "vnd.google.fitness.data_source");
        return this.i(intent);
    }
    
    public ViewDataIntentBuilder setDataSource(final DataSource sh) {
        this.Sh = sh;
        return this;
    }
    
    public ViewDataIntentBuilder setPreferredApplication(final String sj) {
        this.Sj = sj;
        return this;
    }
    
    public ViewDataIntentBuilder setTimeInterval(final long kl, final long si) {
        this.KL = kl;
        this.Si = si;
        return this;
    }
}

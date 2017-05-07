// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;

@ez
class gv$a extends MutableContextWrapper
{
    private Context mD;
    private Activity wO;
    
    public gv$a(final Context baseContext) {
        super(baseContext);
        this.setBaseContext(baseContext);
    }
    
    public Context dA() {
        return (Context)this.wO;
    }
    
    public void setBaseContext(final Context context) {
        this.mD = context.getApplicationContext();
        Activity wo;
        if (context instanceof Activity) {
            wo = (Activity)context;
        }
        else {
            wo = null;
        }
        this.wO = wo;
        super.setBaseContext(this.mD);
    }
    
    public void startActivity(final Intent intent) {
        if (this.wO != null) {
            this.wO.startActivity(intent);
            return;
        }
        intent.setFlags(268435456);
        this.mD.startActivity(intent);
    }
}

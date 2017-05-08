// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.app.Service;
import android.app.Activity;
import android.content.Context;
import android.app.Application;
import android.content.ContextWrapper;

public final class at extends ContextWrapper
{
    Application a;
    private int b;
    
    public at(final Context context) {
        super(context.getApplicationContext());
        this.a = null;
        this.b = 0;
        this.b = at$a.a(context);
        final int b = this.b;
        Application a;
        if (b == at$a.a) {
            a = (Application)context;
        }
        else if (b == at$a.b) {
            a = ((Activity)context).getApplication();
        }
        else if (b == at$a.c) {
            a = ((Service)context).getApplication();
        }
        else {
            context.getApplicationContext();
            if (context instanceof Application) {
                a = (Application)context;
            }
            else {
                a = null;
            }
        }
        this.a = a;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.database.Cursor;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;

class cb$a implements db$a
{
    final /* synthetic */ cb apO;
    
    cb$a(final cb apO) {
        this.apO = apO;
    }
    
    @Override
    public void a(final ap ap) {
        this.apO.y(ap.eH());
    }
    
    @Override
    public void b(final ap ap) {
        this.apO.y(ap.eH());
        bh.V("Permanent failure dispatching hitId: " + ap.eH());
    }
    
    @Override
    public void c(final ap ap) {
        final long or = ap.or();
        if (or == 0L) {
            this.apO.c(ap.eH(), this.apO.yD.currentTimeMillis());
        }
        else if (or + 14400000L < this.apO.yD.currentTimeMillis()) {
            this.apO.y(ap.eH());
            bh.V("Giving up on failed hitId: " + ap.eH());
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Iterator;
import android.content.ContentValues;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.util.concurrent.Executors;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import java.util.concurrent.Executor;
import android.content.Context;

class zzw$3 implements Runnable
{
    final /* synthetic */ zzw zzaPP;
    final /* synthetic */ String zzaPR;
    
    zzw$3(final zzw zzaPP, final String zzaPR) {
        this.zzaPP = zzaPP;
        this.zzaPR = zzaPR;
    }
    
    @Override
    public void run() {
        this.zzaPP.zzeF(this.zzaPR);
    }
}

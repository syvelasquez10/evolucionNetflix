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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.util.concurrent.Executors;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import java.util.concurrent.Executor;
import android.content.Context;
import java.util.List;

class zzw$1 implements Runnable
{
    final /* synthetic */ List zzaPN;
    final /* synthetic */ long zzaPO;
    final /* synthetic */ zzw zzaPP;
    
    zzw$1(final zzw zzaPP, final List zzaPN, final long zzaPO) {
        this.zzaPP = zzaPP;
        this.zzaPN = zzaPN;
        this.zzaPO = zzaPO;
    }
    
    @Override
    public void run() {
        this.zzaPP.zzb(this.zzaPN, this.zzaPO);
    }
}

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

class zzw$2 implements Runnable
{
    final /* synthetic */ zzw zzaPP;
    final /* synthetic */ DataLayer$zzc$zza zzaPQ;
    
    zzw$2(final zzw zzaPP, final DataLayer$zzc$zza zzaPQ) {
        this.zzaPP = zzaPP;
        this.zzaPQ = zzaPQ;
    }
    
    @Override
    public void run() {
        this.zzaPQ.zzo(this.zzaPP.zzzS());
    }
}

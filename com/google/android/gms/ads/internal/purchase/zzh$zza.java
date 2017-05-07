// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.SystemClock;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import java.util.Locale;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class zzh$zza extends SQLiteOpenHelper
{
    final /* synthetic */ zzh zzCZ;
    
    public zzh$zza(final zzh zzCZ, final Context context, final String s) {
        this.zzCZ = zzCZ;
        super(context, s, (SQLiteDatabase$CursorFactory)null, 4);
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(zzh.zzCW);
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        zzb.zzaG("Database updated from version " + n + " to version " + n2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
        this.onCreate(sqLiteDatabase);
    }
}

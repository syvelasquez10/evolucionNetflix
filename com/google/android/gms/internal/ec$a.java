// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.database.sqlite.SQLiteException;
import java.util.List;
import android.os.SystemClock;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class ec$a extends SQLiteOpenHelper
{
    final /* synthetic */ ec sJ;
    
    public ec$a(final ec sj, final Context context, final String s) {
        this.sJ = sj;
        super(context, s, (SQLiteDatabase$CursorFactory)null, 4);
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ec.sG);
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        gs.U("Database updated from version " + n + " to version " + n2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
        this.onCreate(sqLiteDatabase);
    }
}

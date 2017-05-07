// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.ArrayList;
import java.net.URISyntaxException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import java.net.URI;
import java.util.HashMap;
import android.text.TextUtils;
import android.content.ContentValues;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri$Builder;
import java.io.Closeable;
import android.database.Cursor;
import android.os.Build$VERSION;
import java.util.HashSet;
import java.util.Set;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

class zzj$zza extends SQLiteOpenHelper
{
    final /* synthetic */ zzj zzMl;
    
    zzj$zza(final zzj zzMl, final Context context, final String s) {
        this.zzMl = zzMl;
        super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
    }
    
    private void zza(final SQLiteDatabase sqLiteDatabase) {
        final boolean b = true;
        final Set<String> zzb = this.zzb(sqLiteDatabase, "hits2");
        final String[] array = { "hit_id", "hit_string", "hit_time", "hit_url" };
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (!zzb.remove(s)) {
                throw new SQLiteException("Database hits2 is missing required column: " + s);
            }
        }
        final boolean b2 = !zzb.remove("hit_app_id") && b;
        if (!zzb.isEmpty()) {
            throw new SQLiteException("Database hits2 has extra columns");
        }
        if (b2) {
            sqLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
        }
    }
    
    private boolean zza(final SQLiteDatabase p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aload_1        
        //     4: ldc             "SQLITE_MASTER"
        //     6: iconst_1       
        //     7: anewarray       Ljava/lang/String;
        //    10: dup            
        //    11: iconst_0       
        //    12: ldc             "name"
        //    14: aastore        
        //    15: ldc             "name=?"
        //    17: iconst_1       
        //    18: anewarray       Ljava/lang/String;
        //    21: dup            
        //    22: iconst_0       
        //    23: aload_2        
        //    24: aastore        
        //    25: aconst_null    
        //    26: aconst_null    
        //    27: aconst_null    
        //    28: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    31: astore_1       
        //    32: aload_1        
        //    33: astore          4
        //    35: aload           4
        //    37: astore_1       
        //    38: aload           4
        //    40: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    45: istore_3       
        //    46: aload           4
        //    48: ifnull          58
        //    51: aload           4
        //    53: invokeinterface android/database/Cursor.close:()V
        //    58: iload_3        
        //    59: ireturn        
        //    60: astore          5
        //    62: aconst_null    
        //    63: astore          4
        //    65: aload           4
        //    67: astore_1       
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/analytics/internal/zzj$zza.zzMl:Lcom/google/android/gms/analytics/internal/zzj;
        //    72: ldc             "Error querying for table"
        //    74: aload_2        
        //    75: aload           5
        //    77: invokevirtual   com/google/android/gms/analytics/internal/zzj.zzc:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    80: aload           4
        //    82: ifnull          92
        //    85: aload           4
        //    87: invokeinterface android/database/Cursor.close:()V
        //    92: iconst_0       
        //    93: ireturn        
        //    94: astore_1       
        //    95: aload           4
        //    97: astore_2       
        //    98: aload_2        
        //    99: ifnull          108
        //   102: aload_2        
        //   103: invokeinterface android/database/Cursor.close:()V
        //   108: aload_1        
        //   109: athrow         
        //   110: astore          4
        //   112: aload_1        
        //   113: astore_2       
        //   114: aload           4
        //   116: astore_1       
        //   117: goto            98
        //   120: astore          5
        //   122: goto            65
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  3      32     60     65     Landroid/database/sqlite/SQLiteException;
        //  3      32     94     98     Any
        //  38     46     120    125    Landroid/database/sqlite/SQLiteException;
        //  38     46     110    120    Any
        //  68     80     110    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private Set<String> zzb(SQLiteDatabase rawQuery, final String s) {
        final HashSet<String> set = new HashSet<String>();
        rawQuery = (SQLiteDatabase)rawQuery.rawQuery("SELECT * FROM " + s + " LIMIT 0", (String[])null);
        try {
            final String[] columnNames = ((Cursor)rawQuery).getColumnNames();
            for (int i = 0; i < columnNames.length; ++i) {
                set.add(columnNames[i]);
            }
            return set;
        }
        finally {
            ((Cursor)rawQuery).close();
        }
    }
    
    private void zzb(final SQLiteDatabase sqLiteDatabase) {
        int i = 0;
        final Set<String> zzb = this.zzb(sqLiteDatabase, "properties");
        for (String[] array = { "app_uid", "cid", "tid", "params", "adid", "hits_count" }; i < array.length; ++i) {
            final String s = array[i];
            if (!zzb.remove(s)) {
                throw new SQLiteException("Database properties is missing required column: " + s);
            }
        }
        if (!zzb.isEmpty()) {
            throw new SQLiteException("Database properties table has extra columns");
        }
    }
    
    public SQLiteDatabase getWritableDatabase() {
        if (!this.zzMl.zzMk.zzv(3600000L)) {
            throw new SQLiteException("Database open failed");
        }
        try {
            return super.getWritableDatabase();
        }
        catch (SQLiteException ex2) {
            this.zzMl.zzMk.start();
            this.zzMl.zzbc("Opening the database failed, dropping the table and recreating it");
            this.zzMl.getContext().getDatabasePath(this.zzMl.zziJ()).delete();
            try {
                final SQLiteDatabase writableDatabase = super.getWritableDatabase();
                this.zzMl.zzMk.clear();
                return writableDatabase;
            }
            catch (SQLiteException ex) {
                this.zzMl.zze("Failed to open freshly created database", ex);
                throw ex;
            }
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        zzx.zzbj(sqLiteDatabase.getPath());
    }
    
    public void onOpen(final SQLiteDatabase sqLiteDatabase) {
        while (true) {
            if (Build$VERSION.SDK_INT < 15) {
                while (true) {
                    final Cursor rawQuery = sqLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[])null);
                    while (true) {
                        try {
                            rawQuery.moveToFirst();
                            rawQuery.close();
                            if (!this.zza(sqLiteDatabase, "hits2")) {
                                sqLiteDatabase.execSQL(zzj.zzMg);
                                if (!this.zza(sqLiteDatabase, "properties")) {
                                    sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
                                    return;
                                }
                                break;
                            }
                        }
                        finally {
                            rawQuery.close();
                        }
                        this.zza(sqLiteDatabase);
                        continue;
                    }
                }
                this.zzb(sqLiteDatabase);
                return;
            }
            continue;
        }
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
    }
}

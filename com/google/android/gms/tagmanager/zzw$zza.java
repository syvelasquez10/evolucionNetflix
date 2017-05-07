// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Iterator;
import android.content.ContentValues;
import java.util.List;
import java.util.concurrent.Executors;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import java.util.concurrent.Executor;
import android.database.Cursor;
import android.os.Build$VERSION;
import java.util.Set;
import android.database.sqlite.SQLiteException;
import java.util.HashSet;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

class zzw$zza extends SQLiteOpenHelper
{
    final /* synthetic */ zzw zzaPP;
    
    zzw$zza(final zzw zzaPP, final Context context, final String s) {
        this.zzaPP = zzaPP;
        super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
    }
    
    private boolean zza(final String p0, final SQLiteDatabase p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aload_2        
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
        //    23: aload_1        
        //    24: aastore        
        //    25: aconst_null    
        //    26: aconst_null    
        //    27: aconst_null    
        //    28: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    31: astore_2       
        //    32: aload_2        
        //    33: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    38: istore_3       
        //    39: aload_2        
        //    40: ifnull          49
        //    43: aload_2        
        //    44: invokeinterface android/database/Cursor.close:()V
        //    49: iload_3        
        //    50: ireturn        
        //    51: astore_2       
        //    52: aconst_null    
        //    53: astore_2       
        //    54: new             Ljava/lang/StringBuilder;
        //    57: dup            
        //    58: invokespecial   java/lang/StringBuilder.<init>:()V
        //    61: ldc             "Error querying for table "
        //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    66: aload_1        
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    73: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaE:(Ljava/lang/String;)V
        //    76: aload_2        
        //    77: ifnull          86
        //    80: aload_2        
        //    81: invokeinterface android/database/Cursor.close:()V
        //    86: iconst_0       
        //    87: ireturn        
        //    88: astore_1       
        //    89: aload           4
        //    91: astore_2       
        //    92: aload_2        
        //    93: ifnull          102
        //    96: aload_2        
        //    97: invokeinterface android/database/Cursor.close:()V
        //   102: aload_1        
        //   103: athrow         
        //   104: astore_1       
        //   105: goto            92
        //   108: astore_1       
        //   109: goto            92
        //   112: astore          4
        //   114: goto            54
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  3      32     51     54     Landroid/database/sqlite/SQLiteException;
        //  3      32     88     92     Any
        //  32     39     112    117    Landroid/database/sqlite/SQLiteException;
        //  32     39     104    108    Any
        //  54     76     108    112    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 64, Size: 64
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private void zzc(SQLiteDatabase rawQuery) {
        rawQuery = (SQLiteDatabase)rawQuery.rawQuery("SELECT * FROM datalayer WHERE 0", (String[])null);
        final HashSet<String> set = new HashSet<String>();
        try {
            final String[] columnNames = ((Cursor)rawQuery).getColumnNames();
            for (int i = 0; i < columnNames.length; ++i) {
                set.add(columnNames[i]);
            }
            ((Cursor)rawQuery).close();
            if (!set.remove("key") || !set.remove("value") || !set.remove("ID") || !set.remove("expires")) {
                throw new SQLiteException("Database column missing");
            }
        }
        finally {
            ((Cursor)rawQuery).close();
        }
        final Set set2;
        if (!set2.isEmpty()) {
            throw new SQLiteException("Database has extra columns");
        }
    }
    
    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase = null;
        while (true) {
            try {
                writableDatabase = super.getWritableDatabase();
                SQLiteDatabase writableDatabase2 = writableDatabase;
                if (writableDatabase == null) {
                    writableDatabase2 = super.getWritableDatabase();
                }
                return writableDatabase2;
            }
            catch (SQLiteException ex) {
                this.zzaPP.mContext.getDatabasePath("google_tagmanager.db").delete();
                continue;
            }
            break;
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        zzal.zzbj(sqLiteDatabase.getPath());
    }
    
    public void onOpen(final SQLiteDatabase sqLiteDatabase) {
        while (true) {
            if (Build$VERSION.SDK_INT < 15) {
                final Cursor rawQuery = sqLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[])null);
                try {
                    rawQuery.moveToFirst();
                    rawQuery.close();
                    if (!this.zza("datalayer", sqLiteDatabase)) {
                        sqLiteDatabase.execSQL(zzw.zzaPJ);
                        return;
                    }
                }
                finally {
                    rawQuery.close();
                }
                final SQLiteDatabase sqLiteDatabase2;
                this.zzc(sqLiteDatabase2);
                return;
            }
            continue;
        }
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
    }
}

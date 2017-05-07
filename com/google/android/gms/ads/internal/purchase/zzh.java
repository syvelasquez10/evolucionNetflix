// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.SystemClock;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.util.Locale;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzh
{
    private static final String zzCW;
    private static zzh zzCY;
    private static final Object zzpd;
    private final zzh$zza zzCX;
    
    static {
        zzCW = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", "InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time");
        zzpd = new Object();
    }
    
    zzh(final Context context) {
        this.zzCX = new zzh$zza(this, context, "google_inapp_purchase.db");
    }
    
    public static zzh zzw(final Context context) {
        synchronized (zzh.zzpd) {
            if (zzh.zzCY == null) {
                zzh.zzCY = new zzh(context);
            }
            return zzh.zzCY;
        }
    }
    
    public int getRecordCount() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore_2       
        //     4: getstatic       com/google/android/gms/ads/internal/purchase/zzh.zzpd:Ljava/lang/Object;
        //     7: astore          5
        //     9: aload           5
        //    11: monitorenter   
        //    12: aload_0        
        //    13: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    16: astore          4
        //    18: aload           4
        //    20: ifnonnull       28
        //    23: aload           5
        //    25: monitorexit    
        //    26: iconst_0       
        //    27: ireturn        
        //    28: aload           4
        //    30: ldc             "select count(*) from InAppPurchase"
        //    32: aconst_null    
        //    33: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    36: astore          4
        //    38: aload           4
        //    40: astore_2       
        //    41: aload           4
        //    43: astore_3       
        //    44: aload           4
        //    46: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    51: ifeq            92
        //    54: aload           4
        //    56: astore_2       
        //    57: aload           4
        //    59: astore_3       
        //    60: aload           4
        //    62: iconst_0       
        //    63: invokeinterface android/database/Cursor.getInt:(I)I
        //    68: istore_1       
        //    69: aload           4
        //    71: ifnull          81
        //    74: aload           4
        //    76: invokeinterface android/database/Cursor.close:()V
        //    81: aload           5
        //    83: monitorexit    
        //    84: iload_1        
        //    85: ireturn        
        //    86: astore_2       
        //    87: aload           5
        //    89: monitorexit    
        //    90: aload_2        
        //    91: athrow         
        //    92: aload           4
        //    94: ifnull          104
        //    97: aload           4
        //    99: invokeinterface android/database/Cursor.close:()V
        //   104: aload           5
        //   106: monitorexit    
        //   107: iconst_0       
        //   108: ireturn        
        //   109: astore          4
        //   111: aload_2        
        //   112: astore_3       
        //   113: new             Ljava/lang/StringBuilder;
        //   116: dup            
        //   117: invokespecial   java/lang/StringBuilder.<init>:()V
        //   120: ldc             "Error getting record count"
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: aload           4
        //   127: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaH:(Ljava/lang/String;)V
        //   139: aload_2        
        //   140: ifnull          104
        //   143: aload_2        
        //   144: invokeinterface android/database/Cursor.close:()V
        //   149: goto            104
        //   152: astore_2       
        //   153: aload_3        
        //   154: ifnull          163
        //   157: aload_3        
        //   158: invokeinterface android/database/Cursor.close:()V
        //   163: aload_2        
        //   164: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  12     18     86     92     Any
        //  23     26     86     92     Any
        //  28     38     109    152    Landroid/database/sqlite/SQLiteException;
        //  28     38     152    165    Any
        //  44     54     109    152    Landroid/database/sqlite/SQLiteException;
        //  44     54     152    165    Any
        //  60     69     109    152    Landroid/database/sqlite/SQLiteException;
        //  60     69     152    165    Any
        //  74     81     86     92     Any
        //  81     84     86     92     Any
        //  87     90     86     92     Any
        //  97     104    86     92     Any
        //  104    107    86     92     Any
        //  113    139    152    165    Any
        //  143    149    86     92     Any
        //  157    163    86     92     Any
        //  163    165    86     92     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    
    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzCX.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            zzb.zzaH("Error opening writable conversion tracking database");
            return null;
        }
    }
    
    public zzf zza(final Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }
    
    public void zza(final zzf zzf) {
        if (zzf == null) {
            return;
        }
        final SQLiteDatabase writableDatabase;
        synchronized (zzh.zzpd) {
            writableDatabase = this.getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
        }
        final zzf zzf2;
        writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", "purchase_id", zzf2.zzCQ), (String[])null);
    }
    // monitorexit(o)
    
    public void zzb(final zzf zzf) {
        if (zzf == null) {
            return;
        }
        final SQLiteDatabase writableDatabase;
        synchronized (zzh.zzpd) {
            writableDatabase = this.getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
        }
        final ContentValues contentValues = new ContentValues();
        final zzf zzf2;
        contentValues.put("product_id", zzf2.zzCS);
        contentValues.put("developer_payload", zzf2.zzCR);
        contentValues.put("record_time", SystemClock.elapsedRealtime());
        zzf2.zzCQ = writableDatabase.insert("InAppPurchase", (String)null, contentValues);
        if (this.getRecordCount() > 20000L) {
            this.zzfo();
        }
    }
    // monitorexit(o)
    
    public void zzfo() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/ads/internal/purchase/zzh.zzpd:Ljava/lang/Object;
        //     3: astore          4
        //     5: aload           4
        //     7: monitorenter   
        //     8: aload_0        
        //     9: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    12: astore_1       
        //    13: aload_1        
        //    14: ifnonnull       21
        //    17: aload           4
        //    19: monitorexit    
        //    20: return         
        //    21: aload_1        
        //    22: ldc             "InAppPurchase"
        //    24: aconst_null    
        //    25: aconst_null    
        //    26: aconst_null    
        //    27: aconst_null    
        //    28: aconst_null    
        //    29: ldc             "record_time ASC"
        //    31: ldc             "1"
        //    33: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    36: astore_2       
        //    37: aload_2        
        //    38: ifnull          63
        //    41: aload_2        
        //    42: astore_1       
        //    43: aload_2        
        //    44: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    49: ifeq            63
        //    52: aload_2        
        //    53: astore_1       
        //    54: aload_0        
        //    55: aload_0        
        //    56: aload_2        
        //    57: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.zza:(Landroid/database/Cursor;)Lcom/google/android/gms/ads/internal/purchase/zzf;
        //    60: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.zza:(Lcom/google/android/gms/ads/internal/purchase/zzf;)V
        //    63: aload_2        
        //    64: ifnull          73
        //    67: aload_2        
        //    68: invokeinterface android/database/Cursor.close:()V
        //    73: aload           4
        //    75: monitorexit    
        //    76: return         
        //    77: astore_1       
        //    78: aload           4
        //    80: monitorexit    
        //    81: aload_1        
        //    82: athrow         
        //    83: astore_3       
        //    84: aconst_null    
        //    85: astore_2       
        //    86: aload_2        
        //    87: astore_1       
        //    88: new             Ljava/lang/StringBuilder;
        //    91: dup            
        //    92: invokespecial   java/lang/StringBuilder.<init>:()V
        //    95: ldc             "Error remove oldest record"
        //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   100: aload_3        
        //   101: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   110: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzaH:(Ljava/lang/String;)V
        //   113: aload_2        
        //   114: ifnull          73
        //   117: aload_2        
        //   118: invokeinterface android/database/Cursor.close:()V
        //   123: goto            73
        //   126: aload_1        
        //   127: ifnull          136
        //   130: aload_1        
        //   131: invokeinterface android/database/Cursor.close:()V
        //   136: aload_2        
        //   137: athrow         
        //   138: astore_2       
        //   139: goto            126
        //   142: astore_3       
        //   143: goto            86
        //   146: astore_2       
        //   147: aconst_null    
        //   148: astore_1       
        //   149: goto            126
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  8      13     77     83     Any
        //  17     20     77     83     Any
        //  21     37     83     86     Landroid/database/sqlite/SQLiteException;
        //  21     37     146    152    Any
        //  43     52     142    146    Landroid/database/sqlite/SQLiteException;
        //  43     52     138    142    Any
        //  54     63     142    146    Landroid/database/sqlite/SQLiteException;
        //  54     63     138    142    Any
        //  67     73     77     83     Any
        //  73     76     77     83     Any
        //  78     81     77     83     Any
        //  88     113    138    142    Any
        //  117    123    77     83     Any
        //  130    136    77     83     Any
        //  136    138    77     83     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 83, Size: 83
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
}

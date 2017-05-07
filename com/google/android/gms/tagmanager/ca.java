// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;
import java.util.Set;
import java.util.HashSet;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.android.gms.internal.gn;
import android.content.Context;
import com.google.android.gms.internal.gl;

class ca implements at
{
    private static final String vx;
    private gl Wv;
    private final b YI;
    private volatile ab YJ;
    private final au YK;
    private final Context mContext;
    private final String vA;
    private long vC;
    private final int vD;
    
    static {
        vx = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    }
    
    ca(final au au, final Context context) {
        this(au, context, "gtm_urls.db", 2000);
    }
    
    ca(final au yk, final Context context, final String va, final int vd) {
        this.mContext = context.getApplicationContext();
        this.vA = va;
        this.YK = yk;
        this.Wv = gn.ft();
        this.YI = new b(this.mContext, this.vA);
        this.YJ = new da((HttpClient)new DefaultHttpClient(), this.mContext, (da.a)new a());
        this.vC = 0L;
        this.vD = vd;
    }
    
    private SQLiteDatabase L(final String s) {
        try {
            return this.YI.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            bh.z(s);
            return null;
        }
    }
    
    private void c(final long n, final long n2) {
        final SQLiteDatabase l = this.L("Error opening database for getNumStoredHits.");
        if (l == null) {
            return;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", n2);
        try {
            l.update("gtm_hits", contentValues, "hit_id=?", new String[] { String.valueOf(n) });
        }
        catch (SQLiteException ex) {
            bh.z("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + n);
            this.v(n);
        }
    }
    
    private void cV() {
        final int n = this.cX() - this.vD + 1;
        if (n > 0) {
            final List<String> s = this.s(n);
            bh.y("Store full, deleting " + s.size() + " hits to make room.");
            this.a(s.toArray(new String[0]));
        }
    }
    
    private void f(final long n, final String s) {
        final SQLiteDatabase l = this.L("Error opening database for putHit");
        if (l == null) {
            return;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_time", n);
        contentValues.put("hit_url", s);
        contentValues.put("hit_first_send_time", 0);
        try {
            l.insert("gtm_hits", (String)null, contentValues);
            this.YK.r(false);
        }
        catch (SQLiteException ex) {
            bh.z("Error storing hit");
        }
    }
    
    private void v(final long n) {
        this.a(new String[] { String.valueOf(n) });
    }
    
    void a(final String[] array) {
        boolean b = true;
        if (array != null && array.length != 0) {
            final SQLiteDatabase l = this.L("Error opening database for deleteHits.");
            if (l != null) {
                while (true) {
                    final String format = String.format("HIT_ID in (%s)", TextUtils.join((CharSequence)",", (Iterable)Collections.nCopies(array.length, "?")));
                    while (true) {
                        try {
                            l.delete("gtm_hits", format, array);
                            final au yk = this.YK;
                            if (this.cX() == 0) {
                                yk.r(b);
                                return;
                            }
                        }
                        catch (SQLiteException ex) {
                            bh.z("Error deleting hits");
                            return;
                        }
                        b = false;
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public void bW() {
        bh.y("GTM Dispatch running...");
        if (this.YJ.ch()) {
            final List<ap> t = this.t(40);
            if (t.isEmpty()) {
                bh.y("...nothing to dispatch");
                this.YK.r(true);
                return;
            }
            this.YJ.d(t);
            if (this.kR() > 0) {
                cx.lG().bW();
            }
        }
    }
    
    int cW() {
        boolean b = true;
        final long currentTimeMillis = this.Wv.currentTimeMillis();
        if (currentTimeMillis > this.vC + 86400000L) {
            this.vC = currentTimeMillis;
            final SQLiteDatabase l = this.L("Error opening database for deleteStaleHits.");
            if (l != null) {
                final int delete = l.delete("gtm_hits", "HIT_TIME < ?", new String[] { Long.toString(this.Wv.currentTimeMillis() - 2592000000L) });
                final au yk = this.YK;
                if (this.cX() != 0) {
                    b = false;
                }
                yk.r(b);
                return delete;
            }
        }
        return 0;
    }
    
    int cX() {
        Cursor cursor = null;
        Cursor rawQuery = null;
        int n = 0;
        int n2 = 0;
        final SQLiteDatabase l = this.L("Error opening database for getNumStoredHits.");
        if (l == null) {
            return n2;
        }
        try {
            final Cursor cursor2 = cursor = (rawQuery = l.rawQuery("SELECT COUNT(*) from gtm_hits", (String[])null));
            if (cursor2.moveToFirst()) {
                rawQuery = cursor2;
                cursor = cursor2;
                n = (int)cursor2.getLong(0);
            }
            n2 = n;
            return n;
        }
        catch (SQLiteException cursor) {
            cursor = rawQuery;
            bh.z("Error getting numStoredHits");
            return 0;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    
    @Override
    public void e(final long n, final String s) {
        this.cW();
        this.cV();
        this.f(n, s);
    }
    
    int kR() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: ldc             "Error opening database for getNumStoredHits."
        //     6: invokespecial   com/google/android/gms/tagmanager/ca.L:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //     9: astore_3       
        //    10: aload_3        
        //    11: ifnonnull       16
        //    14: iconst_0       
        //    15: ireturn        
        //    16: aload_3        
        //    17: ldc             "gtm_hits"
        //    19: iconst_2       
        //    20: anewarray       Ljava/lang/String;
        //    23: dup            
        //    24: iconst_0       
        //    25: ldc             "hit_id"
        //    27: aastore        
        //    28: dup            
        //    29: iconst_1       
        //    30: ldc             "hit_first_send_time"
        //    32: aastore        
        //    33: ldc_w           "hit_first_send_time=0"
        //    36: aconst_null    
        //    37: aconst_null    
        //    38: aconst_null    
        //    39: aconst_null    
        //    40: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    43: astore_3       
        //    44: aload_3        
        //    45: invokeinterface android/database/Cursor.getCount:()I
        //    50: istore_2       
        //    51: iload_2        
        //    52: istore_1       
        //    53: aload_3        
        //    54: ifnull          65
        //    57: aload_3        
        //    58: invokeinterface android/database/Cursor.close:()V
        //    63: iload_2        
        //    64: istore_1       
        //    65: iload_1        
        //    66: ireturn        
        //    67: astore_3       
        //    68: aconst_null    
        //    69: astore_3       
        //    70: ldc_w           "Error getting num untried hits"
        //    73: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //    76: aload_3        
        //    77: ifnull          133
        //    80: aload_3        
        //    81: invokeinterface android/database/Cursor.close:()V
        //    86: iconst_0       
        //    87: istore_1       
        //    88: goto            65
        //    91: astore_3       
        //    92: aload           4
        //    94: ifnull          104
        //    97: aload           4
        //    99: invokeinterface android/database/Cursor.close:()V
        //   104: aload_3        
        //   105: athrow         
        //   106: astore          5
        //   108: aload_3        
        //   109: astore          4
        //   111: aload           5
        //   113: astore_3       
        //   114: goto            92
        //   117: astore          5
        //   119: aload_3        
        //   120: astore          4
        //   122: aload           5
        //   124: astore_3       
        //   125: goto            92
        //   128: astore          4
        //   130: goto            70
        //   133: iconst_0       
        //   134: istore_1       
        //   135: goto            65
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  16     44     67     70     Landroid/database/sqlite/SQLiteException;
        //  16     44     91     92     Any
        //  44     51     128    133    Landroid/database/sqlite/SQLiteException;
        //  44     51     106    117    Any
        //  70     76     117    128    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 78, Size: 78
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
    
    List<String> s(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore          6
        //     9: iload_1        
        //    10: ifgt            22
        //    13: ldc_w           "Invalid maxHits specified. Skipping"
        //    16: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //    19: aload           6
        //    21: areturn        
        //    22: aload_0        
        //    23: ldc_w           "Error opening database for peekHitIds."
        //    26: invokespecial   com/google/android/gms/tagmanager/ca.L:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    29: astore_3       
        //    30: aload_3        
        //    31: ifnonnull       37
        //    34: aload           6
        //    36: areturn        
        //    37: ldc_w           "%s ASC"
        //    40: iconst_1       
        //    41: anewarray       Ljava/lang/Object;
        //    44: dup            
        //    45: iconst_0       
        //    46: ldc             "hit_id"
        //    48: aastore        
        //    49: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    52: astore          4
        //    54: iload_1        
        //    55: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    58: astore          5
        //    60: aload_3        
        //    61: ldc             "gtm_hits"
        //    63: iconst_1       
        //    64: anewarray       Ljava/lang/String;
        //    67: dup            
        //    68: iconst_0       
        //    69: ldc             "hit_id"
        //    71: aastore        
        //    72: aconst_null    
        //    73: aconst_null    
        //    74: aconst_null    
        //    75: aconst_null    
        //    76: aload           4
        //    78: aload           5
        //    80: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    83: astore          4
        //    85: aload           4
        //    87: astore_3       
        //    88: aload           4
        //    90: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    95: ifeq            135
        //    98: aload           4
        //   100: astore_3       
        //   101: aload           6
        //   103: aload           4
        //   105: iconst_0       
        //   106: invokeinterface android/database/Cursor.getLong:(I)J
        //   111: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   114: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   119: pop            
        //   120: aload           4
        //   122: astore_3       
        //   123: aload           4
        //   125: invokeinterface android/database/Cursor.moveToNext:()Z
        //   130: istore_2       
        //   131: iload_2        
        //   132: ifne            98
        //   135: aload           4
        //   137: ifnull          147
        //   140: aload           4
        //   142: invokeinterface android/database/Cursor.close:()V
        //   147: aload           6
        //   149: areturn        
        //   150: astore          5
        //   152: aconst_null    
        //   153: astore          4
        //   155: aload           4
        //   157: astore_3       
        //   158: new             Ljava/lang/StringBuilder;
        //   161: dup            
        //   162: invokespecial   java/lang/StringBuilder.<init>:()V
        //   165: ldc_w           "Error in peekHits fetching hitIds: "
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: aload           5
        //   173: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   182: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   185: aload           4
        //   187: ifnull          147
        //   190: aload           4
        //   192: invokeinterface android/database/Cursor.close:()V
        //   197: goto            147
        //   200: astore          4
        //   202: aconst_null    
        //   203: astore_3       
        //   204: aload_3        
        //   205: ifnull          214
        //   208: aload_3        
        //   209: invokeinterface android/database/Cursor.close:()V
        //   214: aload           4
        //   216: athrow         
        //   217: astore          4
        //   219: goto            204
        //   222: astore          5
        //   224: goto            155
        //    Signature:
        //  (I)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  37     85     150    155    Landroid/database/sqlite/SQLiteException;
        //  37     85     200    204    Any
        //  88     98     222    227    Landroid/database/sqlite/SQLiteException;
        //  88     98     217    222    Any
        //  101    120    222    227    Landroid/database/sqlite/SQLiteException;
        //  101    120    217    222    Any
        //  123    131    222    227    Landroid/database/sqlite/SQLiteException;
        //  123    131    217    222    Any
        //  158    185    217    222    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0098:
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
    
    public List<ap> t(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore          5
        //     9: aload_0        
        //    10: ldc_w           "Error opening database for peekHits"
        //    13: invokespecial   com/google/android/gms/tagmanager/ca.L:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    16: astore          8
        //    18: aload           8
        //    20: ifnonnull       30
        //    23: aload           5
        //    25: astore          6
        //    27: aload           6
        //    29: areturn        
        //    30: aconst_null    
        //    31: astore          4
        //    33: ldc_w           "%s ASC"
        //    36: iconst_1       
        //    37: anewarray       Ljava/lang/Object;
        //    40: dup            
        //    41: iconst_0       
        //    42: ldc             "hit_id"
        //    44: aastore        
        //    45: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    48: astore          6
        //    50: iload_1        
        //    51: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    54: astore          7
        //    56: aload           8
        //    58: ldc             "gtm_hits"
        //    60: iconst_3       
        //    61: anewarray       Ljava/lang/String;
        //    64: dup            
        //    65: iconst_0       
        //    66: ldc             "hit_id"
        //    68: aastore        
        //    69: dup            
        //    70: iconst_1       
        //    71: ldc             "hit_time"
        //    73: aastore        
        //    74: dup            
        //    75: iconst_2       
        //    76: ldc             "hit_first_send_time"
        //    78: aastore        
        //    79: aconst_null    
        //    80: aconst_null    
        //    81: aconst_null    
        //    82: aconst_null    
        //    83: aload           6
        //    85: aload           7
        //    87: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    90: astore          6
        //    92: aload           6
        //    94: astore          4
        //    96: new             Ljava/util/ArrayList;
        //    99: dup            
        //   100: invokespecial   java/util/ArrayList.<init>:()V
        //   103: astore          7
        //   105: aload           4
        //   107: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   112: ifeq            166
        //   115: aload           7
        //   117: new             Lcom/google/android/gms/tagmanager/ap;
        //   120: dup            
        //   121: aload           4
        //   123: iconst_0       
        //   124: invokeinterface android/database/Cursor.getLong:(I)J
        //   129: aload           4
        //   131: iconst_1       
        //   132: invokeinterface android/database/Cursor.getLong:(I)J
        //   137: aload           4
        //   139: iconst_2       
        //   140: invokeinterface android/database/Cursor.getLong:(I)J
        //   145: invokespecial   com/google/android/gms/tagmanager/ap.<init>:(JJJ)V
        //   148: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   153: pop            
        //   154: aload           4
        //   156: invokeinterface android/database/Cursor.moveToNext:()Z
        //   161: istore_3       
        //   162: iload_3        
        //   163: ifne            115
        //   166: aload           4
        //   168: ifnull          178
        //   171: aload           4
        //   173: invokeinterface android/database/Cursor.close:()V
        //   178: aload           4
        //   180: astore          5
        //   182: ldc_w           "%s ASC"
        //   185: iconst_1       
        //   186: anewarray       Ljava/lang/Object;
        //   189: dup            
        //   190: iconst_0       
        //   191: ldc             "hit_id"
        //   193: aastore        
        //   194: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   197: astore          6
        //   199: aload           4
        //   201: astore          5
        //   203: iload_1        
        //   204: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   207: astore          9
        //   209: aload           4
        //   211: astore          5
        //   213: aload           8
        //   215: ldc             "gtm_hits"
        //   217: iconst_2       
        //   218: anewarray       Ljava/lang/String;
        //   221: dup            
        //   222: iconst_0       
        //   223: ldc             "hit_id"
        //   225: aastore        
        //   226: dup            
        //   227: iconst_1       
        //   228: ldc             "hit_url"
        //   230: aastore        
        //   231: aconst_null    
        //   232: aconst_null    
        //   233: aconst_null    
        //   234: aconst_null    
        //   235: aload           6
        //   237: aload           9
        //   239: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   242: astore          6
        //   244: aload           6
        //   246: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   251: ifeq            304
        //   254: iconst_0       
        //   255: istore_1       
        //   256: aload           6
        //   258: checkcast       Landroid/database/sqlite/SQLiteCursor;
        //   261: invokevirtual   android/database/sqlite/SQLiteCursor.getWindow:()Landroid/database/CursorWindow;
        //   264: invokevirtual   android/database/CursorWindow.getNumRows:()I
        //   267: ifle            403
        //   270: aload           7
        //   272: iload_1        
        //   273: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   278: checkcast       Lcom/google/android/gms/tagmanager/ap;
        //   281: aload           6
        //   283: iconst_1       
        //   284: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   289: invokevirtual   com/google/android/gms/tagmanager/ap.K:(Ljava/lang/String;)V
        //   292: aload           6
        //   294: invokeinterface android/database/Cursor.moveToNext:()Z
        //   299: istore_3       
        //   300: iload_3        
        //   301: ifne            676
        //   304: aload           6
        //   306: ifnull          316
        //   309: aload           6
        //   311: invokeinterface android/database/Cursor.close:()V
        //   316: aload           7
        //   318: areturn        
        //   319: astore          6
        //   321: aconst_null    
        //   322: astore          7
        //   324: aload           5
        //   326: astore          4
        //   328: aload           7
        //   330: astore          5
        //   332: new             Ljava/lang/StringBuilder;
        //   335: dup            
        //   336: invokespecial   java/lang/StringBuilder.<init>:()V
        //   339: ldc_w           "Error in peekHits fetching hitIds: "
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: aload           6
        //   347: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   356: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   359: aload           4
        //   361: astore          6
        //   363: aload           5
        //   365: ifnull          27
        //   368: aload           5
        //   370: invokeinterface android/database/Cursor.close:()V
        //   375: aload           4
        //   377: areturn        
        //   378: astore          6
        //   380: aload           4
        //   382: astore          5
        //   384: aload           6
        //   386: astore          4
        //   388: aload           5
        //   390: ifnull          400
        //   393: aload           5
        //   395: invokeinterface android/database/Cursor.close:()V
        //   400: aload           4
        //   402: athrow         
        //   403: ldc_w           "HitString for hitId %d too large.  Hit will be deleted."
        //   406: iconst_1       
        //   407: anewarray       Ljava/lang/Object;
        //   410: dup            
        //   411: iconst_0       
        //   412: aload           7
        //   414: iload_1        
        //   415: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   420: checkcast       Lcom/google/android/gms/tagmanager/ap;
        //   423: invokevirtual   com/google/android/gms/tagmanager/ap.cP:()J
        //   426: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   429: aastore        
        //   430: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   433: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   436: goto            292
        //   439: astore          5
        //   441: aload           6
        //   443: astore          4
        //   445: aload           5
        //   447: astore          6
        //   449: aload           4
        //   451: astore          5
        //   453: new             Ljava/lang/StringBuilder;
        //   456: dup            
        //   457: invokespecial   java/lang/StringBuilder.<init>:()V
        //   460: ldc_w           "Error in peekHits fetching hit url: "
        //   463: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   466: aload           6
        //   468: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   477: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
        //   480: aload           4
        //   482: astore          5
        //   484: new             Ljava/util/ArrayList;
        //   487: dup            
        //   488: invokespecial   java/util/ArrayList.<init>:()V
        //   491: astore          6
        //   493: iconst_0       
        //   494: istore_1       
        //   495: aload           4
        //   497: astore          5
        //   499: aload           7
        //   501: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   506: astore          7
        //   508: aload           4
        //   510: astore          5
        //   512: aload           7
        //   514: invokeinterface java/util/Iterator.hasNext:()Z
        //   519: ifeq            561
        //   522: aload           4
        //   524: astore          5
        //   526: aload           7
        //   528: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   533: checkcast       Lcom/google/android/gms/tagmanager/ap;
        //   536: astore          8
        //   538: aload           4
        //   540: astore          5
        //   542: aload           8
        //   544: invokevirtual   com/google/android/gms/tagmanager/ap.kE:()Ljava/lang/String;
        //   547: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   550: istore_3       
        //   551: iload_1        
        //   552: istore_2       
        //   553: iload_3        
        //   554: ifeq            578
        //   557: iload_1        
        //   558: ifeq            576
        //   561: aload           4
        //   563: ifnull          573
        //   566: aload           4
        //   568: invokeinterface android/database/Cursor.close:()V
        //   573: aload           6
        //   575: areturn        
        //   576: iconst_1       
        //   577: istore_2       
        //   578: aload           4
        //   580: astore          5
        //   582: aload           6
        //   584: aload           8
        //   586: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   591: pop            
        //   592: iload_2        
        //   593: istore_1       
        //   594: goto            508
        //   597: astore          4
        //   599: aload           5
        //   601: ifnull          611
        //   604: aload           5
        //   606: invokeinterface android/database/Cursor.close:()V
        //   611: aload           4
        //   613: athrow         
        //   614: astore          4
        //   616: aload           6
        //   618: astore          5
        //   620: goto            599
        //   623: astore          6
        //   625: goto            449
        //   628: astore          6
        //   630: aload           4
        //   632: astore          5
        //   634: aload           6
        //   636: astore          4
        //   638: goto            388
        //   641: astore          4
        //   643: goto            388
        //   646: astore          6
        //   648: aload           4
        //   650: astore          7
        //   652: aload           5
        //   654: astore          4
        //   656: aload           7
        //   658: astore          5
        //   660: goto            332
        //   663: astore          6
        //   665: aload           4
        //   667: astore          5
        //   669: aload           7
        //   671: astore          4
        //   673: goto            332
        //   676: iload_1        
        //   677: iconst_1       
        //   678: iadd           
        //   679: istore_1       
        //   680: goto            256
        //    Signature:
        //  (I)Ljava/util/List<Lcom/google/android/gms/tagmanager/ap;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  33     92     319    332    Landroid/database/sqlite/SQLiteException;
        //  33     92     378    388    Any
        //  96     105    646    663    Landroid/database/sqlite/SQLiteException;
        //  96     105    628    641    Any
        //  105    115    663    676    Landroid/database/sqlite/SQLiteException;
        //  105    115    628    641    Any
        //  115    162    663    676    Landroid/database/sqlite/SQLiteException;
        //  115    162    628    641    Any
        //  182    199    623    628    Landroid/database/sqlite/SQLiteException;
        //  182    199    597    599    Any
        //  203    209    623    628    Landroid/database/sqlite/SQLiteException;
        //  203    209    597    599    Any
        //  213    244    623    628    Landroid/database/sqlite/SQLiteException;
        //  213    244    597    599    Any
        //  244    254    439    449    Landroid/database/sqlite/SQLiteException;
        //  244    254    614    623    Any
        //  256    292    439    449    Landroid/database/sqlite/SQLiteException;
        //  256    292    614    623    Any
        //  292    300    439    449    Landroid/database/sqlite/SQLiteException;
        //  292    300    614    623    Any
        //  332    359    641    646    Any
        //  403    436    439    449    Landroid/database/sqlite/SQLiteException;
        //  403    436    614    623    Any
        //  453    480    597    599    Any
        //  484    493    597    599    Any
        //  499    508    597    599    Any
        //  512    522    597    599    Any
        //  526    538    597    599    Any
        //  542    551    597    599    Any
        //  582    592    597    599    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    class a implements da.a
    {
        @Override
        public void a(final ap ap) {
            ca.this.v(ap.cP());
        }
        
        @Override
        public void b(final ap ap) {
            ca.this.v(ap.cP());
            bh.y("Permanent failure dispatching hitId: " + ap.cP());
        }
        
        @Override
        public void c(final ap ap) {
            final long kd = ap.kD();
            if (kd == 0L) {
                ca.this.c(ap.cP(), ca.this.Wv.currentTimeMillis());
            }
            else if (kd + 14400000L < ca.this.Wv.currentTimeMillis()) {
                ca.this.v(ap.cP());
                bh.y("Giving up on failed hitId: " + ap.cP());
            }
        }
    }
    
    class b extends SQLiteOpenHelper
    {
        private boolean vF;
        private long vG;
        
        b(final Context context, final String s) {
            super(context, s, (SQLiteDatabase$CursorFactory)null, 1);
            this.vG = 0L;
        }
        
        private void a(SQLiteDatabase rawQuery) {
            rawQuery = (SQLiteDatabase)rawQuery.rawQuery("SELECT * FROM gtm_hits WHERE 0", (String[])null);
            final HashSet<String> set = new HashSet<String>();
            try {
                final String[] columnNames = ((Cursor)rawQuery).getColumnNames();
                for (int i = 0; i < columnNames.length; ++i) {
                    set.add(columnNames[i]);
                }
                ((Cursor)rawQuery).close();
                if (!set.remove("hit_id") || !set.remove("hit_url") || !set.remove("hit_time") || !set.remove("hit_first_send_time")) {
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
        
        private boolean a(final String p0, final SQLiteDatabase p1) {
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
            //    73: invokestatic    com/google/android/gms/tagmanager/bh.z:(Ljava/lang/String;)V
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            if (this.vF && this.vG + 3600000L > ca.this.Wv.currentTimeMillis()) {
                throw new SQLiteException("Database creation failed");
            }
            SQLiteDatabase writableDatabase = null;
            this.vF = true;
            this.vG = ca.this.Wv.currentTimeMillis();
            while (true) {
                try {
                    writableDatabase = super.getWritableDatabase();
                    SQLiteDatabase writableDatabase2 = writableDatabase;
                    if (writableDatabase == null) {
                        writableDatabase2 = super.getWritableDatabase();
                    }
                    this.vF = false;
                    return writableDatabase2;
                }
                catch (SQLiteException ex) {
                    ca.this.mContext.getDatabasePath(ca.this.vA).delete();
                    continue;
                }
                break;
            }
        }
        
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            ak.G(sqLiteDatabase.getPath());
        }
        
        public void onOpen(final SQLiteDatabase sqLiteDatabase) {
            while (true) {
                if (Build$VERSION.SDK_INT < 15) {
                    final Cursor rawQuery = sqLiteDatabase.rawQuery("PRAGMA journal_mode=memory", (String[])null);
                    try {
                        rawQuery.moveToFirst();
                        rawQuery.close();
                        if (!this.a("gtm_hits", sqLiteDatabase)) {
                            sqLiteDatabase.execSQL(ca.vx);
                            return;
                        }
                    }
                    finally {
                        rawQuery.close();
                    }
                    final SQLiteDatabase sqLiteDatabase2;
                    this.a(sqLiteDatabase2);
                    return;
                }
                continue;
            }
        }
        
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        }
    }
}

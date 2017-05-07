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

class cb implements at
{
    private static final String AY;
    private final String Bb;
    private long Bd;
    private final int Be;
    private final cb$b apL;
    private volatile ab apM;
    private final au apN;
    private final Context mContext;
    private ju yD;
    
    static {
        AY = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    }
    
    cb(final au au, final Context context) {
        this(au, context, "gtm_urls.db", 2000);
    }
    
    cb(final au apN, final Context context, final String bb, final int be) {
        this.mContext = context.getApplicationContext();
        this.Bb = bb;
        this.apN = apN;
        this.yD = jw.hA();
        this.apL = new cb$b(this, this.mContext, this.Bb);
        this.apM = new db((HttpClient)new DefaultHttpClient(), this.mContext, new cb$a(this));
        this.Bd = 0L;
        this.Be = be;
    }
    
    private SQLiteDatabase al(final String s) {
        try {
            return this.apL.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            bh.W(s);
            return null;
        }
    }
    
    private void c(final long n, final long n2) {
        final SQLiteDatabase al = this.al("Error opening database for getNumStoredHits.");
        if (al == null) {
            return;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", n2);
        try {
            al.update("gtm_hits", contentValues, "hit_id=?", new String[] { String.valueOf(n) });
        }
        catch (SQLiteException ex) {
            bh.W("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + n);
            this.y(n);
        }
    }
    
    private void eN() {
        final int n = this.eP() - this.Be + 1;
        if (n > 0) {
            final List<String> f = this.F(n);
            bh.V("Store full, deleting " + f.size() + " hits to make room.");
            this.b(f.toArray(new String[0]));
        }
    }
    
    private void g(final long n, final String s) {
        final SQLiteDatabase al = this.al("Error opening database for putHit");
        if (al == null) {
            return;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("hit_time", n);
        contentValues.put("hit_url", s);
        contentValues.put("hit_first_send_time", 0);
        try {
            al.insert("gtm_hits", (String)null, contentValues);
            this.apN.z(false);
        }
        catch (SQLiteException ex) {
            bh.W("Error storing hit");
        }
    }
    
    private void y(final long n) {
        this.b(new String[] { String.valueOf(n) });
    }
    
    List<String> F(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore          6
        //     9: iload_1        
        //    10: ifgt            21
        //    13: ldc             "Invalid maxHits specified. Skipping"
        //    15: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //    18: aload           6
        //    20: areturn        
        //    21: aload_0        
        //    22: ldc             "Error opening database for peekHitIds."
        //    24: invokespecial   com/google/android/gms/tagmanager/cb.al:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    27: astore_3       
        //    28: aload_3        
        //    29: ifnonnull       35
        //    32: aload           6
        //    34: areturn        
        //    35: ldc             "%s ASC"
        //    37: iconst_1       
        //    38: anewarray       Ljava/lang/Object;
        //    41: dup            
        //    42: iconst_0       
        //    43: ldc             "hit_id"
        //    45: aastore        
        //    46: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    49: astore          4
        //    51: iload_1        
        //    52: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    55: astore          5
        //    57: aload_3        
        //    58: ldc             "gtm_hits"
        //    60: iconst_1       
        //    61: anewarray       Ljava/lang/String;
        //    64: dup            
        //    65: iconst_0       
        //    66: ldc             "hit_id"
        //    68: aastore        
        //    69: aconst_null    
        //    70: aconst_null    
        //    71: aconst_null    
        //    72: aconst_null    
        //    73: aload           4
        //    75: aload           5
        //    77: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    80: astore          4
        //    82: aload           4
        //    84: astore_3       
        //    85: aload           4
        //    87: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    92: ifeq            132
        //    95: aload           4
        //    97: astore_3       
        //    98: aload           6
        //   100: aload           4
        //   102: iconst_0       
        //   103: invokeinterface android/database/Cursor.getLong:(I)J
        //   108: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   111: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   116: pop            
        //   117: aload           4
        //   119: astore_3       
        //   120: aload           4
        //   122: invokeinterface android/database/Cursor.moveToNext:()Z
        //   127: istore_2       
        //   128: iload_2        
        //   129: ifne            95
        //   132: aload           4
        //   134: ifnull          144
        //   137: aload           4
        //   139: invokeinterface android/database/Cursor.close:()V
        //   144: aload           6
        //   146: areturn        
        //   147: astore          5
        //   149: aconst_null    
        //   150: astore          4
        //   152: aload           4
        //   154: astore_3       
        //   155: new             Ljava/lang/StringBuilder;
        //   158: dup            
        //   159: invokespecial   java/lang/StringBuilder.<init>:()V
        //   162: ldc_w           "Error in peekHits fetching hitIds: "
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload           5
        //   170: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   179: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   182: aload           4
        //   184: ifnull          144
        //   187: aload           4
        //   189: invokeinterface android/database/Cursor.close:()V
        //   194: goto            144
        //   197: astore          4
        //   199: aconst_null    
        //   200: astore_3       
        //   201: aload_3        
        //   202: ifnull          211
        //   205: aload_3        
        //   206: invokeinterface android/database/Cursor.close:()V
        //   211: aload           4
        //   213: athrow         
        //   214: astore          4
        //   216: goto            201
        //   219: astore          5
        //   221: goto            152
        //    Signature:
        //  (I)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  35     82     147    152    Landroid/database/sqlite/SQLiteException;
        //  35     82     197    201    Any
        //  85     95     219    224    Landroid/database/sqlite/SQLiteException;
        //  85     95     214    219    Any
        //  98     117    219    224    Landroid/database/sqlite/SQLiteException;
        //  98     117    214    219    Any
        //  120    128    219    224    Landroid/database/sqlite/SQLiteException;
        //  120    128    214    219    Any
        //  155    182    214    219    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
    
    public List<ap> G(final int p0) {
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
        //    13: invokespecial   com/google/android/gms/tagmanager/cb.al:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
        //    16: astore          8
        //    18: aload           8
        //    20: ifnonnull       30
        //    23: aload           5
        //    25: astore          6
        //    27: aload           6
        //    29: areturn        
        //    30: aconst_null    
        //    31: astore          4
        //    33: ldc             "%s ASC"
        //    35: iconst_1       
        //    36: anewarray       Ljava/lang/Object;
        //    39: dup            
        //    40: iconst_0       
        //    41: ldc             "hit_id"
        //    43: aastore        
        //    44: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    47: astore          6
        //    49: iload_1        
        //    50: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //    53: astore          7
        //    55: aload           8
        //    57: ldc             "gtm_hits"
        //    59: iconst_3       
        //    60: anewarray       Ljava/lang/String;
        //    63: dup            
        //    64: iconst_0       
        //    65: ldc             "hit_id"
        //    67: aastore        
        //    68: dup            
        //    69: iconst_1       
        //    70: ldc             "hit_time"
        //    72: aastore        
        //    73: dup            
        //    74: iconst_2       
        //    75: ldc             "hit_first_send_time"
        //    77: aastore        
        //    78: aconst_null    
        //    79: aconst_null    
        //    80: aconst_null    
        //    81: aconst_null    
        //    82: aload           6
        //    84: aload           7
        //    86: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    89: astore          6
        //    91: aload           6
        //    93: astore          4
        //    95: new             Ljava/util/ArrayList;
        //    98: dup            
        //    99: invokespecial   java/util/ArrayList.<init>:()V
        //   102: astore          7
        //   104: aload           4
        //   106: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   111: ifeq            165
        //   114: aload           7
        //   116: new             Lcom/google/android/gms/tagmanager/ap;
        //   119: dup            
        //   120: aload           4
        //   122: iconst_0       
        //   123: invokeinterface android/database/Cursor.getLong:(I)J
        //   128: aload           4
        //   130: iconst_1       
        //   131: invokeinterface android/database/Cursor.getLong:(I)J
        //   136: aload           4
        //   138: iconst_2       
        //   139: invokeinterface android/database/Cursor.getLong:(I)J
        //   144: invokespecial   com/google/android/gms/tagmanager/ap.<init>:(JJJ)V
        //   147: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   152: pop            
        //   153: aload           4
        //   155: invokeinterface android/database/Cursor.moveToNext:()Z
        //   160: istore_3       
        //   161: iload_3        
        //   162: ifne            114
        //   165: aload           4
        //   167: ifnull          177
        //   170: aload           4
        //   172: invokeinterface android/database/Cursor.close:()V
        //   177: aload           4
        //   179: astore          5
        //   181: ldc             "%s ASC"
        //   183: iconst_1       
        //   184: anewarray       Ljava/lang/Object;
        //   187: dup            
        //   188: iconst_0       
        //   189: ldc             "hit_id"
        //   191: aastore        
        //   192: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   195: astore          6
        //   197: aload           4
        //   199: astore          5
        //   201: iload_1        
        //   202: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   205: astore          9
        //   207: aload           4
        //   209: astore          5
        //   211: aload           8
        //   213: ldc             "gtm_hits"
        //   215: iconst_2       
        //   216: anewarray       Ljava/lang/String;
        //   219: dup            
        //   220: iconst_0       
        //   221: ldc             "hit_id"
        //   223: aastore        
        //   224: dup            
        //   225: iconst_1       
        //   226: ldc             "hit_url"
        //   228: aastore        
        //   229: aconst_null    
        //   230: aconst_null    
        //   231: aconst_null    
        //   232: aconst_null    
        //   233: aload           6
        //   235: aload           9
        //   237: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   240: astore          6
        //   242: aload           6
        //   244: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   249: ifeq            302
        //   252: iconst_0       
        //   253: istore_1       
        //   254: aload           6
        //   256: checkcast       Landroid/database/sqlite/SQLiteCursor;
        //   259: invokevirtual   android/database/sqlite/SQLiteCursor.getWindow:()Landroid/database/CursorWindow;
        //   262: invokevirtual   android/database/CursorWindow.getNumRows:()I
        //   265: ifle            401
        //   268: aload           7
        //   270: iload_1        
        //   271: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   276: checkcast       Lcom/google/android/gms/tagmanager/ap;
        //   279: aload           6
        //   281: iconst_1       
        //   282: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   287: invokevirtual   com/google/android/gms/tagmanager/ap.ak:(Ljava/lang/String;)V
        //   290: aload           6
        //   292: invokeinterface android/database/Cursor.moveToNext:()Z
        //   297: istore_3       
        //   298: iload_3        
        //   299: ifne            674
        //   302: aload           6
        //   304: ifnull          314
        //   307: aload           6
        //   309: invokeinterface android/database/Cursor.close:()V
        //   314: aload           7
        //   316: areturn        
        //   317: astore          6
        //   319: aconst_null    
        //   320: astore          7
        //   322: aload           5
        //   324: astore          4
        //   326: aload           7
        //   328: astore          5
        //   330: new             Ljava/lang/StringBuilder;
        //   333: dup            
        //   334: invokespecial   java/lang/StringBuilder.<init>:()V
        //   337: ldc_w           "Error in peekHits fetching hitIds: "
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: aload           6
        //   345: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   354: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   357: aload           4
        //   359: astore          6
        //   361: aload           5
        //   363: ifnull          27
        //   366: aload           5
        //   368: invokeinterface android/database/Cursor.close:()V
        //   373: aload           4
        //   375: areturn        
        //   376: astore          6
        //   378: aload           4
        //   380: astore          5
        //   382: aload           6
        //   384: astore          4
        //   386: aload           5
        //   388: ifnull          398
        //   391: aload           5
        //   393: invokeinterface android/database/Cursor.close:()V
        //   398: aload           4
        //   400: athrow         
        //   401: ldc_w           "HitString for hitId %d too large.  Hit will be deleted."
        //   404: iconst_1       
        //   405: anewarray       Ljava/lang/Object;
        //   408: dup            
        //   409: iconst_0       
        //   410: aload           7
        //   412: iload_1        
        //   413: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   418: checkcast       Lcom/google/android/gms/tagmanager/ap;
        //   421: invokevirtual   com/google/android/gms/tagmanager/ap.eH:()J
        //   424: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   427: aastore        
        //   428: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   431: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   434: goto            290
        //   437: astore          5
        //   439: aload           6
        //   441: astore          4
        //   443: aload           5
        //   445: astore          6
        //   447: aload           4
        //   449: astore          5
        //   451: new             Ljava/lang/StringBuilder;
        //   454: dup            
        //   455: invokespecial   java/lang/StringBuilder.<init>:()V
        //   458: ldc_w           "Error in peekHits fetching hit url: "
        //   461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   464: aload           6
        //   466: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   469: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   472: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   475: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
        //   478: aload           4
        //   480: astore          5
        //   482: new             Ljava/util/ArrayList;
        //   485: dup            
        //   486: invokespecial   java/util/ArrayList.<init>:()V
        //   489: astore          6
        //   491: iconst_0       
        //   492: istore_1       
        //   493: aload           4
        //   495: astore          5
        //   497: aload           7
        //   499: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   504: astore          7
        //   506: aload           4
        //   508: astore          5
        //   510: aload           7
        //   512: invokeinterface java/util/Iterator.hasNext:()Z
        //   517: ifeq            559
        //   520: aload           4
        //   522: astore          5
        //   524: aload           7
        //   526: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   531: checkcast       Lcom/google/android/gms/tagmanager/ap;
        //   534: astore          8
        //   536: aload           4
        //   538: astore          5
        //   540: aload           8
        //   542: invokevirtual   com/google/android/gms/tagmanager/ap.os:()Ljava/lang/String;
        //   545: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   548: istore_3       
        //   549: iload_1        
        //   550: istore_2       
        //   551: iload_3        
        //   552: ifeq            576
        //   555: iload_1        
        //   556: ifeq            574
        //   559: aload           4
        //   561: ifnull          571
        //   564: aload           4
        //   566: invokeinterface android/database/Cursor.close:()V
        //   571: aload           6
        //   573: areturn        
        //   574: iconst_1       
        //   575: istore_2       
        //   576: aload           4
        //   578: astore          5
        //   580: aload           6
        //   582: aload           8
        //   584: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   589: pop            
        //   590: iload_2        
        //   591: istore_1       
        //   592: goto            506
        //   595: astore          4
        //   597: aload           5
        //   599: ifnull          609
        //   602: aload           5
        //   604: invokeinterface android/database/Cursor.close:()V
        //   609: aload           4
        //   611: athrow         
        //   612: astore          4
        //   614: aload           6
        //   616: astore          5
        //   618: goto            597
        //   621: astore          6
        //   623: goto            447
        //   626: astore          6
        //   628: aload           4
        //   630: astore          5
        //   632: aload           6
        //   634: astore          4
        //   636: goto            386
        //   639: astore          4
        //   641: goto            386
        //   644: astore          6
        //   646: aload           4
        //   648: astore          7
        //   650: aload           5
        //   652: astore          4
        //   654: aload           7
        //   656: astore          5
        //   658: goto            330
        //   661: astore          6
        //   663: aload           4
        //   665: astore          5
        //   667: aload           7
        //   669: astore          4
        //   671: goto            330
        //   674: iload_1        
        //   675: iconst_1       
        //   676: iadd           
        //   677: istore_1       
        //   678: goto            254
        //    Signature:
        //  (I)Ljava/util/List<Lcom/google/android/gms/tagmanager/ap;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  33     91     317    330    Landroid/database/sqlite/SQLiteException;
        //  33     91     376    386    Any
        //  95     104    644    661    Landroid/database/sqlite/SQLiteException;
        //  95     104    626    639    Any
        //  104    114    661    674    Landroid/database/sqlite/SQLiteException;
        //  104    114    626    639    Any
        //  114    161    661    674    Landroid/database/sqlite/SQLiteException;
        //  114    161    626    639    Any
        //  181    197    621    626    Landroid/database/sqlite/SQLiteException;
        //  181    197    595    597    Any
        //  201    207    621    626    Landroid/database/sqlite/SQLiteException;
        //  201    207    595    597    Any
        //  211    242    621    626    Landroid/database/sqlite/SQLiteException;
        //  211    242    595    597    Any
        //  242    252    437    447    Landroid/database/sqlite/SQLiteException;
        //  242    252    612    621    Any
        //  254    290    437    447    Landroid/database/sqlite/SQLiteException;
        //  254    290    612    621    Any
        //  290    298    437    447    Landroid/database/sqlite/SQLiteException;
        //  290    298    612    621    Any
        //  330    357    639    644    Any
        //  401    434    437    447    Landroid/database/sqlite/SQLiteException;
        //  401    434    612    621    Any
        //  451    478    595    597    Any
        //  482    491    595    597    Any
        //  497    506    595    597    Any
        //  510    520    595    597    Any
        //  524    536    595    597    Any
        //  540    549    595    597    Any
        //  580    590    595    597    Any
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
    
    void b(final String[] array) {
        boolean b = true;
        if (array != null && array.length != 0) {
            final SQLiteDatabase al = this.al("Error opening database for deleteHits.");
            if (al != null) {
                while (true) {
                    final String format = String.format("HIT_ID in (%s)", TextUtils.join((CharSequence)",", (Iterable)Collections.nCopies(array.length, "?")));
                    while (true) {
                        try {
                            al.delete("gtm_hits", format, array);
                            final au apN = this.apN;
                            if (this.eP() == 0) {
                                apN.z(b);
                                return;
                            }
                        }
                        catch (SQLiteException ex) {
                            bh.W("Error deleting hits");
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
    public void dispatch() {
        bh.V("GTM Dispatch running...");
        if (this.apM.dY()) {
            final List<ap> g = this.G(40);
            if (g.isEmpty()) {
                bh.V("...nothing to dispatch");
                this.apN.z(true);
                return;
            }
            this.apM.j(g);
            if (this.oF() > 0) {
                cy.pu().dispatch();
            }
        }
    }
    
    int eO() {
        boolean b = true;
        final long currentTimeMillis = this.yD.currentTimeMillis();
        if (currentTimeMillis > this.Bd + 86400000L) {
            this.Bd = currentTimeMillis;
            final SQLiteDatabase al = this.al("Error opening database for deleteStaleHits.");
            if (al != null) {
                final int delete = al.delete("gtm_hits", "HIT_TIME < ?", new String[] { Long.toString(this.yD.currentTimeMillis() - 2592000000L) });
                final au apN = this.apN;
                if (this.eP() != 0) {
                    b = false;
                }
                apN.z(b);
                return delete;
            }
        }
        return 0;
    }
    
    int eP() {
        Cursor cursor = null;
        Cursor rawQuery = null;
        int n = 0;
        int n2 = 0;
        final SQLiteDatabase al = this.al("Error opening database for getNumStoredHits.");
        if (al == null) {
            return n2;
        }
        try {
            final Cursor cursor2 = cursor = (rawQuery = al.rawQuery("SELECT COUNT(*) from gtm_hits", (String[])null));
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
            bh.W("Error getting numStoredHits");
            return 0;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    
    @Override
    public void f(final long n, final String s) {
        this.eO();
        this.eN();
        this.g(n, s);
    }
    
    int oF() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: ldc             "Error opening database for getNumStoredHits."
        //     6: invokespecial   com/google/android/gms/tagmanager/cb.al:(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
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
        //    73: invokestatic    com/google/android/gms/tagmanager/bh.W:(Ljava/lang/String;)V
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
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import com.facebook.common.logging.FLog;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class ReactDatabaseSupplier extends SQLiteOpenHelper
{
    private static ReactDatabaseSupplier sReactDatabaseSupplierInstance;
    private Context mContext;
    private SQLiteDatabase mDb;
    private long mMaximumDatabaseSize;
    
    private ReactDatabaseSupplier(final Context mContext) {
        super(mContext, "RKStorage", (SQLiteDatabase$CursorFactory)null, 1);
        this.mMaximumDatabaseSize = 6291456L;
        this.mContext = mContext;
    }
    
    private void closeDatabase() {
        synchronized (this) {
            if (this.mDb != null && this.mDb.isOpen()) {
                this.mDb.close();
                this.mDb = null;
            }
        }
    }
    
    private boolean deleteDatabase() {
        synchronized (this) {
            this.closeDatabase();
            return this.mContext.deleteDatabase("RKStorage");
        }
    }
    
    public static ReactDatabaseSupplier getInstance(final Context context) {
        if (ReactDatabaseSupplier.sReactDatabaseSupplierInstance == null) {
            ReactDatabaseSupplier.sReactDatabaseSupplierInstance = new ReactDatabaseSupplier(context.getApplicationContext());
        }
        return ReactDatabaseSupplier.sReactDatabaseSupplierInstance;
    }
    
    void clear() {
        synchronized (this) {
            this.get().delete("catalystLocalStorage", (String)null, (String[])null);
        }
    }
    
    public void clearAndCloseDatabase() {
        synchronized (this) {
            try {
                this.clear();
                this.closeDatabase();
                FLog.d("React", "Cleaned RKStorage");
                return;
            }
            catch (Exception ex) {
                if (this.deleteDatabase()) {
                    FLog.d("React", "Deleted Local Database RKStorage");
                    return;
                }
            }
        }
        throw new RuntimeException("Clearing and deleting database RKStorage failed");
    }
    
    boolean ensureDatabase() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/facebook/react/modules/storage/ReactDatabaseSupplier.mDb:Landroid/database/sqlite/SQLiteDatabase;
        //     6: ifnull          25
        //     9: aload_0        
        //    10: getfield        com/facebook/react/modules/storage/ReactDatabaseSupplier.mDb:Landroid/database/sqlite/SQLiteDatabase;
        //    13: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //    16: istore_2       
        //    17: iload_2        
        //    18: ifeq            25
        //    21: aload_0        
        //    22: monitorexit    
        //    23: iconst_1       
        //    24: ireturn        
        //    25: aconst_null    
        //    26: astore_3       
        //    27: iconst_0       
        //    28: istore_1       
        //    29: iload_1        
        //    30: iconst_2       
        //    31: if_icmpge       51
        //    34: iload_1        
        //    35: ifle            43
        //    38: aload_0        
        //    39: invokespecial   com/facebook/react/modules/storage/ReactDatabaseSupplier.deleteDatabase:()Z
        //    42: pop            
        //    43: aload_0        
        //    44: aload_0        
        //    45: invokevirtual   com/facebook/react/modules/storage/ReactDatabaseSupplier.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    48: putfield        com/facebook/react/modules/storage/ReactDatabaseSupplier.mDb:Landroid/database/sqlite/SQLiteDatabase;
        //    51: aload_0        
        //    52: getfield        com/facebook/react/modules/storage/ReactDatabaseSupplier.mDb:Landroid/database/sqlite/SQLiteDatabase;
        //    55: ifnonnull       90
        //    58: aload_3        
        //    59: athrow         
        //    60: astore_3       
        //    61: aload_0        
        //    62: monitorexit    
        //    63: aload_3        
        //    64: athrow         
        //    65: astore_3       
        //    66: ldc2_w          30
        //    69: invokestatic    java/lang/Thread.sleep:(J)V
        //    72: iload_1        
        //    73: iconst_1       
        //    74: iadd           
        //    75: istore_1       
        //    76: goto            29
        //    79: astore          4
        //    81: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    84: invokevirtual   java/lang/Thread.interrupt:()V
        //    87: goto            72
        //    90: aload_0        
        //    91: getfield        com/facebook/react/modules/storage/ReactDatabaseSupplier.mDb:Landroid/database/sqlite/SQLiteDatabase;
        //    94: aload_0        
        //    95: getfield        com/facebook/react/modules/storage/ReactDatabaseSupplier.mMaximumDatabaseSize:J
        //    98: invokevirtual   android/database/sqlite/SQLiteDatabase.setMaximumSize:(J)J
        //   101: pop2           
        //   102: goto            21
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  2      17     60     65     Any
        //  38     43     65     90     Landroid/database/sqlite/SQLiteException;
        //  38     43     60     65     Any
        //  43     51     65     90     Landroid/database/sqlite/SQLiteException;
        //  43     51     60     65     Any
        //  51     60     60     65     Any
        //  66     72     79     90     Ljava/lang/InterruptedException;
        //  66     72     60     65     Any
        //  81     87     60     65     Any
        //  90     102    60     65     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    public SQLiteDatabase get() {
        synchronized (this) {
            this.ensureDatabase();
            return this.mDb;
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)");
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        if (n != n2) {
            this.deleteDatabase();
            this.onCreate(sqLiteDatabase);
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.util.Set;
import android.net.Uri;
import android.util.Log;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Handler;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager
{
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock;
    private final HashMap<String, ArrayList<LocalBroadcastManager$ReceiverRecord>> mActions;
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<LocalBroadcastManager$BroadcastRecord> mPendingBroadcasts;
    private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers;
    
    static {
        mLock = new Object();
    }
    
    private LocalBroadcastManager(final Context mAppContext) {
        this.mReceivers = new HashMap<BroadcastReceiver, ArrayList<IntentFilter>>();
        this.mActions = new HashMap<String, ArrayList<LocalBroadcastManager$ReceiverRecord>>();
        this.mPendingBroadcasts = new ArrayList<LocalBroadcastManager$BroadcastRecord>();
        this.mAppContext = mAppContext;
        this.mHandler = new LocalBroadcastManager$1(this, mAppContext.getMainLooper());
    }
    
    private void executePendingBroadcasts() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v4/content/LocalBroadcastManager.mReceivers:Ljava/util/HashMap;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        android/support/v4/content/LocalBroadcastManager.mPendingBroadcasts:Ljava/util/ArrayList;
        //    11: invokevirtual   java/util/ArrayList.size:()I
        //    14: istore_1       
        //    15: iload_1        
        //    16: ifgt            22
        //    19: aload_3        
        //    20: monitorexit    
        //    21: return         
        //    22: iload_1        
        //    23: anewarray       Landroid/support/v4/content/LocalBroadcastManager$BroadcastRecord;
        //    26: astore          4
        //    28: aload_0        
        //    29: getfield        android/support/v4/content/LocalBroadcastManager.mPendingBroadcasts:Ljava/util/ArrayList;
        //    32: aload           4
        //    34: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //    37: pop            
        //    38: aload_0        
        //    39: getfield        android/support/v4/content/LocalBroadcastManager.mPendingBroadcasts:Ljava/util/ArrayList;
        //    42: invokevirtual   java/util/ArrayList.clear:()V
        //    45: aload_3        
        //    46: monitorexit    
        //    47: iconst_0       
        //    48: istore_1       
        //    49: iload_1        
        //    50: aload           4
        //    52: arraylength    
        //    53: if_icmpge       0
        //    56: aload           4
        //    58: iload_1        
        //    59: aaload         
        //    60: astore_3       
        //    61: iconst_0       
        //    62: istore_2       
        //    63: iload_2        
        //    64: aload_3        
        //    65: getfield        android/support/v4/content/LocalBroadcastManager$BroadcastRecord.receivers:Ljava/util/ArrayList;
        //    68: invokevirtual   java/util/ArrayList.size:()I
        //    71: if_icmpge       113
        //    74: aload_3        
        //    75: getfield        android/support/v4/content/LocalBroadcastManager$BroadcastRecord.receivers:Ljava/util/ArrayList;
        //    78: iload_2        
        //    79: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    82: checkcast       Landroid/support/v4/content/LocalBroadcastManager$ReceiverRecord;
        //    85: getfield        android/support/v4/content/LocalBroadcastManager$ReceiverRecord.receiver:Landroid/content/BroadcastReceiver;
        //    88: aload_0        
        //    89: getfield        android/support/v4/content/LocalBroadcastManager.mAppContext:Landroid/content/Context;
        //    92: aload_3        
        //    93: getfield        android/support/v4/content/LocalBroadcastManager$BroadcastRecord.intent:Landroid/content/Intent;
        //    96: invokevirtual   android/content/BroadcastReceiver.onReceive:(Landroid/content/Context;Landroid/content/Intent;)V
        //    99: iload_2        
        //   100: iconst_1       
        //   101: iadd           
        //   102: istore_2       
        //   103: goto            63
        //   106: astore          4
        //   108: aload_3        
        //   109: monitorexit    
        //   110: aload           4
        //   112: athrow         
        //   113: iload_1        
        //   114: iconst_1       
        //   115: iadd           
        //   116: istore_1       
        //   117: goto            49
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      15     106    113    Any
        //  19     21     106    113    Any
        //  22     47     106    113    Any
        //  108    110    106    113    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    public static LocalBroadcastManager getInstance(final Context context) {
        synchronized (LocalBroadcastManager.mLock) {
            if (LocalBroadcastManager.mInstance == null) {
                LocalBroadcastManager.mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            return LocalBroadcastManager.mInstance;
        }
    }
    
    public void registerReceiver(final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            final LocalBroadcastManager$ReceiverRecord localBroadcastManager$ReceiverRecord = new LocalBroadcastManager$ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList<IntentFilter> list;
            if ((list = this.mReceivers.get(broadcastReceiver)) == null) {
                list = new ArrayList<IntentFilter>(1);
                this.mReceivers.put(broadcastReceiver, list);
            }
            list.add(intentFilter);
            for (int i = 0; i < intentFilter.countActions(); ++i) {
                final String action = intentFilter.getAction(i);
                ArrayList<LocalBroadcastManager$ReceiverRecord> list2;
                if ((list2 = this.mActions.get(action)) == null) {
                    list2 = new ArrayList<LocalBroadcastManager$ReceiverRecord>(1);
                    this.mActions.put(action, list2);
                }
                list2.add(localBroadcastManager$ReceiverRecord);
            }
        }
    }
    
    public boolean sendBroadcast(final Intent intent) {
        // monitorexit(hashMap)
        while (true) {
            while (true) {
                String action;
                String resolveTypeIfNeeded;
                Uri data;
                String scheme;
                Set categories;
                int n;
                ArrayList<LocalBroadcastManager$ReceiverRecord> list;
                Object o;
                int n2;
                ArrayList<LocalBroadcastManager$ReceiverRecord> list2 = null;
                int match;
                int i = 0;
                Label_0493_Outer:Label_0472_Outer:
                while (true) {
                Label_0493:
                    while (true) {
                        Block_14_Outer:Label_0161_Outer:
                        while (true) {
                        Label_0311_Outer:
                            while (true) {
                                Label_0485: {
                                    while (true) {
                                        synchronized (this.mReceivers) {
                                            action = intent.getAction();
                                            resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
                                            data = intent.getData();
                                            scheme = intent.getScheme();
                                            categories = intent.getCategories();
                                            if ((intent.getFlags() & 0x8) == 0x0) {
                                                break Label_0311_Outer;
                                            }
                                            n = 1;
                                            if (n != 0) {
                                                Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
                                            }
                                            list = this.mActions.get(intent.getAction());
                                            if (list == null) {
                                                break;
                                            }
                                            if (n != 0) {
                                                Log.v("LocalBroadcastManager", "Action list: " + list);
                                            }
                                            break Label_0485;
                                            // iftrue(Label_0237:, !o.broadcasting)
                                            // iftrue(Label_0297:, n == 0)
                                            // iftrue(Label_0536:, n2 >= list.size())
                                            // iftrue(Label_0482:, list2 != null)
                                            // iftrue(Label_0334:, match < 0)
                                            // iftrue(Label_0214:, n == 0)
                                            // iftrue(Label_0505:, n == 0)
                                        Block_17:
                                            while (true) {
                                            Block_16:
                                                while (true) {
                                                Block_13:
                                                    while (true) {
                                                        Log.v("LocalBroadcastManager", "Matching against filter " + ((LocalBroadcastManager$ReceiverRecord)o).filter);
                                                        Label_0214: {
                                                            break Label_0214;
                                                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                                                            break Label_0493;
                                                        }
                                                        break Block_13;
                                                        Block_11: {
                                                            while (true) {
                                                                break Block_16;
                                                                break Block_11;
                                                                break Block_17;
                                                                Label_0237: {
                                                                    match = ((LocalBroadcastManager$ReceiverRecord)o).filter.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                                                                }
                                                                continue Label_0161_Outer;
                                                            }
                                                            list2.add((LocalBroadcastManager$ReceiverRecord)o);
                                                            ((LocalBroadcastManager$ReceiverRecord)o).broadcasting = true;
                                                            break Label_0493;
                                                        }
                                                        o = list.get(n2);
                                                        continue Block_14_Outer;
                                                    }
                                                    continue Label_0493_Outer;
                                                }
                                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                                                continue Label_0311_Outer;
                                            }
                                            list2 = new ArrayList<LocalBroadcastManager$ReceiverRecord>();
                                            continue Label_0472_Outer;
                                        }
                                        Label_0334: {
                                            if (n != 0) {
                                                switch (match) {
                                                    default: {
                                                        o = "unknown reason";
                                                        break;
                                                    }
                                                    case -3: {
                                                        o = "action";
                                                        break;
                                                    }
                                                    case -4: {
                                                        o = "category";
                                                        break;
                                                    }
                                                    case -2: {
                                                        o = "data";
                                                        break;
                                                    }
                                                    case -1: {
                                                        o = "type";
                                                        break;
                                                    }
                                                }
                                                Log.v("LocalBroadcastManager", "  Filter did not match: " + (String)o);
                                            }
                                        }
                                        break Block_14_Outer;
                                        Label_0482:
                                        continue Label_0472_Outer;
                                    }
                                }
                                list2 = null;
                                n2 = 0;
                                continue Label_0472_Outer;
                                ++n2;
                                continue Label_0472_Outer;
                            }
                            n = 0;
                            continue Label_0493_Outer;
                        }
                        continue Label_0493;
                    }
                    while (i < list2.size()) {
                        list2.get(i).broadcasting = false;
                        ++i;
                    }
                    // monitorexit(hashMap)
                    while (true) {
                        Label_0434: {
                            break Label_0434;
                            return true;
                        }
                        this.mPendingBroadcasts.add(new LocalBroadcastManager$BroadcastRecord(intent, list2));
                        this.mHandler.sendEmptyMessage(1);
                        continue;
                    }
                }
                // iftrue(Label_0472:, this.mHandler.hasMessages(1))
                return false;
                Label_0536: {
                    if (list2 != null) {
                        i = 0;
                        continue;
                    }
                }
                break;
            }
            continue;
        }
    }
    
    public void sendBroadcastSync(final Intent intent) {
        if (this.sendBroadcast(intent)) {
            this.executePendingBroadcasts();
        }
    }
    
    public void unregisterReceiver(final BroadcastReceiver broadcastReceiver) {
        while (true) {
        Label_0054_Outer:
            while (true) {
                ArrayList<IntentFilter> list;
                int n = 0;
                ArrayList<LocalBroadcastManager$ReceiverRecord> list2;
                int n2 = 0;
                int n3 = 0;
                IntentFilter intentFilter;
                String action;
                Label_0054:Label_0094_Outer:
                while (true) {
                Label_0094:
                    while (true) {
                        Label_0172: {
                            Label_0167: {
                                synchronized (this.mReceivers) {
                                    list = this.mReceivers.remove(broadcastReceiver);
                                    if (list == null) {
                                        return;
                                    }
                                    break Label_0167;
                                    Label_0154: {
                                        return;
                                    }
                                    // iftrue(Label_0154:, n >= list.size())
                                    // iftrue(Label_0188:, n3 >= intentFilter.countActions())
                                    // iftrue(Label_0133:, n2 >= list2.size())
                                    // iftrue(Label_0179:, list2 == null)
                                    // iftrue(Label_0164:, (LocalBroadcastManager$ReceiverRecord)list2.get(n2).receiver != broadcastReceiver2)
                                    while (true) {
                                        while (true) {
                                            Block_7: {
                                                Block_5: {
                                                    Block_4: {
                                                        break Block_4;
                                                        list2.remove(n2);
                                                        --n2;
                                                        break Label_0172;
                                                        break Block_5;
                                                        this.mActions.remove(action);
                                                        break Label_0094;
                                                        break Block_7;
                                                    }
                                                    intentFilter = list.get(n);
                                                    n3 = 0;
                                                    continue Label_0054;
                                                }
                                                action = intentFilter.getAction(n3);
                                                list2 = this.mActions.get(action);
                                                n2 = 0;
                                                continue Label_0094;
                                            }
                                            continue Label_0054_Outer;
                                        }
                                        Label_0133:
                                        continue Label_0094_Outer;
                                    }
                                }
                                // iftrue(Label_0179:, list2.size() > 0)
                                Label_0164: {
                                    break Label_0172;
                                }
                            }
                            n = 0;
                            continue Label_0054_Outer;
                        }
                        ++n2;
                        continue Label_0094;
                    }
                    ++n3;
                    continue Label_0054;
                }
                Label_0188: {
                    ++n;
                }
                continue Label_0054_Outer;
            }
        }
    }
}

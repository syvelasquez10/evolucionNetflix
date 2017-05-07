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

public class LocalBroadcastManager
{
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
            Label_0493_Outer:
                while (true) {
                    ArrayList<LocalBroadcastManager$ReceiverRecord> list2 = null;
                Label_0493:
                    while (true) {
                    Label_0161_Outer:
                        while (true) {
                            Label_0500: {
                                while (true) {
                                Block_15_Outer:
                                    while (true) {
                                        final int n;
                                        Object o;
                                        int match;
                                        synchronized (this.mReceivers) {
                                            final String action = intent.getAction();
                                            final String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
                                            final Uri data = intent.getData();
                                            final String scheme = intent.getScheme();
                                            final Set categories = intent.getCategories();
                                            if ((intent.getFlags() & 0x8) == 0x0) {
                                                break Label_0500;
                                            }
                                            n = 1;
                                            if (n != 0) {
                                                Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
                                            }
                                            final ArrayList<LocalBroadcastManager$ReceiverRecord> list = this.mActions.get(intent.getAction());
                                            if (list == null) {
                                                break;
                                            }
                                            if (n != 0) {
                                                Log.v("LocalBroadcastManager", "Action list: " + list);
                                            }
                                            break Block_15_Outer;
                                            list2.add((LocalBroadcastManager$ReceiverRecord)o);
                                            ((LocalBroadcastManager$ReceiverRecord)o).broadcasting = true;
                                            break Label_0493;
                                            // iftrue(Label_0536:, n2 >= list.size())
                                            // iftrue(Label_0505:, n == 0)
                                            // iftrue(Label_0482:, list2 != null)
                                            // iftrue(Label_0297:, n == 0)
                                            // iftrue(Label_0237:, !o.broadcasting)
                                            // iftrue(Label_0214:, n == 0)
                                            // iftrue(Label_0334:, match < 0)
                                            Block_17: {
                                                while (true) {
                                                    while (true) {
                                                        Log.v("LocalBroadcastManager", "Matching against filter " + ((LocalBroadcastManager$ReceiverRecord)o).filter);
                                                        Block_11: {
                                                            while (true) {
                                                                Label_0214: {
                                                                    break Label_0214;
                                                                    while (true) {
                                                                        Log.v("LocalBroadcastManager", "  Filter's target already added");
                                                                        break Label_0493;
                                                                        break Block_11;
                                                                        continue Label_0161_Outer;
                                                                    }
                                                                    while (true) {
                                                                        break Block_17;
                                                                        Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                                                                        continue Block_15_Outer;
                                                                    }
                                                                }
                                                                continue Block_15_Outer;
                                                            }
                                                        }
                                                        final int n2;
                                                        o = list.get(n2);
                                                        continue Label_0161_Outer;
                                                    }
                                                    Label_0237: {
                                                        match = ((LocalBroadcastManager$ReceiverRecord)o).filter.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                                                    }
                                                    continue;
                                                }
                                            }
                                            list2 = new ArrayList<LocalBroadcastManager$ReceiverRecord>();
                                            continue Label_0493_Outer;
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
                                        break Label_0161_Outer;
                                        Label_0482:
                                        continue Label_0493_Outer;
                                    }
                                    list2 = null;
                                    int n2 = 0;
                                    continue;
                                    ++n2;
                                    continue;
                                }
                            }
                            int n = 0;
                            continue Label_0493_Outer;
                        }
                        continue Label_0493;
                    }
                    int i = 0;
                    while (i < list2.size()) {
                        list2.get(i).broadcasting = false;
                        ++i;
                    }
                    this.mPendingBroadcasts.add(new LocalBroadcastManager$BroadcastRecord(intent, list2));
                    // iftrue(Label_0472:, this.mHandler.hasMessages(1))
                    this.mHandler.sendEmptyMessage(1);
                    Label_0472: {
                        return true;
                    }
                }
                // monitorexit(hashMap)
                return false;
                Label_0536: {
                    final ArrayList<LocalBroadcastManager$ReceiverRecord> list2;
                    if (list2 != null) {
                        final int i = 0;
                        continue;
                    }
                }
                break;
            }
            continue;
        }
    }
    
    public void unregisterReceiver(final BroadcastReceiver broadcastReceiver) {
    Label_0031_Outer:
        while (true) {
        Label_0031:
            while (true) {
                ArrayList<IntentFilter> list;
                int n = 0;
                IntentFilter intentFilter;
                int n2 = 0;
                ArrayList<LocalBroadcastManager$ReceiverRecord> list2;
                int n3 = 0;
                String action;
                Block_6_Outer:Label_0094_Outer:
                while (true) {
                Block_8_Outer:
                    while (true) {
                        Label_0172: {
                            Label_0167: {
                                synchronized (this.mReceivers) {
                                    list = this.mReceivers.remove(broadcastReceiver);
                                    if (list == null) {
                                        return;
                                    }
                                    break Label_0167;
                                    // iftrue(Label_0188:, n >= intentFilter.countActions())
                                    // iftrue(Label_0133:, n2 >= list2.size())
                                    // iftrue(Label_0179:, list2 == null)
                                    // iftrue(Label_0179:, list2.size() > 0)
                                    // iftrue(Label_0154:, n3 >= list.size())
                                    // iftrue(Label_0164:, (LocalBroadcastManager$ReceiverRecord)list2.get(n2).receiver != broadcastReceiver2)
                                Block_9:
                                    while (true) {
                                        Block_7: {
                                            while (true) {
                                                while (true) {
                                                    Block_5: {
                                                        break Block_5;
                                                        n2 = 0;
                                                        break Block_7;
                                                        intentFilter = list.get(n3);
                                                        n = 0;
                                                        continue Block_6_Outer;
                                                        list2.remove(n2);
                                                        --n2;
                                                        break Label_0172;
                                                    }
                                                    action = intentFilter.getAction(n);
                                                    list2 = this.mActions.get(action);
                                                    continue Label_0094_Outer;
                                                }
                                                Label_0133: {
                                                    break Block_9;
                                                }
                                                continue Block_8_Outer;
                                            }
                                        }
                                        continue Label_0031_Outer;
                                    }
                                    this.mActions.remove(action);
                                    break Block_8_Outer;
                                    Label_0154: {
                                        return;
                                    }
                                }
                                Label_0164: {
                                    break Label_0172;
                                }
                            }
                            n3 = 0;
                            continue Label_0031;
                        }
                        ++n2;
                        continue Label_0031_Outer;
                    }
                    ++n;
                    continue Label_0094_Outer;
                }
                Label_0188: {
                    ++n3;
                }
                continue Label_0031;
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import java.util.Iterator;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.Context;
import java.util.Hashtable;
import java.util.Map;

public class PartnerFactory
{
    private static final String TAG = "nf_partner";
    private Map<String, Partner> partnerHandlers;
    
    public PartnerFactory() {
        this.partnerHandlers = new Hashtable<String, Partner>();
    }
    
    public String[] getExternalSignUpServices(final Context context) {
        if (context == null || context.getPackageManager() == null) {
            return new String[0];
        }
        final List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(PartnerRequestType.getExternalSignUpService.getIntentName()), 0);
        if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
            final String[] array = new String[queryIntentActivities.size()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = queryIntentActivities.get(i).activityInfo.name;
            }
            return array;
        }
        return new String[0];
    }
    
    public String[] getExternalSsoServices(final Context context) {
        if (context == null || context.getPackageManager() == null) {
            return new String[0];
        }
        final List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(PartnerRequestType.getExternalSsoService.getIntentName()), 0);
        if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
            final String[] array = new String[queryIntentActivities.size()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = queryIntentActivities.get(i).activityInfo.name;
            }
            return array;
        }
        return new String[0];
    }
    
    public Partner getPartner(final Context p0, final String p1, final PartnerCommunicationHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //     5: ifeq            33
        //     8: ldc             "nf_partner"
        //    10: new             Ljava/lang/StringBuilder;
        //    13: dup            
        //    14: invokespecial   java/lang/StringBuilder.<init>:()V
        //    17: ldc             "getPartner:: partner "
        //    19: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    22: aload_2        
        //    23: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    26: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    29: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    32: pop            
        //    33: aload_0        
        //    34: getfield        com/netflix/mediaclient/partner/PartnerFactory.partnerHandlers:Ljava/util/Map;
        //    37: aload_2        
        //    38: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    43: checkcast       Lcom/netflix/mediaclient/partner/Partner;
        //    46: astore          5
        //    48: aload           5
        //    50: ifnull          68
        //    53: ldc             "nf_partner"
        //    55: ldc             "Partner implementation found!"
        //    57: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    60: pop            
        //    61: aload           5
        //    63: astore_1       
        //    64: aload_0        
        //    65: monitorexit    
        //    66: aload_1        
        //    67: areturn        
        //    68: ldc             "nf_partner"
        //    70: ldc             "Partner implementation lookup..."
        //    72: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    75: pop            
        //    76: aload_0        
        //    77: aload_1        
        //    78: invokevirtual   com/netflix/mediaclient/partner/PartnerFactory.getExternalSsoServices:(Landroid/content/Context;)[Ljava/lang/String;
        //    81: astore_1       
        //    82: iconst_0       
        //    83: istore          4
        //    85: iload           4
        //    87: aload_1        
        //    88: arraylength    
        //    89: if_icmpge       157
        //    92: aload_1        
        //    93: iload           4
        //    95: aaload         
        //    96: ifnull          148
        //    99: aload_1        
        //   100: iload           4
        //   102: aaload         
        //   103: aload_2        
        //   104: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   107: ifeq            148
        //   110: ldc             "nf_partner"
        //   112: ldc             "Partner implementation created!"
        //   114: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   117: pop            
        //   118: new             Lcom/netflix/mediaclient/partner/reference/NetflixPartner;
        //   121: dup            
        //   122: aload_2        
        //   123: aload_3        
        //   124: invokespecial   com/netflix/mediaclient/partner/reference/NetflixPartner.<init>:(Ljava/lang/String;Lcom/netflix/mediaclient/partner/PartnerCommunicationHandler;)V
        //   127: astore_1       
        //   128: aload_0        
        //   129: getfield        com/netflix/mediaclient/partner/PartnerFactory.partnerHandlers:Ljava/util/Map;
        //   132: aload_2        
        //   133: aload_1        
        //   134: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   139: pop            
        //   140: goto            64
        //   143: astore_1       
        //   144: aload_0        
        //   145: monitorexit    
        //   146: aload_1        
        //   147: athrow         
        //   148: iload           4
        //   150: iconst_1       
        //   151: iadd           
        //   152: istore          4
        //   154: goto            85
        //   157: ldc             "nf_partner"
        //   159: ldc             "Partner implementation NOT found!"
        //   161: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   164: pop            
        //   165: aconst_null    
        //   166: astore_1       
        //   167: goto            64
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      33     143    148    Any
        //  33     48     143    148    Any
        //  53     61     143    148    Any
        //  68     82     143    148    Any
        //  85     92     143    148    Any
        //  99     140    143    148    Any
        //  157    165    143    148    Any
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
    
    public void releasePartners() {
        synchronized (this) {
            final Iterator<Partner> iterator = this.partnerHandlers.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().release();
            }
        }
        this.partnerHandlers.clear();
    }
    // monitorexit(this)
}

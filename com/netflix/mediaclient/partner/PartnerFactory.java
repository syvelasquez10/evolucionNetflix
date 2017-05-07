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
        String[] array;
        if (context == null || context.getPackageManager() == null) {
            array = new String[0];
        }
        else {
            final List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(PartnerRequestType.getExternalSignUpService.getIntentName()), 0);
            if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                return new String[0];
            }
            final String[] array2 = new String[queryIntentActivities.size()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = queryIntentActivities.get(n).activityInfo.name;
                ++n;
            }
        }
        return array;
    }
    
    public String[] getExternalSsoServices(final Context context) {
        String[] array;
        if (context == null || context.getPackageManager() == null) {
            array = new String[0];
        }
        else {
            final List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(PartnerRequestType.getExternalSsoService.getIntentName()), 0);
            if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                return new String[0];
            }
            final String[] array2 = new String[queryIntentActivities.size()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= array2.length) {
                    break;
                }
                array2[n] = queryIntentActivities.get(n).activityInfo.name;
                ++n;
            }
        }
        return array;
    }
    
    public Partner getPartner(final Context p0, final String p1, final PartnerCommunicationHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: ldc             "nf_partner"
        //     4: iconst_3       
        //     5: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //     8: ifeq            36
        //    11: ldc             "nf_partner"
        //    13: new             Ljava/lang/StringBuilder;
        //    16: dup            
        //    17: invokespecial   java/lang/StringBuilder.<init>:()V
        //    20: ldc             "getPartner:: partner "
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: aload_2        
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    35: pop            
        //    36: aload_0        
        //    37: getfield        com/netflix/mediaclient/partner/PartnerFactory.partnerHandlers:Ljava/util/Map;
        //    40: aload_2        
        //    41: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    46: checkcast       Lcom/netflix/mediaclient/partner/Partner;
        //    49: astore          5
        //    51: aload           5
        //    53: ifnull          71
        //    56: ldc             "nf_partner"
        //    58: ldc             "Partner implementation found!"
        //    60: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    63: pop            
        //    64: aload           5
        //    66: astore_1       
        //    67: aload_0        
        //    68: monitorexit    
        //    69: aload_1        
        //    70: areturn        
        //    71: ldc             "nf_partner"
        //    73: ldc             "Partner implementation lookup..."
        //    75: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    78: pop            
        //    79: aload_0        
        //    80: aload_1        
        //    81: invokevirtual   com/netflix/mediaclient/partner/PartnerFactory.getExternalSsoServices:(Landroid/content/Context;)[Ljava/lang/String;
        //    84: astore_1       
        //    85: iconst_0       
        //    86: istore          4
        //    88: iload           4
        //    90: aload_1        
        //    91: arraylength    
        //    92: if_icmpge       146
        //    95: aload_1        
        //    96: iload           4
        //    98: aaload         
        //    99: ifnull          164
        //   102: aload_1        
        //   103: iload           4
        //   105: aaload         
        //   106: aload_2        
        //   107: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   110: ifeq            164
        //   113: ldc             "nf_partner"
        //   115: ldc             "Partner implementation created!"
        //   117: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   120: pop            
        //   121: new             Lcom/netflix/mediaclient/partner/reference/NetflixPartner;
        //   124: dup            
        //   125: aload_2        
        //   126: aload_3        
        //   127: invokespecial   com/netflix/mediaclient/partner/reference/NetflixPartner.<init>:(Ljava/lang/String;Lcom/netflix/mediaclient/partner/PartnerCommunicationHandler;)V
        //   130: astore_1       
        //   131: aload_0        
        //   132: getfield        com/netflix/mediaclient/partner/PartnerFactory.partnerHandlers:Ljava/util/Map;
        //   135: aload_2        
        //   136: aload_1        
        //   137: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   142: pop            
        //   143: goto            67
        //   146: ldc             "nf_partner"
        //   148: ldc             "Partner implementation NOT found!"
        //   150: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   153: pop            
        //   154: aconst_null    
        //   155: astore_1       
        //   156: goto            67
        //   159: astore_1       
        //   160: aload_0        
        //   161: monitorexit    
        //   162: aload_1        
        //   163: athrow         
        //   164: iload           4
        //   166: iconst_1       
        //   167: iadd           
        //   168: istore          4
        //   170: goto            88
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      36     159    164    Any
        //  36     51     159    164    Any
        //  56     64     159    164    Any
        //  71     85     159    164    Any
        //  88     95     159    164    Any
        //  102    143    159    164    Any
        //  146    154    159    164    Any
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

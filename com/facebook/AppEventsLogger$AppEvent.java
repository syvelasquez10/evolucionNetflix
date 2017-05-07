// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Iterator;
import org.json.JSONException;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.content.Context;
import org.json.JSONObject;
import java.util.HashSet;
import java.io.Serializable;

class AppEventsLogger$AppEvent implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final HashSet<String> validatedIdentifiers;
    private boolean isImplicit;
    private JSONObject jsonObject;
    private String name;
    
    static {
        validatedIdentifiers = new HashSet<String>();
    }
    
    public AppEventsLogger$AppEvent(final Context context, String name, final Double n, final Bundle bundle, final boolean isImplicit) {
        try {
            this.validateIdentifier(name);
            this.name = name;
            this.isImplicit = isImplicit;
            (this.jsonObject = new JSONObject()).put("_eventName", (Object)name);
            this.jsonObject.put("_logTime", System.currentTimeMillis() / 1000L);
            this.jsonObject.put("_ui", (Object)Utility.getActivityName(context));
            if (n != null) {
                this.jsonObject.put("_valueToSum", (double)n);
            }
            if (this.isImplicit) {
                this.jsonObject.put("_implicitlyLogged", (Object)"1");
            }
            final String appVersion = Settings.getAppVersion();
            if (appVersion != null) {
                this.jsonObject.put("_appVersion", (Object)appVersion);
            }
            if (bundle != null) {
                final Iterator<String> iterator = bundle.keySet().iterator();
                if (iterator.hasNext()) {
                    name = iterator.next();
                    this.validateIdentifier(name);
                    final Object value = bundle.get(name);
                    if (!(value instanceof String) && !(value instanceof Number)) {
                        throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", value, name));
                    }
                    goto Label_0243;
                }
            }
        }
        catch (JSONException ex) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", ex.toString());
            this.jsonObject = null;
        }
        catch (FacebookException ex2) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", ex2.toString());
            this.jsonObject = null;
            return;
        }
        if (!this.isImplicit) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", this.jsonObject.toString());
            return;
        }
        goto Label_0242;
    }
    
    private AppEventsLogger$AppEvent(final String s, final boolean isImplicit) {
        this.jsonObject = new JSONObject(s);
        this.isImplicit = isImplicit;
    }
    
    private void validateIdentifier(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          20
        //     4: aload_1        
        //     5: invokevirtual   java/lang/String.length:()I
        //     8: ifeq            20
        //    11: aload_1        
        //    12: invokevirtual   java/lang/String.length:()I
        //    15: bipush          40
        //    17: if_icmple       58
        //    20: aload_1        
        //    21: astore_3       
        //    22: aload_1        
        //    23: ifnonnull       29
        //    26: ldc             "<None Provided>"
        //    28: astore_3       
        //    29: new             Lcom/facebook/FacebookException;
        //    32: dup            
        //    33: ldc             "Identifier '%s' must be less than %d characters"
        //    35: iconst_2       
        //    36: anewarray       Ljava/lang/Object;
        //    39: dup            
        //    40: iconst_0       
        //    41: aload_3        
        //    42: aastore        
        //    43: dup            
        //    44: iconst_1       
        //    45: bipush          40
        //    47: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    50: aastore        
        //    51: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    54: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //    57: athrow         
        //    58: getstatic       com/facebook/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    61: astore_3       
        //    62: aload_3        
        //    63: monitorenter   
        //    64: getstatic       com/facebook/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    67: aload_1        
        //    68: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
        //    71: istore_2       
        //    72: aload_3        
        //    73: monitorexit    
        //    74: iload_2        
        //    75: ifne            103
        //    78: aload_1        
        //    79: ldc             "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$"
        //    81: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    84: ifeq            114
        //    87: getstatic       com/facebook/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    90: astore_3       
        //    91: aload_3        
        //    92: monitorenter   
        //    93: getstatic       com/facebook/AppEventsLogger$AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    96: aload_1        
        //    97: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   100: pop            
        //   101: aload_3        
        //   102: monitorexit    
        //   103: return         
        //   104: astore_1       
        //   105: aload_3        
        //   106: monitorexit    
        //   107: aload_1        
        //   108: athrow         
        //   109: astore_1       
        //   110: aload_3        
        //   111: monitorexit    
        //   112: aload_1        
        //   113: athrow         
        //   114: new             Lcom/facebook/FacebookException;
        //   117: dup            
        //   118: ldc             "Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen."
        //   120: iconst_1       
        //   121: anewarray       Ljava/lang/Object;
        //   124: dup            
        //   125: iconst_0       
        //   126: aload_1        
        //   127: aastore        
        //   128: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   131: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //   134: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  64     74     104    109    Any
        //  93     103    109    114    Any
        //  105    107    104    109    Any
        //  110    112    109    114    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0103:
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
    
    private Object writeReplace() {
        return new AppEventsLogger$AppEvent$SerializationProxyV1(this.jsonObject.toString(), this.isImplicit, null);
    }
    
    public boolean getIsImplicit() {
        return this.isImplicit;
    }
    
    public JSONObject getJSONObject() {
        return this.jsonObject;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", this.jsonObject.optString("_eventName"), this.isImplicit, this.jsonObject.toString());
    }
}

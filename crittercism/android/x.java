// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import java.util.Vector;
import org.json.JSONObject;
import org.json.JSONArray;

public final class x extends s
{
    private String i;
    private JSONArray j;
    private JSONArray k;
    private JSONObject l;
    
    public x(final m m) {
        this(m.a() + "/android_v2/handle_crashes");
    }
    
    private x(final String a) {
        this.i = new String();
        this.j = new JSONArray();
        this.k = new JSONArray();
        this.l = new JSONObject();
        this.a = a;
        this.b = new Vector();
    }
    
    private static x a(final String ex) {
        final x x = new x((String)ex);
        JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                final SharedPreferences m = l.i().m();
                final JSONObject jsonObject2 = new JSONObject(m.getString(a(), new JSONObject().toString()));
                Label_0113: {
                    try {
                        final SharedPreferences$Editor edit = m.edit();
                        edit.remove(a());
                        jsonObject = jsonObject2;
                        if (!edit.commit()) {
                            throw new Exception("failed to remove crashes from Shared Preferences");
                        }
                        break Label_0113;
                    }
                    catch (Exception ex3) {}
                    new StringBuilder("Exception in SdkCrashes.readFromDisk(): ").append(jsonObject.getClass().getName());
                    jsonObject = jsonObject2;
                    try {
                        return a(jsonObject, (String)ex);
                    }
                    catch (Exception ex) {
                        return x;
                    }
                }
            }
            catch (Exception ex2) {
                final JSONObject jsonObject2 = jsonObject;
                jsonObject = (JSONObject)ex2;
                continue;
            }
            break;
        }
    }
    
    private static x a(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcrittercism/android/x;
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   crittercism/android/x.<init>:(Ljava/lang/String;)V
        //     8: astore          4
        //    10: new             Lorg/json/JSONObject;
        //    13: dup            
        //    14: invokespecial   org/json/JSONObject.<init>:()V
        //    17: astore_1       
        //    18: new             Lorg/json/JSONArray;
        //    21: dup            
        //    22: invokespecial   org/json/JSONArray.<init>:()V
        //    25: astore_3       
        //    26: aload_0        
        //    27: ldc             "requestData"
        //    29: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    32: ifeq            42
        //    35: aload_0        
        //    36: ldc             "requestData"
        //    38: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    41: astore_1       
        //    42: aload_1        
        //    43: ldc             "crashes"
        //    45: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    48: ifeq            136
        //    51: aload_1        
        //    52: ldc             "crashes"
        //    54: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //    57: astore_0       
        //    58: iconst_0       
        //    59: istore_2       
        //    60: iload_2        
        //    61: aload_0        
        //    62: invokevirtual   org/json/JSONArray.length:()I
        //    65: if_icmpge       133
        //    68: aload           4
        //    70: aload_0        
        //    71: iload_2        
        //    72: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //    75: invokevirtual   crittercism/android/x.a:(Ljava/lang/Object;)V
        //    78: iload_2        
        //    79: iconst_1       
        //    80: iadd           
        //    81: istore_2       
        //    82: goto            60
        //    85: astore_0       
        //    86: new             Lorg/json/JSONObject;
        //    89: dup            
        //    90: invokespecial   org/json/JSONObject.<init>:()V
        //    93: astore_1       
        //    94: goto            42
        //    97: astore_0       
        //    98: new             Lorg/json/JSONArray;
        //   101: dup            
        //   102: invokespecial   org/json/JSONArray.<init>:()V
        //   105: astore_0       
        //   106: goto            58
        //   109: astore_1       
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: ldc             "Exception in SdkCrashes.fromJSON: "
        //   116: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   119: aload_1        
        //   120: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   123: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: pop            
        //   130: goto            78
        //   133: aload           4
        //   135: areturn        
        //   136: aload_3        
        //   137: astore_0       
        //   138: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  26     42     85     97     Ljava/lang/Exception;
        //  42     58     97     109    Ljava/lang/Exception;
        //  68     78     109    133    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 73, Size: 73
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
    
    private static String a() {
        new String();
        try {
            final String a = l.i().a();
            return "critter_pendingcrashes_" + a;
        }
        catch (Exception ex) {
            final String a = new String();
            return "critter_pendingcrashes_" + a;
        }
    }
    
    private void b(Throwable t) {
        final int index = t.toString().indexOf(":");
        String i;
        if (index >= 0) {
            i = t.toString().substring(0, index);
        }
        else {
            i = t.getClass().getName();
        }
        this.i = i;
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        for (t = t.getCause(); t != null; t = t.getCause()) {
            this.i = t.getClass().getName();
            t.printStackTrace(printWriter);
        }
        final String string = stringWriter.toString();
        this.j = new JSONArray();
        final String[] split = string.split("\n");
        for (int j = 0; j < split.length; ++j) {
            this.j.put((Object)split[j]);
        }
    }
    
    private void h() {
        for (final Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            final JSONObject jsonObject = new JSONObject();
            final Thread thread = entry.getKey();
            try {
                if (thread.getId() == Thread.currentThread().getId()) {
                    continue;
                }
                jsonObject.put("name", (Object)thread.getName());
                jsonObject.put("id", thread.getId());
                jsonObject.put("state", (Object)thread.getState().name());
                final JSONArray jsonArray = new JSONArray();
                final StackTraceElement[] array = entry.getValue();
                for (int length = array.length, i = 0; i < length; ++i) {
                    final StackTraceElement stackTraceElement = array[i];
                    new StringBuilder("  ").append(stackTraceElement);
                    jsonArray.put((Object)stackTraceElement);
                }
                jsonObject.put("stacktrace", (Object)jsonArray);
                this.k.put((Object)jsonObject);
            }
            catch (Exception ex) {
                new StringBuilder("Problem with setBackgroundThreads(): ").append(ex.getClass().getName());
            }
        }
    }
    
    public final void a(final Throwable p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: new             Ljava/lang/String;
        //    11: dup            
        //    12: invokespecial   java/lang/String.<init>:()V
        //    15: pop            
        //    16: ldc_w           ""
        //    19: astore_2       
        //    20: aload_1        
        //    21: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //    24: ifnull          32
        //    27: aload_1        
        //    28: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //    31: astore_2       
        //    32: aload_0        
        //    33: aload_1        
        //    34: invokespecial   crittercism/android/x.b:(Ljava/lang/Throwable;)V
        //    37: aload_0        
        //    38: invokespecial   crittercism/android/x.h:()V
        //    41: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    44: getfield        crittercism/android/l.m:Lcrittercism/android/q;
        //    47: astore          4
        //    49: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    52: getfield        crittercism/android/l.n:Lcrittercism/android/q;
        //    55: astore          5
        //    57: new             Lorg/json/JSONObject;
        //    60: dup            
        //    61: invokespecial   org/json/JSONObject.<init>:()V
        //    64: astore_1       
        //    65: aload_1        
        //    66: ldc_w           "crashed_session"
        //    69: aload           4
        //    71: invokevirtual   crittercism/android/q.a:()Lorg/json/JSONArray;
        //    74: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    77: pop            
        //    78: aload_1        
        //    79: ldc_w           "previous_session"
        //    82: aload           5
        //    84: invokevirtual   crittercism/android/q.a:()Lorg/json/JSONArray;
        //    87: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    90: pop            
        //    91: aload_0        
        //    92: aload_1        
        //    93: putfield        crittercism/android/x.l:Lorg/json/JSONObject;
        //    96: aload_3        
        //    97: ldc_w           "app_state"
        //   100: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //   103: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //   106: iconst_2       
        //   107: newarray        Z
        //   109: dup            
        //   110: iconst_0       
        //   111: ldc_w           1
        //   114: bastore        
        //   115: dup            
        //   116: iconst_1       
        //   117: ldc_w           1
        //   120: bastore        
        //   121: invokevirtual   crittercism/android/i.a:([Z)Lorg/json/JSONObject;
        //   124: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   127: pop            
        //   128: aload_3        
        //   129: ldc_w           "breadcrumbs"
        //   132: aload_0        
        //   133: getfield        crittercism/android/x.l:Lorg/json/JSONObject;
        //   136: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   139: pop            
        //   140: aload_3        
        //   141: ldc_w           "current_thread_id"
        //   144: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   147: invokevirtual   java/lang/Thread.getId:()J
        //   150: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   153: pop            
        //   154: aload_3        
        //   155: ldc_w           "endpoints"
        //   158: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //   161: getfield        crittercism/android/l.o:Lcrittercism/android/q;
        //   164: invokevirtual   crittercism/android/q.a:()Lorg/json/JSONArray;
        //   167: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   170: pop            
        //   171: aload_3        
        //   172: ldc_w           "exception_name"
        //   175: aload_0        
        //   176: getfield        crittercism/android/x.i:Ljava/lang/String;
        //   179: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   182: pop            
        //   183: aload_3        
        //   184: ldc_w           "exception_reason"
        //   187: aload_2        
        //   188: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   191: pop            
        //   192: aload_3        
        //   193: ldc_w           "platform"
        //   196: ldc_w           "android"
        //   199: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   202: pop            
        //   203: aload_3        
        //   204: ldc_w           "threads"
        //   207: aload_0        
        //   208: getfield        crittercism/android/x.k:Lorg/json/JSONArray;
        //   211: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   214: pop            
        //   215: aload_3        
        //   216: ldc_w           "ts"
        //   219: getstatic       crittercism/android/au.a:Lcrittercism/android/au;
        //   222: invokevirtual   crittercism/android/au.a:()Ljava/lang/String;
        //   225: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   228: pop            
        //   229: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   232: invokevirtual   java/lang/Thread.getId:()J
        //   235: lconst_1       
        //   236: lcmp           
        //   237: ifne            358
        //   240: aload_3        
        //   241: ldc_w           "type"
        //   244: getstatic       crittercism/android/s.c:Ljava/lang/String;
        //   247: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   250: pop            
        //   251: aload_3        
        //   252: ldc_w           "unsymbolized_stacktrace"
        //   255: aload_0        
        //   256: getfield        crittercism/android/x.j:Lorg/json/JSONArray;
        //   259: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   262: pop            
        //   263: aload_0        
        //   264: aload_3        
        //   265: invokevirtual   crittercism/android/x.a:(Ljava/lang/Object;)V
        //   268: return         
        //   269: astore_1       
        //   270: new             Lorg/json/JSONObject;
        //   273: dup            
        //   274: invokespecial   org/json/JSONObject.<init>:()V
        //   277: astore_1       
        //   278: goto            91
        //   281: astore_1       
        //   282: new             Ljava/lang/StringBuilder;
        //   285: dup            
        //   286: ldc_w           "Exception in addThrowableToVector: "
        //   289: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   292: aload_1        
        //   293: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   296: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   299: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   302: pop            
        //   303: new             Ljava/lang/String;
        //   306: dup            
        //   307: invokespecial   java/lang/String.<init>:()V
        //   310: astore_2       
        //   311: aload_0        
        //   312: new             Ljava/lang/String;
        //   315: dup            
        //   316: invokespecial   java/lang/String.<init>:()V
        //   319: putfield        crittercism/android/x.i:Ljava/lang/String;
        //   322: aload_0        
        //   323: new             Lorg/json/JSONArray;
        //   326: dup            
        //   327: invokespecial   org/json/JSONArray.<init>:()V
        //   330: putfield        crittercism/android/x.j:Lorg/json/JSONArray;
        //   333: aload_0        
        //   334: new             Lorg/json/JSONArray;
        //   337: dup            
        //   338: invokespecial   org/json/JSONArray.<init>:()V
        //   341: putfield        crittercism/android/x.k:Lorg/json/JSONArray;
        //   344: aload_0        
        //   345: new             Lorg/json/JSONObject;
        //   348: dup            
        //   349: invokespecial   org/json/JSONObject.<init>:()V
        //   352: putfield        crittercism/android/x.l:Lorg/json/JSONObject;
        //   355: goto            96
        //   358: aload_3        
        //   359: ldc_w           "type"
        //   362: getstatic       crittercism/android/s.d:Ljava/lang/String;
        //   365: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   368: pop            
        //   369: goto            251
        //   372: astore_1       
        //   373: new             Ljava/lang/StringBuilder;
        //   376: dup            
        //   377: ldc_w           "JSONException in addThrowableToVector: "
        //   380: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   383: aload_1        
        //   384: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   387: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: pop            
        //   394: goto            263
        //   397: astore_1       
        //   398: new             Ljava/lang/StringBuilder;
        //   401: dup            
        //   402: ldc_w           "Exception in addThrowableToVector: "
        //   405: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   408: aload_1        
        //   409: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   412: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: pop            
        //   419: goto            263
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  20     32     281    358    Ljava/lang/Exception;
        //  32     65     281    358    Ljava/lang/Exception;
        //  65     91     269    281    Lorg/json/JSONException;
        //  65     91     281    358    Ljava/lang/Exception;
        //  91     96     281    358    Ljava/lang/Exception;
        //  96     251    372    397    Lorg/json/JSONException;
        //  96     251    397    422    Ljava/lang/Exception;
        //  251    263    372    397    Lorg/json/JSONException;
        //  251    263    397    422    Ljava/lang/Exception;
        //  270    278    281    358    Ljava/lang/Exception;
        //  358    369    372    397    Lorg/json/JSONException;
        //  358    369    397    422    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0096:
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
    
    @Override
    public final void a(final JSONObject jsonObject) {
        new StringBuilder("responseObject = ").append(jsonObject.toString());
        try {
            if (this.b.size() == 0) {
                return;
            }
        }
        catch (Exception ex) {}
        super.a(jsonObject);
    }
    
    @Override
    public final JSONObject b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore_3       
        //     8: new             Lorg/json/JSONObject;
        //    11: dup            
        //    12: invokespecial   org/json/JSONObject.<init>:()V
        //    15: pop            
        //    16: new             Lorg/json/JSONArray;
        //    19: dup            
        //    20: invokespecial   org/json/JSONArray.<init>:()V
        //    23: pop            
        //    24: new             Lorg/json/JSONArray;
        //    27: dup            
        //    28: aload_0        
        //    29: getfield        crittercism/android/x.b:Ljava/util/Collection;
        //    32: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //    35: astore_1       
        //    36: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    39: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    42: invokevirtual   crittercism/android/i.d:()Lorg/json/JSONObject;
        //    45: astore_2       
        //    46: aload_2        
        //    47: ldc             "crashes"
        //    49: aload_1        
        //    50: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    53: pop            
        //    54: aload_2        
        //    55: astore_1       
        //    56: aload_3        
        //    57: ldc_w           "requestUrl"
        //    60: aload_0        
        //    61: getfield        crittercism/android/x.a:Ljava/lang/String;
        //    64: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    67: pop            
        //    68: aload_3        
        //    69: ldc             "requestData"
        //    71: aload_1        
        //    72: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    75: pop            
        //    76: aload_3        
        //    77: areturn        
        //    78: astore_1       
        //    79: new             Lorg/json/JSONArray;
        //    82: dup            
        //    83: invokespecial   org/json/JSONArray.<init>:()V
        //    86: astore_1       
        //    87: goto            36
        //    90: astore_1       
        //    91: new             Lorg/json/JSONObject;
        //    94: dup            
        //    95: invokespecial   org/json/JSONObject.<init>:()V
        //    98: astore_1       
        //    99: goto            56
        //   102: astore_1       
        //   103: new             Lorg/json/JSONObject;
        //   106: dup            
        //   107: invokespecial   org/json/JSONObject.<init>:()V
        //   110: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  24     36     78     90     Ljava/lang/Exception;
        //  36     54     90     102    Ljava/lang/Exception;
        //  56     76     102    111    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 59, Size: 59
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
    
    @Override
    public final void c() {
        try {
            final SharedPreferences$Editor edit = crittercism.android.l.i().m().edit();
            edit.remove(a());
            edit.putString(a(), this.b().toString());
            if (!edit.commit()) {
                throw new Exception("commit failed");
            }
        }
        catch (Exception ex) {}
    }
    
    @Override
    public final JSONObject d() {
        return super.d();
    }
    
    @Override
    protected final void e() {
        final x a = a(this.a);
        a.a(this.b);
        this.b = a.b;
    }
}

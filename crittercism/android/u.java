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

public final class u extends s
{
    private String i;
    private JSONArray j;
    private JSONArray k;
    private JSONObject l;
    private long m;
    private int n;
    
    public u(final m m) {
        this(m.a() + "/android_v2/handle_exceptions");
    }
    
    private u(final String a) {
        this.i = new String();
        this.j = new JSONArray();
        this.k = new JSONArray();
        this.l = new JSONObject();
        this.m = 0L;
        this.n = 0;
        this.a = a;
        this.b = new Vector();
    }
    
    private static u a(final String ex) {
        final u u = new u((String)ex);
        JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                final SharedPreferences m = l.i().m();
                final JSONObject jsonObject2 = new JSONObject(m.getString(i(), new JSONObject().toString()));
                Label_0113: {
                    try {
                        final SharedPreferences$Editor edit = m.edit();
                        edit.remove(i());
                        jsonObject = jsonObject2;
                        if (!edit.commit()) {
                            throw new Exception("failed to remove handled exceptions from Shared Preferences");
                        }
                        break Label_0113;
                    }
                    catch (Exception ex3) {}
                    new StringBuilder("Exception in HandledExceptions.readFromDisk(): ").append(jsonObject.getClass().getName());
                    jsonObject = jsonObject2;
                    try {
                        return a(jsonObject, (String)ex);
                    }
                    catch (Exception ex) {
                        return u;
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
    
    private static u a(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcrittercism/android/u;
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   crittercism/android/u.<init>:(Ljava/lang/String;)V
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
        //    43: ldc             "exceptions"
        //    45: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    48: ifeq            136
        //    51: aload_1        
        //    52: ldc             "exceptions"
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
        //    75: invokevirtual   crittercism/android/u.a:(Ljava/lang/Object;)V
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
        //   114: ldc             "Exception in HandledExceptions.fromJSON: "
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
    
    private static String i() {
        new String();
        try {
            final String a = l.i().a();
            return "critter_pendingexceptions_" + a;
        }
        catch (Exception ex) {
            final String a = new String();
            return "critter_pendingexceptions_" + a;
        }
    }
    
    private void j() {
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
        //    34: invokespecial   crittercism/android/u.b:(Ljava/lang/Throwable;)V
        //    37: aload_0        
        //    38: invokespecial   crittercism/android/u.j:()V
        //    41: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    44: getfield        crittercism/android/l.m:Lcrittercism/android/q;
        //    47: astore          4
        //    49: new             Lorg/json/JSONObject;
        //    52: dup            
        //    53: invokespecial   org/json/JSONObject.<init>:()V
        //    56: astore_1       
        //    57: aload_1        
        //    58: ldc_w           "current_session"
        //    61: aload           4
        //    63: invokevirtual   crittercism/android/q.a:()Lorg/json/JSONArray;
        //    66: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    69: pop            
        //    70: aload_0        
        //    71: aload_1        
        //    72: putfield        crittercism/android/u.l:Lorg/json/JSONObject;
        //    75: aload_3        
        //    76: ldc_w           "app_state"
        //    79: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    82: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    85: iconst_2       
        //    86: newarray        Z
        //    88: dup            
        //    89: iconst_0       
        //    90: ldc_w           1
        //    93: bastore        
        //    94: dup            
        //    95: iconst_1       
        //    96: ldc_w           1
        //    99: bastore        
        //   100: invokevirtual   crittercism/android/i.a:([Z)Lorg/json/JSONObject;
        //   103: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   106: pop            
        //   107: aload_3        
        //   108: ldc_w           "breadcrumbs"
        //   111: aload_0        
        //   112: getfield        crittercism/android/u.l:Lorg/json/JSONObject;
        //   115: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   118: pop            
        //   119: aload_3        
        //   120: ldc_w           "current_thread_id"
        //   123: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   126: invokevirtual   java/lang/Thread.getId:()J
        //   129: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   132: pop            
        //   133: aload_3        
        //   134: ldc_w           "exception_name"
        //   137: aload_0        
        //   138: getfield        crittercism/android/u.i:Ljava/lang/String;
        //   141: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   144: pop            
        //   145: aload_3        
        //   146: ldc_w           "exception_reason"
        //   149: aload_2        
        //   150: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   153: pop            
        //   154: aload_3        
        //   155: ldc_w           "platform"
        //   158: ldc_w           "android"
        //   161: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   164: pop            
        //   165: aload_3        
        //   166: ldc_w           "threads"
        //   169: aload_0        
        //   170: getfield        crittercism/android/u.k:Lorg/json/JSONArray;
        //   173: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   176: pop            
        //   177: aload_3        
        //   178: ldc_w           "ts"
        //   181: getstatic       crittercism/android/au.a:Lcrittercism/android/au;
        //   184: invokevirtual   crittercism/android/au.a:()Ljava/lang/String;
        //   187: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   190: pop            
        //   191: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   194: invokevirtual   java/lang/Thread.getId:()J
        //   197: lconst_1       
        //   198: lcmp           
        //   199: ifne            320
        //   202: aload_3        
        //   203: ldc_w           "type"
        //   206: getstatic       crittercism/android/s.e:Ljava/lang/String;
        //   209: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   212: pop            
        //   213: aload_3        
        //   214: ldc_w           "unsymbolized_stacktrace"
        //   217: aload_0        
        //   218: getfield        crittercism/android/u.j:Lorg/json/JSONArray;
        //   221: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   224: pop            
        //   225: aload_0        
        //   226: aload_3        
        //   227: invokevirtual   crittercism/android/u.a:(Ljava/lang/Object;)V
        //   230: return         
        //   231: astore_1       
        //   232: new             Lorg/json/JSONObject;
        //   235: dup            
        //   236: invokespecial   org/json/JSONObject.<init>:()V
        //   239: astore_1       
        //   240: goto            70
        //   243: astore_1       
        //   244: new             Ljava/lang/StringBuilder;
        //   247: dup            
        //   248: ldc_w           "Exception in addThrowableToVector: "
        //   251: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   254: aload_1        
        //   255: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   258: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: pop            
        //   265: new             Ljava/lang/String;
        //   268: dup            
        //   269: invokespecial   java/lang/String.<init>:()V
        //   272: astore_2       
        //   273: aload_0        
        //   274: new             Ljava/lang/String;
        //   277: dup            
        //   278: invokespecial   java/lang/String.<init>:()V
        //   281: putfield        crittercism/android/u.i:Ljava/lang/String;
        //   284: aload_0        
        //   285: new             Lorg/json/JSONArray;
        //   288: dup            
        //   289: invokespecial   org/json/JSONArray.<init>:()V
        //   292: putfield        crittercism/android/u.j:Lorg/json/JSONArray;
        //   295: aload_0        
        //   296: new             Lorg/json/JSONArray;
        //   299: dup            
        //   300: invokespecial   org/json/JSONArray.<init>:()V
        //   303: putfield        crittercism/android/u.k:Lorg/json/JSONArray;
        //   306: aload_0        
        //   307: new             Lorg/json/JSONObject;
        //   310: dup            
        //   311: invokespecial   org/json/JSONObject.<init>:()V
        //   314: putfield        crittercism/android/u.l:Lorg/json/JSONObject;
        //   317: goto            75
        //   320: aload_3        
        //   321: ldc_w           "type"
        //   324: getstatic       crittercism/android/s.f:Ljava/lang/String;
        //   327: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   330: pop            
        //   331: goto            213
        //   334: astore_1       
        //   335: new             Ljava/lang/StringBuilder;
        //   338: dup            
        //   339: ldc_w           "JSONException in addThrowableToVector: "
        //   342: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   345: aload_1        
        //   346: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   349: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   352: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   355: pop            
        //   356: goto            225
        //   359: astore_1       
        //   360: new             Ljava/lang/StringBuilder;
        //   363: dup            
        //   364: ldc_w           "Exception in addThrowableToVector: "
        //   367: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   370: aload_1        
        //   371: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   374: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: pop            
        //   381: goto            225
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  20     32     243    320    Ljava/lang/Exception;
        //  32     57     243    320    Ljava/lang/Exception;
        //  57     70     231    243    Lorg/json/JSONException;
        //  57     70     243    320    Ljava/lang/Exception;
        //  70     75     243    320    Ljava/lang/Exception;
        //  75     213    334    359    Lorg/json/JSONException;
        //  75     213    359    384    Ljava/lang/Exception;
        //  213    225    334    359    Lorg/json/JSONException;
        //  213    225    359    384    Ljava/lang/Exception;
        //  232    240    243    320    Ljava/lang/Exception;
        //  320    331    334    359    Lorg/json/JSONException;
        //  320    331    359    384    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
        while (true) {
            try {
                new StringBuilder("this.pendingDataVector.size() = ").append(this.b.size());
                if (this.b.size() == 0) {
                    return;
                }
                if (jsonObject.has("success") && jsonObject.getInt("success") == 1) {
                    this.n += this.b.size();
                }
                this.m = System.nanoTime();
                super.a(jsonObject);
            }
            catch (Exception ex) {
                new StringBuilder("Exception in HandledExceptions$handleResponseObject ").append(ex.getClass().getName());
                continue;
            }
            break;
        }
    }
    
    public final boolean a() {
        return System.nanoTime() - this.m > 60000L;
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
        //    29: getfield        crittercism/android/u.b:Ljava/util/Collection;
        //    32: invokespecial   org/json/JSONArray.<init>:(Ljava/util/Collection;)V
        //    35: astore_1       
        //    36: invokestatic    crittercism/android/l.i:()Lcrittercism/android/l;
        //    39: getfield        crittercism/android/l.c:Lcrittercism/android/i;
        //    42: invokevirtual   crittercism/android/i.d:()Lorg/json/JSONObject;
        //    45: astore_2       
        //    46: aload_2        
        //    47: ldc             "exceptions"
        //    49: aload_1        
        //    50: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    53: pop            
        //    54: aload_2        
        //    55: astore_1       
        //    56: aload_3        
        //    57: ldc_w           "requestUrl"
        //    60: aload_0        
        //    61: getfield        crittercism/android/u.a:Ljava/lang/String;
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
            edit.remove(i());
            edit.putString(i(), this.b().toString());
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
        final u a = a(this.a);
        a.a(this.b);
        this.b = a.b;
    }
    
    public final int h() {
        return this.n;
    }
}

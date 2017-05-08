// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.net.URLEncoder;
import java.lang.reflect.Method;
import com.google.gson.JsonObject;
import java.util.Map;
import java.lang.reflect.ParameterizedType;
import com.google.gson.GsonBuilder;
import java.net.URI;
import com.google.gson.Gson;
import com.netflix.mediaclient.Log;
import com.google.gson.stream.JsonToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.io.BufferedReader;
import java.util.Iterator;

class RemotePathEvaluator$1$1 implements Iterator<PathBoundValue>
{
    String line;
    private BufferedReader reader;
    private boolean started;
    final /* synthetic */ RemotePathEvaluator$1 this$1;
    
    RemotePathEvaluator$1$1(final RemotePathEvaluator$1 this$1) {
        this.this$1 = this$1;
        this.started = false;
        this.reader = null;
        this.line = null;
    }
    
    @Override
    public boolean hasNext() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.started:Z
        //     4: ifne            50
        //     7: aload_0        
        //     8: iconst_1       
        //     9: putfield        com/netflix/falkor/RemotePathEvaluator$1$1.started:Z
        //    12: aload_0        
        //    13: new             Ljava/io/BufferedReader;
        //    16: dup            
        //    17: new             Ljava/io/InputStreamReader;
        //    20: dup            
        //    21: new             Ljava/net/URL;
        //    24: dup            
        //    25: aload_0        
        //    26: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.this$1:Lcom/netflix/falkor/RemotePathEvaluator$1;
        //    29: getfield        com/netflix/falkor/RemotePathEvaluator$1.val$builder:Ljava/lang/StringBuilder;
        //    32: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    35: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    38: invokevirtual   java/net/URL.openStream:()Ljava/io/InputStream;
        //    41: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    44: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    47: putfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //    50: aload_0        
        //    51: aload_0        
        //    52: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //    55: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    58: putfield        com/netflix/falkor/RemotePathEvaluator$1$1.line:Ljava/lang/String;
        //    61: aload_0        
        //    62: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.line:Ljava/lang/String;
        //    65: ifnull          70
        //    68: iconst_1       
        //    69: ireturn        
        //    70: aload_0        
        //    71: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //    74: astore_1       
        //    75: aload_1        
        //    76: ifnull          124
        //    79: aload_0        
        //    80: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //    83: invokevirtual   java/io/BufferedReader.close:()V
        //    86: aload_0        
        //    87: aconst_null    
        //    88: putfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //    91: iconst_0       
        //    92: ireturn        
        //    93: astore_1       
        //    94: aload_0        
        //    95: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //    98: ifnull          124
        //   101: aload_0        
        //   102: getfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //   105: invokevirtual   java/io/BufferedReader.close:()V
        //   108: aload_0        
        //   109: aconst_null    
        //   110: putfield        com/netflix/falkor/RemotePathEvaluator$1$1.reader:Ljava/io/BufferedReader;
        //   113: iconst_0       
        //   114: ireturn        
        //   115: astore_1       
        //   116: goto            108
        //   119: astore_1       
        //   120: goto            86
        //   123: astore_1       
        //   124: iconst_0       
        //   125: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     50     123    124    Ljava/lang/Exception;
        //  50     68     93     119    Ljava/lang/Exception;
        //  70     75     93     119    Ljava/lang/Exception;
        //  79     86     119    123    Ljava/lang/Exception;
        //  86     91     93     119    Ljava/lang/Exception;
        //  101    108    115    119    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0086:
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
    public PathBoundValue next() {
        PQL fromList;
        try {
            final JsonReader jsonReader = new JsonReader(new StringReader(this.line));
            jsonReader.beginObject();
            jsonReader.nextName();
            fromList = PQL.fromList(this.this$1.this$0.gson.fromJson(jsonReader, ArrayList.class));
            if (jsonReader.peek() == JsonToken.NAME) {
                jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                    return new PathBoundValue(fromList, new Option<Object>(PQL.fromList(this.this$1.this$0.gson.fromJson(jsonReader, ArrayList.class))));
                }
                final Object fromJson = this.this$1.this$0.gson.fromJson(jsonReader, this.this$1.this$0.getPathClass(fromList));
                if (fromJson instanceof PathBound) {
                    ((PathBound)fromJson).setPath(fromList);
                }
                return new PathBoundValue(fromList, new Option<Object>(fromJson));
            }
        }
        catch (Exception ex) {
            Log.w("RemotePathEvaluator", ex.toString());
            return null;
        }
        return new PathBoundValue(fromList, new Option<Object>());
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.mediaclient.Log;
import com.google.gson.stream.JsonToken;
import java.io.Reader;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.lang.reflect.Method;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import java.net.URI;
import com.google.gson.Gson;

public class RemotePathEvaluator extends BasePathEvaluator
{
    private static final String TAG = "RemotePathEvaluator";
    final Gson gson;
    Class<?> rootType;
    URI uri;
    
    public RemotePathEvaluator(final Class<?> clazz, final URI uri) {
        this(clazz, uri, new GsonBuilder());
    }
    
    public RemotePathEvaluator(final Class<?> rootType, final URI uri, final GsonBuilder gsonBuilder) {
        this.rootType = rootType;
        this.uri = uri;
        this.gson = gsonBuilder.registerTypeAdapter(PQL.class, new PQLAdapter()).create();
    }
    
    private Type getPathClass(final PQL pql) {
        final List<Object> keySegments = pql.getKeySegments();
        Type rootType = this.rootType;
        Type genericReturnType = null;
        Type type = null;
        final Iterator<Object> iterator = keySegments.iterator();
        String string;
        Class<?> clazz;
        Type genericReturnType2;
        Class<?> returnType;
        Class<?> clazz2;
        Method method;
        Class<?> clazz3;
        Class<?> clazz4;
        Block_9_Outer:Block_8_Outer:Label_0072_Outer:
        while (iterator.hasNext()) {
            string = iterator.next().toString();
            while (true) {
                Label_0248: {
                    if (genericReturnType == null) {
                        break Label_0248;
                    }
                    try {
                        if (this.parseInt(string) != null) {
                            clazz = null;
                            type = null;
                            rootType = genericReturnType;
                            genericReturnType = clazz;
                            continue Block_9_Outer;
                        }
                        break Label_0248;
                        // iftrue(Label_0024:, !genericReturnType2 instanceof ParameterizedType)
                        // iftrue(Label_0239:, !returnType instanceof Class)
                        // iftrue(Label_0171:, !List.class.isAssignableFrom((Class<?>)returnType))
                        // iftrue(Label_0024:, !Map.class.isAssignableFrom((Class<?>)returnType))
                    Block_7_Outer:
                        while (true) {
                            while (true) {
                                type = ((ParameterizedType)genericReturnType2).getActualTypeArguments()[1];
                                rootType = returnType;
                                genericReturnType = clazz2;
                                continue Block_9_Outer;
                                genericReturnType2 = method.getGenericReturnType();
                                rootType = returnType;
                                genericReturnType = clazz2;
                                type = clazz3;
                                continue Block_8_Outer;
                            }
                            while (true) {
                                genericReturnType = method.getGenericReturnType();
                                rootType = returnType;
                                type = clazz3;
                                continue Block_9_Outer;
                                clazz2 = null;
                                clazz3 = null;
                                method = ((Class<?>)rootType).getMethod("get" + string.substring(0, 1).toUpperCase() + string.substring(1), (Class<?>[])new Class[0]);
                                returnType = method.getReturnType();
                                continue Label_0072_Outer;
                            }
                            Label_0171: {
                                rootType = returnType;
                            }
                            genericReturnType = clazz2;
                            type = clazz3;
                            continue Block_7_Outer;
                        }
                        Label_0239: {
                            return JsonObject.class;
                        }
                    }
                    catch (Exception ex) {
                        return JsonObject.class;
                    }
                    break;
                }
                if (type == null) {
                    continue;
                }
                break;
            }
            genericReturnType = null;
            clazz4 = null;
            rootType = type;
            type = clazz4;
        }
        return rootType;
    }
    
    private Integer parseInt(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public Iterable<PathBoundValue> deleteAbsolute(final Iterable<PQL> iterable) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Iterable<PathBoundValue> getAbsolute(final Iterable<PQL> iterable) {
        final String string = this.uri.toString();
        final StringBuilder sb = new StringBuilder(string);
        int n = 0;
        while (true) {
            Label_0113: {
                if (string.indexOf("?") != -1) {
                    break Label_0113;
                }
                sb.append("?");
                sb.append("method=get&responseFormat=json&pathFormat=canonical&progressive=false");
                for (final PQL pql : iterable) {
                    ++n;
                    try {
                        sb.append("&path=").append(URLEncoder.encode(this.gson.toJson(pql), "UTF-8"));
                        continue;
                    }
                    catch (Exception ex) {
                        return new ArrayList<PathBoundValue>();
                    }
                    break Label_0113;
                }
                if (n == 0) {
                    return new ArrayList<PathBoundValue>();
                }
                return new Iterable<PathBoundValue>() {
                    @Override
                    public Iterator<PathBoundValue> iterator() {
                        return new Iterator<PathBoundValue>() {
                            String line = null;
                            private BufferedReader reader = null;
                            private boolean started = false;
                            
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
                                //    76: ifnull          123
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
                                //    98: ifnull          123
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
                                //   123: iconst_0       
                                //   124: ireturn        
                                //   125: astore_1       
                                //   126: iconst_0       
                                //   127: ireturn        
                                //    Exceptions:
                                //  Try           Handler
                                //  Start  End    Start  End    Type                 
                                //  -----  -----  -----  -----  ---------------------
                                //  12     50     125    128    Ljava/lang/Exception;
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
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                                PathBoundValue pathBoundValue;
                                try {
                                    final JsonReader jsonReader = new JsonReader(new StringReader(this.line));
                                    jsonReader.beginObject();
                                    jsonReader.nextName();
                                    final PQL fromList = PQL.fromList(RemotePathEvaluator.this.gson.fromJson(jsonReader, ArrayList.class));
                                    if (jsonReader.peek() != JsonToken.NAME) {
                                        return new PathBoundValue(fromList, new Option<Object>());
                                    }
                                    jsonReader.nextName();
                                    if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                                        pathBoundValue = new PathBoundValue(fromList, new Option<Object>(PQL.fromList(RemotePathEvaluator.this.gson.fromJson(jsonReader, ArrayList.class))));
                                    }
                                    else {
                                        final Object fromJson = RemotePathEvaluator.this.gson.fromJson(jsonReader, RemotePathEvaluator.this.getPathClass(fromList));
                                        if (fromJson instanceof PathBound) {
                                            ((PathBound)fromJson).setPath(fromList);
                                        }
                                        pathBoundValue = new PathBoundValue(fromList, new Option<Object>(fromJson));
                                    }
                                }
                                catch (Exception ex) {
                                    Log.w("RemotePathEvaluator", ex.toString());
                                    return null;
                                }
                                return pathBoundValue;
                            }
                            
                            @Override
                            public void remove() {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }
                };
            }
            sb.append("&");
            continue;
        }
    }
    
    @Override
    public AbstractPathEvaluator getRoot() {
        return this;
    }
    
    @Override
    public Iterable<PathBoundValue> setAbsolute(final Iterable<PathBoundValue> iterable) {
        throw new UnsupportedOperationException();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.lang.reflect.Method;
import com.facebook.internal.Utility;
import java.lang.annotation.Annotation;
import com.facebook.FacebookGraphObjectException;
import org.json.JSONArray;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import org.json.JSONObject;
import java.util.Locale;
import java.util.HashSet;
import java.text.SimpleDateFormat;

public final class GraphObject$Factory
{
    private static final SimpleDateFormat[] dateFormats;
    private static final HashSet<Class<?>> verifiedGraphObjectClasses;
    
    static {
        verifiedGraphObjectClasses = new HashSet<Class<?>>();
        dateFormats = new SimpleDateFormat[] { new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US) };
    }
    
    static <U> U coerceValueToExpectedType(final Object p0, final Class<U> p1, final ParameterizedType p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       10
        //     4: aconst_null    
        //     5: astore          5
        //     7: aload           5
        //     9: areturn        
        //    10: aload_0        
        //    11: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    14: astore          6
        //    16: aload_0        
        //    17: astore          5
        //    19: aload_1        
        //    20: aload           6
        //    22: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    25: ifne            7
        //    28: aload_0        
        //    29: astore          5
        //    31: aload_1        
        //    32: invokevirtual   java/lang/Class.isPrimitive:()Z
        //    35: ifne            7
        //    38: ldc             Lcom/facebook/model/GraphObject;.class
        //    40: aload_1        
        //    41: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    44: ifeq            118
        //    47: ldc             Lorg/json/JSONObject;.class
        //    49: aload           6
        //    51: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    54: ifeq            66
        //    57: aload_1        
        //    58: aload_0        
        //    59: checkcast       Lorg/json/JSONObject;
        //    62: invokestatic    com/facebook/model/GraphObject$Factory.createGraphObjectProxy:(Ljava/lang/Class;Lorg/json/JSONObject;)Lcom/facebook/model/GraphObject;
        //    65: areturn        
        //    66: ldc             Lcom/facebook/model/GraphObject;.class
        //    68: aload           6
        //    70: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    73: ifeq            87
        //    76: aload_0        
        //    77: checkcast       Lcom/facebook/model/GraphObject;
        //    80: aload_1        
        //    81: invokeinterface com/facebook/model/GraphObject.cast:(Ljava/lang/Class;)Lcom/facebook/model/GraphObject;
        //    86: areturn        
        //    87: new             Lcom/facebook/FacebookGraphObjectException;
        //    90: dup            
        //    91: new             Ljava/lang/StringBuilder;
        //    94: dup            
        //    95: invokespecial   java/lang/StringBuilder.<init>:()V
        //    98: ldc             "Can't create GraphObject from "
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload           6
        //   105: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   111: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   114: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   117: athrow         
        //   118: ldc             Ljava/lang/Iterable;.class
        //   120: aload_1        
        //   121: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   124: ifne            154
        //   127: ldc             Ljava/util/Collection;.class
        //   129: aload_1        
        //   130: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   133: ifne            154
        //   136: ldc             Ljava/util/List;.class
        //   138: aload_1        
        //   139: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   142: ifne            154
        //   145: ldc             Lcom/facebook/model/GraphObjectList;.class
        //   147: aload_1        
        //   148: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   151: ifeq            281
        //   154: aload_2        
        //   155: ifnonnull       188
        //   158: new             Lcom/facebook/FacebookGraphObjectException;
        //   161: dup            
        //   162: new             Ljava/lang/StringBuilder;
        //   165: dup            
        //   166: invokespecial   java/lang/StringBuilder.<init>:()V
        //   169: ldc             "can't infer generic type of: "
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: aload_1        
        //   175: invokevirtual   java/lang/Class.toString:()Ljava/lang/String;
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   184: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   187: athrow         
        //   188: aload_2        
        //   189: invokeinterface java/lang/reflect/ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;
        //   194: astore_1       
        //   195: aload_1        
        //   196: ifnull          214
        //   199: aload_1        
        //   200: arraylength    
        //   201: iconst_1       
        //   202: if_icmpne       214
        //   205: aload_1        
        //   206: iconst_0       
        //   207: aaload         
        //   208: instanceof      Ljava/lang/Class;
        //   211: ifne            224
        //   214: new             Lcom/facebook/FacebookGraphObjectException;
        //   217: dup            
        //   218: ldc             "Expect collection properties to be of a type with exactly one generic parameter."
        //   220: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   223: athrow         
        //   224: aload_1        
        //   225: iconst_0       
        //   226: aaload         
        //   227: checkcast       Ljava/lang/Class;
        //   230: astore_1       
        //   231: ldc             Lorg/json/JSONArray;.class
        //   233: aload           6
        //   235: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   238: ifeq            250
        //   241: aload_0        
        //   242: checkcast       Lorg/json/JSONArray;
        //   245: aload_1        
        //   246: invokestatic    com/facebook/model/GraphObject$Factory.createList:(Lorg/json/JSONArray;Ljava/lang/Class;)Lcom/facebook/model/GraphObjectList;
        //   249: areturn        
        //   250: new             Lcom/facebook/FacebookGraphObjectException;
        //   253: dup            
        //   254: new             Ljava/lang/StringBuilder;
        //   257: dup            
        //   258: invokespecial   java/lang/StringBuilder.<init>:()V
        //   261: ldc             "Can't create Collection from "
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: aload           6
        //   268: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   277: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   280: athrow         
        //   281: ldc             Ljava/lang/String;.class
        //   283: aload_1        
        //   284: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   287: ifeq            348
        //   290: ldc             Ljava/lang/Double;.class
        //   292: aload           6
        //   294: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   297: ifne            310
        //   300: ldc             Ljava/lang/Float;.class
        //   302: aload           6
        //   304: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   307: ifeq            324
        //   310: ldc             "%f"
        //   312: iconst_1       
        //   313: anewarray       Ljava/lang/Object;
        //   316: dup            
        //   317: iconst_0       
        //   318: aload_0        
        //   319: aastore        
        //   320: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   323: areturn        
        //   324: ldc             Ljava/lang/Number;.class
        //   326: aload           6
        //   328: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   331: ifeq            416
        //   334: ldc             "%d"
        //   336: iconst_1       
        //   337: anewarray       Ljava/lang/Object;
        //   340: dup            
        //   341: iconst_0       
        //   342: aload_0        
        //   343: aastore        
        //   344: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   347: areturn        
        //   348: ldc             Ljava/util/Date;.class
        //   350: aload_1        
        //   351: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   354: ifeq            416
        //   357: ldc             Ljava/lang/String;.class
        //   359: aload           6
        //   361: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   364: ifeq            416
        //   367: getstatic       com/facebook/model/GraphObject$Factory.dateFormats:[Ljava/text/SimpleDateFormat;
        //   370: astore_2       
        //   371: aload_2        
        //   372: arraylength    
        //   373: istore          4
        //   375: iconst_0       
        //   376: istore_3       
        //   377: iload_3        
        //   378: iload           4
        //   380: if_icmpge       416
        //   383: aload_2        
        //   384: iload_3        
        //   385: aaload         
        //   386: astore          5
        //   388: aload           5
        //   390: aload_0        
        //   391: checkcast       Ljava/lang/String;
        //   394: invokevirtual   java/text/SimpleDateFormat.parse:(Ljava/lang/String;)Ljava/util/Date;
        //   397: astore          5
        //   399: aload           5
        //   401: ifnull          409
        //   404: aload           5
        //   406: areturn        
        //   407: astore          5
        //   409: iload_3        
        //   410: iconst_1       
        //   411: iadd           
        //   412: istore_3       
        //   413: goto            377
        //   416: new             Lcom/facebook/FacebookGraphObjectException;
        //   419: dup            
        //   420: new             Ljava/lang/StringBuilder;
        //   423: dup            
        //   424: invokespecial   java/lang/StringBuilder.<init>:()V
        //   427: ldc             "Can't convert type"
        //   429: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: aload           6
        //   434: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   437: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   440: ldc             " to "
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: aload_1        
        //   446: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   449: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   452: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   455: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   458: athrow         
        //    Signature:
        //  <U:Ljava/lang/Object;>(Ljava/lang/Object;Ljava/lang/Class<TU;>;Ljava/lang/reflect/ParameterizedType;)TU;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  388    399    407    409    Ljava/text/ParseException;
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
    
    static String convertCamelCaseToLowercaseWithUnderscores(final String s) {
        return s.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(Locale.US);
    }
    
    public static GraphObject create() {
        return create(GraphObject.class);
    }
    
    public static <T extends GraphObject> T create(final Class<T> clazz) {
        return createGraphObjectProxy(clazz, new JSONObject());
    }
    
    public static GraphObject create(final JSONObject jsonObject) {
        return create(jsonObject, GraphObject.class);
    }
    
    public static <T extends GraphObject> T create(final JSONObject jsonObject, final Class<T> clazz) {
        return createGraphObjectProxy(clazz, jsonObject);
    }
    
    private static <T extends GraphObject> T createGraphObjectProxy(final Class<T> clazz, final JSONObject jsonObject) {
        verifyCanProxyClass(clazz);
        return (T)Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[] { clazz }, new GraphObject$Factory$GraphObjectProxy(jsonObject, clazz));
    }
    
    private static Map<String, Object> createGraphObjectProxyForMap(final JSONObject jsonObject) {
        return (Map<String, Object>)Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[] { Map.class }, new GraphObject$Factory$GraphObjectProxy(jsonObject, Map.class));
    }
    
    public static <T> GraphObjectList<T> createList(final Class<T> clazz) {
        return createList(new JSONArray(), clazz);
    }
    
    public static <T> GraphObjectList<T> createList(final JSONArray jsonArray, final Class<T> clazz) {
        return new GraphObject$Factory$GraphObjectListImpl<T>(jsonArray, clazz);
    }
    
    private static Object getUnderlyingJSONObject(final Object o) {
        final Class<?> class1 = o.getClass();
        Object innerJSONObject;
        if (GraphObject.class.isAssignableFrom(class1)) {
            innerJSONObject = ((GraphObject)o).getInnerJSONObject();
        }
        else {
            innerJSONObject = o;
            if (GraphObjectList.class.isAssignableFrom(class1)) {
                return ((GraphObjectList)o).getInnerJSONArray();
            }
        }
        return innerJSONObject;
    }
    
    private static <T extends GraphObject> boolean hasClassBeenVerified(final Class<T> clazz) {
        synchronized (GraphObject$Factory.class) {
            return GraphObject$Factory.verifiedGraphObjectClasses.contains(clazz);
        }
    }
    
    public static boolean hasSameId(final GraphObject graphObject, final GraphObject graphObject2) {
        if (graphObject != null && graphObject2 != null && graphObject.asMap().containsKey("id") && graphObject2.asMap().containsKey("id")) {
            if (graphObject.equals(graphObject2)) {
                return true;
            }
            final Object property = graphObject.getProperty("id");
            final Object property2 = graphObject2.getProperty("id");
            if (property != null && property2 != null && property instanceof String && property2 instanceof String) {
                return property.equals(property2);
            }
        }
        return false;
    }
    
    private static <T extends GraphObject> void recordClassHasBeenVerified(final Class<T> clazz) {
        synchronized (GraphObject$Factory.class) {
            GraphObject$Factory.verifiedGraphObjectClasses.add(clazz);
        }
    }
    
    private static <T extends GraphObject> void verifyCanProxyClass(final Class<T> clazz) {
        if (hasClassBeenVerified(clazz)) {
            return;
        }
        if (!clazz.isInterface()) {
            throw new FacebookGraphObjectException("Factory can only wrap interfaces, not class: " + clazz.getName());
        }
        final Method[] methods = clazz.getMethods();
        for (int length = methods.length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            final String name = method.getName();
            final int length2 = method.getParameterTypes().length;
            final Class<?> returnType = method.getReturnType();
            final boolean annotationPresent = method.isAnnotationPresent(PropertyName.class);
            if (!method.getDeclaringClass().isAssignableFrom(GraphObject.class)) {
                if (length2 == 1 && returnType == Void.TYPE) {
                    if (annotationPresent) {
                        if (!Utility.isNullOrEmpty(method.getAnnotation(PropertyName.class).value())) {
                            continue;
                        }
                    }
                    else if (name.startsWith("set") && name.length() > 3) {
                        continue;
                    }
                }
                else if (length2 == 0 && returnType != Void.TYPE) {
                    if (annotationPresent) {
                        if (!Utility.isNullOrEmpty(method.getAnnotation(PropertyName.class).value())) {
                            continue;
                        }
                    }
                    else if (name.startsWith("get") && name.length() > 3) {
                        continue;
                    }
                }
                throw new FacebookGraphObjectException("Factory can't proxy method: " + method.toString());
            }
        }
        recordClassHasBeenVerified((Class<GraphObject>)clazz);
    }
}

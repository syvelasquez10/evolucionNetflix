// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.lang.reflect.Method;
import com.facebook.internal.Utility;
import java.lang.annotation.Annotation;
import com.facebook.FacebookGraphObjectException;
import java.util.Iterator;
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
        //     1: ifnonnull       52
        //     4: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //     7: aload_1        
        //     8: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    11: ifeq            23
        //    14: iconst_0       
        //    15: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    18: astore          5
        //    20: aload           5
        //    22: areturn        
        //    23: getstatic       java/lang/Character.TYPE:Ljava/lang/Class;
        //    26: aload_1        
        //    27: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    30: ifeq            38
        //    33: iconst_0       
        //    34: invokestatic    java/lang/Character.valueOf:(C)Ljava/lang/Character;
        //    37: areturn        
        //    38: aload_1        
        //    39: invokevirtual   java/lang/Class.isPrimitive:()Z
        //    42: ifeq            50
        //    45: iconst_0       
        //    46: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    49: areturn        
        //    50: aconst_null    
        //    51: areturn        
        //    52: aload_0        
        //    53: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    56: astore          6
        //    58: aload_0        
        //    59: astore          5
        //    61: aload_1        
        //    62: aload           6
        //    64: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    67: ifne            20
        //    70: aload_0        
        //    71: astore          5
        //    73: aload_1        
        //    74: invokevirtual   java/lang/Class.isPrimitive:()Z
        //    77: ifne            20
        //    80: ldc             Lcom/facebook/model/GraphObject;.class
        //    82: aload_1        
        //    83: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    86: ifeq            160
        //    89: ldc             Lorg/json/JSONObject;.class
        //    91: aload           6
        //    93: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    96: ifeq            108
        //    99: aload_1        
        //   100: aload_0        
        //   101: checkcast       Lorg/json/JSONObject;
        //   104: invokestatic    com/facebook/model/GraphObject$Factory.createGraphObjectProxy:(Ljava/lang/Class;Lorg/json/JSONObject;)Lcom/facebook/model/GraphObject;
        //   107: areturn        
        //   108: ldc             Lcom/facebook/model/GraphObject;.class
        //   110: aload           6
        //   112: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   115: ifeq            129
        //   118: aload_0        
        //   119: checkcast       Lcom/facebook/model/GraphObject;
        //   122: aload_1        
        //   123: invokeinterface com/facebook/model/GraphObject.cast:(Ljava/lang/Class;)Lcom/facebook/model/GraphObject;
        //   128: areturn        
        //   129: new             Lcom/facebook/FacebookGraphObjectException;
        //   132: dup            
        //   133: new             Ljava/lang/StringBuilder;
        //   136: dup            
        //   137: invokespecial   java/lang/StringBuilder.<init>:()V
        //   140: ldc             "Can't create GraphObject from "
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: aload           6
        //   147: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   156: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   159: athrow         
        //   160: ldc             Ljava/lang/Iterable;.class
        //   162: aload_1        
        //   163: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   166: ifne            196
        //   169: ldc             Ljava/util/Collection;.class
        //   171: aload_1        
        //   172: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   175: ifne            196
        //   178: ldc             Ljava/util/List;.class
        //   180: aload_1        
        //   181: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   184: ifne            196
        //   187: ldc             Lcom/facebook/model/GraphObjectList;.class
        //   189: aload_1        
        //   190: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   193: ifeq            323
        //   196: aload_2        
        //   197: ifnonnull       230
        //   200: new             Lcom/facebook/FacebookGraphObjectException;
        //   203: dup            
        //   204: new             Ljava/lang/StringBuilder;
        //   207: dup            
        //   208: invokespecial   java/lang/StringBuilder.<init>:()V
        //   211: ldc             "can't infer generic type of: "
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: aload_1        
        //   217: invokevirtual   java/lang/Class.toString:()Ljava/lang/String;
        //   220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   226: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   229: athrow         
        //   230: aload_2        
        //   231: invokeinterface java/lang/reflect/ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;
        //   236: astore_1       
        //   237: aload_1        
        //   238: ifnull          256
        //   241: aload_1        
        //   242: arraylength    
        //   243: iconst_1       
        //   244: if_icmpne       256
        //   247: aload_1        
        //   248: iconst_0       
        //   249: aaload         
        //   250: instanceof      Ljava/lang/Class;
        //   253: ifne            266
        //   256: new             Lcom/facebook/FacebookGraphObjectException;
        //   259: dup            
        //   260: ldc             "Expect collection properties to be of a type with exactly one generic parameter."
        //   262: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   265: athrow         
        //   266: aload_1        
        //   267: iconst_0       
        //   268: aaload         
        //   269: checkcast       Ljava/lang/Class;
        //   272: astore_1       
        //   273: ldc             Lorg/json/JSONArray;.class
        //   275: aload           6
        //   277: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   280: ifeq            292
        //   283: aload_0        
        //   284: checkcast       Lorg/json/JSONArray;
        //   287: aload_1        
        //   288: invokestatic    com/facebook/model/GraphObject$Factory.createList:(Lorg/json/JSONArray;Ljava/lang/Class;)Lcom/facebook/model/GraphObjectList;
        //   291: areturn        
        //   292: new             Lcom/facebook/FacebookGraphObjectException;
        //   295: dup            
        //   296: new             Ljava/lang/StringBuilder;
        //   299: dup            
        //   300: invokespecial   java/lang/StringBuilder.<init>:()V
        //   303: ldc             "Can't create Collection from "
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: aload           6
        //   310: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   316: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   319: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   322: athrow         
        //   323: ldc             Ljava/lang/String;.class
        //   325: aload_1        
        //   326: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   329: ifeq            390
        //   332: ldc             Ljava/lang/Double;.class
        //   334: aload           6
        //   336: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   339: ifne            352
        //   342: ldc             Ljava/lang/Float;.class
        //   344: aload           6
        //   346: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   349: ifeq            366
        //   352: ldc             "%f"
        //   354: iconst_1       
        //   355: anewarray       Ljava/lang/Object;
        //   358: dup            
        //   359: iconst_0       
        //   360: aload_0        
        //   361: aastore        
        //   362: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   365: areturn        
        //   366: ldc             Ljava/lang/Number;.class
        //   368: aload           6
        //   370: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   373: ifeq            458
        //   376: ldc             "%d"
        //   378: iconst_1       
        //   379: anewarray       Ljava/lang/Object;
        //   382: dup            
        //   383: iconst_0       
        //   384: aload_0        
        //   385: aastore        
        //   386: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   389: areturn        
        //   390: ldc             Ljava/util/Date;.class
        //   392: aload_1        
        //   393: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   396: ifeq            458
        //   399: ldc             Ljava/lang/String;.class
        //   401: aload           6
        //   403: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   406: ifeq            458
        //   409: getstatic       com/facebook/model/GraphObject$Factory.dateFormats:[Ljava/text/SimpleDateFormat;
        //   412: astore_2       
        //   413: aload_2        
        //   414: arraylength    
        //   415: istore          4
        //   417: iconst_0       
        //   418: istore_3       
        //   419: iload_3        
        //   420: iload           4
        //   422: if_icmpge       458
        //   425: aload_2        
        //   426: iload_3        
        //   427: aaload         
        //   428: astore          5
        //   430: aload           5
        //   432: aload_0        
        //   433: checkcast       Ljava/lang/String;
        //   436: invokevirtual   java/text/SimpleDateFormat.parse:(Ljava/lang/String;)Ljava/util/Date;
        //   439: astore          5
        //   441: aload           5
        //   443: ifnull          451
        //   446: aload           5
        //   448: areturn        
        //   449: astore          5
        //   451: iload_3        
        //   452: iconst_1       
        //   453: iadd           
        //   454: istore_3       
        //   455: goto            419
        //   458: new             Lcom/facebook/FacebookGraphObjectException;
        //   461: dup            
        //   462: new             Ljava/lang/StringBuilder;
        //   465: dup            
        //   466: invokespecial   java/lang/StringBuilder.<init>:()V
        //   469: ldc             "Can't convert type"
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: aload           6
        //   476: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   479: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   482: ldc             " to "
        //   484: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   487: aload_1        
        //   488: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   491: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   494: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   497: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
        //   500: athrow         
        //    Signature:
        //  <U:Ljava/lang/Object;>(Ljava/lang/Object;Ljava/lang/Class<TU;>;Ljava/lang/reflect/ParameterizedType;)TU;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  430    441    449    451    Ljava/text/ParseException;
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
        Object o2;
        if (o == null) {
            o2 = null;
        }
        else {
            final Class<?> class1 = o.getClass();
            if (GraphObject.class.isAssignableFrom(class1)) {
                return ((GraphObject)o).getInnerJSONObject();
            }
            if (GraphObjectList.class.isAssignableFrom(class1)) {
                return ((GraphObjectList)o).getInnerJSONArray();
            }
            o2 = o;
            if (Iterable.class.isAssignableFrom(class1)) {
                final JSONArray jsonArray = new JSONArray();
                for (final GraphObject next : (Iterable)o) {
                    if (GraphObject.class.isAssignableFrom(next.getClass())) {
                        jsonArray.put((Object)next.getInnerJSONObject());
                    }
                    else {
                        jsonArray.put((Object)next);
                    }
                }
                return jsonArray;
            }
        }
        return o2;
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

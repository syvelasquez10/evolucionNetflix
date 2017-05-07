// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.util.Iterator;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONException;
import com.facebook.internal.Validate;
import java.util.AbstractList;
import java.lang.reflect.Method;
import com.facebook.internal.Utility;
import java.lang.annotation.Annotation;
import com.facebook.FacebookGraphObjectException;
import org.json.JSONArray;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.ParameterizedType;
import java.util.Locale;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import java.util.Map;

public interface GraphObject
{
    Map<String, Object> asMap();
    
     <T extends GraphObject> T cast(final Class<T> p0);
    
    JSONObject getInnerJSONObject();
    
    Object getProperty(final String p0);
    
    void removeProperty(final String p0);
    
    void setProperty(final String p0, final Object p1);
    
    public static final class Factory
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
            //     1: ifnonnull       6
            //     4: aconst_null    
            //     5: areturn        
            //     6: aload_0        
            //     7: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
            //    10: astore          5
            //    12: aload_1        
            //    13: aload           5
            //    15: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //    18: ifeq            23
            //    21: aload_0        
            //    22: areturn        
            //    23: aload_1        
            //    24: invokevirtual   java/lang/Class.isPrimitive:()Z
            //    27: ifeq            32
            //    30: aload_0        
            //    31: areturn        
            //    32: ldc             Lcom/facebook/model/GraphObject;.class
            //    34: aload_1        
            //    35: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //    38: ifeq            112
            //    41: ldc             Lorg/json/JSONObject;.class
            //    43: aload           5
            //    45: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //    48: ifeq            60
            //    51: aload_1        
            //    52: aload_0        
            //    53: checkcast       Lorg/json/JSONObject;
            //    56: invokestatic    com/facebook/model/GraphObject$Factory.createGraphObjectProxy:(Ljava/lang/Class;Lorg/json/JSONObject;)Lcom/facebook/model/GraphObject;
            //    59: areturn        
            //    60: ldc             Lcom/facebook/model/GraphObject;.class
            //    62: aload           5
            //    64: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //    67: ifeq            81
            //    70: aload_0        
            //    71: checkcast       Lcom/facebook/model/GraphObject;
            //    74: aload_1        
            //    75: invokeinterface com/facebook/model/GraphObject.cast:(Ljava/lang/Class;)Lcom/facebook/model/GraphObject;
            //    80: areturn        
            //    81: new             Lcom/facebook/FacebookGraphObjectException;
            //    84: dup            
            //    85: new             Ljava/lang/StringBuilder;
            //    88: dup            
            //    89: invokespecial   java/lang/StringBuilder.<init>:()V
            //    92: ldc             "Can't create GraphObject from "
            //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    97: aload           5
            //    99: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
            //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   105: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   108: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
            //   111: athrow         
            //   112: ldc             Ljava/lang/Iterable;.class
            //   114: aload_1        
            //   115: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   118: ifne            148
            //   121: ldc             Ljava/util/Collection;.class
            //   123: aload_1        
            //   124: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   127: ifne            148
            //   130: ldc             Ljava/util/List;.class
            //   132: aload_1        
            //   133: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   136: ifne            148
            //   139: ldc             Lcom/facebook/model/GraphObjectList;.class
            //   141: aload_1        
            //   142: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   145: ifeq            275
            //   148: aload_2        
            //   149: ifnonnull       182
            //   152: new             Lcom/facebook/FacebookGraphObjectException;
            //   155: dup            
            //   156: new             Ljava/lang/StringBuilder;
            //   159: dup            
            //   160: invokespecial   java/lang/StringBuilder.<init>:()V
            //   163: ldc             "can't infer generic type of: "
            //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   168: aload_1        
            //   169: invokevirtual   java/lang/Class.toString:()Ljava/lang/String;
            //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   175: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   178: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
            //   181: athrow         
            //   182: aload_2        
            //   183: invokeinterface java/lang/reflect/ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;
            //   188: astore_1       
            //   189: aload_1        
            //   190: ifnull          208
            //   193: aload_1        
            //   194: arraylength    
            //   195: iconst_1       
            //   196: if_icmpne       208
            //   199: aload_1        
            //   200: iconst_0       
            //   201: aaload         
            //   202: instanceof      Ljava/lang/Class;
            //   205: ifne            218
            //   208: new             Lcom/facebook/FacebookGraphObjectException;
            //   211: dup            
            //   212: ldc             "Expect collection properties to be of a type with exactly one generic parameter."
            //   214: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
            //   217: athrow         
            //   218: aload_1        
            //   219: iconst_0       
            //   220: aaload         
            //   221: checkcast       Ljava/lang/Class;
            //   224: astore_1       
            //   225: ldc             Lorg/json/JSONArray;.class
            //   227: aload           5
            //   229: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //   232: ifeq            244
            //   235: aload_0        
            //   236: checkcast       Lorg/json/JSONArray;
            //   239: aload_1        
            //   240: invokestatic    com/facebook/model/GraphObject$Factory.createList:(Lorg/json/JSONArray;Ljava/lang/Class;)Lcom/facebook/model/GraphObjectList;
            //   243: areturn        
            //   244: new             Lcom/facebook/FacebookGraphObjectException;
            //   247: dup            
            //   248: new             Ljava/lang/StringBuilder;
            //   251: dup            
            //   252: invokespecial   java/lang/StringBuilder.<init>:()V
            //   255: ldc             "Can't create Collection from "
            //   257: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   260: aload           5
            //   262: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
            //   265: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   268: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   271: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
            //   274: athrow         
            //   275: ldc             Ljava/lang/String;.class
            //   277: aload_1        
            //   278: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   281: ifeq            342
            //   284: ldc             Ljava/lang/Double;.class
            //   286: aload           5
            //   288: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //   291: ifne            304
            //   294: ldc             Ljava/lang/Float;.class
            //   296: aload           5
            //   298: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //   301: ifeq            318
            //   304: ldc             "%f"
            //   306: iconst_1       
            //   307: anewarray       Ljava/lang/Object;
            //   310: dup            
            //   311: iconst_0       
            //   312: aload_0        
            //   313: aastore        
            //   314: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   317: areturn        
            //   318: ldc             Ljava/lang/Number;.class
            //   320: aload           5
            //   322: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //   325: ifeq            410
            //   328: ldc             "%d"
            //   330: iconst_1       
            //   331: anewarray       Ljava/lang/Object;
            //   334: dup            
            //   335: iconst_0       
            //   336: aload_0        
            //   337: aastore        
            //   338: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   341: areturn        
            //   342: ldc             Ljava/util/Date;.class
            //   344: aload_1        
            //   345: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   348: ifeq            410
            //   351: ldc             Ljava/lang/String;.class
            //   353: aload           5
            //   355: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //   358: ifeq            410
            //   361: getstatic       com/facebook/model/GraphObject$Factory.dateFormats:[Ljava/text/SimpleDateFormat;
            //   364: astore_2       
            //   365: aload_2        
            //   366: arraylength    
            //   367: istore          4
            //   369: iconst_0       
            //   370: istore_3       
            //   371: iload_3        
            //   372: iload           4
            //   374: if_icmpge       410
            //   377: aload_2        
            //   378: iload_3        
            //   379: aaload         
            //   380: astore          6
            //   382: aload           6
            //   384: aload_0        
            //   385: checkcast       Ljava/lang/String;
            //   388: invokevirtual   java/text/SimpleDateFormat.parse:(Ljava/lang/String;)Ljava/util/Date;
            //   391: astore          6
            //   393: aload           6
            //   395: ifnull          403
            //   398: aload           6
            //   400: areturn        
            //   401: astore          6
            //   403: iload_3        
            //   404: iconst_1       
            //   405: iadd           
            //   406: istore_3       
            //   407: goto            371
            //   410: new             Lcom/facebook/FacebookGraphObjectException;
            //   413: dup            
            //   414: new             Ljava/lang/StringBuilder;
            //   417: dup            
            //   418: invokespecial   java/lang/StringBuilder.<init>:()V
            //   421: ldc             "Can't convert type"
            //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   426: aload           5
            //   428: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
            //   431: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   434: ldc             " to "
            //   436: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   439: aload_1        
            //   440: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
            //   443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   446: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   449: invokespecial   com/facebook/FacebookGraphObjectException.<init>:(Ljava/lang/String;)V
            //   452: athrow         
            //    Signature:
            //  <U:Ljava/lang/Object;>(Ljava/lang/Object;Ljava/lang/Class<TU;>;Ljava/lang/reflect/ParameterizedType;)TU;
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                      
            //  -----  -----  -----  -----  --------------------------
            //  382    393    401    403    Ljava/text/ParseException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            return (T)Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[] { clazz }, new GraphObjectProxy(jsonObject, clazz));
        }
        
        private static Map<String, Object> createGraphObjectProxyForMap(final JSONObject jsonObject) {
            return (Map<String, Object>)Proxy.newProxyInstance(GraphObject.class.getClassLoader(), new Class[] { Map.class }, new GraphObjectProxy(jsonObject, Map.class));
        }
        
        public static <T> GraphObjectList<T> createList(final Class<T> clazz) {
            return createList(new JSONArray(), clazz);
        }
        
        public static <T> GraphObjectList<T> createList(final JSONArray jsonArray, final Class<T> clazz) {
            return new GraphObjectListImpl<T>(jsonArray, clazz);
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
            synchronized (Factory.class) {
                return Factory.verifiedGraphObjectClasses.contains(clazz);
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
            synchronized (Factory.class) {
                Factory.verifiedGraphObjectClasses.add(clazz);
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
        
        private static final class GraphObjectListImpl<T> extends AbstractList<T> implements GraphObjectList<T>
        {
            private final Class<?> itemType;
            private final JSONArray state;
            
            public GraphObjectListImpl(final JSONArray state, final Class<?> itemType) {
                Validate.notNull(state, "state");
                Validate.notNull(itemType, "itemType");
                this.state = state;
                this.itemType = itemType;
            }
            
            private void checkIndex(final int n) {
                if (n < 0 || n >= this.state.length()) {
                    throw new IndexOutOfBoundsException();
                }
            }
            
            private void put(final int n, final T t) {
                final Object access$200 = getUnderlyingJSONObject(t);
                try {
                    this.state.put(n, access$200);
                }
                catch (JSONException ex) {
                    throw new IllegalArgumentException((Throwable)ex);
                }
            }
            
            @Override
            public void add(final int n, final T t) {
                if (n < 0) {
                    throw new IndexOutOfBoundsException();
                }
                if (n < this.size()) {
                    throw new UnsupportedOperationException("Only adding items at the end of the list is supported.");
                }
                this.put(n, t);
            }
            
            @Override
            public final <U extends GraphObject> GraphObjectList<U> castToListOf(final Class<U> clazz) {
                if (!GraphObject.class.isAssignableFrom(this.itemType)) {
                    throw new FacebookGraphObjectException("Can't cast GraphObjectCollection of non-GraphObject type " + this.itemType);
                }
                if (clazz.isAssignableFrom(this.itemType)) {
                    return (GraphObjectList<U>)this;
                }
                return Factory.createList(this.state, clazz);
            }
            
            @Override
            public void clear() {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public boolean equals(final Object o) {
                return this == o || (this.getClass() == o.getClass() && this.state.equals((Object)((GraphObjectListImpl)o).state));
            }
            
            @Override
            public T get(final int n) {
                this.checkIndex(n);
                return Factory.coerceValueToExpectedType(this.state.opt(n), this.itemType, null);
            }
            
            @Override
            public final JSONArray getInnerJSONArray() {
                return this.state;
            }
            
            @Override
            public int hashCode() {
                return this.state.hashCode();
            }
            
            @Override
            public boolean remove(final Object o) {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public boolean removeAll(final Collection<?> collection) {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public boolean retainAll(final Collection<?> collection) {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public T set(final int n, final T t) {
                this.checkIndex(n);
                final T value = this.get(n);
                this.put(n, t);
                return value;
            }
            
            @Override
            public int size() {
                return this.state.length();
            }
            
            @Override
            public String toString() {
                return String.format("GraphObjectList{itemType=%s, state=%s}", this.itemType.getSimpleName(), this.state);
            }
        }
        
        private static final class GraphObjectProxy extends ProxyBase<JSONObject>
        {
            private static final String CASTTOMAP_METHOD = "asMap";
            private static final String CAST_METHOD = "cast";
            private static final String CLEAR_METHOD = "clear";
            private static final String CONTAINSKEY_METHOD = "containsKey";
            private static final String CONTAINSVALUE_METHOD = "containsValue";
            private static final String ENTRYSET_METHOD = "entrySet";
            private static final String GETINNERJSONOBJECT_METHOD = "getInnerJSONObject";
            private static final String GETPROPERTY_METHOD = "getProperty";
            private static final String GET_METHOD = "get";
            private static final String ISEMPTY_METHOD = "isEmpty";
            private static final String KEYSET_METHOD = "keySet";
            private static final String PUTALL_METHOD = "putAll";
            private static final String PUT_METHOD = "put";
            private static final String REMOVEPROPERTY_METHOD = "removeProperty";
            private static final String REMOVE_METHOD = "remove";
            private static final String SETPROPERTY_METHOD = "setProperty";
            private static final String SIZE_METHOD = "size";
            private static final String VALUES_METHOD = "values";
            private final Class<?> graphObjectClass;
            
            public GraphObjectProxy(final JSONObject jsonObject, final Class<?> graphObjectClass) {
                super(jsonObject);
                this.graphObjectClass = graphObjectClass;
            }
            
            private final Object proxyGraphObjectGettersAndSetters(final Method method, final Object[] array) throws JSONException {
                final String name = method.getName();
                final int length = method.getParameterTypes().length;
                final PropertyName propertyName = method.getAnnotation(PropertyName.class);
                String s;
                if (propertyName != null) {
                    s = propertyName.value();
                }
                else {
                    s = Factory.convertCamelCaseToLowercaseWithUnderscores(name.substring(3));
                }
                if (length == 0) {
                    final Object opt = ((JSONObject)this.state).opt(s);
                    final Class<?> returnType = method.getReturnType();
                    final Type genericReturnType = method.getGenericReturnType();
                    ParameterizedType parameterizedType = null;
                    if (genericReturnType instanceof ParameterizedType) {
                        parameterizedType = (ParameterizedType)genericReturnType;
                    }
                    return Factory.coerceValueToExpectedType(opt, returnType, parameterizedType);
                }
                if (length == 1) {
                    final Object o = array[0];
                    Object o2;
                    if (GraphObject.class.isAssignableFrom(((GraphObjectList<Object>)o).getClass())) {
                        o2 = ((GraphObject)o).getInnerJSONObject();
                    }
                    else if (GraphObjectList.class.isAssignableFrom(((GraphObjectList<Object>)o).getClass())) {
                        o2 = ((GraphObjectList<Object>)o).getInnerJSONArray();
                    }
                    else {
                        o2 = o;
                        if (Iterable.class.isAssignableFrom(((GraphObjectList<Object>)o).getClass())) {
                            o2 = new JSONArray();
                            for (final GraphObject next : (GraphObjectList<Object>)o) {
                                if (GraphObject.class.isAssignableFrom(next.getClass())) {
                                    ((JSONArray)o2).put((Object)next.getInnerJSONObject());
                                }
                                else {
                                    ((JSONArray)o2).put((Object)next);
                                }
                            }
                        }
                    }
                    ((JSONObject)this.state).putOpt(s, o2);
                    return null;
                }
                return ((ProxyBase)this).throwUnexpectedMethodSignature(method);
            }
            
            private final Object proxyGraphObjectMethods(final Object o, final Method method, final Object[] jsonProperty) {
                final String name = method.getName();
                if (name.equals("cast")) {
                    final Class clazz = (Class)jsonProperty[0];
                    if (clazz != null && clazz.isAssignableFrom(this.graphObjectClass)) {
                        return o;
                    }
                    return createGraphObjectProxy((Class<GraphObject>)clazz, (JSONObject)this.state);
                }
                else {
                    if (name.equals("getInnerJSONObject")) {
                        return ((GraphObjectProxy)Proxy.getInvocationHandler(o)).state;
                    }
                    if (name.equals("asMap")) {
                        return createGraphObjectProxyForMap((JSONObject)this.state);
                    }
                    if (name.equals("getProperty")) {
                        return ((JSONObject)this.state).opt((String)jsonProperty[0]);
                    }
                    if (name.equals("setProperty")) {
                        return this.setJSONProperty(jsonProperty);
                    }
                    if (name.equals("removeProperty")) {
                        ((JSONObject)this.state).remove((String)jsonProperty[0]);
                        return null;
                    }
                    return ((ProxyBase)this).throwUnexpectedMethodSignature(method);
                }
            }
            
            private final Object proxyMapMethods(final Method method, final Object[] jsonProperty) {
                final String name = method.getName();
                if (name.equals("clear")) {
                    JsonUtil.jsonObjectClear((JSONObject)this.state);
                    return null;
                }
                if (name.equals("containsKey")) {
                    return ((JSONObject)this.state).has((String)jsonProperty[0]);
                }
                if (name.equals("containsValue")) {
                    return JsonUtil.jsonObjectContainsValue((JSONObject)this.state, jsonProperty[0]);
                }
                if (name.equals("entrySet")) {
                    return JsonUtil.jsonObjectEntrySet((JSONObject)this.state);
                }
                if (name.equals("get")) {
                    return ((JSONObject)this.state).opt((String)jsonProperty[0]);
                }
                if (name.equals("isEmpty")) {
                    return ((JSONObject)this.state).length() == 0;
                }
                if (name.equals("keySet")) {
                    return JsonUtil.jsonObjectKeySet((JSONObject)this.state);
                }
                if (name.equals("put")) {
                    return this.setJSONProperty(jsonProperty);
                }
                if (name.equals("putAll")) {
                    Map<String, Object> map = null;
                    if (jsonProperty[0] instanceof Map) {
                        map = (Map<String, Object>)jsonProperty[0];
                    }
                    else if (jsonProperty[0] instanceof GraphObject) {
                        map = ((GraphObject)jsonProperty[0]).asMap();
                    }
                    JsonUtil.jsonObjectPutAll((JSONObject)this.state, map);
                    return null;
                }
                if (name.equals("remove")) {
                    ((JSONObject)this.state).remove((String)jsonProperty[0]);
                    return null;
                }
                if (name.equals("size")) {
                    return ((JSONObject)this.state).length();
                }
                if (name.equals("values")) {
                    return JsonUtil.jsonObjectValues((JSONObject)this.state);
                }
                return ((ProxyBase)this).throwUnexpectedMethodSignature(method);
            }
            
            private Object setJSONProperty(final Object[] array) {
                final String s = (String)array[0];
                final Object access$200 = getUnderlyingJSONObject(array[1]);
                try {
                    ((JSONObject)this.state).putOpt(s, access$200);
                    return null;
                }
                catch (JSONException ex) {
                    throw new IllegalArgumentException((Throwable)ex);
                }
            }
            
            @Override
            public final Object invoke(final Object o, final Method method, final Object[] array) throws Throwable {
                final Class<?> declaringClass = method.getDeclaringClass();
                if (declaringClass == Object.class) {
                    return ((ProxyBase)this).proxyObjectMethods(o, method, array);
                }
                if (declaringClass == Map.class) {
                    return this.proxyMapMethods(method, array);
                }
                if (declaringClass == GraphObject.class) {
                    return this.proxyGraphObjectMethods(o, method, array);
                }
                if (GraphObject.class.isAssignableFrom(declaringClass)) {
                    return this.proxyGraphObjectGettersAndSetters(method, array);
                }
                return ((ProxyBase)this).throwUnexpectedMethodSignature(method);
            }
            
            @Override
            public String toString() {
                return String.format("GraphObject{graphObjectClass=%s, state=%s}", this.graphObjectClass.getSimpleName(), this.state);
            }
        }
        
        private abstract static class ProxyBase<STATE> implements InvocationHandler
        {
            private static final String EQUALS_METHOD = "equals";
            private static final String TOSTRING_METHOD = "toString";
            protected final STATE state;
            
            protected ProxyBase(final STATE state) {
                this.state = state;
            }
            
            protected final Object proxyObjectMethods(Object o, final Method method, final Object[] array) throws Throwable {
                final String name = method.getName();
                if (name.equals("equals")) {
                    o = array[0];
                    if (o == null) {
                        return false;
                    }
                    final InvocationHandler invocationHandler = Proxy.getInvocationHandler(o);
                    if (!(invocationHandler instanceof GraphObjectProxy)) {
                        return false;
                    }
                    return this.state.equals(((GraphObjectProxy)invocationHandler).state);
                }
                else {
                    if (name.equals("toString")) {
                        return this.toString();
                    }
                    return method.invoke(this.state, array);
                }
            }
            
            protected final Object throwUnexpectedMethodSignature(final Method method) {
                throw new FacebookGraphObjectException(this.getClass().getName() + " got an unexpected method signature: " + method.toString());
            }
        }
    }
}

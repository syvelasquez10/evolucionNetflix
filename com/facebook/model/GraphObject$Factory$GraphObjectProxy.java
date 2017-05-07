// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.lang.annotation.Annotation;
import com.facebook.FacebookGraphObjectException;
import org.json.JSONArray;
import java.lang.reflect.InvocationHandler;
import java.util.Locale;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import java.util.Map;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import com.facebook.internal.Utility;
import org.json.JSONObject;

final class GraphObject$Factory$GraphObjectProxy extends GraphObject$Factory$ProxyBase<JSONObject>
{
    private static final String CASTTOMAP_METHOD = "asMap";
    private static final String CAST_METHOD = "cast";
    private static final String CLEAR_METHOD = "clear";
    private static final String CONTAINSKEY_METHOD = "containsKey";
    private static final String CONTAINSVALUE_METHOD = "containsValue";
    private static final String ENTRYSET_METHOD = "entrySet";
    private static final String GETINNERJSONOBJECT_METHOD = "getInnerJSONObject";
    private static final String GETPROPERTYASLIST_METHOD = "getPropertyAsList";
    private static final String GETPROPERTYAS_METHOD = "getPropertyAs";
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
    
    public GraphObject$Factory$GraphObjectProxy(final JSONObject jsonObject, final Class<?> graphObjectClass) {
        super(jsonObject);
        this.graphObjectClass = graphObjectClass;
    }
    
    private Object createGraphObjectsFromParameters(final CreateGraphObject createGraphObject, final Object o) {
        Object list = o;
        if (createGraphObject != null) {
            list = o;
            if (!Utility.isNullOrEmpty(createGraphObject.value())) {
                final String value = createGraphObject.value();
                if (!List.class.isAssignableFrom(o.getClass())) {
                    final GraphObject create = GraphObject$Factory.create();
                    create.setProperty(value, o);
                    return create;
                }
                list = GraphObject$Factory.createList(GraphObject.class);
                for (final Object next : (List)o) {
                    final GraphObject create2 = GraphObject$Factory.create();
                    create2.setProperty(value, next);
                    ((List<GraphObject>)list).add(create2);
                }
            }
        }
        return list;
    }
    
    private final Object proxyGraphObjectGettersAndSetters(final Method method, final Object[] array) {
        final String name = method.getName();
        final int length = method.getParameterTypes().length;
        final PropertyName propertyName = method.getAnnotation(PropertyName.class);
        String s;
        if (propertyName != null) {
            s = propertyName.value();
        }
        else {
            s = GraphObject$Factory.convertCamelCaseToLowercaseWithUnderscores(name.substring(3));
        }
        if (length == 0) {
            final Object opt = ((JSONObject)this.state).opt(s);
            final Class<?> returnType = method.getReturnType();
            final Type genericReturnType = method.getGenericReturnType();
            ParameterizedType parameterizedType;
            if (genericReturnType instanceof ParameterizedType) {
                parameterizedType = (ParameterizedType)genericReturnType;
            }
            else {
                parameterizedType = null;
            }
            return GraphObject$Factory.coerceValueToExpectedType(opt, returnType, parameterizedType);
        }
        if (length == 1) {
            ((JSONObject)this.state).putOpt(s, getUnderlyingJSONObject(this.createGraphObjectsFromParameters(method.getAnnotation(CreateGraphObject.class), array[0])));
            return null;
        }
        return this.throwUnexpectedMethodSignature(method);
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
                return ((GraphObject$Factory$GraphObjectProxy)Proxy.getInvocationHandler(o)).state;
            }
            if (name.equals("asMap")) {
                return createGraphObjectProxyForMap((JSONObject)this.state);
            }
            if (name.equals("getProperty")) {
                return ((JSONObject)this.state).opt((String)jsonProperty[0]);
            }
            if (name.equals("getPropertyAs")) {
                return GraphObject$Factory.coerceValueToExpectedType(((JSONObject)this.state).opt((String)jsonProperty[0]), (Class<Object>)jsonProperty[1], null);
            }
            if (name.equals("getPropertyAsList")) {
                return GraphObject$Factory.coerceValueToExpectedType(((JSONObject)this.state).opt((String)jsonProperty[0]), GraphObjectList.class, new GraphObject$Factory$GraphObjectProxy$1(this, (Class)jsonProperty[1]));
            }
            if (name.equals("setProperty")) {
                return this.setJSONProperty(jsonProperty);
            }
            if (name.equals("removeProperty")) {
                ((JSONObject)this.state).remove((String)jsonProperty[0]);
                return null;
            }
            return this.throwUnexpectedMethodSignature(method);
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
            Map<String, Object> map;
            if (jsonProperty[0] instanceof Map) {
                map = (Map<String, Object>)jsonProperty[0];
            }
            else {
                if (!(jsonProperty[0] instanceof GraphObject)) {
                    return null;
                }
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
        return this.throwUnexpectedMethodSignature(method);
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
    public final Object invoke(final Object o, final Method method, final Object[] array) {
        final Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass == Object.class) {
            return this.proxyObjectMethods(o, method, array);
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
        return this.throwUnexpectedMethodSignature(method);
    }
    
    @Override
    public String toString() {
        return String.format("GraphObject{graphObjectClass=%s, state=%s}", this.graphObjectClass.getSimpleName(), this.state);
    }
}

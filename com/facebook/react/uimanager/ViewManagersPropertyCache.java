// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Dynamic;
import java.lang.reflect.Method;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.HashMap;
import java.util.Map;

class ViewManagersPropertyCache
{
    private static final Map<Class, Map<String, ViewManagersPropertyCache$PropSetter>> CLASS_PROPS_CACHE;
    private static final Map<String, ViewManagersPropertyCache$PropSetter> EMPTY_PROPS_MAP;
    
    static {
        CLASS_PROPS_CACHE = new HashMap<Class, Map<String, ViewManagersPropertyCache$PropSetter>>();
        EMPTY_PROPS_MAP = new HashMap<String, ViewManagersPropertyCache$PropSetter>();
    }
    
    private static ViewManagersPropertyCache$PropSetter createPropSetter(final ReactProp reactProp, final Method method, final Class<?> clazz) {
        if (clazz == Dynamic.class) {
            return new ViewManagersPropertyCache$DynamicPropSetter(reactProp, method);
        }
        if (clazz == Boolean.TYPE) {
            return new ViewManagersPropertyCache$BooleanPropSetter(reactProp, method, reactProp.defaultBoolean());
        }
        if (clazz == Integer.TYPE) {
            return new ViewManagersPropertyCache$IntPropSetter(reactProp, method, reactProp.defaultInt());
        }
        if (clazz == Float.TYPE) {
            return new ViewManagersPropertyCache$FloatPropSetter(reactProp, method, reactProp.defaultFloat());
        }
        if (clazz == Double.TYPE) {
            return new ViewManagersPropertyCache$DoublePropSetter(reactProp, method, reactProp.defaultDouble());
        }
        if (clazz == String.class) {
            return new ViewManagersPropertyCache$StringPropSetter(reactProp, method);
        }
        if (clazz == Boolean.class) {
            return new ViewManagersPropertyCache$BoxedBooleanPropSetter(reactProp, method);
        }
        if (clazz == Integer.class) {
            return new ViewManagersPropertyCache$BoxedIntPropSetter(reactProp, method);
        }
        if (clazz == ReadableArray.class) {
            return new ViewManagersPropertyCache$ArrayPropSetter(reactProp, method);
        }
        if (clazz == ReadableMap.class) {
            return new ViewManagersPropertyCache$MapPropSetter(reactProp, method);
        }
        throw new RuntimeException("Unrecognized type: " + clazz + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }
    
    private static void createPropSetters(final ReactPropGroup reactPropGroup, final Method method, final Class<?> clazz, final Map<String, ViewManagersPropertyCache$PropSetter> map) {
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        int i = 0;
        final String[] names = reactPropGroup.names();
        if (clazz == Dynamic.class) {
            while (i < names.length) {
                map.put(names[i], new ViewManagersPropertyCache$DynamicPropSetter(reactPropGroup, method, i));
                ++i;
            }
        }
        else if (clazz == Integer.TYPE) {
            for (int j = n; j < names.length; ++j) {
                map.put(names[j], new ViewManagersPropertyCache$IntPropSetter(reactPropGroup, method, j, reactPropGroup.defaultInt()));
            }
        }
        else if (clazz == Float.TYPE) {
            for (int k = n2; k < names.length; ++k) {
                map.put(names[k], new ViewManagersPropertyCache$FloatPropSetter(reactPropGroup, method, k, reactPropGroup.defaultFloat()));
            }
        }
        else if (clazz == Double.TYPE) {
            for (int l = 0; l < names.length; ++l) {
                map.put(names[l], new ViewManagersPropertyCache$DoublePropSetter(reactPropGroup, method, l, reactPropGroup.defaultDouble()));
            }
        }
        else {
            if (clazz != Integer.class) {
                throw new RuntimeException("Unrecognized type: " + clazz + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
            }
            for (int n4 = n3; n4 < names.length; ++n4) {
                map.put(names[n4], new ViewManagersPropertyCache$BoxedIntPropSetter(reactPropGroup, method, n4));
            }
        }
    }
    
    private static void extractPropSettersFromShadowNodeClassDefinition(final Class<? extends ReactShadowNode> clazz, final Map<String, ViewManagersPropertyCache$PropSetter> map) {
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int length = declaredMethods.length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            final ReactProp reactProp = method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + clazz.getName() + "#" + method.getName());
                }
                map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[0]));
            }
            final ReactPropGroup reactPropGroup = method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                final Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length != 2) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + clazz.getName() + "#" + method.getName());
                }
                if (parameterTypes2[0] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + clazz.getName() + "#" + method.getName());
                }
                createPropSetters(reactPropGroup, method, parameterTypes2[1], map);
            }
        }
    }
    
    private static void extractPropSettersFromViewManagerClassDefinition(final Class<? extends ViewManager> clazz, final Map<String, ViewManagersPropertyCache$PropSetter> map) {
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; ++i) {
            final Method method = declaredMethods[i];
            final ReactProp reactProp = method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + clazz.getName() + "#" + method.getName());
                }
                if (!View.class.isAssignableFrom(parameterTypes[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + clazz.getName() + "#" + method.getName());
                }
                map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[1]));
            }
            final ReactPropGroup reactPropGroup = method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                final Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length != 3) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + clazz.getName() + "#" + method.getName());
                }
                if (!View.class.isAssignableFrom(parameterTypes2[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + clazz.getName() + "#" + method.getName());
                }
                if (parameterTypes2[1] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + clazz.getName() + "#" + method.getName());
                }
                createPropSetters(reactPropGroup, method, parameterTypes2[2], map);
            }
        }
    }
    
    static Map<String, ViewManagersPropertyCache$PropSetter> getNativePropSettersForShadowNodeClass(final Class<? extends ReactShadowNode> clazz) {
        Map<String, ViewManagersPropertyCache$PropSetter> empty_PROPS_MAP;
        if (clazz == ReactShadowNode.class) {
            empty_PROPS_MAP = ViewManagersPropertyCache.EMPTY_PROPS_MAP;
        }
        else if ((empty_PROPS_MAP = ViewManagersPropertyCache.CLASS_PROPS_CACHE.get(clazz)) == null) {
            final HashMap<String, ViewManagersPropertyCache$PropSetter> hashMap = new HashMap<String, ViewManagersPropertyCache$PropSetter>(getNativePropSettersForShadowNodeClass((Class<? extends ReactShadowNode>)clazz.getSuperclass()));
            extractPropSettersFromShadowNodeClassDefinition(clazz, hashMap);
            ViewManagersPropertyCache.CLASS_PROPS_CACHE.put(clazz, hashMap);
            return hashMap;
        }
        return empty_PROPS_MAP;
    }
    
    static Map<String, ViewManagersPropertyCache$PropSetter> getNativePropSettersForViewManagerClass(final Class<? extends ViewManager> clazz) {
        Map<String, ViewManagersPropertyCache$PropSetter> empty_PROPS_MAP;
        if (clazz == ViewManager.class) {
            empty_PROPS_MAP = ViewManagersPropertyCache.EMPTY_PROPS_MAP;
        }
        else if ((empty_PROPS_MAP = ViewManagersPropertyCache.CLASS_PROPS_CACHE.get(clazz)) == null) {
            final HashMap<String, ViewManagersPropertyCache$PropSetter> hashMap = new HashMap<String, ViewManagersPropertyCache$PropSetter>(getNativePropSettersForViewManagerClass((Class<? extends ViewManager>)clazz.getSuperclass()));
            extractPropSettersFromViewManagerClassDefinition(clazz, hashMap);
            ViewManagersPropertyCache.CLASS_PROPS_CACHE.put(clazz, hashMap);
            return hashMap;
        }
        return empty_PROPS_MAP;
    }
}

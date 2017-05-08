// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import android.view.View;
import com.facebook.common.logging.FLog;
import java.util.HashMap;
import java.util.Map;

public class ViewManagerPropertyUpdater
{
    private static final Map<Class<?>, ViewManagerPropertyUpdater$ShadowNodeSetter<?>> SHADOW_NODE_SETTER_MAP;
    private static final Map<Class<?>, ViewManagerPropertyUpdater$ViewManagerSetter<?, ?>> VIEW_MANAGER_SETTER_MAP;
    
    static {
        VIEW_MANAGER_SETTER_MAP = new HashMap<Class<?>, ViewManagerPropertyUpdater$ViewManagerSetter<?, ?>>();
        SHADOW_NODE_SETTER_MAP = new HashMap<Class<?>, ViewManagerPropertyUpdater$ShadowNodeSetter<?>>();
    }
    
    private static <T> T findGeneratedSetter(final Class<?> ex) {
        final String name = ((Class)ex).getName();
        try {
            return (T)Class.forName(name + "$$PropsSetter").newInstance();
        }
        catch (ClassNotFoundException ex2) {
            FLog.w("ViewManagerPropertyUpdater", "Could not find generated setter for " + ex);
            return null;
        }
        catch (InstantiationException ex3) {}
        catch (IllegalAccessException ex) {
            goto Label_0061;
        }
    }
    
    private static <T extends ViewManager, V extends View> ViewManagerPropertyUpdater$ViewManagerSetter<T, V> findManagerSetter(final Class<? extends ViewManager> clazz) {
        ViewManagerPropertyUpdater$ViewManagerSetter<?, ?> viewManagerPropertyUpdater$ViewManagerSetter;
        if ((viewManagerPropertyUpdater$ViewManagerSetter = ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP.get(clazz)) == null) {
            if ((viewManagerPropertyUpdater$ViewManagerSetter = findGeneratedSetter(clazz)) == null) {
                viewManagerPropertyUpdater$ViewManagerSetter = new ViewManagerPropertyUpdater$FallbackViewManagerSetter<T, V>(clazz, null);
            }
            ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP.put(clazz, viewManagerPropertyUpdater$ViewManagerSetter);
        }
        return (ViewManagerPropertyUpdater$ViewManagerSetter<T, V>)viewManagerPropertyUpdater$ViewManagerSetter;
    }
    
    private static <T extends ReactShadowNode> ViewManagerPropertyUpdater$ShadowNodeSetter<T> findNodeSetter(final Class<? extends ReactShadowNode> clazz) {
        ViewManagerPropertyUpdater$ShadowNodeSetter<?> viewManagerPropertyUpdater$ShadowNodeSetter;
        if ((viewManagerPropertyUpdater$ShadowNodeSetter = ViewManagerPropertyUpdater.SHADOW_NODE_SETTER_MAP.get(clazz)) == null) {
            if ((viewManagerPropertyUpdater$ShadowNodeSetter = findGeneratedSetter(clazz)) == null) {
                viewManagerPropertyUpdater$ShadowNodeSetter = new ViewManagerPropertyUpdater$FallbackShadowNodeSetter<T>(clazz, null);
            }
            ViewManagerPropertyUpdater.SHADOW_NODE_SETTER_MAP.put(clazz, viewManagerPropertyUpdater$ShadowNodeSetter);
        }
        return (ViewManagerPropertyUpdater$ShadowNodeSetter<T>)viewManagerPropertyUpdater$ShadowNodeSetter;
    }
    
    public static Map<String, String> getNativeProps(final Class<? extends ViewManager> clazz, final Class<? extends ReactShadowNode> clazz2) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        findManagerSetter(clazz).getProperties(hashMap);
        findNodeSetter(clazz2).getProperties(hashMap);
        return hashMap;
    }
    
    public static <T extends ReactShadowNode> void updateProps(final T t, final ReactStylesDiffMap reactStylesDiffMap) {
        final ViewManagerPropertyUpdater$ShadowNodeSetter<T> nodeSetter = findNodeSetter(t.getClass());
        final ReadableMapKeySetIterator keySetIterator = reactStylesDiffMap.mBackingMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            nodeSetter.setProperty(t, keySetIterator.nextKey(), reactStylesDiffMap);
        }
    }
    
    public static <T extends ViewManager, V extends View> void updateProps(final T t, final V v, final ReactStylesDiffMap reactStylesDiffMap) {
        final ViewManagerPropertyUpdater$ViewManagerSetter<T, V> managerSetter = findManagerSetter(t.getClass());
        final ReadableMapKeySetIterator keySetIterator = reactStylesDiffMap.mBackingMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            managerSetter.setProperty(t, v, keySetIterator.nextKey(), reactStylesDiffMap);
        }
    }
}

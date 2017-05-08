// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import java.util.Map;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import com.facebook.infer.annotation.Assertions;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.WeakHashMap;

public class JavaScriptModuleRegistry
{
    private final WeakHashMap<ExecutorToken, HashMap<Class<? extends JavaScriptModule>, JavaScriptModule>> mModuleInstances;
    private final HashMap<Class<? extends JavaScriptModule>, JavaScriptModuleRegistration> mModuleRegistrations;
    
    public JavaScriptModuleRegistry(final List<JavaScriptModuleRegistration> list) {
        this.mModuleInstances = new WeakHashMap<ExecutorToken, HashMap<Class<? extends JavaScriptModule>, JavaScriptModule>>();
        this.mModuleRegistrations = new HashMap<Class<? extends JavaScriptModule>, JavaScriptModuleRegistration>();
        for (final JavaScriptModuleRegistration javaScriptModuleRegistration : list) {
            this.mModuleRegistrations.put(javaScriptModuleRegistration.getModuleInterface(), javaScriptModuleRegistration);
        }
    }
    
    public <T extends JavaScriptModule> T getJavaScriptModule(final CatalystInstance catalystInstance, final ExecutorToken executorToken, final Class<T> clazz) {
        while (true) {
            while (true) {
                Label_0158: {
                    synchronized (this) {
                        Map<K, V> map = (Map<K, V>)this.mModuleInstances.get(executorToken);
                        if (map == null) {
                            map = (Map<K, V>)new HashMap<Object, T>();
                            this.mModuleInstances.put(executorToken, (HashMap<Class<? extends JavaScriptModule>, JavaScriptModule>)map);
                            final JavaScriptModule javaScriptModule = ((HashMap<Object, T>)map).get(clazz);
                            JavaScriptModule javaScriptModule2;
                            if (javaScriptModule != null) {
                                javaScriptModule2 = javaScriptModule;
                            }
                            else {
                                javaScriptModule2 = (JavaScriptModule)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new JavaScriptModuleRegistry$JavaScriptModuleInvocationHandler(executorToken, catalystInstance, Assertions.assertNotNull(this.mModuleRegistrations.get(clazz), "JS module " + clazz.getSimpleName() + " hasn't been registered!")));
                                ((HashMap<Class<T>, T>)map).put(clazz, (T)javaScriptModule2);
                            }
                            return (T)javaScriptModule2;
                        }
                        break Label_0158;
                    }
                }
                continue;
            }
        }
    }
}

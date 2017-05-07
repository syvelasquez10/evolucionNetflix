// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import com.google.gson.reflect.TypeToken;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collection;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.Collections;
import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;
import java.util.Map;

public final class ConstructorConstructor
{
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    
    public ConstructorConstructor() {
        this(Collections.emptyMap());
    }
    
    public ConstructorConstructor(final Map<Type, InstanceCreator<?>> instanceCreators) {
        this.instanceCreators = instanceCreators;
    }
    
    private <T> ObjectConstructor<T> newDefaultConstructor(final Class<? super T> clazz) {
        try {
            final Constructor<? super T> declaredConstructor = clazz.getDeclaredConstructor((Class<?>[])new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    try {
                        return declaredConstructor.newInstance((Object[])null);
                    }
                    catch (InstantiationException ex) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", ex);
                    }
                    catch (InvocationTargetException ex2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", ex2.getTargetException());
                    }
                    catch (IllegalAccessException ex3) {
                        throw new AssertionError((Object)ex3);
                    }
                }
            };
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Class<? super T> clazz) {
        if (Collection.class.isAssignableFrom(clazz)) {
            if (SortedSet.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new TreeSet();
                    }
                };
            }
            if (Set.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new LinkedList();
                    }
                };
            }
            return new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    return (T)new ArrayList();
                }
            };
        }
        else {
            if (Map.class.isAssignableFrom(clazz)) {
                return new ObjectConstructor<T>() {
                    @Override
                    public T construct() {
                        return (T)new LinkedHashMap();
                    }
                };
            }
            return null;
        }
    }
    
    private <T> ObjectConstructor<T> newUnsafeAllocator(final Type type, final Class<? super T> clazz) {
        return new ObjectConstructor<T>() {
            private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
            
            @Override
            public T construct() {
                try {
                    return this.unsafeAllocator.newInstance(clazz);
                }
                catch (Exception ex) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", ex);
                }
            }
        };
    }
    
    public <T> ObjectConstructor<T> get(final TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        final Class<? super T> rawType = typeToken.getRawType();
        final InstanceCreator<?> instanceCreator = this.instanceCreators.get(type);
        Object o;
        if (instanceCreator != null) {
            o = new ObjectConstructor<T>() {
                @Override
                public T construct() {
                    return instanceCreator.createInstance(type);
                }
            };
        }
        else if ((o = this.newDefaultConstructor((Class<? super Object>)rawType)) == null && (o = this.newDefaultImplementationConstructor((Class<? super Object>)rawType)) == null) {
            return (ObjectConstructor<T>)this.newUnsafeAllocator(type, (Class<? super Object>)rawType);
        }
        return (ObjectConstructor<T>)o;
    }
    
    @Override
    public String toString() {
        return this.instanceCreators.toString();
    }
}

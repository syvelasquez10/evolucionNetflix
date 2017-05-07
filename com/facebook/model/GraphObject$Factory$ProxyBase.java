// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import com.facebook.FacebookGraphObjectException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

abstract class GraphObject$Factory$ProxyBase<STATE> implements InvocationHandler
{
    protected final STATE state;
    
    protected GraphObject$Factory$ProxyBase(final STATE state) {
        this.state = state;
    }
    
    protected final Object proxyObjectMethods(Object o, final Method method, final Object[] array) {
        final String name = method.getName();
        if (name.equals("equals")) {
            o = array[0];
            if (o == null) {
                return false;
            }
            final InvocationHandler invocationHandler = Proxy.getInvocationHandler(o);
            if (!(invocationHandler instanceof GraphObject$Factory$GraphObjectProxy)) {
                return false;
            }
            return this.state.equals(((GraphObject$Factory$GraphObjectProxy)invocationHandler).state);
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

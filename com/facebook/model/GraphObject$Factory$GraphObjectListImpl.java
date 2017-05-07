// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import java.lang.reflect.Method;
import com.facebook.internal.Utility;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import org.json.JSONObject;
import java.util.Locale;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.lang.reflect.ParameterizedType;
import com.facebook.FacebookGraphObjectException;
import org.json.JSONException;
import com.facebook.internal.Validate;
import org.json.JSONArray;
import java.util.AbstractList;

final class GraphObject$Factory$GraphObjectListImpl<T> extends AbstractList<T> implements GraphObjectList<T>
{
    private final Class<?> itemType;
    private final JSONArray state;
    
    public GraphObject$Factory$GraphObjectListImpl(final JSONArray state, final Class<?> itemType) {
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
        return GraphObject$Factory.createList(this.state, clazz);
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (this.getClass() == o.getClass() && this.state.equals((Object)((GraphObject$Factory$GraphObjectListImpl)o).state));
    }
    
    @Override
    public T get(final int n) {
        this.checkIndex(n);
        return GraphObject$Factory.coerceValueToExpectedType(this.state.opt(n), this.itemType, null);
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

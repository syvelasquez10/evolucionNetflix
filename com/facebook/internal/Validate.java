// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Iterator;
import java.util.Collection;

public final class Validate
{
    public static <T> void containsNoNulls(final Collection<T> collection, final String s) {
        notNull(collection, s);
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new NullPointerException("Container '" + s + "' cannot contain null values");
            }
        }
    }
    
    public static <T> void notEmpty(final Collection<T> collection, final String s) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + s + "' cannot be empty");
        }
    }
    
    public static <T> void notEmptyAndContainsNoNulls(final Collection<T> collection, final String s) {
        containsNoNulls((Collection<Object>)collection, s);
        notEmpty((Collection<Object>)collection, s);
    }
    
    public static void notNull(final Object o, final String s) {
        if (o == null) {
            throw new NullPointerException("Argument '" + s + "' cannot be null");
        }
    }
    
    public static void notNullOrEmpty(final String s, final String s2) {
        if (Utility.isNullOrEmpty(s)) {
            throw new IllegalArgumentException("Argument '" + s2 + "' cannot be null or empty");
        }
    }
}

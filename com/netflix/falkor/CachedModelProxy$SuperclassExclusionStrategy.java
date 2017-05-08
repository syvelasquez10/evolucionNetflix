// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.google.gson.FieldAttributes;
import java.lang.reflect.Field;
import com.google.gson.ExclusionStrategy;

class CachedModelProxy$SuperclassExclusionStrategy implements ExclusionStrategy
{
    final /* synthetic */ CachedModelProxy this$0;
    
    private CachedModelProxy$SuperclassExclusionStrategy(final CachedModelProxy this$0) {
        this.this$0 = this$0;
    }
    
    private Field getField(final Class<?> clazz, final String s) {
        try {
            return clazz.getDeclaredField(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private boolean isFieldInSuperclass(final Class<?> clazz, final String s) {
        for (Class<?> clazz2 = clazz.getSuperclass(); clazz2 != null; clazz2 = clazz2.getSuperclass()) {
            if (this.getField(clazz2, s) != null) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean shouldSkipClass(final Class<?> clazz) {
        return false;
    }
    
    @Override
    public boolean shouldSkipField(final FieldAttributes fieldAttributes) {
        return this.isFieldInSuperclass(fieldAttributes.getDeclaringClass(), fieldAttributes.getName());
    }
}

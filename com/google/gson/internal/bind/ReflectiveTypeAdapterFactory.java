// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.internal.ObjectConstructor;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import com.google.gson.Gson;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.TypeAdapterFactory;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory
{
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    
    public ReflectiveTypeAdapterFactory(final ConstructorConstructor constructorConstructor, final FieldNamingStrategy fieldNamingPolicy, final Excluder excluder) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
    }
    
    private ReflectiveTypeAdapterFactory$BoundField createBoundField(final Gson gson, final Field field, final String s, final TypeToken<?> typeToken, final boolean b, final boolean b2) {
        return new ReflectiveTypeAdapterFactory$1(this, s, b, b2, gson, typeToken, field, Primitives.isPrimitive(typeToken.getRawType()));
    }
    
    private Map<String, ReflectiveTypeAdapterFactory$BoundField> getBoundFields(final Gson gson, TypeToken<?> value, Class<?> rawType) {
        final LinkedHashMap<String, ReflectiveTypeAdapterFactory$BoundField> linkedHashMap = new LinkedHashMap<String, ReflectiveTypeAdapterFactory$BoundField>();
        if (rawType.isInterface()) {
            return linkedHashMap;
        }
        final Type type = value.getType();
        while (rawType != Object.class) {
            final Field[] declaredFields = rawType.getDeclaredFields();
            for (int length = declaredFields.length, i = 0; i < length; ++i) {
                final Field field = declaredFields[i];
                final boolean excludeField = this.excludeField(field, true);
                final boolean excludeField2 = this.excludeField(field, false);
                if (excludeField || excludeField2) {
                    field.setAccessible(true);
                    final ReflectiveTypeAdapterFactory$BoundField boundField = this.createBoundField(gson, field, this.getFieldName(field), TypeToken.get($Gson$Types.resolve(value.getType(), rawType, field.getGenericType())), excludeField, excludeField2);
                    final ReflectiveTypeAdapterFactory$BoundField reflectiveTypeAdapterFactory$BoundField = linkedHashMap.put(boundField.name, boundField);
                    if (reflectiveTypeAdapterFactory$BoundField != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + reflectiveTypeAdapterFactory$BoundField.name);
                    }
                }
            }
            value = TypeToken.get($Gson$Types.resolve(value.getType(), rawType, rawType.getGenericSuperclass()));
            rawType = value.getRawType();
        }
        return linkedHashMap;
    }
    
    private String getFieldName(final Field field) {
        final SerializedName serializedName = field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return this.fieldNamingPolicy.translateName(field);
        }
        return serializedName.value();
    }
    
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        final Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        return new ReflectiveTypeAdapterFactory$Adapter<T>(this, this.constructorConstructor.get(typeToken), this.getBoundFields(gson, typeToken, rawType), null);
    }
    
    public boolean excludeField(final Field field, final boolean b) {
        return !this.excluder.excludeClass(field.getType(), b) && !this.excluder.excludeField(field, b);
    }
}

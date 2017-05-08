// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.$Gson$Preconditions;
import java.util.Collections;
import java.util.Collection;
import java.sql.Timestamp;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.List;
import com.google.gson.internal.Excluder;

public final class GsonBuilder
{
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle;
    private boolean escapeHtmlChars;
    private Excluder excluder;
    private final List<TypeAdapterFactory> factories;
    private FieldNamingStrategy fieldNamingPolicy;
    private boolean generateNonExecutableJson;
    private final List<TypeAdapterFactory> hierarchyFactories;
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    private LongSerializationPolicy longSerializationPolicy;
    private boolean prettyPrinting;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private int timeStyle;
    
    public GsonBuilder() {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        this.instanceCreators = new HashMap<Type, InstanceCreator<?>>();
        this.factories = new ArrayList<TypeAdapterFactory>();
        this.hierarchyFactories = new ArrayList<TypeAdapterFactory>();
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.escapeHtmlChars = true;
    }
    
    private void addTypeAdaptersForDate(final String s, final int n, final int n2, final List<TypeAdapterFactory> list) {
        DefaultDateTypeAdapter defaultDateTypeAdapter;
        if (s != null && !"".equals(s.trim())) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(s);
        }
        else {
            if (n == 2 || n2 == 2) {
                return;
            }
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(n, n2);
        }
        list.add(TreeTypeAdapter.newFactory(TypeToken.get((Class<?>)Date.class), defaultDateTypeAdapter));
        list.add(TreeTypeAdapter.newFactory(TypeToken.get((Class<?>)Timestamp.class), defaultDateTypeAdapter));
        list.add(TreeTypeAdapter.newFactory(TypeToken.get((Class<?>)java.sql.Date.class), defaultDateTypeAdapter));
    }
    
    public GsonBuilder addDeserializationExclusionStrategy(final ExclusionStrategy exclusionStrategy) {
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, false, true);
        return this;
    }
    
    public GsonBuilder addSerializationExclusionStrategy(final ExclusionStrategy exclusionStrategy) {
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, true, false);
        return this;
    }
    
    public Gson create() {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.addAll(this.factories);
        Collections.reverse(list);
        list.addAll(this.hierarchyFactories);
        this.addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, (List<TypeAdapterFactory>)list);
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, (List<TypeAdapterFactory>)list);
    }
    
    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }
    
    public GsonBuilder registerTypeAdapter(final Type type, final Object o) {
        $Gson$Preconditions.checkArgument(o instanceof JsonSerializer || o instanceof JsonDeserializer || o instanceof InstanceCreator || o instanceof TypeAdapter);
        if (o instanceof InstanceCreator) {
            this.instanceCreators.put(type, (InstanceCreator<?>)o);
        }
        if (o instanceof JsonSerializer || o instanceof JsonDeserializer) {
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type), o));
        }
        if (o instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter<?>)o));
        }
        return this;
    }
    
    public GsonBuilder registerTypeAdapterFactory(final TypeAdapterFactory typeAdapterFactory) {
        this.factories.add(typeAdapterFactory);
        return this;
    }
    
    public GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }
    
    public GsonBuilder setVersion(final double n) {
        this.excluder = this.excluder.withVersion(n);
        return this;
    }
}

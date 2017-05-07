// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.Streams;
import java.io.StringWriter;
import java.util.Iterator;
import java.io.StringReader;
import java.io.Reader;
import java.io.EOFException;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.Primitives;
import com.google.gson.stream.JsonWriter;
import java.io.Writer;
import java.io.IOException;
import com.google.gson.stream.MalformedJsonException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.DateTypeAdapter;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Collection;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.Collections;
import com.google.gson.internal.Excluder;
import java.util.List;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Map;

public final class Gson
{
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    private final ThreadLocal<Map<TypeToken<?>, Gson$FutureTypeAdapter<?>>> calls;
    private final ConstructorConstructor constructorConstructor;
    final JsonDeserializationContext deserializationContext;
    private final List<TypeAdapterFactory> factories;
    private final boolean generateNonExecutableJson;
    private final boolean htmlSafe;
    private final boolean prettyPrinting;
    final JsonSerializationContext serializationContext;
    private final boolean serializeNulls;
    private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache;
    
    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }
    
    Gson(final Excluder excluder, final FieldNamingStrategy fieldNamingStrategy, final Map<Type, InstanceCreator<?>> map, final boolean serializeNulls, final boolean b, final boolean generateNonExecutableJson, final boolean htmlSafe, final boolean prettyPrinting, final boolean b2, final LongSerializationPolicy longSerializationPolicy, final List<TypeAdapterFactory> list) {
        this.calls = new Gson$1(this);
        this.typeTokenCache = Collections.synchronizedMap(new HashMap<TypeToken<?>, TypeAdapter<?>>());
        this.deserializationContext = new Gson$2(this);
        this.serializationContext = new Gson$3(this);
        this.constructorConstructor = new ConstructorConstructor(map);
        this.serializeNulls = serializeNulls;
        this.generateNonExecutableJson = generateNonExecutableJson;
        this.htmlSafe = htmlSafe;
        this.prettyPrinting = prettyPrinting;
        final ArrayList<TypeAdapterFactory> list2 = new ArrayList<TypeAdapterFactory>();
        list2.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        list2.add(ObjectTypeAdapter.FACTORY);
        list2.addAll((Collection<?>)list);
        list2.add(TypeAdapters.STRING_FACTORY);
        list2.add(TypeAdapters.INTEGER_FACTORY);
        list2.add(TypeAdapters.BOOLEAN_FACTORY);
        list2.add(TypeAdapters.BYTE_FACTORY);
        list2.add(TypeAdapters.SHORT_FACTORY);
        list2.add(TypeAdapters.newFactory(Long.TYPE, Long.class, this.longAdapter(longSerializationPolicy)));
        list2.add(TypeAdapters.newFactory(Double.TYPE, Double.class, this.doubleAdapter(b2)));
        list2.add(TypeAdapters.newFactory(Float.TYPE, Float.class, this.floatAdapter(b2)));
        list2.add(TypeAdapters.NUMBER_FACTORY);
        list2.add(TypeAdapters.CHARACTER_FACTORY);
        list2.add(TypeAdapters.STRING_BUILDER_FACTORY);
        list2.add(TypeAdapters.STRING_BUFFER_FACTORY);
        list2.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        list2.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        list2.add(TypeAdapters.URL_FACTORY);
        list2.add(TypeAdapters.URI_FACTORY);
        list2.add(TypeAdapters.UUID_FACTORY);
        list2.add(TypeAdapters.LOCALE_FACTORY);
        list2.add(TypeAdapters.INET_ADDRESS_FACTORY);
        list2.add(TypeAdapters.BIT_SET_FACTORY);
        list2.add(DateTypeAdapter.FACTORY);
        list2.add(TypeAdapters.CALENDAR_FACTORY);
        list2.add(TimeTypeAdapter.FACTORY);
        list2.add(SqlDateTypeAdapter.FACTORY);
        list2.add(TypeAdapters.TIMESTAMP_FACTORY);
        list2.add(ArrayTypeAdapter.FACTORY);
        list2.add(TypeAdapters.ENUM_FACTORY);
        list2.add(TypeAdapters.CLASS_FACTORY);
        list2.add(excluder);
        list2.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
        list2.add(new MapTypeAdapterFactory(this.constructorConstructor, b));
        list2.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, fieldNamingStrategy, excluder));
        this.factories = (List<TypeAdapterFactory>)Collections.unmodifiableList((List<?>)list2);
    }
    
    private static void assertFullConsumption(final Object o, final JsonReader jsonReader) {
        if (o != null) {
            try {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            }
            catch (MalformedJsonException ex) {
                throw new JsonSyntaxException(ex);
            }
            catch (IOException ex2) {
                throw new JsonIOException(ex2);
            }
        }
    }
    
    private void checkValidFloatingPoint(final double n) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            throw new IllegalArgumentException(n + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialDoubleValues() method.");
        }
    }
    
    private TypeAdapter<Number> doubleAdapter(final boolean b) {
        if (b) {
            return TypeAdapters.DOUBLE;
        }
        return new Gson$4(this);
    }
    
    private TypeAdapter<Number> floatAdapter(final boolean b) {
        if (b) {
            return TypeAdapters.FLOAT;
        }
        return new Gson$5(this);
    }
    
    private TypeAdapter<Number> longAdapter(final LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        return new Gson$6(this);
    }
    
    private JsonWriter newJsonWriter(final Writer writer) {
        if (this.generateNonExecutableJson) {
            writer.write(")]}'\n");
        }
        final JsonWriter jsonWriter = new JsonWriter(writer);
        if (this.prettyPrinting) {
            jsonWriter.setIndent("  ");
        }
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }
    
    public <T> T fromJson(final JsonElement jsonElement, final Class<T> clazz) {
        return Primitives.wrap(clazz).cast(this.fromJson(jsonElement, (Type)clazz));
    }
    
    public <T> T fromJson(final JsonElement jsonElement, final Type type) {
        if (jsonElement == null) {
            return null;
        }
        return this.fromJson(new JsonTreeReader(jsonElement), type);
    }
    
    public <T> T fromJson(final JsonReader jsonReader, final Type type) {
        boolean b = true;
        jsonReader.isLenient();
        jsonReader.setLenient(true);
        try {
            jsonReader.peek();
            b = false;
            return (T)this.getAdapter(TypeToken.get(type)).read(jsonReader);
        }
        catch (EOFException ex) {
            if (b) {
                return null;
            }
            throw new JsonSyntaxException(ex);
        }
        catch (IllegalStateException ex3) {}
        catch (IOException ex4) {
            final IOException ex2;
            throw new JsonSyntaxException(ex2);
        }
    }
    
    public <T> T fromJson(final Reader reader, final Class<T> clazz) {
        final JsonReader jsonReader = new JsonReader(reader);
        final Object fromJson = this.fromJson(jsonReader, clazz);
        assertFullConsumption(fromJson, jsonReader);
        return Primitives.wrap(clazz).cast(fromJson);
    }
    
    public <T> T fromJson(final Reader reader, final Type type) {
        final JsonReader jsonReader = new JsonReader(reader);
        final Object fromJson = this.fromJson(jsonReader, type);
        assertFullConsumption(fromJson, jsonReader);
        return (T)fromJson;
    }
    
    public <T> T fromJson(final String s, final Class<T> clazz) {
        return Primitives.wrap(clazz).cast(this.fromJson(s, (Type)clazz));
    }
    
    public <T> T fromJson(final String s, final Type type) {
        if (s == null) {
            return null;
        }
        return this.fromJson(new StringReader(s), type);
    }
    
    public <T> TypeAdapter<T> getAdapter(final TypeToken<T> typeToken) {
        final TypeAdapter<?> typeAdapter = this.typeTokenCache.get(typeToken);
        if (typeAdapter != null) {
            return (TypeAdapter<T>)typeAdapter;
        }
        final Map<TypeToken<?>, Gson$FutureTypeAdapter<?>> map = this.calls.get();
        final Gson$FutureTypeAdapter<T> gson$FutureTypeAdapter = map.get(typeToken);
        if (gson$FutureTypeAdapter != null) {
            return gson$FutureTypeAdapter;
        }
        final Gson$FutureTypeAdapter<T> gson$FutureTypeAdapter2 = new Gson$FutureTypeAdapter<T>();
        map.put(typeToken, gson$FutureTypeAdapter2);
        try {
            final Iterator<TypeAdapterFactory> iterator = this.factories.iterator();
            while (iterator.hasNext()) {
                final TypeAdapter<T> create = iterator.next().create(this, typeToken);
                if (create != null) {
                    gson$FutureTypeAdapter2.setDelegate(create);
                    this.typeTokenCache.put(typeToken, create);
                    return create;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + typeToken);
        }
        finally {
            map.remove(typeToken);
        }
    }
    
    public <T> TypeAdapter<T> getAdapter(final Class<T> clazz) {
        return this.getAdapter((TypeToken<T>)TypeToken.get((Class<T>)clazz));
    }
    
    public <T> TypeAdapter<T> getDelegateAdapter(final TypeAdapterFactory typeAdapterFactory, final TypeToken<T> typeToken) {
        final Iterator<TypeAdapterFactory> iterator = this.factories.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final TypeAdapterFactory typeAdapterFactory2 = iterator.next();
            if (n == 0) {
                if (typeAdapterFactory2 != typeAdapterFactory) {
                    continue;
                }
                n = 1;
            }
            else {
                final TypeAdapter<T> create = typeAdapterFactory2.create(this, typeToken);
                if (create != null) {
                    return create;
                }
                continue;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + typeToken);
    }
    
    public String toJson(final JsonElement jsonElement) {
        final StringWriter stringWriter = new StringWriter();
        this.toJson(jsonElement, stringWriter);
        return stringWriter.toString();
    }
    
    public String toJson(final Object o) {
        if (o == null) {
            return this.toJson(JsonNull.INSTANCE);
        }
        return this.toJson(o, o.getClass());
    }
    
    public String toJson(final Object o, final Type type) {
        final StringWriter stringWriter = new StringWriter();
        this.toJson(o, type, stringWriter);
        return stringWriter.toString();
    }
    
    public void toJson(final JsonElement jsonElement, final JsonWriter jsonWriter) {
        final boolean lenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        final boolean htmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        final boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(jsonElement, jsonWriter);
        }
        catch (IOException ex) {
            throw new JsonIOException(ex);
        }
        finally {
            jsonWriter.setLenient(lenient);
            jsonWriter.setHtmlSafe(htmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }
    
    public void toJson(final JsonElement jsonElement, final Appendable appendable) {
        try {
            this.toJson(jsonElement, this.newJsonWriter(Streams.writerForAppendable(appendable)));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void toJson(final Object o, final Appendable appendable) {
        if (o != null) {
            this.toJson(o, o.getClass(), appendable);
            return;
        }
        this.toJson(JsonNull.INSTANCE, appendable);
    }
    
    public void toJson(final Object o, final Type type, final JsonWriter jsonWriter) {
        final TypeAdapter<?> adapter = this.getAdapter(TypeToken.get(type));
        final boolean lenient = jsonWriter.isLenient();
        jsonWriter.setLenient(true);
        final boolean htmlSafe = jsonWriter.isHtmlSafe();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        final boolean serializeNulls = jsonWriter.getSerializeNulls();
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            adapter.write(jsonWriter, o);
        }
        catch (IOException ex) {
            throw new JsonIOException(ex);
        }
        finally {
            jsonWriter.setLenient(lenient);
            jsonWriter.setHtmlSafe(htmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls);
        }
    }
    
    public void toJson(final Object o, final Type type, final Appendable appendable) {
        try {
            this.toJson(o, type, this.newJsonWriter(Streams.writerForAppendable(appendable)));
        }
        catch (IOException ex) {
            throw new JsonIOException(ex);
        }
    }
    
    public JsonElement toJsonTree(final Object o) {
        if (o == null) {
            return JsonNull.INSTANCE;
        }
        return this.toJsonTree(o, o.getClass());
    }
    
    public JsonElement toJsonTree(final Object o, final Type type) {
        final JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
        this.toJson(o, type, jsonTreeWriter);
        return jsonTreeWriter.get();
    }
    
    @Override
    public String toString() {
        return "{" + "serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}

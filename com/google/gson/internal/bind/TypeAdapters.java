// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal.bind;

import com.google.gson.reflect.TypeToken;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.net.URL;
import java.net.URI;
import java.util.Locale;
import com.google.gson.JsonElement;
import java.net.InetAddress;
import java.util.Calendar;
import com.google.gson.TypeAdapterFactory;
import java.util.BitSet;
import java.math.BigInteger;
import java.math.BigDecimal;
import com.google.gson.TypeAdapter;

public final class TypeAdapters
{
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL;
    public static final TypeAdapter<BigInteger> BIG_INTEGER;
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter<Number> DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter<Number> FLOAT;
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter<Number> LONG;
    public static final TypeAdapter<Number> NUMBER;
    public static final TypeAdapterFactory NUMBER_FACTORY;
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter<String> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY;
    
    static {
        CLASS = new TypeAdapters$1();
        CLASS_FACTORY = newFactory(Class.class, TypeAdapters.CLASS);
        BIT_SET = new TypeAdapters$2();
        BIT_SET_FACTORY = newFactory(BitSet.class, TypeAdapters.BIT_SET);
        BOOLEAN = new TypeAdapters$3();
        BOOLEAN_AS_STRING = new TypeAdapters$4();
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, TypeAdapters.BOOLEAN);
        BYTE = new TypeAdapters$5();
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, TypeAdapters.BYTE);
        SHORT = new TypeAdapters$6();
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, TypeAdapters.SHORT);
        INTEGER = new TypeAdapters$7();
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, TypeAdapters.INTEGER);
        LONG = new TypeAdapters$8();
        FLOAT = new TypeAdapters$9();
        DOUBLE = new TypeAdapters$10();
        NUMBER = new TypeAdapters$11();
        NUMBER_FACTORY = newFactory(Number.class, TypeAdapters.NUMBER);
        CHARACTER = new TypeAdapters$12();
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, TypeAdapters.CHARACTER);
        STRING = new TypeAdapters$13();
        BIG_DECIMAL = new TypeAdapters$14();
        BIG_INTEGER = new TypeAdapters$15();
        STRING_FACTORY = newFactory(String.class, TypeAdapters.STRING);
        STRING_BUILDER = new TypeAdapters$16();
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, TypeAdapters.STRING_BUILDER);
        STRING_BUFFER = new TypeAdapters$17();
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, TypeAdapters.STRING_BUFFER);
        URL = new TypeAdapters$18();
        URL_FACTORY = newFactory(URL.class, TypeAdapters.URL);
        URI = new TypeAdapters$19();
        URI_FACTORY = newFactory(URI.class, TypeAdapters.URI);
        INET_ADDRESS = new TypeAdapters$20();
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, TypeAdapters.INET_ADDRESS);
        UUID = new TypeAdapters$21();
        UUID_FACTORY = newFactory(UUID.class, TypeAdapters.UUID);
        TIMESTAMP_FACTORY = new TypeAdapters$22();
        CALENDAR = new TypeAdapters$23();
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, TypeAdapters.CALENDAR);
        LOCALE = new TypeAdapters$24();
        LOCALE_FACTORY = newFactory(Locale.class, TypeAdapters.LOCALE);
        JSON_ELEMENT = new TypeAdapters$25();
        JSON_ELEMENT_FACTORY = newFactory(JsonElement.class, TypeAdapters.JSON_ELEMENT);
        ENUM_FACTORY = newEnumTypeHierarchyFactory();
    }
    
    public static TypeAdapterFactory newEnumTypeHierarchyFactory() {
        return new TypeAdapters$26();
    }
    
    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> typeToken, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapters$27(typeToken, typeAdapter);
    }
    
    public static <TT> TypeAdapterFactory newFactory(final Class<TT> clazz, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapters$28(clazz, typeAdapter);
    }
    
    public static <TT> TypeAdapterFactory newFactory(final Class<TT> clazz, final Class<TT> clazz2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapters$29(clazz, clazz2, typeAdapter);
    }
    
    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> clazz, final Class<? extends TT> clazz2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapters$30(clazz, clazz2, typeAdapter);
    }
    
    public static <TT> TypeAdapterFactory newTypeHierarchyFactory(final Class<TT> clazz, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapters$31(clazz, typeAdapter);
    }
}

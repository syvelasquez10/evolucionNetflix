// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import java.io.Reader;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import java.lang.ref.SoftReference;
import java.io.Serializable;

public class JsonFactory implements Serializable
{
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS;
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS;
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS;
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;
    protected final transient ByteQuadsCanonicalizer _byteSymbolCanonicalizer;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected int _parserFeatures;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;
    
    static {
        DEFAULT_FACTORY_FEATURE_FLAGS = JsonFactory$Feature.collectDefaults();
        DEFAULT_PARSER_FEATURE_FLAGS = JsonParser$Feature.collectDefaults();
        DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator$Feature.collectDefaults();
        DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        _recyclerRef = new ThreadLocal<SoftReference<BufferRecycler>>();
    }
    
    public JsonFactory() {
        this(null);
    }
    
    public JsonFactory(final ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
    }
    
    protected IOContext _createContext(final Object o, final boolean b) {
        return new IOContext(this._getBufferRecycler(), o, b);
    }
    
    protected JsonParser _createParser(final Reader reader, final IOContext ioContext) {
        return new ReaderBasedJsonParser(ioContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
    }
    
    protected final Reader _decorate(final Reader reader, final IOContext ioContext) {
        Reader reader2 = reader;
        if (this._inputDecorator != null) {
            final Reader decorate = this._inputDecorator.decorate(ioContext, reader);
            reader2 = reader;
            if (decorate != null) {
                reader2 = decorate;
            }
        }
        return reader2;
    }
    
    public BufferRecycler _getBufferRecycler() {
        if (this.isEnabled(JsonFactory$Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING)) {
            final SoftReference<BufferRecycler> softReference = JsonFactory._recyclerRef.get();
            BufferRecycler bufferRecycler;
            if (softReference == null) {
                bufferRecycler = null;
            }
            else {
                bufferRecycler = softReference.get();
            }
            BufferRecycler bufferRecycler2 = bufferRecycler;
            if (bufferRecycler == null) {
                bufferRecycler2 = new BufferRecycler();
                JsonFactory._recyclerRef.set(new SoftReference<BufferRecycler>(bufferRecycler2));
            }
            return bufferRecycler2;
        }
        return new BufferRecycler();
    }
    
    public JsonParser createParser(final Reader reader) {
        final IOContext createContext = this._createContext(reader, false);
        return this._createParser(this._decorate(reader, createContext), createContext);
    }
    
    public final boolean isEnabled(final JsonFactory$Feature jsonFactory$Feature) {
        return (this._factoryFeatures & jsonFactory$Feature.getMask()) != 0x0;
    }
}

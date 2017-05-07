// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.stream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.io.Flushable;
import java.io.Closeable;

public class JsonWriter implements Closeable, Flushable
{
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private final List<JsonScope> stack;
    
    static {
        REPLACEMENT_CHARS = new String[128];
        for (int i = 0; i <= 31; ++i) {
            JsonWriter.REPLACEMENT_CHARS[i] = String.format("\\u%04x", i);
        }
        JsonWriter.REPLACEMENT_CHARS[34] = "\\\"";
        JsonWriter.REPLACEMENT_CHARS[92] = "\\\\";
        JsonWriter.REPLACEMENT_CHARS[9] = "\\t";
        JsonWriter.REPLACEMENT_CHARS[8] = "\\b";
        JsonWriter.REPLACEMENT_CHARS[10] = "\\n";
        JsonWriter.REPLACEMENT_CHARS[13] = "\\r";
        JsonWriter.REPLACEMENT_CHARS[12] = "\\f";
        (HTML_SAFE_REPLACEMENT_CHARS = JsonWriter.REPLACEMENT_CHARS.clone())[60] = "\\u003c";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
    }
    
    public JsonWriter(final Writer out) {
        (this.stack = new ArrayList<JsonScope>()).add(JsonScope.EMPTY_DOCUMENT);
        this.separator = ":";
        this.serializeNulls = true;
        if (out == null) {
            throw new NullPointerException("out == null");
        }
        this.out = out;
    }
    
    private void beforeName() {
        final JsonScope peek = this.peek();
        if (peek == JsonScope.NONEMPTY_OBJECT) {
            this.out.write(44);
        }
        else if (peek != JsonScope.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
        this.newline();
        this.replaceTop(JsonScope.DANGLING_NAME);
    }
    
    private void beforeValue(final boolean b) {
        switch (JsonWriter$1.$SwitchMap$com$google$gson$stream$JsonScope[this.peek().ordinal()]) {
            default: {
                throw new IllegalStateException("Nesting problem: " + this.stack);
            }
            case 1: {
                if (!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            case 2: {
                if (!this.lenient && !b) {
                    throw new IllegalStateException("JSON must start with an array or an object.");
                }
                this.replaceTop(JsonScope.NONEMPTY_DOCUMENT);
            }
            case 3: {
                this.replaceTop(JsonScope.NONEMPTY_ARRAY);
                this.newline();
            }
            case 4: {
                this.out.append(',');
                this.newline();
            }
            case 5: {
                this.out.append((CharSequence)this.separator);
                this.replaceTop(JsonScope.NONEMPTY_OBJECT);
            }
        }
    }
    
    private JsonWriter close(final JsonScope jsonScope, final JsonScope jsonScope2, final String s) {
        final JsonScope peek = this.peek();
        if (peek != jsonScope2 && peek != jsonScope) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
        if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        this.stack.remove(this.stack.size() - 1);
        if (peek == jsonScope2) {
            this.newline();
        }
        this.out.write(s);
        return this;
    }
    
    private void newline() {
        if (this.indent != null) {
            this.out.write("\n");
            for (int i = 1; i < this.stack.size(); ++i) {
                this.out.write(this.indent);
            }
        }
    }
    
    private JsonWriter open(final JsonScope jsonScope, final String s) {
        this.beforeValue(true);
        this.stack.add(jsonScope);
        this.out.write(s);
        return this;
    }
    
    private JsonScope peek() {
        final int size = this.stack.size();
        if (size == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.stack.get(size - 1);
    }
    
    private void replaceTop(final JsonScope jsonScope) {
        this.stack.set(this.stack.size() - 1, jsonScope);
    }
    
    private void string(final String s) {
        int n = 0;
        String[] array;
        if (this.htmlSafe) {
            array = JsonWriter.HTML_SAFE_REPLACEMENT_CHARS;
        }
        else {
            array = JsonWriter.REPLACEMENT_CHARS;
        }
        this.out.write("\"");
        final int length = s.length();
        int i = 0;
    Label_0071_Outer:
        while (i < length) {
            final char char1 = s.charAt(i);
            while (true) {
                String s2 = null;
                Label_0101: {
                    int n2;
                    if (char1 < '\u0080') {
                        if ((s2 = array[char1]) != null) {
                            break Label_0101;
                        }
                        n2 = n;
                    }
                    else {
                        if (char1 == '\u2028') {
                            s2 = "\\u2028";
                            break Label_0101;
                        }
                        n2 = n;
                        if (char1 == '\u2029') {
                            s2 = "\\u2029";
                            break Label_0101;
                        }
                    }
                    ++i;
                    n = n2;
                    continue Label_0071_Outer;
                }
                if (n < i) {
                    this.out.write(s, n, i - n);
                }
                this.out.write(s2);
                int n2 = i + 1;
                continue;
            }
        }
        if (n < length) {
            this.out.write(s, n, length - n);
        }
        this.out.write("\"");
    }
    
    private void writeDeferredName() {
        if (this.deferredName != null) {
            this.beforeName();
            this.string(this.deferredName);
            this.deferredName = null;
        }
    }
    
    public JsonWriter beginArray() {
        this.writeDeferredName();
        return this.open(JsonScope.EMPTY_ARRAY, "[");
    }
    
    public JsonWriter beginObject() {
        this.writeDeferredName();
        return this.open(JsonScope.EMPTY_OBJECT, "{");
    }
    
    @Override
    public void close() {
        this.out.close();
        final int size = this.stack.size();
        if (size > 1 || (size == 1 && this.stack.get(size - 1) != JsonScope.NONEMPTY_DOCUMENT)) {
            throw new IOException("Incomplete document");
        }
        this.stack.clear();
    }
    
    public JsonWriter endArray() {
        return this.close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
    }
    
    public JsonWriter endObject() {
        return this.close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
    }
    
    @Override
    public void flush() {
        if (this.stack.isEmpty()) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }
    
    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }
    
    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }
    
    public boolean isLenient() {
        return this.lenient;
    }
    
    public JsonWriter name(final String deferredName) {
        if (deferredName == null) {
            throw new NullPointerException("name == null");
        }
        if (this.deferredName != null) {
            throw new IllegalStateException();
        }
        if (this.stack.isEmpty()) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.deferredName = deferredName;
        return this;
    }
    
    public JsonWriter nullValue() {
        if (this.deferredName != null) {
            if (!this.serializeNulls) {
                this.deferredName = null;
                return this;
            }
            this.writeDeferredName();
        }
        this.beforeValue(false);
        this.out.write("null");
        return this;
    }
    
    public final void setHtmlSafe(final boolean htmlSafe) {
        this.htmlSafe = htmlSafe;
    }
    
    public final void setIndent(final String indent) {
        if (indent.length() == 0) {
            this.indent = null;
            this.separator = ":";
            return;
        }
        this.indent = indent;
        this.separator = ": ";
    }
    
    public final void setLenient(final boolean lenient) {
        this.lenient = lenient;
    }
    
    public final void setSerializeNulls(final boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }
    
    public JsonWriter value(final long n) {
        this.writeDeferredName();
        this.beforeValue(false);
        this.out.write(Long.toString(n));
        return this;
    }
    
    public JsonWriter value(final Number n) {
        if (n == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        final String string = n.toString();
        if (!this.lenient && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + n);
        }
        this.beforeValue(false);
        this.out.append((CharSequence)string);
        return this;
    }
    
    public JsonWriter value(final String s) {
        if (s == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue(false);
        this.string(s);
        return this;
    }
    
    public JsonWriter value(final boolean b) {
        this.writeDeferredName();
        this.beforeValue(false);
        final Writer out = this.out;
        String s;
        if (b) {
            s = "true";
        }
        else {
            s = "false";
        }
        out.write(s);
        return this;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.io.IOException;
import java.io.Writer;

public class JSONWriter
{
    private static final int maxdepth = 200;
    private boolean comma;
    protected char mode;
    private final JSONObject[] stack;
    private int top;
    protected Writer writer;
    
    public JSONWriter(final Writer writer) {
        this.comma = false;
        this.mode = 'i';
        this.stack = new JSONObject[200];
        this.top = 0;
        this.writer = writer;
    }
    
    private JSONWriter append(final String s) {
        if (s == null) {
            throw new JSONException("Null pointer");
        }
        if (this.mode != 'o') {
            if (this.mode != 'a') {
                throw new JSONException("Value out of sequence.");
            }
        }
        try {
            if (this.comma && this.mode == 'a') {
                this.writer.write(44);
            }
            this.writer.write(s);
            if (this.mode == 'o') {
                this.mode = 'k';
            }
            this.comma = true;
            return this;
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
        throw new JSONException("Value out of sequence.");
    }
    
    private JSONWriter end(final char c, final char c2) {
        if (this.mode != c) {
            String s;
            if (c == 'a') {
                s = "Misplaced endArray.";
            }
            else {
                s = "Misplaced endObject.";
            }
            throw new JSONException(s);
        }
        this.pop(c);
        try {
            this.writer.write(c2);
            this.comma = true;
            return this;
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
    }
    
    private void pop(final char c) {
        char mode = 'a';
        if (this.top <= 0) {
            throw new JSONException("Nesting error.");
        }
        char c2;
        if (this.stack[this.top - 1] == null) {
            c2 = 'a';
        }
        else {
            c2 = 'k';
        }
        if (c2 != c) {
            throw new JSONException("Nesting error.");
        }
        --this.top;
        if (this.top == 0) {
            mode = 'd';
        }
        else if (this.stack[this.top - 1] != null) {
            mode = 'k';
        }
        this.mode = mode;
    }
    
    private void push(final JSONObject jsonObject) {
        if (this.top >= 200) {
            throw new JSONException("Nesting too deep.");
        }
        char mode;
        if ((this.stack[this.top] = jsonObject) == null) {
            mode = 'a';
        }
        else {
            mode = 'k';
        }
        this.mode = mode;
        ++this.top;
    }
    
    public JSONWriter array() {
        if (this.mode == 'i' || this.mode == 'o' || this.mode == 'a') {
            this.push(null);
            this.append("[");
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced array.");
    }
    
    public JSONWriter endArray() {
        return this.end('a', ']');
    }
    
    public JSONWriter endObject() {
        return this.end('k', '}');
    }
    
    public JSONWriter key(final String s) {
        if (s == null) {
            throw new JSONException("Null key.");
        }
        if (this.mode == 'k') {
            try {
                this.stack[this.top - 1].putOnce(s, Boolean.TRUE);
                if (this.comma) {
                    this.writer.write(44);
                }
                this.writer.write(JSONObject.quote(s));
                this.writer.write(58);
                this.comma = false;
                this.mode = 'o';
                return this;
            }
            catch (IOException ex) {
                throw new JSONException(ex);
            }
        }
        throw new JSONException("Misplaced key.");
    }
    
    public JSONWriter object() {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        if (this.mode == 'o' || this.mode == 'a') {
            this.append("{");
            this.push(new JSONObject());
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced object.");
    }
    
    public JSONWriter value(final double n) {
        return this.value(new Double(n));
    }
    
    public JSONWriter value(final long n) {
        return this.append(Long.toString(n));
    }
    
    public JSONWriter value(final Object o) {
        return this.append(JSONObject.valueToString(o));
    }
    
    public JSONWriter value(final boolean b) {
        String s;
        if (b) {
            s = "true";
        }
        else {
            s = "false";
        }
        return this.append(s);
    }
}

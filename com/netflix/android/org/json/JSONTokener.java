// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.io.IOException;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;

public class JSONTokener
{
    private long character;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private Reader reader;
    private boolean usePrevious;
    
    public JSONTokener(final InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }
    
    public JSONTokener(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.reader = reader;
        this.eof = false;
        this.usePrevious = false;
        this.previous = '\0';
        this.index = 0L;
        this.character = 1L;
        this.line = 1L;
    }
    
    public JSONTokener(final String s) {
        this(new StringReader(s));
    }
    
    public static int dehexchar(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        return -1;
    }
    
    public void back() {
        if (this.usePrevious || this.index <= 0L) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        --this.index;
        --this.character;
        this.usePrevious = true;
        this.eof = false;
    }
    
    public boolean end() {
        return this.eof && !this.usePrevious;
    }
    
    public boolean more() {
        this.next();
        if (this.end()) {
            return false;
        }
        this.back();
        return true;
    }
    
    public char next() {
        long character = 0L;
        int previous = 0;
        while (true) {
            Label_0104: {
                Label_0021: {
                    if (this.usePrevious) {
                        this.usePrevious = false;
                        previous = this.previous;
                    }
                    else {
                        int read = 0;
                        Label_0146: {
                            try {
                                read = this.reader.read();
                                if (read <= 0) {
                                    this.eof = true;
                                    break Label_0021;
                                }
                                break Label_0146;
                            }
                            catch (IOException ex) {
                                throw new JSONException(ex);
                            }
                            break Label_0104;
                        }
                        previous = read;
                    }
                }
                ++this.index;
                if (this.previous == '\r') {
                    ++this.line;
                    if (previous != 10) {
                        break Label_0104;
                    }
                }
                else {
                    if (previous == 10) {
                        ++this.line;
                        this.character = 0L;
                        return this.previous = (char)previous;
                    }
                    ++this.character;
                    return this.previous = (char)previous;
                }
                this.character = character;
                return this.previous = (char)previous;
            }
            character = 1L;
            continue;
        }
    }
    
    public char next(final char c) {
        final char next = this.next();
        if (next != c) {
            throw this.syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
        }
        return next;
    }
    
    public String next(final int n) {
        if (n == 0) {
            return "";
        }
        final char[] array = new char[n];
        for (int i = 0; i < n; ++i) {
            array[i] = this.next();
            if (this.end()) {
                throw this.syntaxError("Substring bounds error");
            }
        }
        return new String(array);
    }
    
    public char nextClean() {
        char next;
        do {
            next = this.next();
        } while (next != '\0' && next <= ' ');
        return next;
    }
    
    public String nextString(final char c) {
        final StringBuffer sb = new StringBuffer();
        while (true) {
            final char next = this.next();
            switch (next) {
                default: {
                    if (next == c) {
                        return sb.toString();
                    }
                    sb.append(next);
                    continue;
                }
                case 0:
                case 10:
                case 13: {
                    throw this.syntaxError("Unterminated string");
                }
                case 92: {
                    final char next2 = this.next();
                    switch (next2) {
                        default: {
                            throw this.syntaxError("Illegal escape.");
                        }
                        case 98: {
                            sb.append('\b');
                            continue;
                        }
                        case 116: {
                            sb.append('\t');
                            continue;
                        }
                        case 110: {
                            sb.append('\n');
                            continue;
                        }
                        case 102: {
                            sb.append('\f');
                            continue;
                        }
                        case 114: {
                            sb.append('\r');
                            continue;
                        }
                        case 117: {
                            sb.append((char)Integer.parseInt(this.next(4), 16));
                            continue;
                        }
                        case 34:
                        case 39:
                        case 47:
                        case 92: {
                            sb.append(next2);
                            continue;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public String nextTo(final char c) {
        final StringBuffer sb = new StringBuffer();
        char next;
        while (true) {
            next = this.next();
            if (next == c || next == '\0' || next == '\n' || next == '\r') {
                break;
            }
            sb.append(next);
        }
        if (next != '\0') {
            this.back();
        }
        return sb.toString().trim();
    }
    
    public String nextTo(final String s) {
        final StringBuffer sb = new StringBuffer();
        char next;
        while (true) {
            next = this.next();
            if (s.indexOf(next) >= 0 || next == '\0' || next == '\n' || next == '\r') {
                break;
            }
            sb.append(next);
        }
        if (next != '\0') {
            this.back();
        }
        return sb.toString().trim();
    }
    
    public Object nextValue() {
        char c = this.nextClean();
        switch (c) {
            default: {
                final StringBuffer sb = new StringBuffer();
                while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
                    sb.append(c);
                    c = this.next();
                }
                this.back();
                final String trim = sb.toString().trim();
                if ("".equals(trim)) {
                    throw this.syntaxError("Missing value");
                }
                return JSONObject.stringToValue(trim);
            }
            case 34:
            case 39: {
                return this.nextString(c);
            }
            case 123: {
                this.back();
                return new JSONObject(this);
            }
            case 91: {
                this.back();
                return new JSONArray(this);
            }
        }
    }
    
    public char skipTo(final char c) {
        try {
            final long index = this.index;
            final long character = this.character;
            final long line = this.line;
            this.reader.mark(1000000);
            char next;
            do {
                next = this.next();
                if (next == '\0') {
                    this.reader.reset();
                    this.index = index;
                    this.character = character;
                    this.line = line;
                    return next;
                }
            } while (next != c);
            this.back();
            return next;
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
    }
    
    public JSONException syntaxError(final String s) {
        return new JSONException(s + this.toString());
    }
    
    @Override
    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}

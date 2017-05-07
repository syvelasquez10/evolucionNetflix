// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.stream;

import java.io.IOException;
import java.io.EOFException;
import com.google.gson.internal.JsonReaderInternalAccess;
import java.io.Reader;
import java.io.Closeable;

public class JsonReader implements Closeable
{
    private static final char[] NON_EXECUTE_PREFIX;
    private final char[] buffer;
    private int bufferStartColumn;
    private int bufferStartLine;
    private final Reader in;
    private boolean lenient;
    private int limit;
    private String name;
    private int pos;
    private boolean skipping;
    private JsonScope[] stack;
    private int stackSize;
    private final StringPool stringPool;
    private JsonToken token;
    private String value;
    private int valueLength;
    private int valuePos;
    
    static {
        NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
        JsonReaderInternalAccess.INSTANCE = new JsonReader$1();
    }
    
    public JsonReader(final Reader in) {
        this.stringPool = new StringPool();
        this.lenient = false;
        this.buffer = new char[1024];
        this.pos = 0;
        this.limit = 0;
        this.bufferStartLine = 1;
        this.bufferStartColumn = 1;
        this.stack = new JsonScope[32];
        this.stackSize = 0;
        this.push(JsonScope.EMPTY_DOCUMENT);
        this.skipping = false;
        if (in == null) {
            throw new NullPointerException("in == null");
        }
        this.in = in;
    }
    
    private JsonToken advance() {
        this.peek();
        final JsonToken token = this.token;
        this.token = null;
        this.value = null;
        this.name = null;
        return token;
    }
    
    private void checkLenient() {
        if (!this.lenient) {
            throw this.syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }
    
    private void consumeNonExecutePrefix() {
        this.nextNonWhitespace(true);
        --this.pos;
        if (this.pos + JsonReader.NON_EXECUTE_PREFIX.length <= this.limit || this.fillBuffer(JsonReader.NON_EXECUTE_PREFIX.length)) {
            for (int i = 0; i < JsonReader.NON_EXECUTE_PREFIX.length; ++i) {
                if (this.buffer[this.pos + i] != JsonReader.NON_EXECUTE_PREFIX[i]) {
                    return;
                }
            }
            this.pos += JsonReader.NON_EXECUTE_PREFIX.length;
        }
    }
    
    private JsonToken decodeLiteral() {
        if (this.valuePos == -1) {
            return JsonToken.STRING;
        }
        if (this.valueLength == 4 && ('n' == this.buffer[this.valuePos] || 'N' == this.buffer[this.valuePos]) && ('u' == this.buffer[this.valuePos + 1] || 'U' == this.buffer[this.valuePos + 1]) && ('l' == this.buffer[this.valuePos + 2] || 'L' == this.buffer[this.valuePos + 2]) && ('l' == this.buffer[this.valuePos + 3] || 'L' == this.buffer[this.valuePos + 3])) {
            this.value = "null";
            return JsonToken.NULL;
        }
        if (this.valueLength == 4 && ('t' == this.buffer[this.valuePos] || 'T' == this.buffer[this.valuePos]) && ('r' == this.buffer[this.valuePos + 1] || 'R' == this.buffer[this.valuePos + 1]) && ('u' == this.buffer[this.valuePos + 2] || 'U' == this.buffer[this.valuePos + 2]) && ('e' == this.buffer[this.valuePos + 3] || 'E' == this.buffer[this.valuePos + 3])) {
            this.value = "true";
            return JsonToken.BOOLEAN;
        }
        if (this.valueLength == 5 && ('f' == this.buffer[this.valuePos] || 'F' == this.buffer[this.valuePos]) && ('a' == this.buffer[this.valuePos + 1] || 'A' == this.buffer[this.valuePos + 1]) && ('l' == this.buffer[this.valuePos + 2] || 'L' == this.buffer[this.valuePos + 2]) && ('s' == this.buffer[this.valuePos + 3] || 'S' == this.buffer[this.valuePos + 3]) && ('e' == this.buffer[this.valuePos + 4] || 'E' == this.buffer[this.valuePos + 4])) {
            this.value = "false";
            return JsonToken.BOOLEAN;
        }
        this.value = this.stringPool.get(this.buffer, this.valuePos, this.valueLength);
        return this.decodeNumber(this.buffer, this.valuePos, this.valueLength);
    }
    
    private JsonToken decodeNumber(final char[] array, final int n, final int n2) {
        char c = array[n];
        int n3;
        if (c == '-') {
            n3 = n + 1;
            c = array[n3];
        }
        else {
            n3 = n;
        }
        int n4;
        char c2;
        if (c == '0') {
            n4 = n3 + 1;
            c2 = array[n4];
        }
        else {
            if (c < '1' || c > '9') {
                return JsonToken.STRING;
            }
            int n5 = n3 + 1;
            char c3 = array[n5];
            while (true) {
                c2 = c3;
                n4 = n5;
                if (c3 < '0') {
                    break;
                }
                c2 = c3;
                n4 = n5;
                if (c3 > '9') {
                    break;
                }
                ++n5;
                c3 = array[n5];
            }
        }
        char c4 = c2;
        int n6 = n4;
        if (c2 == '.') {
            int n7 = n4 + 1;
            char c5 = array[n7];
            while (true) {
                c4 = c5;
                n6 = n7;
                if (c5 < '0') {
                    break;
                }
                c4 = c5;
                n6 = n7;
                if (c5 > '9') {
                    break;
                }
                ++n7;
                c5 = array[n7];
            }
        }
        int n8 = 0;
        Label_0320: {
            if (c4 != 'e') {
                n8 = n6;
                if (c4 != 'E') {
                    break Label_0320;
                }
            }
            final int n9 = n6 + 1;
            final char c6 = array[n9];
            char c7 = '\0';
            int n10 = 0;
            Label_0253: {
                if (c6 != '+') {
                    c7 = c6;
                    n10 = n9;
                    if (c6 != '-') {
                        break Label_0253;
                    }
                }
                n10 = n9 + 1;
                c7 = array[n10];
            }
            if (c7 < '0' || c7 > '9') {
                return JsonToken.STRING;
            }
            int n11 = n10 + 1;
            char c8 = array[n11];
            while (true) {
                n8 = n11;
                if (c8 < '0') {
                    break;
                }
                n8 = n11;
                if (c8 > '9') {
                    break;
                }
                ++n11;
                c8 = array[n11];
            }
        }
        if (n8 == n + n2) {
            return JsonToken.NUMBER;
        }
        return JsonToken.STRING;
    }
    
    private void expect(final JsonToken jsonToken) {
        this.peek();
        if (this.token != jsonToken) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        this.advance();
    }
    
    private boolean fillBuffer(final int n) {
        final char[] buffer = this.buffer;
        int bufferStartLine = this.bufferStartLine;
        int bufferStartColumn = this.bufferStartColumn;
        for (int pos = this.pos, i = 0; i < pos; ++i) {
            if (buffer[i] == '\n') {
                ++bufferStartLine;
                bufferStartColumn = 1;
            }
            else {
                ++bufferStartColumn;
            }
        }
        this.bufferStartLine = bufferStartLine;
        this.bufferStartColumn = bufferStartColumn;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(buffer, this.pos, buffer, 0, this.limit);
        }
        else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            final int read = this.in.read(buffer, this.limit, buffer.length - this.limit);
            if (read == -1) {
                return false;
            }
            this.limit += read;
            if (this.bufferStartLine != 1 || this.bufferStartColumn != 1 || this.limit <= 0 || buffer[0] != '\ufeff') {
                continue;
            }
            ++this.pos;
            --this.bufferStartColumn;
        } while (this.limit < n);
        return true;
    }
    
    private int getColumnNumber() {
        int bufferStartColumn = this.bufferStartColumn;
        for (int i = 0; i < this.pos; ++i) {
            if (this.buffer[i] == '\n') {
                bufferStartColumn = 1;
            }
            else {
                ++bufferStartColumn;
            }
        }
        return bufferStartColumn;
    }
    
    private int getLineNumber() {
        int bufferStartLine = this.bufferStartLine;
        int n;
        for (int i = 0; i < this.pos; ++i, bufferStartLine = n) {
            n = bufferStartLine;
            if (this.buffer[i] == '\n') {
                n = bufferStartLine + 1;
            }
        }
        return bufferStartLine;
    }
    
    private JsonToken nextInArray(final boolean b) {
        if (b) {
            this.stack[this.stackSize - 1] = JsonScope.NONEMPTY_ARRAY;
        }
        else {
            switch (this.nextNonWhitespace(true)) {
                case 59: {
                    this.checkLenient();
                }
                case 44: {
                    break;
                }
                default: {
                    throw this.syntaxError("Unterminated array");
                }
                case 93: {
                    --this.stackSize;
                    return this.token = JsonToken.END_ARRAY;
                }
            }
        }
        switch (this.nextNonWhitespace(true)) {
            default: {
                --this.pos;
                return this.nextValue();
            }
            case 93: {
                if (b) {
                    --this.stackSize;
                    return this.token = JsonToken.END_ARRAY;
                }
            }
            case 44:
            case 59: {
                this.checkLenient();
                --this.pos;
                this.value = "null";
                return this.token = JsonToken.NULL;
            }
        }
    }
    
    private JsonToken nextInObject(final boolean b) {
        if (b) {
            switch (this.nextNonWhitespace(true)) {
                default: {
                    --this.pos;
                    break;
                }
                case 125: {
                    --this.stackSize;
                    return this.token = JsonToken.END_OBJECT;
                }
            }
        }
        else {
            switch (this.nextNonWhitespace(true)) {
                case 44:
                case 59: {
                    break;
                }
                default: {
                    throw this.syntaxError("Unterminated object");
                }
                case 125: {
                    --this.stackSize;
                    return this.token = JsonToken.END_OBJECT;
                }
            }
        }
        final int nextNonWhitespace = this.nextNonWhitespace(true);
        switch (nextNonWhitespace) {
            default: {
                this.checkLenient();
                --this.pos;
                this.name = this.nextLiteral(false);
                if (this.name.length() == 0) {
                    throw this.syntaxError("Expected name");
                }
                break;
            }
            case 39: {
                this.checkLenient();
            }
            case 34: {
                this.name = this.nextString((char)nextNonWhitespace);
                break;
            }
        }
        this.stack[this.stackSize - 1] = JsonScope.DANGLING_NAME;
        return this.token = JsonToken.NAME;
    }
    
    private String nextLiteral(final boolean b) {
        final String s = null;
        this.valuePos = -1;
        this.valueLength = 0;
        int n = 0;
        StringBuilder sb = null;
    Label_0205:
        while (true) {
            Block_7: {
                Block_5: {
                    StringBuilder sb2;
                    int n2;
                    while (true) {
                        if (this.pos + n < this.limit) {
                            sb2 = sb;
                            n2 = n;
                            switch (this.buffer[this.pos + n]) {
                                default: {
                                    ++n;
                                    continue;
                                }
                                case '#':
                                case '/':
                                case ';':
                                case '=':
                                case '\\': {
                                    this.checkLenient();
                                    n2 = n;
                                    sb2 = sb;
                                }
                                case '\t':
                                case '\n':
                                case '\f':
                                case '\r':
                                case ' ':
                                case ',':
                                case ':':
                                case '[':
                                case ']':
                                case '{':
                                case '}': {
                                    break Label_0205;
                                }
                            }
                        }
                        else if (n < this.buffer.length) {
                            if (!this.fillBuffer(n + 1)) {
                                break Block_5;
                            }
                            continue;
                        }
                        else {
                            if ((sb2 = sb) == null) {
                                sb2 = new StringBuilder();
                            }
                            sb2.append(this.buffer, this.pos, n);
                            this.valueLength += n;
                            this.pos += n;
                            if (!this.fillBuffer(1)) {
                                break Block_7;
                            }
                            n = 0;
                            sb = sb2;
                        }
                    }
                    String s2;
                    if (b && sb2 == null) {
                        this.valuePos = this.pos;
                        s2 = s;
                    }
                    else if (this.skipping) {
                        s2 = "skipped!";
                    }
                    else if (sb2 == null) {
                        s2 = this.stringPool.get(this.buffer, this.pos, n2);
                    }
                    else {
                        sb2.append(this.buffer, this.pos, n2);
                        s2 = sb2.toString();
                    }
                    this.valueLength += n2;
                    this.pos += n2;
                    return s2;
                }
                this.buffer[this.limit] = '\0';
                StringBuilder sb2 = sb;
                int n2 = n;
                continue Label_0205;
            }
            int n2 = 0;
            continue Label_0205;
        }
    }
    
    private int nextNonWhitespace(final boolean b) {
        final char[] buffer = this.buffer;
        int n = this.pos;
        int n2 = this.limit;
        while (true) {
            int limit = n2;
            int pos = n;
            if (n == n2) {
                this.pos = n;
                if (!this.fillBuffer(1)) {
                    if (b) {
                        throw new EOFException("End of input at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                    }
                    return -1;
                }
                else {
                    pos = this.pos;
                    limit = this.limit;
                }
            }
            n = pos + 1;
            final char c = buffer[pos];
            switch (c) {
                default: {
                    this.pos = n;
                    return c;
                }
                case 9:
                case 10:
                case 13:
                case 32: {
                    n2 = limit;
                    continue;
                }
                case 47: {
                    this.pos = n;
                    if (n == limit) {
                        --this.pos;
                        final boolean fillBuffer = this.fillBuffer(2);
                        ++this.pos;
                        if (!fillBuffer) {
                            return c;
                        }
                    }
                    this.checkLenient();
                    switch (buffer[this.pos]) {
                        default: {
                            return c;
                        }
                        case '*': {
                            ++this.pos;
                            if (!this.skipTo("*/")) {
                                throw this.syntaxError("Unterminated comment");
                            }
                            n = this.pos + 2;
                            n2 = this.limit;
                            continue;
                        }
                        case '/': {
                            ++this.pos;
                            this.skipToEndOfLine();
                            n = this.pos;
                            n2 = this.limit;
                            continue;
                        }
                    }
                    break;
                }
                case 35: {
                    this.pos = n;
                    this.checkLenient();
                    this.skipToEndOfLine();
                    n = this.pos;
                    n2 = this.limit;
                    continue;
                }
            }
        }
    }
    
    private String nextString(final char c) {
        final char[] buffer = this.buffer;
        StringBuilder sb = null;
        while (true) {
            int pos = this.pos;
            int n = this.limit;
            int i = pos;
            while (i < n) {
                final int n2 = i + 1;
                final char c2 = buffer[i];
                if (c2 == c) {
                    this.pos = n2;
                    if (this.skipping) {
                        return "skipped!";
                    }
                    if (sb == null) {
                        return this.stringPool.get(buffer, pos, n2 - pos - 1);
                    }
                    sb.append(buffer, pos, n2 - pos - 1);
                    return sb.toString();
                }
                else if (c2 == '\\') {
                    this.pos = n2;
                    StringBuilder sb2;
                    if ((sb2 = sb) == null) {
                        sb2 = new StringBuilder();
                    }
                    sb2.append(buffer, pos, n2 - pos - 1);
                    sb2.append(this.readEscapeCharacter());
                    i = this.pos;
                    n = this.limit;
                    sb = sb2;
                    pos = i;
                }
                else {
                    i = n2;
                }
            }
            StringBuilder sb3;
            if ((sb3 = sb) == null) {
                sb3 = new StringBuilder();
            }
            sb3.append(buffer, pos, i - pos);
            this.pos = i;
            sb = sb3;
            if (!this.fillBuffer(1)) {
                throw this.syntaxError("Unterminated string");
            }
        }
    }
    
    private JsonToken nextValue() {
        final int nextNonWhitespace = this.nextNonWhitespace(true);
        switch (nextNonWhitespace) {
            default: {
                --this.pos;
                return this.readLiteral();
            }
            case 123: {
                this.push(JsonScope.EMPTY_OBJECT);
                return this.token = JsonToken.BEGIN_OBJECT;
            }
            case 91: {
                this.push(JsonScope.EMPTY_ARRAY);
                return this.token = JsonToken.BEGIN_ARRAY;
            }
            case 39: {
                this.checkLenient();
            }
            case 34: {
                this.value = this.nextString((char)nextNonWhitespace);
                return this.token = JsonToken.STRING;
            }
        }
    }
    
    private JsonToken objectValue() {
        switch (this.nextNonWhitespace(true)) {
            default: {
                throw this.syntaxError("Expected ':'");
            }
            case 61: {
                this.checkLenient();
                if ((this.pos < this.limit || this.fillBuffer(1)) && this.buffer[this.pos] == '>') {
                    ++this.pos;
                }
            }
            case 58: {
                this.stack[this.stackSize - 1] = JsonScope.NONEMPTY_OBJECT;
                return this.nextValue();
            }
        }
    }
    
    private void push(final JsonScope jsonScope) {
        if (this.stackSize == this.stack.length) {
            final JsonScope[] stack = new JsonScope[this.stackSize * 2];
            System.arraycopy(this.stack, 0, stack, 0, this.stackSize);
            this.stack = stack;
        }
        this.stack[this.stackSize++] = jsonScope;
    }
    
    private char readEscapeCharacter() {
        if (this.pos == this.limit && !this.fillBuffer(1)) {
            throw this.syntaxError("Unterminated escape sequence");
        }
        final char c = this.buffer[this.pos++];
        switch (c) {
            default: {
                return c;
            }
            case 117: {
                if (this.pos + 4 > this.limit && !this.fillBuffer(4)) {
                    throw this.syntaxError("Unterminated escape sequence");
                }
                final int pos = this.pos;
                char c2 = '\0';
                for (int i = pos; i < pos + 4; ++i) {
                    final char c3 = this.buffer[i];
                    final char c4 = (char)(c2 << 4);
                    if (c3 >= '0' && c3 <= '9') {
                        c2 = (char)(c4 + (c3 - '0'));
                    }
                    else if (c3 >= 'a' && c3 <= 'f') {
                        c2 = (char)(c4 + (c3 - 'a' + '\n'));
                    }
                    else {
                        if (c3 < 'A' || c3 > 'F') {
                            throw new NumberFormatException("\\u" + this.stringPool.get(this.buffer, this.pos, 4));
                        }
                        c2 = (char)(c4 + (c3 - 'A' + '\n'));
                    }
                }
                this.pos += 4;
                return c2;
            }
            case 116: {
                return '\t';
            }
            case 98: {
                return '\b';
            }
            case 110: {
                return '\n';
            }
            case 114: {
                return '\r';
            }
            case 102: {
                return '\f';
            }
        }
    }
    
    private JsonToken readLiteral() {
        this.value = this.nextLiteral(true);
        if (this.valueLength == 0) {
            throw this.syntaxError("Expected literal value");
        }
        this.token = this.decodeLiteral();
        if (this.token == JsonToken.STRING) {
            this.checkLenient();
        }
        return this.token;
    }
    
    private boolean skipTo(final String s) {
        final boolean b = false;
        boolean b2 = false;
    Label_0003:
        while (true) {
            if (this.pos + s.length() > this.limit) {
                b2 = b;
                if (!this.fillBuffer(s.length())) {
                    break;
                }
            }
            for (int i = 0; i < s.length(); ++i) {
                if (this.buffer[this.pos + i] != s.charAt(i)) {
                    ++this.pos;
                    continue Label_0003;
                }
            }
            b2 = true;
            break;
        }
        return b2;
    }
    
    private void skipToEndOfLine() {
        while (this.pos < this.limit || this.fillBuffer(1)) {
            final char c = this.buffer[this.pos++];
            if (c == '\r' || c == '\n') {
                break;
            }
        }
    }
    
    private IOException syntaxError(final String s) {
        throw new MalformedJsonException(s + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
    }
    
    public void beginArray() {
        this.expect(JsonToken.BEGIN_ARRAY);
    }
    
    public void beginObject() {
        this.expect(JsonToken.BEGIN_OBJECT);
    }
    
    @Override
    public void close() {
        this.value = null;
        this.token = null;
        this.stack[0] = JsonScope.CLOSED;
        this.stackSize = 1;
        this.in.close();
    }
    
    public void endArray() {
        this.expect(JsonToken.END_ARRAY);
    }
    
    public void endObject() {
        this.expect(JsonToken.END_OBJECT);
    }
    
    public boolean hasNext() {
        this.peek();
        return this.token != JsonToken.END_OBJECT && this.token != JsonToken.END_ARRAY;
    }
    
    public final boolean isLenient() {
        return this.lenient;
    }
    
    public boolean nextBoolean() {
        this.peek();
        if (this.token != JsonToken.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.token + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        final boolean b = this.value == "true";
        this.advance();
        return b;
    }
    
    public double nextDouble() {
        this.peek();
        if (this.token != JsonToken.STRING && this.token != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected a double but was " + this.token + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        final double double1 = Double.parseDouble(this.value);
        if (double1 >= 1.0 && this.value.startsWith("0")) {
            throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        if (!this.lenient && (Double.isNaN(double1) || Double.isInfinite(double1))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + this.value + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        this.advance();
        return double1;
    }
    
    public int nextInt() {
        this.peek();
        if (this.token != JsonToken.STRING && this.token != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected an int but was " + this.token + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        int int1;
        while (true) {
            try {
                int1 = Integer.parseInt(this.value);
                if (int1 >= 1L && this.value.startsWith("0")) {
                    throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                }
            }
            catch (NumberFormatException ex) {
                final double double1 = Double.parseDouble(this.value);
                if ((int1 = (int)double1) != double1) {
                    throw new NumberFormatException("Expected an int but was " + this.value + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                }
                continue;
            }
            break;
        }
        this.advance();
        return int1;
    }
    
    public long nextLong() {
        this.peek();
        if (this.token != JsonToken.STRING && this.token != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected a long but was " + this.token + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        long long1;
        while (true) {
            try {
                long1 = Long.parseLong(this.value);
                if (long1 >= 1L && this.value.startsWith("0")) {
                    throw new MalformedJsonException("JSON forbids octal prefixes: " + this.value + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                }
            }
            catch (NumberFormatException ex) {
                final double double1 = Double.parseDouble(this.value);
                if ((long1 = (long)double1) != double1) {
                    throw new NumberFormatException("Expected a long but was " + this.value + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                }
                continue;
            }
            break;
        }
        this.advance();
        return long1;
    }
    
    public String nextName() {
        this.peek();
        if (this.token != JsonToken.NAME) {
            throw new IllegalStateException("Expected a name but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        final String name = this.name;
        this.advance();
        return name;
    }
    
    public void nextNull() {
        this.peek();
        if (this.token != JsonToken.NULL) {
            throw new IllegalStateException("Expected null but was " + this.token + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        this.advance();
    }
    
    public String nextString() {
        this.peek();
        if (this.token != JsonToken.STRING && this.token != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected a string but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
        }
        final String value = this.value;
        this.advance();
        return value;
    }
    
    public JsonToken peek() {
        JsonToken jsonToken = null;
        if (this.token != null) {
            jsonToken = this.token;
        }
        else {
            switch (JsonReader$2.$SwitchMap$com$google$gson$stream$JsonScope[this.stack[this.stackSize - 1].ordinal()]) {
                default: {
                    throw new AssertionError();
                }
                case 1: {
                    if (this.lenient) {
                        this.consumeNonExecutePrefix();
                    }
                    this.stack[this.stackSize - 1] = JsonScope.NONEMPTY_DOCUMENT;
                    final JsonToken jsonToken2 = jsonToken = this.nextValue();
                    if (this.lenient) {
                        break;
                    }
                    jsonToken = jsonToken2;
                    if (this.token == JsonToken.BEGIN_ARRAY) {
                        break;
                    }
                    jsonToken = jsonToken2;
                    if (this.token != JsonToken.BEGIN_OBJECT) {
                        throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.token + " at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                    }
                    break;
                }
                case 2: {
                    return this.nextInArray(true);
                }
                case 3: {
                    return this.nextInArray(false);
                }
                case 4: {
                    return this.nextInObject(true);
                }
                case 5: {
                    return this.objectValue();
                }
                case 6: {
                    return this.nextInObject(false);
                }
                case 7: {
                    if (this.nextNonWhitespace(false) == -1) {
                        return JsonToken.END_DOCUMENT;
                    }
                    --this.pos;
                    if (!this.lenient) {
                        throw this.syntaxError("Expected EOF");
                    }
                    return this.nextValue();
                }
                case 8: {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
        }
        return jsonToken;
    }
    
    public final void setLenient(final boolean lenient) {
        this.lenient = lenient;
    }
    
    public void skipValue() {
        this.skipping = true;
        int n = 0;
        try {
            int n2;
            do {
                final JsonToken advance = this.advance();
                if (advance == JsonToken.BEGIN_ARRAY || advance == JsonToken.BEGIN_OBJECT) {
                    n2 = n + 1;
                }
                else {
                    if (advance != JsonToken.END_ARRAY) {
                        final JsonToken end_OBJECT = JsonToken.END_OBJECT;
                        n2 = n;
                        if (advance != end_OBJECT) {
                            continue;
                        }
                    }
                    n2 = n - 1;
                }
            } while ((n = n2) != 0);
        }
        finally {
            this.skipping = false;
        }
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber();
    }
}

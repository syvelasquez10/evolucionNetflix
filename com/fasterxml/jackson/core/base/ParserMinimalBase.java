// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.core.JsonParser$Feature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParser;

public abstract class ParserMinimalBase extends JsonParser
{
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;
    
    protected ParserMinimalBase(final int n) {
        super(n);
    }
    
    protected static final String _getCharDesc(final int n) {
        final char c = (char)n;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + n + ")";
        }
        if (n > 255) {
            return "'" + c + "' (code " + n + " / 0x" + Integer.toHexString(n) + ")";
        }
        return "'" + c + "' (code " + n + ")";
    }
    
    protected final JsonParseException _constructError(final String s, final Throwable t) {
        return new JsonParseException(this, s, t);
    }
    
    protected abstract void _handleEOF();
    
    protected char _handleUnrecognizedCharacterEscape(final char c) {
        if (!this.isEnabled(JsonParser$Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) && (c != '\'' || !this.isEnabled(JsonParser$Feature.ALLOW_SINGLE_QUOTES))) {
            this._reportError("Unrecognized character escape " + _getCharDesc(c));
            return c;
        }
        return c;
    }
    
    protected boolean _hasTextualNull(final String s) {
        return "null".equals(s);
    }
    
    protected final void _reportError(final String s) {
        throw this._constructError(s);
    }
    
    protected void _reportInvalidEOF() {
        this._reportInvalidEOF(" in " + this._currToken);
    }
    
    protected void _reportInvalidEOF(final String s) {
        this._reportError("Unexpected end-of-input" + s);
    }
    
    protected void _reportInvalidEOFInValue() {
        this._reportInvalidEOF(" in a value");
    }
    
    protected void _reportMissingRootWS(final int n) {
        this._reportUnexpectedChar(n, "Expected space separating root-level values");
    }
    
    protected void _reportUnexpectedChar(final int n, final String s) {
        if (n < 0) {
            this._reportInvalidEOF();
        }
        String s2 = "Unexpected character (" + _getCharDesc(n) + ")";
        if (s != null) {
            s2 = s2 + ": " + s;
        }
        this._reportError(s2);
    }
    
    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }
    
    protected void _throwInvalidSpace(int n) {
        n = (char)n;
        this._reportError("Illegal character (" + _getCharDesc(n) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }
    
    protected void _throwUnquotedSpace(int n, final String s) {
        if (!this.isEnabled(JsonParser$Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || n > 32) {
            n = (char)n;
            this._reportError("Illegal unquoted character (" + _getCharDesc(n) + "): has to be escaped using backslash to be included in " + s);
        }
    }
    
    protected final void _wrapError(final String s, final Throwable t) {
        throw this._constructError(s, t);
    }
    
    @Override
    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }
    
    @Override
    public abstract String getCurrentName();
    
    @Override
    public JsonToken getCurrentToken() {
        return this._currToken;
    }
    
    public abstract String getText();
    
    @Override
    public boolean getValueAsBoolean(final boolean b) {
        final boolean b2 = true;
        final JsonToken currToken = this._currToken;
        if (currToken != null) {
            boolean b3 = b2;
            switch (currToken.id()) {
                case 9: {
                    return b3;
                }
                case 6: {
                    final String trim = this.getText().trim();
                    b3 = b2;
                    if ("true".equals(trim)) {
                        return b3;
                    }
                    if ("false".equals(trim)) {
                        return false;
                    }
                    if (this._hasTextualNull(trim)) {
                        return false;
                    }
                    break;
                }
                case 7: {
                    b3 = b2;
                    if (this.getIntValue() == 0) {
                        return false;
                    }
                    return b3;
                }
                case 10:
                case 11: {
                    return false;
                }
                case 12: {
                    final Object embeddedObject = this.getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return (boolean)embeddedObject;
                    }
                    break;
                }
            }
        }
        return b;
    }
    
    @Override
    public double getValueAsDouble(final double n) {
        final JsonToken currToken = this._currToken;
        if (currToken != null) {
            switch (currToken.id()) {
                case 6: {
                    final String text = this.getText();
                    if (this._hasTextualNull(text)) {
                        return 0.0;
                    }
                    return NumberInput.parseAsDouble(text, n);
                }
                case 7:
                case 8: {
                    return this.getDoubleValue();
                }
                case 9: {
                    return 1.0;
                }
                case 10:
                case 11: {
                    return 0.0;
                }
                case 12: {
                    final Object embeddedObject = this.getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number)embeddedObject).doubleValue();
                    }
                    break;
                }
            }
        }
        return n;
    }
    
    @Override
    public int getValueAsInt() {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return this.getIntValue();
        }
        return this.getValueAsInt(0);
    }
    
    @Override
    public int getValueAsInt(final int n) {
        final JsonToken currToken = this._currToken;
        int intValue;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            intValue = this.getIntValue();
        }
        else {
            intValue = n;
            if (currToken != null) {
                switch (currToken.id()) {
                    default: {
                        return n;
                    }
                    case 6: {
                        final String text = this.getText();
                        if (this._hasTextualNull(text)) {
                            return 0;
                        }
                        return NumberInput.parseAsInt(text, n);
                    }
                    case 9: {
                        return 1;
                    }
                    case 10: {
                        return 0;
                    }
                    case 11: {
                        return 0;
                    }
                    case 12: {
                        final Object embeddedObject = this.getEmbeddedObject();
                        intValue = n;
                        if (embeddedObject instanceof Number) {
                            return ((Number)embeddedObject).intValue();
                        }
                        break;
                    }
                }
            }
        }
        return intValue;
    }
    
    @Override
    public long getValueAsLong() {
        final JsonToken currToken = this._currToken;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return this.getLongValue();
        }
        return this.getValueAsLong(0L);
    }
    
    @Override
    public long getValueAsLong(final long n) {
        final JsonToken currToken = this._currToken;
        long longValue;
        if (currToken == JsonToken.VALUE_NUMBER_INT || currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            longValue = this.getLongValue();
        }
        else {
            longValue = n;
            if (currToken != null) {
                switch (currToken.id()) {
                    default: {
                        return n;
                    }
                    case 6: {
                        final String text = this.getText();
                        if (this._hasTextualNull(text)) {
                            return 0L;
                        }
                        return NumberInput.parseAsLong(text, n);
                    }
                    case 9: {
                        return 1L;
                    }
                    case 10:
                    case 11: {
                        return 0L;
                    }
                    case 12: {
                        final Object embeddedObject = this.getEmbeddedObject();
                        longValue = n;
                        if (embeddedObject instanceof Number) {
                            return ((Number)embeddedObject).longValue();
                        }
                        break;
                    }
                }
            }
        }
        return longValue;
    }
    
    @Override
    public String getValueAsString() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this.getText();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this.getCurrentName();
        }
        return this.getValueAsString(null);
    }
    
    @Override
    public String getValueAsString(final String s) {
        String text;
        if (this._currToken == JsonToken.VALUE_STRING) {
            text = this.getText();
        }
        else {
            if (this._currToken == JsonToken.FIELD_NAME) {
                return this.getCurrentName();
            }
            text = s;
            if (this._currToken != null) {
                text = s;
                if (this._currToken != JsonToken.VALUE_NULL) {
                    text = s;
                    if (this._currToken.isScalarValue()) {
                        return this.getText();
                    }
                }
            }
        }
        return text;
    }
}

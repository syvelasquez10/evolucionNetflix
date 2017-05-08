// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core;

import java.io.Serializable;

public class JsonLocation implements Serializable
{
    public static final JsonLocation NA;
    final int _columnNr;
    final int _lineNr;
    final transient Object _sourceRef;
    final long _totalBytes;
    final long _totalChars;
    
    static {
        NA = new JsonLocation("N/A", -1L, -1L, -1, -1);
    }
    
    public JsonLocation(final Object o, final long n, final int n2, final int n3) {
        this(o, -1L, n, n2, n3);
    }
    
    public JsonLocation(final Object sourceRef, final long totalBytes, final long totalChars, final int lineNr, final int columnNr) {
        this._sourceRef = sourceRef;
        this._totalBytes = totalBytes;
        this._totalChars = totalChars;
        this._lineNr = lineNr;
        this._columnNr = columnNr;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (o == this) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (o instanceof JsonLocation) {
                    final JsonLocation jsonLocation = (JsonLocation)o;
                    if (this._sourceRef == null) {
                        b3 = b2;
                        if (jsonLocation._sourceRef != null) {
                            return b3;
                        }
                    }
                    else if (!this._sourceRef.equals(jsonLocation._sourceRef)) {
                        return false;
                    }
                    return this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && this.getByteOffset() == jsonLocation.getByteOffset() && b;
                }
            }
        }
        return b3;
    }
    
    public long getByteOffset() {
        return this._totalBytes;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this._sourceRef == null) {
            hashCode = 1;
        }
        else {
            hashCode = this._sourceRef.hashCode();
        }
        return ((hashCode ^ this._lineNr) + this._columnNr ^ (int)this._totalChars) + (int)this._totalBytes;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        if (this._sourceRef == null) {
            sb.append("UNKNOWN");
        }
        else {
            sb.append(this._sourceRef.toString());
        }
        sb.append("; line: ");
        sb.append(this._lineNr);
        sb.append(", column: ");
        sb.append(this._columnNr);
        sb.append(']');
        return sb.toString();
    }
}

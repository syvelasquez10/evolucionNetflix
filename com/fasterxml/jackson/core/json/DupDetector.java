// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonParser;
import java.util.HashSet;

public class DupDetector
{
    protected String _firstName;
    protected String _secondName;
    protected HashSet<String> _seen;
    protected final Object _source;
    
    private DupDetector(final Object source) {
        this._source = source;
    }
    
    public static DupDetector rootDetector(final JsonParser jsonParser) {
        return new DupDetector(jsonParser);
    }
    
    public DupDetector child() {
        return new DupDetector(this._source);
    }
    
    public Object getSource() {
        return this._source;
    }
    
    public boolean isDup(final String s) {
        final boolean b = true;
        boolean b2;
        if (this._firstName == null) {
            this._firstName = s;
            b2 = false;
        }
        else {
            b2 = b;
            if (!s.equals(this._firstName)) {
                if (this._secondName == null) {
                    this._secondName = s;
                    return false;
                }
                b2 = b;
                if (!s.equals(this._secondName)) {
                    if (this._seen == null) {
                        (this._seen = new HashSet<String>(16)).add(this._firstName);
                        this._seen.add(this._secondName);
                    }
                    b2 = b;
                    if (this._seen.add(s)) {
                        return false;
                    }
                }
            }
        }
        return b2;
    }
    
    public void reset() {
        this._firstName = null;
        this._secondName = null;
        this._seen = null;
    }
}

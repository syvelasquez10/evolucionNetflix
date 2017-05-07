// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public class iy extends IOException
{
    public iy(final String s) {
        super(s);
    }
    
    static iy gg() {
        return new iy("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }
    
    static iy gh() {
        return new iy("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
    
    static iy gi() {
        return new iy("CodedInputStream encountered a malformed varint.");
    }
    
    static iy gj() {
        return new iy("Protocol message contained an invalid tag (zero).");
    }
    
    static iy gk() {
        return new iy("Protocol message end-group tag did not match expected tag.");
    }
    
    static iy gl() {
        return new iy("Protocol message tag had invalid wire type.");
    }
}

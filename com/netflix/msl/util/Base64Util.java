// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import javax.xml.bind.DatatypeConverter;

public class Base64Util
{
    public static byte[] platformUrlDecode(final String s) {
        return DatatypeConverter.parseBase64Binary(s.replaceAll("\\.", "="));
    }
    
    public static String platformUrlEncode(final byte[] val) {
        return DatatypeConverter.printBase64Binary(val).replaceAll("=", ".");
    }
}

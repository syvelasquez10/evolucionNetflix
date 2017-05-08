// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import javax.xml.bind.DatatypeConverter;

public class Base64Jaxb implements Base64$Base64Impl
{
    @Override
    public byte[] decode(final String lexicalXSDBase64Binary) {
        if (!Base64.isValidBase64(lexicalXSDBase64Binary)) {
            throw new IllegalArgumentException("Invalid Base64 encoded string: " + lexicalXSDBase64Binary);
        }
        return DatatypeConverter.parseBase64Binary(lexicalXSDBase64Binary);
    }
    
    @Override
    public String encode(final byte[] val) {
        return DatatypeConverter.printBase64Binary(val);
    }
}

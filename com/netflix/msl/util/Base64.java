// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.util.regex.Pattern;

public class Base64
{
    private static final Pattern BASE64_PATTERN;
    private static final String WHITESPACE_REGEX = "\\s";
    private static Base64$Base64Impl impl;
    
    static {
        BASE64_PATTERN = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
        Base64.impl = new Base64Jaxb();
    }
    
    public static byte[] decode(final String s) {
        return Base64.impl.decode(s);
    }
    
    public static String encode(final byte[] array) {
        return Base64.impl.encode(array);
    }
    
    public static boolean isValidBase64(String replaceAll) {
        replaceAll = replaceAll.replaceAll("\\s", "");
        return Base64.BASE64_PATTERN.matcher(replaceAll).matches();
    }
    
    public static void setImpl(final Base64$Base64Impl impl) {
        if (impl == null) {
            throw new NullPointerException("Base64 implementation cannot be null.");
        }
        Base64.impl = impl;
    }
}

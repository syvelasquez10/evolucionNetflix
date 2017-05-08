// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;

public final class IpAddressUtils
{
    private static Pattern VALID_IPV4_PATTERN;
    private static Pattern VALID_IPV6_PATTERN;
    private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
    
    static {
        IpAddressUtils.VALID_IPV4_PATTERN = null;
        IpAddressUtils.VALID_IPV6_PATTERN = null;
        try {
            IpAddressUtils.VALID_IPV4_PATTERN = Pattern.compile("(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])", 2);
            IpAddressUtils.VALID_IPV6_PATTERN = Pattern.compile("([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}", 2);
        }
        catch (PatternSyntaxException ex) {}
    }
    
    public static boolean isIPv4Address(final String s) {
        return IpAddressUtils.VALID_IPV4_PATTERN.matcher(s).matches();
    }
    
    public static boolean isIPv6Address(final String s) {
        return IpAddressUtils.VALID_IPV6_PATTERN.matcher(s).matches();
    }
    
    public static boolean isIpAddress(final String s) {
        return isIPv4Address(s) || isIPv6Address(s);
    }
}

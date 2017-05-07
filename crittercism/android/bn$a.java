// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public enum bn$a
{
    a("US_WEST_1_PROD", 0, "00555300", "crittercism.com"), 
    b("US_WEST_2_CI", 1, "00555304", "crit-ci.com"), 
    c("US_WEST_2_STAGING", 2, "00555305", "crit-staging.com"), 
    d("EU_CENTRAL_1", 3, "00444503", "eu.crittercism.com");
    
    private final String e;
    private final String f;
    
    private bn$a(final String s, final int n, final String e, final String f) {
        this.e = e;
        this.f = f;
    }
    
    public static bn$a a(String substring) {
        if (substring.matches("[0-9a-fA-F]+")) {
            if (substring.length() == 24) {
                return bn$a.a;
            }
            if (substring.length() == 40) {
                substring = substring.substring(substring.length() - 8);
                final bn$a[] values = values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    final bn$a bn$a = values[i];
                    if (substring.equals(bn$a.e)) {
                        return bn$a;
                    }
                }
            }
        }
        return null;
    }
    
    public final String a() {
        return this.f;
    }
}

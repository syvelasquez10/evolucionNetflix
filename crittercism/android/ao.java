// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import android.provider.Settings$Secure;
import android.content.Context;

public final class ao implements t
{
    public String a;
    
    private ao(final String a) {
        this.a = a;
    }
    
    public final boolean a(final h h, final String s, final String s2) {
        synchronized (this) {
            h.b(s, s2, this.a);
            return true;
        }
    }
    
    public static final class a
    {
        public static ao a(final String s) {
            return new ao(s, (byte)0);
        }
        
        static String a(final Context context) {
            final String string = Settings$Secure.getString(context.getContentResolver(), "android_id");
            while (true) {
                Label_0070: {
                    if (string == null || string.length() <= 0 || string.equals("9774d56d682e549c")) {
                        break Label_0070;
                    }
                    String string2;
                    try {
                        final UUID nameUUIDFromBytes = UUID.nameUUIDFromBytes(string.getBytes("utf8"));
                        if (nameUUIDFromBytes == null) {
                            break Label_0070;
                        }
                        string2 = nameUUIDFromBytes.toString();
                        if (string2 != null && string2.length() == 0) {
                            return null;
                        }
                    }
                    catch (UnsupportedEncodingException ex) {
                        string2 = null;
                        continue;
                    }
                    return string2;
                }
                String string2 = null;
                continue;
            }
        }
        
        static String b(String s) {
            while (true) {
                if (s == null || s.length() <= 0) {
                    break Label_0044;
                }
                try {
                    s = new String(new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(s.getBytes())).toString(16));
                    if (s != null && s.length() == 0) {
                        return null;
                    }
                }
                catch (NoSuchAlgorithmException ex) {
                    s = null;
                    continue;
                }
                break;
            }
            return s;
        }
    }
}

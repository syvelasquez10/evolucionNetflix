// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import android.content.Context;

public class k extends j
{
    private k(final Context context, final n n, final o o) {
        super(context, n, o);
    }
    
    public static k a(final String s, final Context context) {
        final e e = new e();
        j.a(s, context, e);
        return new k(context, e, new q(239));
    }
    
    @Override
    protected void b(final Context context) {
        final long n = 1L;
        super.b(context);
        a a = null;
        try {
            final a f;
            a = (f = this.f(context));
            final boolean b = f.isLimitAdTrackingEnabled();
            if (!b) {
                goto Label_0055;
            }
            final k k = this;
            final int n2 = 28;
            final long n3 = n;
            k.a(n2, n3);
            final a a2 = a;
            final String s = a2.getId();
            final String s3;
            final String s2 = s3 = s;
            if (s3 != null) {
                final k i = this;
                final int n4 = 30;
                final String s4 = s2;
                i.a(n4, s4);
            }
            return;
        }
        catch (IOException ex) {
            this.a(28, 1L);
            return;
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
        try {
            final a f = a;
            final boolean b = f.isLimitAdTrackingEnabled();
            if (!b) {
                goto Label_0055;
            }
            final k k = this;
            final int n2 = 28;
            final long n3 = n;
            k.a(n2, n3);
            final a a2 = a;
            final String s = a2.getId();
            final String s3;
            final String s2 = s3 = s;
            if (s3 != null) {
                final k i = this;
                final int n4 = 30;
                final String s4 = s2;
                i.a(n4, s4);
            }
        }
        catch (IOException ex3) {}
    }
    
    a f(final Context context) throws IOException, GooglePlayServicesNotAvailableException {
        int i = 0;
        AdvertisingIdClient.Info advertisingIdInfo;
        byte[] array;
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            final String s = advertisingIdInfo.getId();
            if (s == null || !s.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")) {
                return new a(s, advertisingIdInfo.isLimitAdTrackingEnabled());
            }
            array = new byte[16];
            int n = 0;
            while (i < s.length()) {
                int n2;
                if (i == 8 || i == 13 || i == 18 || (n2 = i) == 23) {
                    n2 = i + 1;
                }
                array[n] = (byte)((Character.digit(s.charAt(n2), 16) << 4) + Character.digit(s.charAt(n2 + 1), 16));
                ++n;
                i = n2 + 2;
            }
        }
        catch (GooglePlayServicesRepairableException ex) {
            throw new IOException(ex);
        }
        final String s = this.jP.a(array, true);
        return new a(s, advertisingIdInfo.isLimitAdTrackingEnabled());
    }
    
    class a
    {
        private String ka;
        private boolean kb;
        
        public a(final String ka, final boolean kb) {
            this.ka = ka;
            this.kb = kb;
        }
        
        public String getId() {
            return this.ka;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.kb;
        }
    }
}

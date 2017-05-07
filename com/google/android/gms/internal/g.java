// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import android.content.Context;

public class g extends f
{
    private g(final Context context, final j j, final k k) {
        super(context, j, k);
    }
    
    public static g a(final String s, final Context context) {
        final com.google.android.gms.internal.a a = new com.google.android.gms.internal.a();
        f.a(s, context, a);
        return new g(context, a, new m(239));
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
            final g g = this;
            final int n2 = 28;
            final long n3 = n;
            g.a(n2, n3);
            final a a2 = a;
            final String s = a2.getId();
            final String s3;
            final String s2 = s3 = s;
            if (s3 != null) {
                final g g2 = this;
                final int n4 = 30;
                final String s4 = s2;
                g2.a(n4, s4);
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
            final g g = this;
            final int n2 = 28;
            final long n3 = n;
            g.a(n2, n3);
            final a a2 = a;
            final String s = a2.getId();
            final String s3;
            final String s2 = s3 = s;
            if (s3 != null) {
                final g g2 = this;
                final int n4 = 30;
                final String s4 = s2;
                g2.a(n4, s4);
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
        final String s = this.dw.a(array, true);
        return new a(s, advertisingIdInfo.isLimitAdTrackingEnabled());
    }
    
    class a
    {
        private String dH;
        private boolean dI;
        
        public a(final String dh, final boolean di) {
            this.dH = dh;
            this.dI = di;
        }
        
        public String getId() {
            return this.dH;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.dI;
        }
    }
}

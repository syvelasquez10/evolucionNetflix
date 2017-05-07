// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.content.Context;

public class j extends i
{
    protected j(final Context context, final m m, final n n) {
        super(context, m, n);
    }
    
    public static j a(final String s, final Context context) {
        final e e = new e();
        i.a(s, context, e);
        return new j(context, e, new p(239));
    }
    
    @Override
    protected void b(final Context context) {
        super.b(context);
        try {
            try {
                final a h = this.h(context);
                long n;
                if (h.isLimitAdTrackingEnabled()) {
                    n = 1L;
                }
                else {
                    n = 0L;
                }
                this.a(28, n);
                final String id = h.getId();
                if (id != null) {
                    this.a(26, 5L);
                    this.a(24, id);
                }
            }
            catch (GooglePlayServicesNotAvailableException ex) {
                this.a(24, i.d(context));
            }
        }
        catch (IOException ex2) {}
        catch (i.a a) {}
    }
    
    a h(final Context context) throws IOException, GooglePlayServicesNotAvailableException {
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
        catch (SecurityException ex2) {
            throw new IOException(ex2);
        }
        final String s = this.ky.a(array, true);
        return new a(s, advertisingIdInfo.isLimitAdTrackingEnabled());
    }
    
    class a
    {
        private String kO;
        private boolean kP;
        
        public a(final String ko, final boolean kp) {
            this.kO = ko;
            this.kP = kp;
        }
        
        public String getId() {
            return this.kO;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.kP;
        }
    }
}

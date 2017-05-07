// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.InvalidAlgorithmParameterException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class o
{
    private final SecureRandom kW;
    private final m ky;
    
    public o(final m ky, final SecureRandom kw) {
        this.ky = ky;
        this.kW = kw;
    }
    
    static void c(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] ^= 0x44;
        }
    }
    
    public byte[] b(final String s) throws a {
        byte[] a;
        try {
            a = this.ky.a(s, false);
            if (a.length != 32) {
                throw new a();
            }
        }
        catch (IllegalArgumentException ex) {
            throw new a(ex);
        }
        final ByteBuffer wrap = ByteBuffer.wrap(a, 4, 16);
        final byte[] array = new byte[16];
        wrap.get(array);
        c(array);
        return array;
    }
    
    public byte[] c(final byte[] array, final String s) throws a {
        if (array.length != 16) {
            throw new a();
        }
        try {
            if (this.ky.a(s, false).length <= 16) {
                throw new a();
            }
            goto Label_0055;
        }
        catch (NoSuchAlgorithmException ex) {
            throw new a(ex);
        }
        catch (InvalidKeyException ex2) {
            throw new a(ex2);
        }
        catch (IllegalBlockSizeException ex3) {
            throw new a(ex3);
        }
        catch (NoSuchPaddingException ex4) {
            throw new a(ex4);
        }
        catch (BadPaddingException ex5) {
            throw new a(ex5);
        }
        catch (InvalidAlgorithmParameterException ex6) {
            throw new a(ex6);
        }
        catch (IllegalArgumentException ex7) {
            throw new a(ex7);
        }
    }
    
    public class a extends Exception
    {
        public a() {
        }
        
        public a(final Throwable t) {
            super(t);
        }
    }
}

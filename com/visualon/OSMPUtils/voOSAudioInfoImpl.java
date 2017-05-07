// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import java.io.UnsupportedEncodingException;
import android.os.Parcel;

public class voOSAudioInfoImpl implements voOSAudioInfo
{
    private voOSAudioFormat mFormat;
    private String mLanguage;
    
    public voOSAudioInfoImpl() {
    }
    
    public voOSAudioInfoImpl(final voOSAudioFormat mFormat, final String mLanguage) {
        this.mFormat = mFormat;
        this.mLanguage = mLanguage;
    }
    
    @Override
    public voOSAudioFormat Format() {
        return this.mFormat;
    }
    
    @Override
    public String Language() {
        return this.mLanguage;
    }
    
    @Override
    public boolean parse(final Parcel parcel) {
        (this.mFormat = new voOSAudioFormatImpl()).parse(parcel);
        final byte[] array = new byte[16];
        parcel.readByteArray(array);
        try {
            this.mLanguage = new String(array, 0, 16, "utf-8");
            return true;
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return true;
        }
    }
}

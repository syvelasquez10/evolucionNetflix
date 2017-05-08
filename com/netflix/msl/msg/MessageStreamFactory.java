// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.OutputStream;
import com.netflix.msl.crypto.ICryptoContext;
import java.util.Map;
import com.netflix.msl.keyx.KeyRequestData;
import java.util.Set;
import java.nio.charset.Charset;
import java.io.InputStream;
import com.netflix.msl.util.MslContext;

public class MessageStreamFactory
{
    public MessageInputStream createInputStream(final MslContext mslContext, final InputStream inputStream, final Charset charset, final Set<KeyRequestData> set, final Map<String, ICryptoContext> map) {
        return new MessageInputStream(mslContext, inputStream, charset, set, map);
    }
    
    public MessageOutputStream createOutputStream(final MslContext mslContext, final OutputStream outputStream, final Charset charset, final ErrorHeader errorHeader) {
        return new MessageOutputStream(mslContext, outputStream, charset, errorHeader);
    }
    
    public MessageOutputStream createOutputStream(final MslContext mslContext, final OutputStream outputStream, final Charset charset, final MessageHeader messageHeader, final ICryptoContext cryptoContext) {
        return new MessageOutputStream(mslContext, outputStream, charset, messageHeader, cryptoContext);
    }
}

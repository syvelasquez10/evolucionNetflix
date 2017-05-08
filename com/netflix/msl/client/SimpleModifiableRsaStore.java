// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.netflix.msl.util.Base64;
import com.netflix.msl.util.IoUtil;
import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.security.KeyFactory;

public class SimpleModifiableRsaStore implements ModifiableRsaStore
{
    private final KeyFactory factory;
    private final Map<String, PublicKey> keys;
    private final Map<String, PrivateKey> privateKeys;
    
    public SimpleModifiableRsaStore() {
        this.keys = new HashMap<String, PublicKey>();
        this.privateKeys = new HashMap<String, PrivateKey>();
        try {
            this.factory = KeyFactory.getInstance("RSA");
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Unable to get RSA key factory", ex);
        }
    }
    
    private void addPublicKey(final String s, final byte[] array) {
        final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(array);
        PublicKey generatePublic;
        try {
            generatePublic = this.factory.generatePublic(x509EncodedKeySpec);
            if (!(generatePublic instanceof RSAPublicKey)) {
                throw new IllegalArgumentException("Public key is not an instance of RSAPublicKey.");
            }
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalArgumentException("Public key can not be parsed", ex);
        }
        this.keys.put(s, generatePublic);
    }
    
    @Override
    public void addPrivateKey(final String s, final PrivateKey privateKey) {
        if (!(privateKey instanceof RSAPrivateKey)) {
            throw new IllegalArgumentException("Private key is not an instance of RSAPrivateKey.");
        }
        this.privateKeys.put(s, privateKey);
    }
    
    @Override
    public void addPublicKey(final String s, final InputStream inputStream) {
        this.addPublicKey(s, IoUtil.readBytes(inputStream, 2048));
    }
    
    @Override
    public void addPublicKey(final String s, final String s2) {
        this.addPublicKey(s, Base64.decode(s2));
    }
    
    @Override
    public Set<String> getIdentities() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<String>();
        set.addAll(this.keys.keySet());
        set.addAll(this.privateKeys.keySet());
        return (Set<String>)set;
    }
    
    @Override
    public PrivateKey getPrivateKey(final String s) {
        return this.privateKeys.get(s);
    }
    
    @Override
    public PublicKey getPublicKey(final String s) {
        return this.keys.get(s);
    }
}

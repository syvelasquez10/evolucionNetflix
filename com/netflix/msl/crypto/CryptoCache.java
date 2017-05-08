// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.crypto;

import java.security.Signature;
import javax.crypto.Mac;
import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import javax.crypto.KeyAgreement;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import java.util.Map;

public class CryptoCache
{
    private static ThreadLocal<Map<String, Cipher>> cipherCache;
    private static ThreadLocal<Map<String, MessageDigest>> digestCache;
    private static ThreadLocal<Map<String, KeyAgreement>> keyAgreementCache;
    private static ThreadLocal<Map<String, KeyFactory>> keyFactoryCache;
    private static ThreadLocal<Map<String, KeyPairGenerator>> keyPairGeneratorCache;
    private static ThreadLocal<Map<String, Mac>> macCache;
    private static ThreadLocal<Map<String, Signature>> signatureCache;
    
    static {
        CryptoCache.cipherCache = new CryptoCache$1();
        CryptoCache.signatureCache = new CryptoCache$2();
        CryptoCache.digestCache = new CryptoCache$3();
        CryptoCache.macCache = new CryptoCache$4();
        CryptoCache.keyFactoryCache = new CryptoCache$5();
        CryptoCache.keyAgreementCache = new CryptoCache$6();
        CryptoCache.keyPairGeneratorCache = new CryptoCache$7();
    }
    
    public static Cipher getCipher(final String s) {
        final Map<String, Cipher> map = CryptoCache.cipherCache.get();
        if (!map.containsKey(s)) {
            map.put(s, Cipher.getInstance(s));
        }
        return map.get(s);
    }
    
    public static KeyAgreement getKeyAgreement(final String s) {
        final Map<String, KeyAgreement> map = CryptoCache.keyAgreementCache.get();
        if (!map.containsKey(s)) {
            map.put(s, KeyAgreement.getInstance(s));
        }
        return map.get(s);
    }
    
    public static KeyFactory getKeyFactory(final String s) {
        final Map<String, KeyFactory> map = CryptoCache.keyFactoryCache.get();
        if (!map.containsKey(s)) {
            map.put(s, KeyFactory.getInstance(s));
        }
        return map.get(s);
    }
    
    public static KeyPairGenerator getKeyPairGenerator(final String s) {
        final Map<String, KeyPairGenerator> map = CryptoCache.keyPairGeneratorCache.get();
        if (!map.containsKey(s)) {
            map.put(s, KeyPairGenerator.getInstance(s));
        }
        return map.get(s);
    }
    
    public static Mac getMac(final String s) {
        final Map<String, Mac> map = CryptoCache.macCache.get();
        if (!map.containsKey(s)) {
            map.put(s, Mac.getInstance(s));
        }
        return map.get(s);
    }
    
    public static MessageDigest getMessageDigest(final String s) {
        final Map<String, MessageDigest> map = CryptoCache.digestCache.get();
        if (!map.containsKey(s)) {
            map.put(s, MessageDigest.getInstance(s));
        }
        return map.get(s);
    }
    
    public static Signature getSignature(final String s) {
        final Map<String, Signature> map = CryptoCache.signatureCache.get();
        if (!map.containsKey(s)) {
            map.put(s, Signature.getInstance(s));
        }
        return map.get(s);
    }
    
    public static void resetCipher(final String s) {
        CryptoCache.cipherCache.get().remove(s);
    }
}

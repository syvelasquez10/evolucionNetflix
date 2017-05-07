// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.repository.SecurityRepository;

public interface EsnProvider
{
    public static final int CRYPTO_FACTORY_TYPE_CDM_WIDEVINE = 2;
    public static final int CRYPTO_FACTORY_TYPE_LEGACY = 1;
    public static final String ESN_PREFIX = SecurityRepository.getEsnPrefix();
    
    void destroy();
    
    int getCryptoFactoryType();
    
    String getDeviceId();
    
    String getDeviceModel();
    
    String getESNPrefix();
    
    String getEsn();
    
    String getFesn();
    
    String getFesnModelId();
    
    String getManufacturer();
    
    String getModelId();
}

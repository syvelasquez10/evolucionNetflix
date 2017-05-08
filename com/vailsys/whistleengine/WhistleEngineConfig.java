// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

public class WhistleEngineConfig
{
    public static final int SAMPLERATE_16K = 16000;
    public static final int SAMPLERATE_24K = 24000;
    public static final int SAMPLERATE_32K = 32000;
    public static final int SAMPLERATE_48K = 48000;
    public static final int SAMPLERATE_8K = 8000;
    private String account;
    private String applicationIdentifier;
    private String certificateAuthorityFile;
    private String certificateChain;
    private WhistleEngineThresholds connectivityThresholds;
    private boolean echoCanceler;
    private String password;
    private String privateKey;
    private String proxy;
    private boolean registrationEnabled;
    private String rootCertificate;
    private int sampleRate;
    private int tlsPort;
    private WhistleEngineConfig$TransportMode transport;
    
    public WhistleEngineConfig(final String proxy, final String account) {
        this.proxy = proxy;
        this.account = account;
        this.transport = WhistleEngineConfig$TransportMode.UDP;
        this.registrationEnabled = true;
        this.echoCanceler = true;
        this.tlsPort = 5061;
        this.sampleRate = 8000;
    }
    
    public String getAccount() {
        return this.account;
    }
    
    public String getApplicationIdentifier() {
        return this.applicationIdentifier;
    }
    
    public String getCertificateAuthorityFile() {
        return this.certificateAuthorityFile;
    }
    
    public String getCertificateChain() {
        return this.certificateChain;
    }
    
    public WhistleEngineThresholds getConnectivityThresholds() {
        return this.connectivityThresholds;
    }
    
    public boolean getEchoCanceler() {
        return this.echoCanceler;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getPrivateKey() {
        return this.privateKey;
    }
    
    public String getProxy() {
        return this.proxy;
    }
    
    public boolean getRegistrationEnabled() {
        return this.registrationEnabled;
    }
    
    public String getRootCertificate() {
        return this.rootCertificate;
    }
    
    public int getSampleRate() {
        return this.sampleRate;
    }
    
    public int getTLSPort() {
        return this.tlsPort;
    }
    
    public WhistleEngineConfig$TransportMode getTransportMode() {
        return this.transport;
    }
    
    public void setApplicationIdentifier(final String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }
    
    public void setCertificateAuthorityFile(final String certificateAuthorityFile) {
        this.certificateAuthorityFile = certificateAuthorityFile;
    }
    
    public void setCertificateChain(final String certificateChain) {
        this.certificateChain = certificateChain;
    }
    
    public void setConnectivityThresholds(final WhistleEngineThresholds connectivityThresholds) {
        this.connectivityThresholds = connectivityThresholds;
    }
    
    public void setEchoCanceler(final boolean echoCanceler) {
        this.echoCanceler = echoCanceler;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }
    
    public void setRegistrationEnabled(final boolean registrationEnabled) {
        this.registrationEnabled = registrationEnabled;
    }
    
    public void setRootCertificate(final String rootCertificate) {
        this.rootCertificate = rootCertificate;
    }
    
    public void setSamplerate(final int sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    public void setTLSPort(final int tlsPort) {
        this.tlsPort = tlsPort;
    }
    
    public void setTransportMode(final WhistleEngineConfig$TransportMode transport) {
        this.transport = transport;
    }
}

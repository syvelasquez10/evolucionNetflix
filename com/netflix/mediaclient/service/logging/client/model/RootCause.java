// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

public enum RootCause
{
    clientFailure, 
    clientRequestBad, 
    dnsLookupFailure, 
    dnsTimeout, 
    handledException, 
    http4xx, 
    http5xx, 
    networkFailure, 
    serverFailure, 
    serverResponseBad, 
    sslExpiredCert, 
    sslHandshakeFailure, 
    sslNoCipher, 
    sslUntrustedCert, 
    tcpConnectionRefusal, 
    tcpConnectionTimeout, 
    tcpNoRouteToHost, 
    unhandledException, 
    unknownFailure;
}

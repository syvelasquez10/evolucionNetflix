// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import java.util.Map;
import javax.crypto.spec.DHParameterSpec;

public interface DiffieHellmanParameters
{
    DHParameterSpec getParameterSpec(final String p0);
    
    Map<String, DHParameterSpec> getParameterSpecs();
}

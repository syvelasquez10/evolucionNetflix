// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.lang.reflect.Constructor;

public final class l
{
    public static Constructor a(final String s, final String[] array) {
        final Constructor<?>[] declaredConstructors = Class.forName(s).getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; ++i) {
            final Class<?>[] parameterTypes = declaredConstructors[i].getParameterTypes();
            int n = 0;
            Label_0034: {
                if (parameterTypes.length != array.length) {
                    n = 0;
                }
                else {
                    for (int j = 0; j < parameterTypes.length; ++j) {
                        if (!parameterTypes[j].getName().equals(array[j])) {
                            n = 0;
                            break Label_0034;
                        }
                    }
                    n = 1;
                }
            }
            if (n != 0) {
                return declaredConstructors[i];
            }
        }
        return null;
    }
}

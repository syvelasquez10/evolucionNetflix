// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ReactProp {
    String customType() default "__default_type__";
    
    boolean defaultBoolean() default false;
    
    double defaultDouble() default 0.0;
    
    float defaultFloat() default 0.0f;
    
    int defaultInt() default 0;
    
    String name();
}

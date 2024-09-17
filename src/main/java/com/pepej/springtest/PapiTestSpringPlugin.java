package com.pepej.springtest;

import com.pepej.papi.ap.Plugin;
import com.pepej.papi.spring.SpringPapiJavaPlugin;


@Plugin(name = "gamma",
        version = "1.1.1",
        authors = "pepej",
        hardDepends = "papi")
public class PapiTestSpringPlugin extends SpringPapiJavaPlugin {
    @Override
    protected Class<?> getApplicationClass() {
        return PapiTestSpringApplication.class;
    }
}

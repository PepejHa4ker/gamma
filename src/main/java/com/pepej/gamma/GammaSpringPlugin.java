package com.pepej.gamma;

import com.pepej.papi.ap.Plugin;
import com.pepej.papi.spring.SpringPapiJavaPlugin;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;


@Plugin(name = "gamma",
        version = "1.1.1",
        authors = "pepej",
        hardDepends = "papi")
public class GammaSpringPlugin extends SpringPapiJavaPlugin {
    @Override
    protected Class<?> getApplicationClass() {
        return GammaSpringApplication.class;
    }

    @Override
    protected String getPropertySourceFile() {
        return "config.yml";
    }


    @Override
    public SpringApplicationBuilder applicationBuilder() {
        return new SpringApplicationBuilder()
                .resourceLoader(resourceLoader())
                .sources(getApplicationClass())
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(true)
                .initializers(this);
    }

    @Override
    public void onPluginEnable() {
        super.onPluginEnable();
    }
}

package com.example.libros.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * Configura el CookieLocaleResolver para manejar el Locale a través de cookies.
     * @return Una instancia de CookieLocaleResolver.
     */
    @Bean
    public CookieLocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    /**
     * Configura el LocaleChangeInterceptor para cambiar el Locale basándose en el parámetro 'lang'.
     * @return Una instancia de LocaleChangeInterceptor.
     */
    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor localInterceptor = new LocaleChangeInterceptor();
        localInterceptor.setParamName("lang"); // Nombre del parámetro que se utilizará para cambiar el Locale
        return localInterceptor;
    }

    /**
     * Agrega el LocaleChangeInterceptor a la cadena de manipuladores de solicitudes de Spring MVC.
     * @param registry El registro de interceptores.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }
}

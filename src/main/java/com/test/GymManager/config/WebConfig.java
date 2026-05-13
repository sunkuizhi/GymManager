package com.test.GymManager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.format.Formatter;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
    }

    public static class LocalDateFormatter implements Formatter<LocalDate> {

        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public LocalDate parse(String text, Locale locale) {
            return LocalDate.parse(text, formatter);
        }

        @Override
        public String print(LocalDate object, Locale locale) {
            return object.format(formatter);
        }
    }
}
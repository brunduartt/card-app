package org.bancodobrasil.infrastructure.i18n;

public final class I18nFactoryInfra {
    public static I18nInfra get() {
        return new I18NPtBRInfra();
    }
}

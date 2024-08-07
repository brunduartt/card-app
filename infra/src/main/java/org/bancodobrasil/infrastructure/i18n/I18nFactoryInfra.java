package org.bancodobrasil.infrastructure.i18n;

public final class I18nFactoryInfra {
    public static I18nInfra getDefault() {
        return new I18NPtBRInfra();
    }

    public static I18nInfra get(String locale) {
        return switch (locale) {
            case "en-US" -> new I18NEnUSInfra();
            case "pt-BR" -> new I18NPtBRInfra();
            default -> getDefault();
        };
    }
}

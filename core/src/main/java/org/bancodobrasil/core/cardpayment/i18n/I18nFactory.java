package org.bancodobrasil.core.cardpayment.i18n;

public final class I18nFactory {
    public static I18n getDefault() {
        return new I18nPtBR();
    }
    public static I18n get(String locale) {
        return switch (locale) {
            case "en-US" -> new I18nEnUS();
            case "pt-BR" -> new I18nPtBR();
            default -> getDefault();
        };
    }
}

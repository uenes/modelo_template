package br.template.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceUtils {

    protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = defaultObject.getClass().getClassLoader();
        }
        return loader;
    }

    public static String getMessageResourceString(
            String bundleName,
            String key,
            Object params[],
            Locale locale) {

        String text = null;

        ResourceBundle bundle =
                ResourceBundle.getBundle(bundleName, locale,
                getCurrentClassLoader(params));

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? mensagem \"" + key + "\" não encontrada ??";
        }

        if (params != null) {
            MessageFormat mf = new MessageFormat(text, locale);
            text = mf.format(params, new StringBuffer(), null).toString();
        }

        return text;
    }

    public static ResourceBundle getResourceBundle(String bundleName) {
        return ResourceBundle.getBundle(bundleName, new Locale("pt-BR"),
                getCurrentClassLoader(new Object[]{}));
    }
}

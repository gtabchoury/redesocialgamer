package rsg.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private static Properties properties;

    public static String get(String key) {
        String result = null;
        try{
            result = System.getenv(key);
        }catch(Exception ignored){}
        if(result == null) {
            if (properties == null ) {
                properties = new Properties();
                String nomeArquivo = "config.properties";
                try {
                    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(nomeArquivo));
                } catch (IOException e) {
                    Print.out("ERRO ao carregar o arquivo: " + nomeArquivo);
                }
            }
            result = properties.getProperty(key);
            if (result == null) {
                Print.out("ERRO ao carregar a chave: " + key);
            }
        }
        return result;
    }
}

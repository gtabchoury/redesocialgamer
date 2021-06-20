package rsg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe com métodos de print customizados.
 */
public class Print {

    /**
     * Formato do print: [horário no formato yyyy-MM-dd HH:mm:ss]: [classe].[método]()<br>
     * Exemplo: 2016-08-10 17:22:48: LojaAction.produto()
     */
    public static void out() {
        print(null);
    }

    /**
     * Formato do print: [horário no formato yyyy-MM-dd HH:mm:ss]: [classe].[método]()[ - mensagem opcional]<br>
     * Exemplo sem mensagem: 2016-08-10 17:22:48: LojaAction.produto()<br>
     * Exemplo com mensagem: 2016-08-10 17:22:48: LojaAction.produto() - Configurações do produto ok.
     *
     * @param o ({@link Object}) - Mensagem com informações adicionais a serem printadas (cast para {@link String}).
     */
    public static void out(Object o) {
        print(o);
    }

    /**
     * Formato do print: [horário no formato yyyy-MM-dd HH:mm:ss]: [classe].[método]()[ - mensagem opcional]<br>
     * Exemplo sem mensagem: 2016-08-10 17:22:48: LojaAction.produto()<br>
     * Exemplo com mensagem: 2016-08-10 17:22:48: LojaAction.produto() - Configurações do produto ok.
     *
     * @param msg ({@link String}) - Mensagem com informações adicionais a serem printadas.
     */
    private static void print(Object msg) {
        Integer indexStrackTrace = 2;
        String format = "yyyy-MM-dd HH:mm:ss";
        System.out.println((new SimpleDateFormat(format)).format(new Date()) + ": " + ClassMethodUtils.getClassAndMethodNameLevel(indexStrackTrace) + "()" + (msg != null && !msg.toString().isEmpty() ? " - " + msg : ""));
    }

    /* GETTERS E SETTERS */
}

package rsg.utils;

public class ClassMethodUtils {

    /* CONSTANTES */

    /* VARIÁVEIS */

    /* FUNÇÕES */

    /**
     * Retorna o nome da classe onde este está sendo chamado.
     *
     * @return {@link String} - Nome da clase.
     */
    public static String getClassName() {
        return getClassNameLevel(1);
    }

    /**
     * Retorna o nome da classe onde este está sendo chamado de acordo com o nível da camada (parâmetro index).<br>
     * O index funciona da seguinte forma:
     * 0 - Re
     *
     * @return {@link String} - Nome da clase.
     */
    public static String getClassNameLevel(Integer index) {
        int i = index + 2;
        while (i < Thread.currentThread().getStackTrace().length &&
                !Thread.currentThread().getStackTrace()[i].getClassName().contains("srg")) {
            i++;
        }
        String[] packages = Thread.currentThread().getStackTrace()[i].getClassName().split("[.]");
        return packages[packages.length - 1];
    }

    /**
     * Retorna o nome do método onde este está sendo chamado.
     *
     * @return {@link String} - Nome do método.
     */
    public static String getMethodName() {
        return getMethodNameLevel(1);
    }

    public static String getMethodNameLevel(Integer index) {
        int i = index + 2;
        while (i < Thread.currentThread().getStackTrace().length &&
                !Thread.currentThread().getStackTrace()[i].getClassName().contains("rsg")) {
            i++;
        }
        return Thread.currentThread().getStackTrace()[i].getMethodName();
    }

    /**
     * Retorna o nome da classe e do método onde este está sendo chamado no formato: [classe].[método]
     *
     * @return {@link String} - Nome da classe e do método.
     */
    public static String getClassAndMethodName() {
        return getClassAndMethodNameLevel(1);
    }

    public static String getClassAndMethodNameLevel(Integer index) {
        return getClassNameLevel(index + 1) + "." + getMethodNameLevel(index + 1);
    }

    /* GETTERS E SETTERS */
}


package modelo;

import java.io.*;

/**
 * Clase que hereda de ObjectInputStream y evita que se lea una cabecera antes del objeto
 */
public class MyObjectInputStream extends ObjectInputStream {
    /** 
     * Constructor que recibe FileInputStream 
     */
    public MyObjectInputStream(FileInputStream fis) throws IOException
    {
        super(fis);
    }

    /** 
     * Redefinición del método de leer la cabecera para que no haga nada. 
     */
    protected void readStreamHeader() throws IOException
    {
    }

}
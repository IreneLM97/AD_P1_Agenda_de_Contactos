package modelo;

import java.io.*;

/**
 * Clase que hereda de ObjectOutputStream y evita que se escriba una cabecera antes del objeto
 */
public class MyObjectOutputStream extends ObjectOutputStream {
    /** 
     * Constructor que recibe FileOutputStream 
     */
    public MyObjectOutputStream(FileOutputStream fos) throws IOException
    {
        super(fos);
    }

    /** 
     * Redefinición del método de escribir la cabecera para que no haga nada. 
     */
    protected void writeStreamHeader() throws IOException
    {
    }

}
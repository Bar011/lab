/*
 * Copyright (c) 2020 Diego Urrutia-Astorga. http://durrutia.cl.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package cl.ucn.disc.pdis.lab.services;

import cl.ucn.disc.pdis.lab.zeroice.model.Engine;
import com.zeroc.Ice.Current;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The implementation of {@link cl.ucn.disc.pdis.lab.zeroice.model.Engine}.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class EngineImpl implements Engine {

    /**
     * @see Engine#getDate(Current)
     */
    @Override
    public String getDate(Current current) {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
    /**
     *
     * @param rut
     * @param current The Current object for the invocation.
     * @return digito
     * @author http://cristobaldiaz.cl/blog/validacion-del-rut/ (formula)
     */
    @Override
    public String getDigitoVerificador(String rut, Current current) {
        /**
         * inicializo variables
         */
        int mul = 0, sum =1 ;
        char dv = 0;
        String dig = null;
        try {/**
         * Try catch para controlar excepciones
         */
            /**
             * funcion que devuelve la cadena en mayúsculas
             */
            rut = rut.toUpperCase(); /**
             * funcion para ignorar "."
             */
            rut = rut.replace(".", "");
            /**
             * funcion para ignorar el "-"
             */
            rut = rut.replace("-", "");

            /**
             * Variable auxiliar para redimensionar bien el rut
             */
            int aux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            /**
             * variable que captura el ultimo digito de la cadena de strings que es el rut.
             */
            dv = rut.charAt((rut.length() - 1));
            /**
             * Recorro el arreglo de atras hacia adelante multiplicando cada numero por el multiplicador,
             * agregandolo a la suma y sacando los restos
             */
            for (;aux != 0; aux/= 10) {
                sum = (sum + aux % 10 * (9 - mul++ % 6)) % 11;
            }
            /**
             * se prueba la igualdad entre la variable dv dada y la calculada
             * si coinciden, retorna el digito, si no, envía un mensaje de advertencia.
             */
            if (dv == (char) (sum != 0 ? sum + 47 : 75)) {
                dig = String.valueOf(dv);
            }else {
                dig= "Error";
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return dig;
    }
    }

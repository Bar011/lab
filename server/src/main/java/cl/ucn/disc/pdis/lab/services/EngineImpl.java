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

    @Override
    public String getDigitoVerificador(String rut, Current current) {
        int m = 0, r =1 ;
        char dv = 0;
        String dig = null;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");

            int aux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            // retorna un entero dado un string
            dv = rut.charAt((rut.length() - 1));
            //retorno del char dado el rut
            for (;aux != 0; aux/= 10) {
                r = (r + aux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (r != 0 ? r + 47 : 75)) {
                dig = String.valueOf(dv);
            }else {
                dig= "Error";
            }
            //codificar aqui el metodo
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return dig;
    }
    }

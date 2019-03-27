package com.jmisnaza.callcenter.logic.services;

import com.jmisnaza.callcenter.enums.RolEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Definición de los servicios que debe ofrecer la interfaz
 * de Empleados
 */
public interface EmployedServices {

    /**
     * Constantes que determinan la cantidad de personal bajo
     * el rol indicado. Se usa <code>AtomicInteger</code> para que estas
     * constantes puedan ser accedidas por varios hilos
     */
    AtomicInteger OPERATORS_QUANTITY = new AtomicInteger(7);
    AtomicInteger SUPERVISORS_QUANTITY = new AtomicInteger(2);
    AtomicInteger DIRECTORS_QUANTITY = new AtomicInteger(1);

    /**
     * Determina que rol debe atender la llamada según la disponibilidad
     * de actores ocupados
     * @return
     *        Rol que debe atender la llamada.
     */
    RolEnum assignEmployed();

    /**
     * Tras finalizar la llamada el rol debe ser liberado para que pueda
     * atender otra llamada
     * @param rol
     *        Rol que debe liberarse
     */
    void enableEmployed(RolEnum rol);
}

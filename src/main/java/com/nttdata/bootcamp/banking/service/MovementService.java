/**
 * Resumen.
 * Objeto                   : MovementService.java
 * Descripción              : Clase para los métodos de la interface de servicio del movimiento.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.Movement;
import reactor.core.publisher.Mono;

/**
 * Clase para los métodos de la interface de servicio del movimiento.
 */
public interface MovementService extends GenericService<Movement, String> {

    Mono<Movement> findByCode(String code);

}

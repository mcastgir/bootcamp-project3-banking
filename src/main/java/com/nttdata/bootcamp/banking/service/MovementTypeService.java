/**
 * Resumen.
 * Objeto                   : MovementTypeService.java
 * Descripción              : Clase para los métodos de la interface de servicio de tipo de movimiento.
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

import com.nttdata.bootcamp.banking.model.document.Movement;
import com.nttdata.bootcamp.banking.model.document.MovementType;
import reactor.core.publisher.Mono;

/**
 * Clase para los métodos de la interface de servicio de tipo de movimiento.
 */
public interface MovementTypeService extends GenericService<MovementType, String> {

    Mono<MovementType> findByCode(String code);

}

/**
 * Resumen.
 * Objeto                   : GenericService.java
 * Descripción              : Clase para los métodos de la interface de servicio del entorno genérico.
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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * Clase para los métodos de la interface de servicio del entorno genérico.
 */
public interface GenericService <T, Id extends Serializable> {

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el T, tipo Mono
     */
    Mono<T> insert(T t);

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el T, tipo Mono
     */
    Mono<T> update(T t);

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    Mono<Void> delete(Id id);

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el T, tipo String
     */
    Mono<T> find(Id id);

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el T, tipo String
     */
    Flux<T> findAll();

}

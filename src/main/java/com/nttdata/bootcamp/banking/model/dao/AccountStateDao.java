/**
 * Resumen.
 * Objeto                   : AccountStateDao.java
 * Descripción              : Clase de tipo document para obtener o establecer los datos de cada atributo.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un campo nuevo.
 */

package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.AccountState;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Clase de tipo document para obtener o establecer los datos de cada atributo.
 */
public interface AccountStateDao extends ReactiveMongoRepository<AccountState, String> {

    /**
     * Método que obtiene los datos del document AccountState
     * @return Mono retorna el AccountState, tipo Mono
     */
    Mono<AccountState> findByCode(String code);

}

/**
 * Resumen.
 * Objeto                   : AccountStateServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio del estado de cuenta.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.AccountDao;
import com.nttdata.bootcamp.banking.model.dao.AccountStateDao;
import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.AccountState;
import com.nttdata.bootcamp.banking.service.AccountStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase para los métodos de la implementación de servicio del estado de cuenta.
 */
@Service
public class AccountStateServiceImpl implements AccountStateService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(AccountStateServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private AccountStateDao accountStateDao;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el AccountState, tipo Mono
     */
    @Override
    public Mono<AccountState> insert(AccountState accountState) {
        return accountStateDao.save(accountState)
                .doFirst(() -> log.info("Begin Insert AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert AccountState"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el AccountState, tipo Mono
     */
    @Override
    public Mono<AccountState> update(AccountState accountState) {
        return accountStateDao.findById(accountState.getId())
                .doFirst(() -> log.info("Begin Update AccountState"))
                .map(a -> accountState)
                .flatMap(this.accountStateDao::save)
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Update AccountState"));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return accountStateDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete AccountState"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el AccountState, tipo String
     */
    @Override
    public Mono<AccountState> find(String id) {
        return accountStateDao.findById(id)
                .doFirst(() -> log.info("Begin Find AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Find AccountState"));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el AccountState, tipo String
     */
    @Override
    public Mono<AccountState> findByCode(String code) {
        return accountStateDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode AccountState"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el AccountState, tipo String
     */
    @Override
    public Flux<AccountState> findAll() {
        return accountStateDao.findAll()
                .doFirst(() -> log.info("Begin FindAll AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll AccountState"));
    }

}

/**
 * Resumen.
 * Objeto                   : HolderServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio del titular.
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
import com.nttdata.bootcamp.banking.model.dao.HolderDao;
import com.nttdata.bootcamp.banking.model.document.Holder;
import com.nttdata.bootcamp.banking.service.AccountService;
import com.nttdata.bootcamp.banking.service.HolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * Clase para los métodos de la implementación de servicio del titular.
 */
@Service
public class HolderServiceImpl implements HolderService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(HolderServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private HolderDao holderDao;
    @Autowired
    private AccountService accountService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Holder, tipo Mono
     */
    @Override
    public Mono<Holder> insert(Holder holder) {
        holder.setDateRegister(new Date());
        return holderDao.save(holder)
                .doFirst(() -> log.info("Begin Insert Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Holder"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Holder, tipo Mono
     */
    @Override
    public Mono<Holder> update(Holder holder) {
        return holderDao.findById(holder.getId())
                .doFirst(() -> log.info("Begin Update Holder"))
                .map(h -> holder)
                .flatMap(this.holderDao::save)
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Holder"));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return holderDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Holder"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Holder, tipo String
     */
    @Override
    public Mono<Holder> find(String id) {
        return holderDao.findById(id)
                .doFirst(() -> log.info("Begin Find Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Holder"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el Holder, tipo String
     */
    @Override
    public Flux<Holder> findAll() {
        return holderDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Holder"));
    }

}

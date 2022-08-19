/**
 * Resumen.
 * Objeto                   : SignerServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio del firmante.
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

import com.nttdata.bootcamp.banking.model.dao.SignerDao;
import com.nttdata.bootcamp.banking.model.document.Signer;
import com.nttdata.bootcamp.banking.service.SignerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase para los métodos de la implementación de servicio del firmante.
 */
@Service
public class SignerServiceImpl implements SignerService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(SignerServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private SignerDao signerDao;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Signer, tipo Mono
     */
    @Override
    public Mono<Signer> insert(Signer signer) {
        return signerDao.save(signer)
                .doFirst(() -> log.info("Begin Insert Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Signer"));
    }

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Signer, tipo Mono
     */
    @Override
    public Mono<Signer> update(Signer signer) {
        return signerDao.findById(signer.getId())
                .doFirst(() -> log.info("Begin Update Signer"))
                .map(s -> signer)
                .flatMap(this.signerDao::save)
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Signer"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return signerDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Signer"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Signer, tipo String
     */
    @Override
    public Mono<Signer> find(String id) {
        return signerDao.findById(id)
                .doFirst(() -> log.info("Begin Find Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Signer"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el Signer, tipo String
     */
    @Override
    public Flux<Signer> findAll() {
        return signerDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Signer"));
    }

}

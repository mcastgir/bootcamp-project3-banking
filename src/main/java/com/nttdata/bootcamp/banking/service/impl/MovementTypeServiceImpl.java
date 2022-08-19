/**
 * Resumen.
 * Objeto                   : MovementTypeServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio de tipo de movimiento.
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

import com.nttdata.bootcamp.banking.model.dao.MovementTypeDao;
import com.nttdata.bootcamp.banking.model.document.MovementType;
import com.nttdata.bootcamp.banking.service.MovementTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase para los métodos de la implementación de servicio de tipo de movimiento.
 */
@Service
public class MovementTypeServiceImpl implements MovementTypeService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(MovementTypeServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private MovementTypeDao movementTypeDao;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el MovementType, tipo Mono
     */
    @Override
    public Mono<MovementType> insert(MovementType movementType) {
        return movementTypeDao.save(movementType)
                .doFirst(() -> log.info("Begin Insert MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert MovementType"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el MovementType, tipo Mono
     */
    @Override
    public Mono<MovementType> update(MovementType movementType) {
        return movementTypeDao.findById(movementType.getId())
                .doFirst(() -> log.info("Begin Update MovementType"))
                .map(m -> movementType)
                .flatMap(this.movementTypeDao::save)
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Update MovementType"));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return movementTypeDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete MovementType"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el MovementType, tipo String
     */
    @Override
    public Mono<MovementType> find(String id) {
        return movementTypeDao.findById(id)
                .doFirst(() -> log.info("Begin Find MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Find MovementType"));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el MovementType, tipo String
     */
    @Override
    public Mono<MovementType> findByCode(String code) {
        return movementTypeDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode MovementType"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el MovementType, tipo String
     */
    @Override
    public Flux<MovementType> findAll() {
        return movementTypeDao.findAll()
                .doFirst(() -> log.info("Begin FindAll MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll MovementType"));
    }

}

/**
 * Resumen.
 * Objeto                   : MovementServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio del movimiento.
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
import com.nttdata.bootcamp.banking.model.dao.MovementDao;
import com.nttdata.bootcamp.banking.model.document.Movement;
import com.nttdata.bootcamp.banking.model.document.MovementType;
import com.nttdata.bootcamp.banking.service.AccountService;
import com.nttdata.bootcamp.banking.service.MovementService;
import com.nttdata.bootcamp.banking.service.MovementTypeService;
import com.nttdata.bootcamp.banking.service.ProductService;
import com.nttdata.bootcamp.banking.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

import static com.nttdata.bootcamp.banking.util.Constant.OPERATION_TYPE_ENRTY;
import static com.nttdata.bootcamp.banking.util.Constant.OPERATION_TYPE_EXIT;

/**
 * Clase para los métodos de la implementación de servicio del movimiento.
 */
@Transactional
@Service
public class MovementServiceImpl implements MovementService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(MovementServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private MovementDao movementDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MovementTypeService movementTypeService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Movement, tipo Mono
     */
    @Override
    public Mono<Movement> insert(Movement movement) {
        movement.setCode(UUID.randomUUID().toString());
        movement.setDateRegister(new Date());
        return movementTypeService.findByCode(movement.getCodeMovementType()).flatMap(movementType -> {
            if(movementType.getOperationType().equals(OPERATION_TYPE_ENRTY)) {
                return accountService.findByAccountNumber(movement.getAccountNumber()).flatMap(account -> {
                    movement.setPreviousAmount(account.getAvailableAmount());
                    movement.setFinalAmount(MathUtil.sum(movement.getPreviousAmount(),
                            movement.getMovementAmount()));
                    account.setAvailableAmount(movement.getFinalAmount());
                    return accountService.update(account)
                            .flatMap(result -> {
                                return movementDao.save(movement);
                            });
                });
            } else if(movementType.getOperationType().equals(OPERATION_TYPE_EXIT)) {
                return accountService.findByAccountNumber(movement.getAccountNumber()).flatMap(account -> {
                    if(account.getAvailableAmount() >= movement.getMovementAmount()) {
                        movement.setPreviousAmount(account.getAvailableAmount());
                        movement.setFinalAmount(MathUtil.rest(movement.getPreviousAmount(),
                                movement.getMovementAmount()));
                        account.setAvailableAmount(movement.getFinalAmount());
                        return accountService.update(account)
                       .flatMap(result -> {
                           return movementDao.save(movement);
                       });
                    } else {
                        return Mono.error(new RuntimeException("Saldo insuficiente"));
                    }
                });
            } else {
                return Mono.error(new RuntimeException("El código tipo de movimiento no existe"));
            }
        }).doFirst(() -> log.info("Begin Insert Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Movement"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Movement, tipo Mono
     */
    @Override
    public Mono<Movement> update(Movement movement) {
        return movementDao.findById(movement.getId())
                .doFirst(() -> log.info("Begin Update Movement"))
                .map(m -> movement)
                .flatMap(this.movementDao::save)
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Movement"));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return movementDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Movement"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Movement, tipo String
     */
    @Override
    public Mono<Movement> find(String id) {
        return movementDao.findById(id)
                .doFirst(() -> log.info("Begin Find Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Movement"));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el Movement, tipo String
     */
    @Override
    public Mono<Movement> findByCode(String code) {
        return movementDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode Movement"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el Movement, tipo String
     */
    @Override
    public Flux<Movement> findAll() {
        return movementDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Movement"));
    }

}

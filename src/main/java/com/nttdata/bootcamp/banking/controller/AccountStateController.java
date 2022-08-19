/**
 * Resumen.
 * Objeto                   : AccountController.java
 * Descripción              : Clase de controladora para invocar a métodos CRUD con rest api.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.AccountState;
import com.nttdata.bootcamp.banking.service.AccountStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase de controladora para invocar a métodos CRUD con rest api.
 */
@RestController
@RequestMapping("/api/accountstates")
public class AccountStateController {

    /** Declaración de la clase service */
    @Autowired
    private AccountStateService accountStateService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el AccountState, tipo Mono
     */
    @PostMapping
    public Mono<ResponseEntity<AccountState>> create(@RequestBody AccountState accountState){
        return this.accountStateService.insert(accountState)
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el AccountState, tipo Mono
     */
    @PutMapping
    public Mono<ResponseEntity<AccountState>> update(@RequestBody AccountState accountState){
        return this.accountStateService.update(accountState)
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return this.accountStateService.delete(id)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el AccountState, tipo String
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<AccountState>> find(@PathVariable String id) {
        return this.accountStateService.find(id)
                .map(accountState -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(accountState));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el AccountState, tipo String
     */
    @GetMapping("/findByCode/{code}")
    public Mono<ResponseEntity<AccountState>> findByCode(@PathVariable String code) {
        return this.accountStateService.findByCode(code)
                .map(accountState -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(accountState));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Flux retorna el AccountState, tipo Flux
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<AccountState>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.accountStateService.findAll()));
    }

}

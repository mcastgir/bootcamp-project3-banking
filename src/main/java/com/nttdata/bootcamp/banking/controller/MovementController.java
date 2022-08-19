/**
 * Resumen.
 * Objeto                   : MovementController.java
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

import com.nttdata.bootcamp.banking.model.document.Movement;
import com.nttdata.bootcamp.banking.service.MovementService;
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
@RequestMapping("/api/movements")
public class MovementController {

    /** Declaración de la clase service */
    @Autowired
    private MovementService movementService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Movement, tipo Mono
     */
    @PostMapping
    public Mono<ResponseEntity<Movement>> create(@RequestBody Movement movement){
        return this.movementService.insert(movement)
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Movement, tipo Mono
     */
    @PutMapping
    public Mono<ResponseEntity<Movement>> update(@RequestBody Movement movement){
        return this.movementService.update(movement)
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return this.movementService.delete(id)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Movement, tipo String
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Movement>> find(@PathVariable String id) {
        return this.movementService.find(id)
                .map(movement -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(movement));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el Movement, tipo String
     */
    @GetMapping("/findByCode/{code}")
    public Mono<ResponseEntity<Movement>> findByCode(@PathVariable String code) {
        return this.movementService.findByCode(code)
                .map(movement -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(movement));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Flux retorna el Movement, tipo Flux
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<Movement>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.movementService.findAll()));
    }

}

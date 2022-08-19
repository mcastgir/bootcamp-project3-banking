/**
 * Resumen.
 * Objeto                   : SignerController.java
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

import com.nttdata.bootcamp.banking.model.document.Signer;
import com.nttdata.bootcamp.banking.service.SignerService;
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
@RequestMapping("/api/signers")
public class SignerController {

    /** Declaración de la clase service */
    @Autowired
    private SignerService signerService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Signer, tipo Mono
     */
    @PostMapping
    public Mono<ResponseEntity<Signer>> create(@RequestBody Signer signer){
        return this.signerService.insert(signer)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Signer, tipo Mono
     */
    @PutMapping
    public Mono<ResponseEntity<Signer>> update(@RequestBody Signer signer){
        return this.signerService.update(signer)
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return this.signerService.delete(id)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Signer, tipo String
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Signer>> find(@PathVariable String id) {
        return this.signerService.find(id)
                .map(signer -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(signer));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Flux retorna el Signer, tipo Flux
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<Signer>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.signerService.findAll()));
    }

}

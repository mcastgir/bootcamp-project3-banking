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

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.service.AccountService;
import com.nttdata.bootcamp.banking.service.ClientService;
import com.nttdata.bootcamp.banking.util.ApiResponse;
import com.nttdata.bootcamp.banking.util.Constant;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletionService;

import static com.nttdata.bootcamp.banking.util.Constant.NUMERO_UNO;

/**
 * Clase de controladora para invocar a métodos CRUD con rest api.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    /** Declaración de la clase service */
    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Account, tipo Mono
     */
    @PostMapping
    @CircuitBreaker(name = "createAccountCB", fallbackMethod = "fallbackCreateAccount")
    public Mono<ResponseEntity<Account>> create(@RequestBody Account account) {

        // validar monto minimo de apertura
        if (account.getAmountMinimunOpen() < Constant.NUMERO_CERO) {
            return   Mono.error(new RuntimeException("Estimado usuario, el monto mínimo de apertura no puede ser menor que cero."));
        }



        // Insertar cuenta
        return this.accountService.insert(account)
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK));

    }

    public Mono<ResponseEntity<String>> fallbackCreateAccount(Account account, CallNotPermittedException e) {
        return Mono.just(new ResponseEntity<>("Creación de Cuentas no disponible",
                HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Account, tipo Mono
     */
    @PutMapping
    public Mono<ResponseEntity<Account>> update(@RequestBody Account account){
        return this.accountService.update(account)
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return this.accountService.delete(id)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Account, tipo String
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Account>> find(@PathVariable String id) {
        return this.accountService.find(id)
                .map(account -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(account));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el Account, tipo String
     */
    @GetMapping("/findByAccountNumber/{accountNumber}")
    public Mono<ResponseEntity<Account>> findByAccountNumber(@PathVariable String accountNumber) {
        return this.accountService.findByAccountNumber(accountNumber)
                .map(account -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(account));
    }

    /**
     * Método que realiza la acción buscar datos por código del cliente
     * @return Mono retorna el Account, tipo String
     */
    @GetMapping("/findAllByCodeClient/{code}")
    public Mono<ResponseEntity<Flux<Account>>> findAllByCodeClient(@PathVariable String code) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.accountService.findByCodeClient(code)));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Flux retorna el Account, tipo Flux
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<Account>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.accountService.findAll())
        );
    }

}

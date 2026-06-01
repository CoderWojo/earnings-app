package org.wojo.earnings_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wojo.earnings_app.entity.Earning;
import org.wojo.earnings_app.service.EarningsService;

import java.util.List;

@RestController
@RequestMapping("earning")
public class EarningController {

    private final EarningsService service;

    public EarningController(EarningsService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Earning>> getAll() {
        return ResponseEntity
                .ok(service.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Earning> save(@RequestBody Earning earning) {
        Earning saved = service.save(earning);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/update")
    public ResponseEntity<Earning> update(@RequestBody Earning earning) {

        // TODO: napisz ExceptionHandler niech wyłapie wyjątek NotFoundException
        //  i wtedy zwróci ResponseEntity z odpowiednimi właściwościami
        Earning updated = service.updateEarning(earning);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        // The 204 (No Content) status code indicates that the server has successfully fulfilled the request
        // and that there is no additional content to send in the response content.
        //  TODO: wyłap wyjątek
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.F5aes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.F5aes.model.BootcampModel;
import com.F5aes.service.BootcampService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bootcamp")
public class BootcampController {

    private final BootcampService bootcampService;

    @GetMapping
    public List<BootcampModel> getAllBootcamp() {
        return bootcampService.findAll();
    }

    @GetMapping({ "/{id}" })
    public BootcampModel getBootcampById(@PathVariable Long id) {
        return bootcampService.findById(id);
    }

    @GetMapping("/name/{name}")
    public BootcampModel getBootcampByName(@PathVariable String name) {
        return bootcampService.findByName(name);
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<BootcampModel> createBootcamp(@RequestBody BootcampModel bootcampModel) {
        BootcampModel savedBootcampModel = bootcampService.save(bootcampModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBootcampModel);
    }

    @PutMapping("/update/{id}")
    public BootcampModel updateBootcamp(@PathVariable Long id, @RequestBody BootcampModel bootcampDetails) {
        return bootcampService.updateBootcamp(id, bootcampDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBootcamp(@PathVariable Long id) {
        bootcampService.deleteById(id);
        return ResponseEntity.ok("Bootcamp deleted succesfully");
    }
}

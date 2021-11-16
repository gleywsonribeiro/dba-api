package com.dba.dbaapi.controller;

import com.dba.dbaapi.model.User;
import com.dba.dbaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public Page<User> listar(@PageableDefault(page = 0, size = 10) Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> detalhar(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> cadastrar(@RequestBody @Valid User user, UriComponentsBuilder builder) {
        repository.save(user);
        URI uri = builder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<User> atualizar(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            User atualizado = optional.get();
            atualizado.setCpf(user.getCpf());
            atualizado.setNome(user.getNome());
            repository.save(atualizado);
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }


}

package com.dba.dbaapi.controller;

import com.dba.dbaapi.model.User;
import com.dba.dbaapi.model.UserDocumentation;
import com.dba.dbaapi.repository.UserDocumentationRepository;
import com.dba.dbaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-documentation")
public class UserDocumantationController {
    @Autowired
    private UserDocumentationRepository repository;

    @GetMapping
    public List<UserDocumentation> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDocumentation> detalhar(@PathVariable Long id) {
        Optional<UserDocumentation> userDocumentation = repository.findById(id);
        if (userDocumentation.isPresent()) {
            return ResponseEntity.ok(userDocumentation.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDocumentation> cadastrar(@RequestBody @Valid UserDocumentation documantation, UriComponentsBuilder builder) {
        repository.save(documantation);
        URI uri = builder.path("/user-documentation/{id}").buildAndExpand(documantation.getId()).toUri();
        return ResponseEntity.created(uri).body(documantation);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<UserDocumentation> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDocumentation> atualizar(@PathVariable Long id, @Valid @RequestBody UserDocumentation documentation) {
        Optional<UserDocumentation> optional = repository.findById(id);
        if (optional.isPresent()) {
            UserDocumentation atualizado = optional.get();
            atualizado.setDocument(documentation.getDocument());
            atualizado.setTipoDocumento(documentation.getTipoDocumento());
            repository.save(atualizado);
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }
}

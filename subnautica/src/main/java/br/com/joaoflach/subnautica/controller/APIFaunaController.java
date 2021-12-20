package br.com.joaoflach.subnautica.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.joaoflach.subnautica.models.Biome;
import br.com.joaoflach.subnautica.models.Type;
import br.com.joaoflach.subnautica.models.dto.FaunaDTO;
import br.com.joaoflach.subnautica.service.FaunaService;
import br.com.joaoflach.subnautica.util.FaunaMapping;

@RestController
@RequestMapping("/api/fauna")
public class APIFaunaController {
    @Autowired
    private FaunaService service;

    @PostMapping
    public ResponseEntity<FaunaDTO> insert(@RequestBody FaunaDTO dto) {
        if (dto.getId() != 0)
            return new ResponseEntity<>(new FaunaDTO(), HttpStatus.BAD_REQUEST);

        var fauna = service.insertOrUpdate(
                FaunaMapping.toFauna(dto));

        if (fauna != null)
            return new ResponseEntity<>(
                    FaunaMapping.fromFauna(fauna), HttpStatus.CREATED);
        return new ResponseEntity<>(new FaunaDTO(), HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<FaunaDTO> update(@RequestBody FaunaDTO dto) {
        if (dto.getId() <= 0)
            return new ResponseEntity<>(new FaunaDTO(), HttpStatus.BAD_REQUEST);

        var fauna = service.findById(dto.getId());

        if (fauna.isPresent())
            return new ResponseEntity<>(
                    FaunaMapping.fromFauna(
                            service.insertOrUpdate(
                                    FaunaMapping.toFauna(dto))),
                    HttpStatus.OK);
        return new ResponseEntity<>(new FaunaDTO(), HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<FaunaDTO>> findAll() {
        return new ResponseEntity<>(
                service.findAll().stream().map(
                        (obj) -> FaunaMapping.fromFauna
                        (obj)).collect(Collectors.toList
                        ()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        var fauna = service.findById(id);
        if (fauna.isPresent()) {
            var isSuccess = service.delete(id);

            if (isSuccess)
                return new ResponseEntity<>("Item removido com sucesso!!!", HttpStatus.OK);
            return new ResponseEntity<>("Não foi possível remover o item!!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Item não localizado!!!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/Name/{name}")
    public ResponseEntity<List<FaunaDTO>> findByName(@PathVariable String name) {
        return new ResponseEntity<>(
            service.findByName(name).stream().map(
                (obj) -> FaunaMapping.fromFauna
                (obj)).collect(Collectors.toList
                ()),HttpStatus.OK
        );
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<FaunaDTO>> findByType(@PathVariable Type type) {
        return new ResponseEntity<>(
            service.findByType(type).stream().map(
                (obj) -> FaunaMapping.fromFauna
                (obj)).collect(Collectors.toList
                ()),HttpStatus.OK
        );
    }

    @GetMapping("/biome/{biome}")
    public ResponseEntity<List<FaunaDTO>> findByBiome(@PathVariable Biome biome){
        return new ResponseEntity<>(
            service.findByBiome(biome).stream().map(
                (obj) -> FaunaMapping.fromFauna
                (obj)).collect(Collectors.toList
                ()),HttpStatus.OK
        );
    }
}

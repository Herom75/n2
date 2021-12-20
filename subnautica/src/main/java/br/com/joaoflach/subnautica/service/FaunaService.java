package br.com.joaoflach.subnautica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaoflach.subnautica.models.Biome;
import br.com.joaoflach.subnautica.models.Fauna;
import br.com.joaoflach.subnautica.models.Type;
import br.com.joaoflach.subnautica.repository.FaunaRepository;

@Service
public class FaunaService {
    @Autowired
    private FaunaRepository repository;

    public Fauna insertOrUpdate(Fauna fauna) {
        return repository.save(fauna);
    }

    public List<Fauna> findAll() {
        return repository.findAll();
    }

    public boolean delete(int id) {
        var fauna = repository.findById(id);

        if (fauna.isPresent()) {
            repository.deleteById(id);
            fauna = repository.findById(id);
            return fauna.isEmpty();
        }
        return false;
    }

    public List<Fauna> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Fauna> findByType(Type name) {
        return repository.findByType(name);
    }

    public Optional<Fauna> findById(int id) {
        return repository.findById(id);
    }

    public List<Fauna> findByBiome(Biome name) {
        return repository.findByBiome(name);
    }

}
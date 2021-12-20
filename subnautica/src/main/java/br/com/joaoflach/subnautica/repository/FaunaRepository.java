package br.com.joaoflach.subnautica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joaoflach.subnautica.models.Biome;
import br.com.joaoflach.subnautica.models.Fauna;
import br.com.joaoflach.subnautica.models.Type;


@Repository
public interface FaunaRepository extends JpaRepository<Fauna, Integer> {

    public List<Fauna> findByName(String name);

    public List<Fauna> findByBiome(Biome biome);

    public List<Fauna> findByType(Type type);

}

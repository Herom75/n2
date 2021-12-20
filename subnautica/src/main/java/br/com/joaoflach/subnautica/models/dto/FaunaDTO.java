package br.com.joaoflach.subnautica.models.dto;

import br.com.joaoflach.subnautica.models.Biome;
import br.com.joaoflach.subnautica.models.Type;

public class FaunaDTO {
    private int id;
    private String name;
    private Type type;
    private Biome biome;

    public FaunaDTO() {
    }

    public FaunaDTO(int id, String name, Biome biome, Type type) {
        this.id = id;
        this.name = name;
        this.biome = biome;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Biome getBiome() {
        return this.biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}

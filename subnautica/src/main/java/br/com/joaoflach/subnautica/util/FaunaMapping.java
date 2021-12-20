package br.com.joaoflach.subnautica.util;

import br.com.joaoflach.subnautica.models.Fauna;
import br.com.joaoflach.subnautica.models.dto.FaunaDTO;


public class FaunaMapping {
    private FaunaMapping() {
    }

    public static FaunaDTO fromFauna(Fauna fauna) {
        return new FaunaDTO(fauna.getId(), fauna.getName(), fauna.getBiome(), fauna.getType());
    }

    public static Fauna toFauna(FaunaDTO dto) {
        return new Fauna(dto.getId(), dto.getName(), dto.getBiome(),dto.getType());
    }
}
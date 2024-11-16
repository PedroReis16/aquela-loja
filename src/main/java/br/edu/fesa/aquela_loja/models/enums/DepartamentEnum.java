package br.edu.fesa.aquela_loja.models.enums;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.ACESSORIOS_GAMER;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.ACESSORIOS_PARA_TVS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.APARELHOS_DE_SOM;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.ASSISTENTE_VIRTUAL;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.CABOS_E_ADAPTADORES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.CAIXA_DE_SOM;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.COIFAS_E_DEPURADORES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.COOKTOP;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.COOLERS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.DISCO_RIGIDO;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.DJ;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.DVD_E_BLURAY;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.FOGOES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.FONE_DE_OUVIDO;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.FONTES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.FORNOS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.GABINETES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.GELADEIRAS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.HEADSETS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.HOME_THEATER_E_SOUNDBAR;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.IMPRESSORAS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.MEMORIA_RAM;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.MESA_DIGITALIZADORA;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.MICROFONE;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.MONITORES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.MOUSE;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.MOUSE_PAD;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.NINTENDO;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.NOTEBOOKS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.PC;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.PLACA_DE_VIDEO;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.PLAYSTATION;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.PORTATEIS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.PROCESSADORES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.RADIO;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.SCANNERS;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.SIMULADORES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.SMART_TV;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.SSD;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.SUPORTES;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.TECLADO;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.TV_4K;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.TV_8K;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.TV_LED;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.WEBCAM_E_VIDEOCONFERENCIA;
import static br.edu.fesa.aquela_loja.models.enums.CategoryEnum.XBOX;
import lombok.Getter;

public enum DepartamentEnum {
    HARDWARE("Hardware", Set.of(COOLERS, DISCO_RIGIDO, FONTES, MEMORIA_RAM, PLACA_DE_VIDEO, PROCESSADORES, SSD)),
    PERIPHERALS("Periféricos", Set.of(CABOS_E_ADAPTADORES, GABINETES, HEADSETS, MESA_DIGITALIZADORA, MOUSE, MOUSE_PAD, SUPORTES, TECLADO, WEBCAM_E_VIDEOCONFERENCIA)),
    COMPUTERS("Computadores", Set.of(IMPRESSORAS, MONITORES, NOTEBOOKS, PC, SCANNERS)),
    GAMING("Games", Set.of(ACESSORIOS_GAMER, NINTENDO, PLAYSTATION, PORTATEIS, SIMULADORES, XBOX)),
    TELEVISIONS("Televisores", Set.of(ACESSORIOS_PARA_TVS, SMART_TV, TV_4K, TV_8K, TV_LED)),
    AUDIO("Áudio", Set.of(APARELHOS_DE_SOM, ASSISTENTE_VIRTUAL, CAIXA_DE_SOM, DJ, DVD_E_BLURAY, FONE_DE_OUVIDO, HOME_THEATER_E_SOUNDBAR, MICROFONE, RADIO)),
    ELETRODOMESTICS("Eletrodomésticos", Set.of(COIFAS_E_DEPURADORES, COOKTOP, FOGOES, FORNOS, GELADEIRAS));

    @Getter
    private final String displayName;
    @Getter
    private final Set<CategoryEnum> categories;

    DepartamentEnum(String displayName, Set<CategoryEnum> categories) {
        this.displayName = displayName;
        this.categories = categories;
    }

    public static Map<String, Set<String>> getDepartaments() {
        Map<String, Set<String>> departaments = new LinkedHashMap<>();

        for (DepartamentEnum departament : DepartamentEnum.values()) {
            departaments.put(
                    departament.getDisplayName(),
                    departament.getCategories()
                            .stream()
                            .map(CategoryEnum::getDisplayName)
                            .collect(Collectors.toSet())
            );
        }
        return departaments;
    }

}

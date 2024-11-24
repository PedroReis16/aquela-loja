package br.edu.fesa.aquela_loja.models.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CategoryEnum {
    ACESSORIOS_GAMER("Acessórios Gamer"),
    ACESSORIOS_PARA_TVS("Acessórios para TVs"),
    APARELHOS_DE_SOM("Aparelhos de Som"),
    ASSISTENTE_VIRTUAL("Assistente Virtual"),
    CABOS_E_ADAPTADORES("Cabos e Adaptadores"),
    CAIXA_DE_SOM("Caixa de Som"),
    COIFAS_E_DEPURADORES("Coifas e Depuradores"),
    COOLERS("Coolers"),
    COOKTOP("Cooktop"),
    DISCO_RIGIDO("Disco Rígido"),
    DJ("DJ"),
    DVD_E_BLURAY("DVD e Blu-ray"),
    FONE_DE_OUVIDO("Fone de Ouvido"),
    FONTES("Fontes"),
    FOGOES("Fogões"),
    FORNOS("Fornos"),
    GABINETES("Gabinetes"),
    GELADEIRAS("Geladeiras"),
    HEADSETS("Headsets"),
    HOME_THEATER_E_SOUNDBAR("Home Theater e Soundbar"),
    IMPRESSORAS("Impressoras"),
    MEMORIA_RAM("Memória RAM"),
    MESA_DIGITALIZADORA("Mesa Digitalizadora"),
    MICROFONE("Microfone"),
    MONITORES("Monitores"),
    MOUSE("Mouse"),
    MOUSE_PAD("Mouse Pad"),
    NINTENDO("Nintendo"),
    NOTEBOOKS("Notebooks"),
    PC("PC"),
    PEN_DRIVE("Pen Drive"),
    PLAYSTATION("PlayStation"),
    PLACA_DE_VIDEO("Placa de Vídeo"),
    PORTATEIS("Portáteis"),
    PROCESSADORES("Processadores"),
    RADIO("Rádio"),
    SCANNERS("Scanners"),
    SIMULADORES("Simuladores"),
    SSD("SSD"),
    SUPORTES("Suportes"),
    SMART_TV("Smart TV"),
    TECLADO("Teclado"),
    TV_4K("TV 4K"),
    TV_8K("TV 8K"),
    TV_LED("TV LED"),
    WEBCAM_E_VIDEOCONFERENCIA("Webcam e Videoconferência"),
    XBOX("Xbox");

    // ACESSORIOS_GAMER("ACESSÓRIOS GAMER"),
    // ACESSORIOS_PARA_TVS("ACESSÓRIOS PARA TVS"),
    // APARELHOS_DE_SOM("APARELHOS DE SOM"),
    // ASSISTENTE_VIRTUAL("ASSISTENTE VIRTUAL"),
    // CABOS_E_ADAPTADORES("CABOS E ADAPTADORES"),
    // CAIXA_DE_SOM("CAIXA DE SOM"),
    // COIFAS_E_DEPURADORES("COIFAS E DEPURADORES"),
    // COOLERS("COOLERS"),
    // COOKTOP("COOKTOP"),
    // DISCO_RIGIDO("DISCO RÍGIDO"),
    // DJ("DJ"),
    // DVD_E_BLURAY("DVD E BLU-RAY"),
    // FONE_DE_OUVIDO("FONE DE OUVIDO"),
    // FONTES("FONTES"),
    // FOGOES("FOGÕES"),
    // FORNOS("FORNOS"),
    // GABINETES("GABINETES"),
    // GELADEIRAS("GELADEIRAS"),
    // HEADSETS("HEADSETS"),
    // HOME_THEATER_E_SOUNDBAR("HOME THEATER E SOUNDBAR"),
    // IMPRESSORAS("IMPRESSORAS"),
    // MEMORIA_RAM("MEMÓRIA RAM"),
    // MESA_DIGITALIZADORA("MESA DIGITALIZADORA"),
    // MICROFONE("MICROFONE"),
    // MONITORES("MONITORES"),
    // MOUSE("MOUSE"),
    // MOUSE_PAD("MOUSE PAD"),
    // NINTENDO("NINTENDO"),
    // NOTEBOOKS("NOTEBOOKS"),
    // PC("PC"),
    // PEN_DRIVE("PEN DRIVE"),
    // PLAYSTATION("PLAYSTATION"),
    // PLACA_DE_VIDEO("PLACA DE VÍDEO"),
    // PORTATEIS("PORTÁTEIS"),
    // PROCESSADORES("PROCESSADORES"),
    // RADIO("RÁDIO"),
    // SCANNERS("SCANNERS"),
    // SIMULADORES("SIMULADORES"),
    // SSD("SSD"),
    // SUPORTES("SUPORTES"),
    // SMART_TV("SMART TV"),
    // TECLADO("TECLADO"),
    // TV_4K("TV 4K"),
    // TV_8K("TV 8K"),
    // TV_LED("TV LED"),
    // WEBCAM_E_VIDEOCONFERENCIA("WEBCAM E VIDEOCONFERÊNCIA"),
    // XBOX("XBOX");

    private final String displayName;

    private CategoryEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<CategoryEnum, String> getCategories() {
        Map<CategoryEnum, String> categoryDisplayNames = new LinkedHashMap<>();

        for (CategoryEnum category : CategoryEnum.values()) {
            categoryDisplayNames.put(category, category.getDisplayName());
        }

        return categoryDisplayNames;
    }
}

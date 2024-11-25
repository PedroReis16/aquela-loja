package br.edu.fesa.aquela_loja.models.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BrandEnum {
    // Gabinetes e Componentes
    CORSAIR("Corsair"),
    NZXT("NZXT"),
    COOLER_MASTER("Cooler Master"),
    THERMALTAKE("Thermaltake"),
    LIAN_LI("Lian Li"),
    PHANTEKS("Phanteks"),
    FRACTAL_DESIGN("Fractal Design"),
    SILVERSTONE("SilverStone"),
    AEROCOOL("Aerocool"),
    BE_QUIET("Be Quiet!"),
    
    // Monitores
    LG("LG"),
    SAMSUNG("Samsung"),
    DELL("Dell"),
    ASUS("ASUS"),
    AOC("AOC"),
    BENQ("BenQ"),
    MSI("MSI"),
    ACER("Acer"),
    VIEWSONIC("ViewSonic"),
    GIGABYTE("Gigabyte"),

    // Periféricos (Teclados, Mouses, Headsets)
    LOGITECH("Logitech"),
    RAZER("Razer"),
    HYPERX("HyperX"),
    STEELSERIES("SteelSeries"),
    ROCCAT("Roccat"),
    REDRAGON("Redragon"),
    ZOWIE("Zowie"),
    COUGAR("Cougar"),
    SHARKOON("Sharkoon"),

    // Fones de Ouvido e Áudio
    SONY("Sony"),
    JBL("JBL"),
    BOSE("Bose"),
    SENNHEISER("Sennheiser"),
    BEATS("Beats"),
    AUDIO_TECHNICA("Audio-Technica"),
    AKG("AKG"),
    SKULLCANDY("Skullcandy"),
    EDIFIER("Edifier"),
    MARSHALL("Marshall"),

    // Fontes de Alimentação
    EVGA("EVGA"),
    SEASONIC("Seasonic"),
    XPG("XPG"),
    ANTEC("Antec"),
    FSP("FSP"),

    // Placas-mãe, GPUs e Processadores
    INTEL("Intel"),
    AMD("AMD"),
    NVIDIA("NVIDIA"),
    ASROCK("ASRock"),

    // Armazenamento
    SEAGATE("Seagate"),
    WESTERN_DIGITAL("Western Digital"),
    KINGSTON("Kingston"),
    CRUCIAL("Crucial"),
    SANDISK("SanDisk"),
    ADATA("Adata"),
    TOSHIBA("Toshiba"),

    // Câmeras e Acessórios
    CANON("Canon"),
    NIKON("Nikon"),
    GOPRO("GoPro"),
    PANASONIC("Panasonic"),
    FUJIFILM("Fujifilm"),

    // Acessórios Diversos
    ELGATO("Elgato"),
    UGREEN("Ugreen"),
    TP_LINK("TP-Link"),
    ANKER("Anker"),
    AUKEY("Aukey"),
    HYPER("Hyper");

    private final String displayName;

    private BrandEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Map<BrandEnum, String> getBrands() {
        Map<BrandEnum, String> brandDisplayNames = new LinkedHashMap<>();

        for (BrandEnum brand : BrandEnum.values()) {
            brandDisplayNames.put(brand, brand.getDisplayName());
        }

        return brandDisplayNames;
    }
}

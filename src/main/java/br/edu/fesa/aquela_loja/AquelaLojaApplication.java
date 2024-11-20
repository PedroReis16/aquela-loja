package br.edu.fesa.aquela_loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AquelaLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquelaLojaApplication.class, args);
    }

    // @Bean
    // public CommandLineRunner commandLineRunner(BCryptPasswordEncoder passwordEncoder, IUserRepository userRepository, IUserAddressRepository addressRepository) {
    //     return args -> {
    //         UserModel admin = UserModel.builder()
    //                 .username("Administrador")
    //                 .email("admin")
    //                 .password(passwordEncoder.encode("admin"))
    //                 .gender("Other")
    //                 .birthdate("01/01/2000")
    //                 .document("858.703.940-74")
    //                 .phone("(11) 99999-9999")
    //                 .role(ADMIN)
    //                 .build();

    //         UserAddressModel adminAdrress = UserAddressModel.builder()
    //                 .cep("12345-678")
    //                 .addressIdentification("Casa")
    //                 .street("Rua dos Bobos")
    //                 .number("0")
    //                 .neighborhood("Jardim das Oliveiras")
    //                 .city("São Paulo")
    //                 .state("SP")
    //                 .complement("Casa")
    //                 .reference("Próximo ao mercado")
    //                 .isDefault(true)
    //                 .appUser(admin)
    //                 .build();

    //         userRepository.save(admin);
    //         addressRepository.save(adminAdrress);
    //     };
    // }
}

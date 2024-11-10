package br.edu.fesa.aquela_loja;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import static br.edu.fesa.aquela_loja.models.enums.Role.ADMIN;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;

@SpringBootApplication
public class AquelaLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquelaLojaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BCryptPasswordEncoder passwordEncoder, IAppUserRepository userRepository, IAddressRepository addressRepository) {
        return args -> {
            AppUserModel admin = AppUserModel.builder()
                    .username("Administrador")
                    .email("admin")
                    .password(passwordEncoder.encode("admin"))
                    .gender("Other")
                    .birthdate("01/01/2000")
                    .document("123.456.789-00")
                    .phone("11 99999-9999")
                    .role(ADMIN)
                    .build();

            AddressModel adminAdrress = AddressModel.builder()
                    .cep("12345-678")
                    .addressIdentification("Casa")
                    .street("Rua dos Bobos")
                    .number("0")
                    .neighborhood("Jardim das Oliveiras")
                    .city("São Paulo")
                    .state("SP")
                    .complement("Casa")
                    .reference("Próximo ao mercado")
                    .appUser(admin)
                    .build();

            userRepository.save(admin);
            addressRepository.save(adminAdrress);
        };
    }
}

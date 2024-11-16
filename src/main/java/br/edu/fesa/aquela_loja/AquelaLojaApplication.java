package br.edu.fesa.aquela_loja;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.fesa.aquela_loja.models.entity.AddressModel;
import br.edu.fesa.aquela_loja.models.entity.AppUserModel;
import br.edu.fesa.aquela_loja.models.entity.PaymentCardModel;
import static br.edu.fesa.aquela_loja.models.enums.Role.ADMIN;
import br.edu.fesa.aquela_loja.repository.IAddressRepository;
import br.edu.fesa.aquela_loja.repository.IAppUserRepository;
import br.edu.fesa.aquela_loja.repository.IPaymentCardRepository;

@SpringBootApplication
public class AquelaLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquelaLojaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BCryptPasswordEncoder passwordEncoder, IAppUserRepository userRepository, IAddressRepository addressRepository, IPaymentCardRepository paymentCardRepository) {
        return args -> {
            AppUserModel admin = AppUserModel.builder()
                    .username("Administrador")
                    .email("admin")
                    .password(passwordEncoder.encode("admin"))
                    .gender("Other")
                    .birthdate("01/01/2000")
                    .document("858.703.940-74")
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
                    .isDefault(true)
                    .appUser(admin)
                    .build();

            PaymentCardModel adminCard = PaymentCardModel.builder()
                    .holderName("Administrador")
                    .number("1234 5678")
                    .expirationDate("01/23")
                    .cvv(123)
                    .appUser(admin)
                    .build();

            userRepository.save(admin);
            addressRepository.save(adminAdrress);
            paymentCardRepository.save(adminCard);
        };
    }
}

package com.dashviagens.admin;

import com.dashviagens.admin.model.AdminUser;
import com.dashviagens.admin.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Cria o usuario admin padrão na primeira inicialização se a tabela estiver vazia.
 * Em produção, sobrescrever via env vars:
 *   DASHVIAGENS_ADMIN_USERNAME=meunome
 *   DASHVIAGENS_ADMIN_PASSWORD=minhasenhaforte
 */
@Component
public class AdminBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminBootstrap.class);

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${dashviagens.admin.username:admin}")
    private String defaultUsername;

    @Value("${dashviagens.admin.password:admin123}")
    private String defaultPassword;

    public AdminBootstrap(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (adminUserRepository.count() == 0) {
            AdminUser admin = AdminUser.builder()
                    .username(defaultUsername)
                    .passwordHash(passwordEncoder.encode(defaultPassword))
                    .role("ADMIN")
                    .build();

            adminUserRepository.save(admin);
            log.warn("=================================================");
            log.warn("Admin criado: username='{}'. TROQUE A SENHA!", defaultUsername);
            log.warn("=================================================");
        }
    }
}

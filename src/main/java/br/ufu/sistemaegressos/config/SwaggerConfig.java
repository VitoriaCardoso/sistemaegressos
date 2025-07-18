package br.ufu.sistemaegressos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API REST do Sistema de Acompanhamento de Egressos da UFU",
                version = "1.0"
        )
)

public class SwaggerConfig {
}

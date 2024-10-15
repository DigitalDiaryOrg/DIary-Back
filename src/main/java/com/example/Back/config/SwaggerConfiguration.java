package com.example.Back.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomizer;

@OpenAPIDefinition(
        info = @Info(title = "API 명세서",
                description = "API 명세서 입니다. ",
                version = "v1")
)
@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi customDiaryOpenApi(){
        String[] paths = {"/diary/**"};

        return GroupedOpenApi
                .builder()
                .group("일기장 API")
                .pathsToMatch(paths)
//                .addOpenApiCustomizer(buildSecurityOpenApi())
                .build();

    }

//    public OpenApiCustomizer buildSecurityOpenApi() {
//        // jwt token 을 한번 설정하면 header 에 값을 넣어주는 코드, 자세한건 아래에 추가적으로 설명할 예정
//        return OpenApi -> OpenApi.addSecurityItem(new SecurityRequirement().addList("jwt token"))
//                .getComponents().addSecuritySchemes("jwt token", new SecurityScheme()
//                        .name("Authorization")
//                        .type(SecurityScheme.Type.HTTP)
//                        .in(SecurityScheme.In.HEADER)
//                        .bearerFormat("JWT")
//                        .scheme("bearer"));
//    }
}


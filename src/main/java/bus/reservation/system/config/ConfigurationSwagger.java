package bus.reservation.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
//To use the Swagger you need either to be logged in or disable the Authentication mechanism.
//Access the Swagger documentation "/swagger-ui.html".
public class ConfigurationSwagger {
    @Bean
    public Docket api() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(this.appInfo());
    }

    public ApiInfo appInfo(){
        return new ApiInfo("Bus Reservation Service Api Documentation",
                "Documentation automatically generated",
                "1.0.0",
                null,
                new Contact("Ermal Meco", "", "ermalmeco@gmail.com").toString(),
                null,
                null);
    }
}
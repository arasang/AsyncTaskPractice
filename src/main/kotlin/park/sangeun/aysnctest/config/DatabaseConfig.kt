package park.sangeun.aysnctest.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EntityScan(basePackages = ["park.sangeun.aysnctest"])
@EnableJpaRepositories(basePackages = ["park.sangeun.aysnctest"])
@EnableJpaAuditing
@EnableTransactionManagement
class DatabaseConfig(
    @Value("\${spring.datasource.url}")
    private val url: String,
    @Value("\${spring.datasource.username}")
    private val username: String,
    @Value("\${spring.datasource.password}")
    private val password: String
) {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .build()
    }
}
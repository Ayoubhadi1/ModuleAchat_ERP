package ma.worldaudit.achat.infra.config


import ma.worldaudit.achat.domain.port.api.IFixedAssetServicesPort
import ma.worldaudit.achat.domain.port.infra.*
import ma.worldaudit.achat.domain.usecase.amortisation.AmortisationUseCase
import ma.worldaudit.achat.domain.usecase.bill.UseCaseBill
import ma.worldaudit.achat.domain.usecase.supplier.UseCaseSupplier
import ma.worldaudit.achat.infra.bill.repository.IAccountingEntryRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories("ma.worldaudit.achat.infra.bill.repository", "ma.worldaudit.achat.infra.supplier.repository","ma.worldaudit.achat.infra.fixedasset.repository")
@ComponentScan("ma.worldaudit.achat.infra")
open class Config {
    @Bean
    open fun getConfiguredSupplier(supplierRepository: ISupplierRepositoryPort): UseCaseSupplier {
        return UseCaseSupplier(supplierRepository)
    }

    @Bean
    open fun getConfiguredBill(
        billRepository: IBillRepositoryPort,
        accountingEntryRepository: IAccountingEntryRepositoryPort
    ): UseCaseBill {
        return UseCaseBill(billRepository, accountingEntryRepository)
    }

    @Bean
    open fun getConfigureAmortisation(
        iAccountingEntryRepository: IAccountingEntryRepository,
        iAmortisationRepositoryPort: IAmortisationRepositoryPort,
        iFixedAssetRepositoryPort: IFixedAssetRepositoryPort
    ): AmortisationUseCase {
        return AmortisationUseCase(iAccountingEntryRepository, iAmortisationRepositoryPort, iFixedAssetRepositoryPort)
    }
}
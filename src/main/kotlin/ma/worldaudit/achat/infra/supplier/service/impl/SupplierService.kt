package ma.worldaudit.achat.infra.supplier.service.impl

import ma.worldaudit.achat.infra.supplier.repository.ISupplierRepository
import ma.worldaudit.achat.infra.supplier.service.ISupplierServices
import ma.worldaudit.achat.infra.model.Supplier
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class SupplierService(private val iSupplierRepository: ISupplierRepository) :
    ISupplierServices {
    override fun getAll(): List<Supplier> {
        return iSupplierRepository.findAll()
    }

    override fun save(supplier: Supplier) {
        iSupplierRepository.save(supplier)
    }

    override fun getById(fournisseurId: Long): Supplier {
        return iSupplierRepository.findById(fournisseurId).get()
    }

    override fun deleteById(fournisseurId: Long) {
        iSupplierRepository.delete(getById(fournisseurId))
    }

    override fun getByEmail(fournisseurEmail: String): Supplier {
        return iSupplierRepository.findByMail(fournisseurEmail)
    }

    override fun getAllFournisseur(page: Int, size: Int): Page<Supplier> {
        return iSupplierRepository.findAll(PageRequest.of(page, size))
    }

    override fun getAllFournisseur(): List<Supplier> {
        return iSupplierRepository.findAll()
    }
}

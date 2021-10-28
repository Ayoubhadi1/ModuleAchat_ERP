package ma.worldaudit.achat.infra.supplier.service

import ma.worldaudit.achat.infra.model.Supplier
import org.springframework.data.domain.Page

interface ISupplierServices {
    fun getAll(): List<Supplier>
    fun save(supplier: Supplier)
    fun getById(fournisseurId: Long): Supplier
    fun deleteById(fournisseurId: Long)
    fun getByEmail(fournisseurEmail: String): Supplier
    fun getAllFournisseur(page: Int, size: Int): Page<Supplier>
    fun getAllFournisseur(): List<Supplier>
}

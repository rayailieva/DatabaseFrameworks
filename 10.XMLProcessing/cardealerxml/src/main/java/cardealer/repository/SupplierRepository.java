package cardealer.repository;

import cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT new cardealer.domain.dtos.SupplierExportDto( " +
            "s.id, s.name, COUNT(s)) " +
            "FROM cardealer.domain.entities.Supplier AS s " +
            "JOIN cardealer.domain.entities.Part AS p " +
            "ON s.id = p.supplier.id " +
            "GROUP BY s.id")
    List<Supplier> getAllLocalSuppliers();
}

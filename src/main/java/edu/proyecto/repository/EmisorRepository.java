package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.EmisorEntity;

@Repository
public interface EmisorRepository extends JpaRepository<EmisorEntity, Integer>{
    @Query(value = "SELECT * FROM emisor WHERE activo=1", nativeQuery = true)
    public List<EmisorEntity> listarEmisoresActivos();
}
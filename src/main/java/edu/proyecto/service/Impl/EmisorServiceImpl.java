package edu.proyecto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.proyecto.entity.EmisorEntity;
import edu.proyecto.repository.EmisorRepository;
import edu.proyecto.service.EmisorService;

@Service
public class EmisorServiceImpl implements EmisorService{
    @Autowired
    private EmisorRepository emisorRepository;

    @Override
    public List<EmisorEntity> listarEmisores() {
        return emisorRepository.listarEmisoresActivos();
    }

}

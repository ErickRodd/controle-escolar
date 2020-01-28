package com.hbsis.controle.escolar.bimestres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BimestreService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BimestreService.class);
    private final IBimestreRepository iBimestreRepository;

    public BimestreService(IBimestreRepository iBimestreRepository) {
        this.iBimestreRepository = iBimestreRepository;
    }

    public BimestreDTO findById(Long id){
        Optional<Bimestre> bimestreOptional = this.iBimestreRepository.findById(id);

        if(bimestreOptional.isPresent()){
            LOGGER.info("Procurando por bimestre de ID [{}]...", id);

            return BimestreDTO.of(bimestreOptional.get());
        }

        throw new IllegalArgumentException("Bimestre não encontrado.");
    }

    public Optional<Bimestre> findByIdOptional(Long id){
        Optional<Bimestre> bimestreOptional = this.iBimestreRepository.findById(id);

        if(bimestreOptional.isPresent()){
            LOGGER.info("Procurando por bimestre de ID [{}]...", id);

            return bimestreOptional;
        }

        throw new IllegalArgumentException("Bimestre não encontrado.");
    }
}

package com.hbsis.controle.escolar.bimestres;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BimestreService {
    private final IBimestreRepository iBimestreRepository;

    public BimestreService(IBimestreRepository iBimestreRepository) {
        this.iBimestreRepository = iBimestreRepository;
    }

    public BimestreDTO findById(Long id) {
        Optional<Bimestre> bimestreOptional = this.iBimestreRepository.findById(id);

        if (bimestreOptional.isPresent()) {
            return BimestreDTO.of(bimestreOptional.get());
        }
        throw new IllegalArgumentException("Bimestre não encontrado.");
    }

    public Optional<Bimestre> findByIdOptional(Long id) {
        Optional<Bimestre> bimestreOptional = this.iBimestreRepository.findById(id);

        if (bimestreOptional.isPresent()) {
            return bimestreOptional;
        }
        throw new IllegalArgumentException("Bimestre não encontrado.");
    }

    public List<Bimestre> list() {
        return iBimestreRepository.findAll();
    }
}

package com.F5aes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.F5aes.Exceptions.BootcampNotFoundExceptions;
import com.F5aes.model.BootcampModel;
import com.F5aes.repository.BootcampRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BootcampService {

    private final BootcampRepository bootcampRepository;

    public List<BootcampModel> findAll() {
        return bootcampRepository.findAll();
    }

    public BootcampModel findbyId(Long id) {
        return bootcampRepository.findById(id)
                .orElseThrow(() -> new BootcampNotFoundExceptions("Bootcamp not found whit id:" + id));
    }

    public BootcampModel findByName(String name) {
        return bootcampRepository.findByName(name)
                .orElseThrow(() -> new BootcampNotFoundExceptions("Bootcamp not found whit name " + name));
    }

    public BootcampModel save(BootcampModel bootcampModel) {
        return bootcampRepository.save(bootcampModel);
    }

    @Transactional
    public BootcampModel updateBootcamp(Long id, BootcampModel bootcampDetails) {
        BootcampModel bootcampModel = findbyId(id);
        bootcampModel.setName(bootcampDetails.getName());
        bootcampModel.setDuration(bootcampDetails.getDuration());
        bootcampModel.setStartDate(bootcampDetails.getStartDate());
        bootcampModel.setEndDate(bootcampDetails.getEndDate());
        return save(bootcampModel);
    }

    public void deleteById(Long Id) {
        bootcampRepository.deleteById(Id);
    }

}

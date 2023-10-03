package com.example.HelpDesk.Service.Impl;

import com.example.HelpDesk.Dto.SupportDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Model.Support;
import com.example.HelpDesk.Repository.SupportRepository;
import com.example.HelpDesk.Service.SupportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SupportServiceImpl implements SupportService {

    private final SupportRepository supportRepository;

    @Override
    public SupportDto createSupport(SupportDto supportDto) {
        Support support = new Support();
        support.setName(supportDto.getName());
        support.setEmail(supportDto.getEmail());

        SupportDto NewSupportDto = mapToDto(supportRepository.save(support));
        return NewSupportDto;
    }

    @Override
    public List<SupportDto> findAll() {
        List<Support> allSupports = supportRepository.findAll();
        return allSupports.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Response<SupportDto> finById(int id) {
        Optional<Support> optionalSupport = supportRepository.findById(id);
        if (optionalSupport.isPresent()) {
            SupportDto supportDto = mapToDto(optionalSupport.get());
            return new Response<>("Success", "Support found", supportDto);
        }
        return new Response<>("Error", "Support with id " + id + " not found", null);
    }



    @Override
    public Response<SupportDto> updateSupport(SupportDto supportDto, int id) {
        Optional<Support> optionalSupport = supportRepository.findById(id);
        if (optionalSupport.isPresent()) {
            Support existingSupport = optionalSupport.get();
            existingSupport.setName(supportDto.getName());
            existingSupport.setEmail(supportDto.getEmail());

            // Sauvegardez la mise à jour dans le référentiel
            supportRepository.save(existingSupport);

            return new Response<>("Success", "Support updated", mapToDto(existingSupport));
        }
        return new Response<>("Error", "Support with id " + id + " not found", null);
    }

    @Override
    public Response deleteSupport(int id) {
        Optional<Support> optionalSupport = supportRepository.findById(id);
        if (optionalSupport.isPresent()) {
            // Supprimez le support s'il existe dans le référentiel
            supportRepository.delete(optionalSupport.get());
            return new Response("Success", "Support deleted", null);
        }
        return new Response("Error", "Support with id " + id + " does not exist", null);
    }

    private SupportDto mapToDto(Support support) {
        SupportDto supportDto = new SupportDto();
        supportDto.setId(support.getId());
        supportDto.setName(support.getName());
        supportDto.setEmail(support.getEmail());
        return supportDto;
    }
}



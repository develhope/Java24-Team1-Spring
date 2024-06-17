package com.develhope.spring.mappers;

import com.develhope.spring.entities.Iscrizione;
import com.develhope.spring.models.DTO.IscrizioneDTO;
import org.springframework.stereotype.Component;

@Component
public class IscrizioneMapper {
    public Iscrizione dtoToEntity(IscrizioneDTO iscrizioneDTO){
        return new Iscrizione(
                iscrizioneDTO.getId(),
                iscrizioneDTO.getUser(),
                iscrizioneDTO.getCourse(),
                iscrizioneDTO.getDataIscrizione(),
                iscrizioneDTO.getPayed()
        );
    }
    public IscrizioneDTO entityToDTO(Iscrizione iscrizione){
        return new IscrizioneDTO(
                iscrizione.getId(),
                iscrizione.getUser(),
                iscrizione.getCourse(),
                iscrizione.getDataIscrizione(),
                iscrizione.getPayed()
        );
    }
}

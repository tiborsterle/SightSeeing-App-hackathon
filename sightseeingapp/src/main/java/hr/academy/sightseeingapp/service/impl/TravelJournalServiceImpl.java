package hr.academy.sightseeingapp.service.impl;

import hr.academy.sightseeingapp.dto.JournalAttractionsDescDto;
import hr.academy.sightseeingapp.dto.TravelJournalCreationResult;
import hr.academy.sightseeingapp.dto.TravelJournalDto;
import hr.academy.sightseeingapp.exception.NoAttractionFoundException;
import hr.academy.sightseeingapp.exception.NoTravelJournalFoundException;
import hr.academy.sightseeingapp.exception.NoUserFoundException;
import hr.academy.sightseeingapp.mappers.TravelJournalMapper;
import hr.academy.sightseeingapp.model.Attraction;
import hr.academy.sightseeingapp.model.JournalAttractionsDesc;
import hr.academy.sightseeingapp.model.TravelJournal;
import hr.academy.sightseeingapp.model.User;
import hr.academy.sightseeingapp.repository.AttractionRepository;
import hr.academy.sightseeingapp.repository.TravelJournalRepository;
import hr.academy.sightseeingapp.repository.UserRepository;
import hr.academy.sightseeingapp.service.TravelJournalService;
import hr.academy.sightseeingapp.validator.TravelJournalValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelJournalServiceImpl implements TravelJournalService {


    private final TravelJournalRepository travelJournalRepository;
    private final TravelJournalMapper travelJournalMapper;
    private final TravelJournalValidator  travelJournalValidator;

    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;

    @Autowired
    public TravelJournalServiceImpl(TravelJournalRepository travelJournalRepository, TravelJournalMapper travelJournalMapper, TravelJournalValidator travelJournalValidator, UserRepository userRepository, AttractionRepository attractionRepository) {
        this.travelJournalRepository = travelJournalRepository;
        this.travelJournalMapper = travelJournalMapper;
        this.travelJournalValidator = travelJournalValidator;
        this.userRepository = userRepository;
        this.attractionRepository = attractionRepository;
    }


    @Transactional
    @Override
    public TravelJournalCreationResult saveTravelJournalByUserId(Long userId, TravelJournalDto travelJournalDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoUserFoundException("Korisnik nije pronađen!"));

        travelJournalValidator.validate(travelJournalDto);
        TravelJournal travelJournal = new TravelJournal();
        travelJournal.setUser(user);
        travelJournal.setStartDate(travelJournalDto.getStartDate());
        travelJournal.setEndDate(travelJournalDto.getEndDate());
        travelJournal.setDescription(travelJournalDto.getDescription());
        travelJournal.setJournalAttractionsDesc(buildJournalAttractionsList(travelJournalDto.getAttractions(), travelJournal));

        TravelJournal saved = travelJournalRepository.save(travelJournal);

        return new TravelJournalCreationResult(saved.getId(), travelJournalMapper.toDto(saved));
    }

    @Transactional
    @Override
    public TravelJournalDto updateTravelJournal(Long travelJournalId, TravelJournalDto travelJournalDto) {
        TravelJournal travelJournal = travelJournalRepository.findById(travelJournalId)
                .orElseThrow(() -> new NoTravelJournalFoundException("Putopis nije pronađen!"));

        if(travelJournalDto.getEndDate() != null) {
            travelJournal.setEndDate(travelJournalDto.getEndDate());
        }
        if(travelJournalDto.getDescription() != null) {
            travelJournal.setDescription(travelJournalDto.getDescription());
        }
        if (travelJournalDto.getAttractions() != null) {
            travelJournal.getJournalAttractionsDesc().addAll(buildJournalAttractionsList(travelJournalDto.getAttractions(), travelJournal));
        }

        TravelJournal saved = travelJournalRepository.save(travelJournal);
        return travelJournalMapper.toDto(saved);
    }

    @Override
    public TravelJournalDto fetchTravelJournal(Long id) {
        TravelJournal travelJournal = travelJournalRepository.findById(id).orElseThrow(() -> new NoTravelJournalFoundException("Putopis nije pronađen!"));

        return travelJournalMapper.toDto(travelJournal);
    }

    private List<JournalAttractionsDesc> buildJournalAttractionsList(List<JournalAttractionsDescDto> attractionsListDto, TravelJournal travelJournal) {
        List<JournalAttractionsDesc> journalAttractionsList = new ArrayList<>();
        for (JournalAttractionsDescDto  item : attractionsListDto) {
            Attraction attraction = attractionRepository.findAttractionByNameAndLocation_Name(item.getAttraction(), item.getLocation())
                    .orElseThrow(() -> new NoAttractionFoundException("Atrakcija " + item.getAttraction() + " nije pronađena!"));

            JournalAttractionsDesc temp = new JournalAttractionsDesc();
            temp.setAttraction(attraction);
            temp.setComment(item.getComment());
            temp.setLocalDate(item.getDate());
            temp.setTravelJournal(travelJournal);

            journalAttractionsList.add(temp);
        }

        return journalAttractionsList;
    }
}

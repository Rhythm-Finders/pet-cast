package com.varchar6.petcast.domain.proposalandestimate.command.service;


import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.ProposalsResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import com.varchar6.petcast.domain.proposalandestimate.command.domain.repository.ProposalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class ProposalsServiceImpl implements ProposalsService{

    private final ProposalsRepository proposalRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public ProposalsServiceImpl ( ProposalsRepository proposalsRepository) {
        this.proposalRepository = proposalsRepository;
    }


    @Override
    public List<ProposalsResponseDTO> findAllProposalsByMemberId(int memberId) {
        return List.of();
    }

    @Override
    public List<ProposalsResponseDTO> findAllProposalsByCompanyId(int companyId) {
        return List.of();
    }

    @Override
    public ProposalsResponseDTO findProposalById(int proposalId) {
        return null;
    }

    // 기획서 작성
    @Transactional
    public ProposalsResponseDTO createProposal(ProposalsRequestDTO proposalRequestDTO) {
        Proposals proposals = Proposals.builder()
                .hopeLocation(proposalRequestDTO.getHopeLocation())
                .hopeTime(proposalRequestDTO.getHopeTime())
                .hopeCost(proposalRequestDTO.getHopeCost())
                .content(proposalRequestDTO.getContent())
                .createdAt(proposalRequestDTO.getCreatedAt())
                .updatedAt(proposalRequestDTO.getUpdatedAt())
                .status(proposalRequestDTO.getStatus())
                .active(proposalRequestDTO.isActive())
                .member_id(proposalRequestDTO.getMemberId())
                .build();

        ProposalsResponseDTO.builder()
                .content(proposals.getContent())
                .hopeLocation(proposals.getHopeLocation())
                .hopeTime(proposals.getHopeTime())
                .hopeCost(proposals.getHopeCost())
                .build();

        return entityToResponseDTO(proposals);
    }

    // 기획서 삭제
    @Transactional
    public void deleteProposal(int proposalsId) {
        if (proposalRepository.existsById(proposalsId)) {
            proposalRepository.deleteById(proposalsId);
        } else {
            throw new IllegalArgumentException("해당 기획서를 찾을 수 없습니다.");
        }
    }

    public static ProposalsResponseDTO entityToResponseDTO(Proposals proposals) {
        return ProposalsResponseDTO.builder()
                .content(proposals.getContent())
                .hopeLocation(proposals.getHopeLocation())
                .hopeTime(proposals.getHopeTime())
                .hopeCost(proposals.getHopeCost())
                .build();
    }
}

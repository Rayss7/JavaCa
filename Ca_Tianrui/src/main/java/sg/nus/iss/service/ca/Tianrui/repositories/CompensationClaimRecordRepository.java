package sg.nus.iss.service.ca.Tianrui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.service.ca.Tianrui.entities.CompensationClaimRecord;

@Repository
public interface CompensationClaimRecordRepository extends JpaRepository<CompensationClaimRecord, Long> {

}

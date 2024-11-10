package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.gwoll.KursovayaContactManager.Entities.Report;

@RepositoryRestResource
public interface ReportRepository extends CrudRepository<Report, Long> {
    @Query("SELECT r FROM Report r WHERE r.id = :id")
    Report findReportById(@Param("id") Long id);

    Report findByStatusAndContent(Report.Status status, String content);
}

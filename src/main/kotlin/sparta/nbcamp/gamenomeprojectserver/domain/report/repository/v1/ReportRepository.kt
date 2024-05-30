package sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.Report

interface ReportRepository : JpaRepository<Report, Long> {
    fun findByEntityType(entityType: EntityType): List<Report>
}
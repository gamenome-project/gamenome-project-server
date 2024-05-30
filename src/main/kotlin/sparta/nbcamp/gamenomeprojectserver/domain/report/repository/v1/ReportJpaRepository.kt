package sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.Report

interface ReportJpaRepository : JpaRepository<Report, Long> {
    fun findByEntityType(entityType: EntityType): List<Report>

    fun findByEntityId(entityId: Long): List<Report>

    fun findByUserId(userId: Long): List<Report>
}
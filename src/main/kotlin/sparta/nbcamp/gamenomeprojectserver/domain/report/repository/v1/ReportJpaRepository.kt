package sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.Report

interface ReportJpaRepository : JpaRepository<Report, Long> {
    fun findByEntityType(entityType: EntityType): List<Report>

    fun findByEntityId(entityId: Long): List<Report>

    fun findByUserId(userId: Long): List<Report>

    @Query("select count(r) from Report r where r.entityId = :entityId and r.entityType = :entityType")
    fun countByEntityIdAndEntityType(entityId: Long, entityType: EntityType): Long
}
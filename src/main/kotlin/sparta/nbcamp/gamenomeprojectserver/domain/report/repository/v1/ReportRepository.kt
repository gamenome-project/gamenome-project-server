package sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1

import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.Report

interface ReportRepository {
    fun findAll(): List<Report>
    fun findByEntityType(entityType: EntityType): List<Report>
    fun findByEntityId(entityId: Long): List<Report>
    fun findByUserId(userId: Long): List<Report>
    fun save(report: Report): Report
    fun deleteById(reportId: Long)
}
package sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1

import org.springframework.stereotype.Repository
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.Report

@Repository
class ReportRepositoryImpl(
    private val reportJpaRepository: ReportJpaRepository
) : ReportRepository {
    override fun findAll(): List<Report> {
        return reportJpaRepository.findAll()
    }

    override fun findByEntityType(entityType: EntityType): List<Report> {
        return reportJpaRepository.findByEntityType(entityType)
    }

    override fun findByEntityId(entityId: Long): List<Report> {
        return reportJpaRepository.findByEntityId(entityId)
    }

    override fun findByUserId(userId: Long): List<Report> {
        return reportJpaRepository.findByUserId(userId)
    }

    override fun save(report: Report): Report {
        return reportJpaRepository.save(report)
    }

    override fun deleteById(reportId: Long) {
        return reportJpaRepository.deleteById(reportId)
    }
}
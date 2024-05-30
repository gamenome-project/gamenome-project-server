package sparta.nbcamp.gamenomeprojectserver.domain.report.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.ReportCommentRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.Report
import sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1.ReportRepository
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@Service
class ReportService(
    private val reportRepository: ReportRepository
) {
    @Transactional
    fun createReport(user: User, entityId: Long, entityType: EntityType, reportDto: ReviewReportDto): Report {
        val report = Report(
            user = user,
            entityId = entityId,
            entityType = entityType,
            description = reportDto.description
        )
        return reportRepository.save(report)
    }

    fun getReportsByEntityType(entityType: EntityType): List<Report> {
        return reportRepository.findByEntityType(entityType)
    }

    @Transactional
    fun createCommentReport(user: User, entityId: Long, entityType: EntityType, reportCommentRequestDto: ReportCommentRequestDto): Report {
        val report = Report(
            user = user,
            entityId = entityId,
            entityType = entityType,
            description = reportCommentRequestDto.description
        )
        return reportRepository.save(report)
    }

}
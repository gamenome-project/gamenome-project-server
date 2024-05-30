package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.Report

data class CommentReportResponseDto (
    val userId: Long,
    val description: String,
    val entityType: String,
) {
    companion object {
        fun from(report: Report): CommentReportResponseDto {
            return CommentReportResponseDto(
                userId = report.user.id!!,
                description = report.description,
                entityType = report.entityType.toString()
            )
        }
    }
}

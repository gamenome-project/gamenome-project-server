package sparta.nbcamp.gamenomeprojectserver.domain.report.service

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1.ReportRepository

@Service
class ReportService(
    private val reportRepository: ReportRepository
) {


}
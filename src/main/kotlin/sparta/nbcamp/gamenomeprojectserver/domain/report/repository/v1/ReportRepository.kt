package sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.Report

interface ReportRepository:JpaRepository<Report, Long> {
}
package sparta.nbcamp.gamenomeprojectserver.domain.s3.service.v1

import org.springframework.web.multipart.MultipartFile

interface S3Service {
    fun uploadImage(image: MultipartFile): String
    fun getImageUrl(fileName: String): String
}
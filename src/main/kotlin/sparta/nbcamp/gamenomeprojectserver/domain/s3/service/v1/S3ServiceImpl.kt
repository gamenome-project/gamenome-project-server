package sparta.nbcamp.gamenomeprojectserver.domain.s3.service.v1

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import sparta.nbcamp.gamenomeprojectserver.domain.common.utils.S3FileManagement

@Service
class S3ServiceImpl(
    private val s3FileManagement: S3FileManagement
): S3Service {
    override fun uploadImage(image: MultipartFile): String {
        return s3FileManagement.uploadImage(image)
    }

    override fun getImageUrl(fileName: String): String {
        return s3FileManagement.getFile(fileName)
    }
}
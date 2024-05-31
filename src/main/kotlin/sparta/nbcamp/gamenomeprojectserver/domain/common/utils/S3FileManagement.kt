package sparta.nbcamp.gamenomeprojectserver.domain.common.utils

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import sparta.nbcamp.gamenomeprojectserver.exception.FileValidateException
import java.util.*

@Component
class S3FileManagement(
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String,
    private val amazonS3: AmazonS3,
) {
    companion object {
        const val TYPE_IMAGE = "image"
    }
    fun uploadImage(multipartFile: MultipartFile): String {
        val originalFilename = multipartFile.originalFilename
            ?: throw FileValidateException("잘못된 확장자입니다.")
        val fileName = "${UUID.randomUUID()}-${originalFilename}"
        val objectMetadata = setFileDateOption(
            type = TYPE_IMAGE,
            file = getFileExtension(originalFilename),
            multipartFile = multipartFile
        )
        amazonS3.putObject(bucket, fileName, multipartFile.inputStream, objectMetadata)
        return fileName
    }

    fun getFile(fileName: String): String {
        return amazonS3.getUrl(bucket,fileName).toString()
    }

    fun delete(fileName: String) {
        amazonS3.deleteObject(bucket,fileName)
    }

    private fun getFileExtension(fileName: String): String {
        val extensionIndex = fileName.lastIndexOf('.')
        return fileName.substring(extensionIndex + 1)
    }

    private fun setFileDateOption(
        type: String,
        file: String,
        multipartFile: MultipartFile
    ): ObjectMetadata {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = "/${type}/${getFileExtension(file)}"
        objectMetadata.contentLength = multipartFile.inputStream.available().toLong()
        return objectMetadata
    }
}
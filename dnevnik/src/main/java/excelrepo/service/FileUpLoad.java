package excelrepo.service;


import org.springframework.web.multipart.MultipartFile;

// init data for uploading to server
//
public class FileUpLoad {

    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}

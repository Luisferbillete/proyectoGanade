package com.gandec.ganadecs.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ServiceCloudinary {
    Cloudinary cloudinary=new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "da1hfxach",
            "api_key", "222695267151493",
            "api_secret", "NpJhEbSb9ai-YrqwfKYPBUuITPU"
    ));
    public String uploadImage(MultipartFile file) throws Exception {
        String url = "";
        String publicId = "";
        try {
            var uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            url = uploadResult.get("public_id").toString();
        } catch (Exception e) {
            throw new Exception("Error al subir la imagen");
        }
        return url;
    }
    public String deleteImage(String id) throws Exception{
        String url="";
        try{
            var uploadResult=cloudinary.uploader().destroy(id,ObjectUtils.emptyMap());
            url=uploadResult.get("url").toString();
        }catch (Exception e){
            throw new Exception("Error al eliminar la imagen");
        }
        return url;
    }
}

package rsg.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import rsg.model.File;

public class FilesUtils {

    private static final String IMAGE_URL="/images/";

    public static String getUserProfilePhotoUrl(File file){
        return ConfigUtils.get("API.baseUrl")+IMAGE_URL+file.getName()+"."+file.getExtension();
    }
}

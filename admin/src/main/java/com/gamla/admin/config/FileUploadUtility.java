package com.gamla.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * Created by boris on 6/28/17.
 */
public class FileUploadUtility {

    private static final String ABS_PATH = "C:\\Users\\Owner\\Trabajo\\Gamla\\store\\src\\main\\resources\\static\\images\\";

    private static String REAL_PATH = "";

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

    public static void uploadFile(HttpServletRequest request, MultipartFile imageprofile, Long patientId) {
        // obtener el REal path
        REAL_PATH= request.getSession().getServletContext().getRealPath("/resources/images/");
        System.out.println("real "+REAL_PATH);
        logger.info(REAL_PATH);

        //para asegurar los dierctorios existen crear directorios
        if(!new File(REAL_PATH).exists()){
            new File(REAL_PATH).mkdirs();
        }
        if(!new File(ABS_PATH).exists()){
            new File(ABS_PATH).mkdirs();
        }

        try{
            System.out.println("IMAGEN ultimo paso");
            imageprofile.transferTo(new File(REAL_PATH +patientId+".jpg"));
            imageprofile.transferTo(new File(ABS_PATH +patientId+".jpg"));
        }
        catch (IOException e) {

        }

    }


}

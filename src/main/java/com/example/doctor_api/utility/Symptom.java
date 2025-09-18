package com.example.doctor_api.utility;

import java.util.HashMap;
import java.util.Map;

public class Symptom {
    public static Map<String,String> map=new HashMap<>();
    static{
        map.put("Arthritis","Ortho");
        map.put("Backpain","Ortho");
        map.put("Tisssue injuries","Ortho");
        map.put("Dysmenorrhea","Gyne");
        map.put("Skin infection","Dermatology");
        map.put("Skin burn","Dermatology");
        map.put("Ear pain","Ent");

    }

}

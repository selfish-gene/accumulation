package com.selfish.gene.third.party.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
public class FormValidator {

    /**
     * get the log object
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String REQUIRED = "required";

    private static final String NUMBER = "number";

    private static final String EMAIL = "email";

    private static final String MIN = "min";

    private static final String MAX = "max";

    private static final String MINLENGTH = "minlength";

    private static final String MAXLENGTH = "maxlength";

    private static final String REGEX = "regex";

    /**
     * 获取校验配置
     * @param fileName json文件名
     * @return 存储json配置的Map
     */
    public Map<String, Object> getFormValidator(String fileName){
        String path = FormValidator.class.getResource("/").getPath() + "/validator/" + fileName + ".json";
        File file = new File(path);
        if(file == null){
            LOGGER.error("can not find file :" + fileName);
            return null;
        }

        // 读取配置内容
        String content;
        StringBuilder sb = new StringBuilder();
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            // 按行读取配置文件
            LineIterator lineIterator = new LineIterator(br);
            while(lineIterator.hasNext()){
                content = lineIterator.next();
                if(content == null){
                    break;
                }
                sb.append(content.trim());
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Read file error :" + e.getMessage());
            return null;
        } catch (IOException e) {
            return null;
        }

        // 将json文件的配置解析为Map<String, Object>
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        return jsonObject;
    }

    // 验证表单
    public boolean validateForm(Map<String, Object> form, String fileName){
        // 如果表单为空无需校验
        if(form == null){
            LOGGER.info("form is null");
            return true;
        }
        // 获取校验配置
        Map<String, Object> validatorMap = getFormValidator(fileName);
        // 遍历获取form的key与value
        Iterator<Map.Entry<String, Object>> iterator = form.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            // 获取对应key的校验配置
            List<Map<String, Object>> validatorList = (List<Map<String, Object>>) validatorMap.get(key);
            // 开始校验
            if(validateParam(value, validatorList)){
                LOGGER.error("validate failed");
                return false;
            } else {
                LOGGER.error("validate success");
            }
        }
        return true;
    }

    // 验证单个参数
    private boolean validateParam(Object value, List<Map<String, Object>> validatorList) {
        Map<String, Object> validator;
        String type;
        for (Map<String, Object> map: validatorList) {
            validator = map;
            type = (String) validator.get("type");

            // 如果值为空，先判断required，如果required为true，则返回false，如果值为false，则返回true.
            if(value == null){
                if(type.equals(REQUIRED)){
                    boolean result = (boolean) validator.get("type");
                    return result ? false : true;
                } else {
                    continue;
                }
            }

            // minlength
            if(type.equals(MINLENGTH)){
                Integer minlength = Integer.parseInt(String.valueOf(validator.get("value")));
                if(value.toString().length() < minlength){
                    return false;
                }
            }

            // maxlength
            if(type.equals(MAXLENGTH)){
                Integer maxlength = Integer.parseInt(String.valueOf(validator.get("value")));
                if(value.toString().length() > maxlength){
                    return false;
                }
            }

            // min
            if(type.equals(MIN)){
                BigDecimal min = new BigDecimal(String.valueOf(validator.get("value")));
                BigDecimal val = new BigDecimal(String.valueOf(value));
                if(val.compareTo(min) < 0){
                    return false;
                }
            }

            // max
            if(type.equals(MAX)){
                Float max = new Float(String.valueOf(validator.get("value")));
                Float val = new Float(String.valueOf(value));
                if(val.compareTo(max) > 0){
                    return false;
                }
            }

            // regex
            if(type.equals(REGEX)){
                String regex = (String) validator.get("value");
                if(!value.toString().matches(regex)){
                    return false;
                }
            }
        }
        return true;
    }


}

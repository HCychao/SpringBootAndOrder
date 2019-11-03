package com.hhh.wechat_order.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把date类型的时间戳转换为long类型，因为date返回的时间戳比正常的要大1000倍
 * @author HHH
 * @version 1.0 2019/10/17
 */
public class Date2StringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Date sqlDate = new java.sql.Date(value.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(sqlDate);
        gen.writeString(dateString);
    }
}

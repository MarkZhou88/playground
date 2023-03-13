package org.play.playground.services;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SimpleRetryService {
    // spring retry use case
    // 添加重试注解,当有异常时触发重试机制.设置重试5次,默认是3.延时2000ms再次执行,每次延时提高1.5倍.当返回结果不符合要求时,主动报错触发重试
    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String download() throws Exception {
        String result = "";
        // 模拟测试
        if(StrUtil.isBlank(result)){
            log.info("重试查询下载链接: {}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            throw new Exception("正在导出文件,请稍后");
        }else {
            return result;
        }
    }
    // 定义回调,注意异常类型和方法返回值类型要与重试方法一致
    @Recover
    public String recover(Exception e) {
        log.error("获取下载链接失败! {}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return e.getMessage();
    }
}

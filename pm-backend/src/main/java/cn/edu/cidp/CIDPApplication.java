package cn.edu.cidp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 */
@MapperScan("cn.edu.cidp.mapper")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CIDPApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CIDPApplication.class, args);
        System.out.println("启动成功................... \n");
    }
}

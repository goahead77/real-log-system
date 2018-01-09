package cn.wenqi.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Setter
@Getter
public class LogInfo implements Serializable{

    private String content;
}

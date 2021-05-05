package cn.ywrby.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Tag {
    private int id;         //标签ID
    private String tagName; //标签名称
}

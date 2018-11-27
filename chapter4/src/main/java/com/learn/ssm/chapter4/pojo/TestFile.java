package com.learn.ssm.chapter4.pojo;

import org.apache.ibatis.type.Alias;

import java.io.InputStream;

@Alias("testFile")
public class TestFile {

    private long id;
//    private byte[] content;
    private InputStream content;

    /** setter and getter **/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }*/

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }
}

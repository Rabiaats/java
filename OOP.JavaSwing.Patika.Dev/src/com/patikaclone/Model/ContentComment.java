package com.patikaclone.Model;


public class ContentComment extends ContentOfContent{
    public ContentComment(int id, int user_id, int content_id, String contentofcontent) {
        super(id, user_id, content_id, contentofcontent, "comment");
    }
}
